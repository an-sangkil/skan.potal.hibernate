/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.Code;
import com.dongbu.farm.common.ajax.AjaxUtil;
import com.dongbu.farm.common.board.BoardException;
import com.dongbu.farm.common.board.manager.IBoardManager;
import com.dongbu.farm.common.board.manager.ICommentManager;
import com.dongbu.farm.common.board.model.BoardInfo;
import com.dongbu.farm.common.board.model.Comment;
import com.dongbu.farm.common.model.PaginatedList;
import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.common.repository.manager.IRepositoryManager;
import com.dongbu.farm.common.repository.model.UploadFile;
import com.dongbu.farm.common.utils.SessionUtils;

@Controller
public class BoardController extends RepositoryController {
	
	@Resource(name="boardManagerImpl")
	private IBoardManager boardManagerImpl;
	
	@Resource(name="repositoryManager")
	private IRepositoryManager repositoryManager;
	
	@Resource(name="commentManagerImpl")
	private ICommentManager commentManagerImpl;
	
	
	/**
	 * 게시판 리스트 정보 가져오기
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/board/getBoardList.common")
	public ModelAndView getBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//모델 뷰 생성
		ModelAndView mav = new ModelAndView();
		Map<String,String> searchMap = new HashMap<String,String>();
		
		
		//서블릿 요청값 셋팅
		String bbsid 		= ServletRequestUtils.getStringParameter(request, "bbsid");							//게시판 유형
		String pagingpage   = ServletRequestUtils.getStringParameter(request, "pagingpage" , "1");				//요청 페이지
		String pagesize     = ServletRequestUtils.getStringParameter(request, "pagesize", "10");				//한페이지당 리스트 갯수
		String pagingnumberper = ServletRequestUtils.getStringParameter(request, "pagingnumberper", "10");		//한화면에 보여질 하단의 페이징 페이지 갯수
		
		
		//검색 조건셋팅
		searchMap.put("bbsid", bbsid);
		searchMap.put("pagingpage", pagingpage);
		searchMap.put("pagesize", pagesize);
		searchMap.put("pagingnumberper", pagingnumberper);

		
		try{
			//검색
			PaginatedList boardList = this.boardManagerImpl.getBoardList(searchMap);
			
			//화면에 보낼 값 셋팅
			mav.addAllObjects(searchMap);
			mav.addObject("boardList", boardList);
			mav.setViewName("common/board/boardList");
		}catch (Exception e) {
			//throw new BoardException(bbsid + "boardInsert Error .......... ", e);
			e.printStackTrace();
		}

		return mav;
	}
	   
	/**
	 * 글쓰기 페이지로 이동 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/writeBoardMove.common")
	public ModelAndView writeBoardMove (HttpServletRequest request, HttpServletResponse response) throws Exception{
		return new ModelAndView("/common/board/boardWrite").addObject("bbsid", ServletRequestUtils.getStringParameter(request, "bbsid")); 
	}
	
	/**
	 * 글쓰기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/writeBoard.common")
	public ModelAndView writeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		
		//기본정보 binding
		BoardInfo boardInfo = new BoardInfo();
		bind(request, boardInfo);
		
		
		//삭제될 파일 리스트
		String[] deletefilelist = ServletRequestUtils.getStringParameters(request, "deletefilelist");

		
		//기타 정보 set
		boardInfo.setIp(request.getRemoteAddr());
		boardInfo.setWriter(SessionUtils.getUserName(request));
		boardInfo.setWriterid(SessionUtils.getUserID(request));
		boardInfo.setMaskname("common/board/boardWrite");
		boardInfo.setCreatedtime(new Date());
		boardInfo.setModifiedtime(new Date());
		
		try {
			
			//파일 구현 필요
			{
				boardInfo.setFilegroupid(multiFileUpload(request, null, deletefilelist , Code.HLC_FILE_PATH));
			}
			
			//게시글 등록
			String seq = this.boardManagerImpl.writeBoard(boardInfo);
			
			mav.addObject("bbsid", boardInfo.getBbsid());
			mav.addObject("seq", seq);
			mav.addObject("viewType", "detailView");
			mav.setViewName("redirect:/common/board/getBoard.common");
			
		} catch (Exception e) {
			//throw new BoardException("BoardController boatdInsert Error witeBoard() Method ..... " , e);
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 게시판 내용 상세 보기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/getBoard.common")
	public ModelAndView getBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ModelAndView mav = new ModelAndView();
		String bbsid = ServletRequestUtils.getRequiredStringParameter(request, "bbsid");
		String seq   = ServletRequestUtils.getRequiredStringParameter(request, "seq");
		String viewType = ServletRequestUtils.getRequiredStringParameter(request, "viewType");
		
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("bbsid", bbsid);
		searchMap.put("seq", seq);
		
		String viewName = "";
		if("detailView".equals(viewType)){
			viewName = "/common/board/boardView";
		}else if("modifyMode".equals(viewType)){
			viewName = "/common/board/boardModify";
		}else if("replyMode".equals(viewType)){
			viewName = "/common/board/boardModify"; //답글을 입력할 경우, 기본 수정모드 위에 입힌다.
		}
		
		
		try {
			
			//조회수 증가
			//FIXME : 조회수 증가시에 발생되는 버그, bug 있음
			//        수정할때 현재 값 redCnt값을 저장 하기 때문에 count 증가가 되지 않는다.
			//TODO : 작성자 아이디와 클릭한 사람의 아이디가 같으면 조회수 증가되지 않도록 구현 필요.
			if ("detailView".equals(viewType)) {
				this.boardManagerImpl.readCountIncrease(searchMap);
				
				
				//댓글을 불러오기위한 조건 설정
				Map<String,String> commentSearchMap = new HashMap<String,String>();
				commentSearchMap.putAll(searchMap);
				commentSearchMap.put("parent_seq", seq);
				
				
				//상세보기 인경우만 댓글을 불러온다.
				//TODO :: 페이징정보 없음, 필요 한경우 구현필요.
				List<Comment> commentList = commentManagerImpl.getCommentList(commentSearchMap);
				mav.addObject("commentList" , commentList);
			}
			
			//게시판 내용 상세 조회
			BoardInfo boardInfo  = this.boardManagerImpl.getBoard(searchMap);
					
			//첨부된 파일 그룹아이디로  파일 그룹 조회
			List<UploadFile> uploadFileList = null;
			if(viewType.equals("detailView") || viewType.equals("modifyMode")){
				uploadFileList = repositoryManager.getFileGroupList(boardInfo.getFilegroupid());
			}
			
			
			mav.addAllObjects(searchMap);
			mav.addObject("saveMode", viewType);
			mav.addObject("boardInfo", boardInfo);
			mav.addObject("uploadFileList", uploadFileList);
			
			mav.setViewName(viewName);
			
		} catch (Exception e) {
			
			//throw new BoardException("BoardColntroller detail Search Error ....... getBoard() ..... ", e);
			e.printStackTrace();
			
			
		}
		
		return mav;
	}
	
	
	/**
	 * 내용 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/updateBoard.common")
	public ModelAndView updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//모델 뷰 생성 
		ModelAndView mav = new ModelAndView();
		
		String saveMode = ServletRequestUtils.getRequiredStringParameter(request, "saveMode");
		
		BoardInfo boardInfo = new BoardInfo();
		bind(request , boardInfo);
		
		boardInfo.setIp(request.getRemoteAddr());
		boardInfo.setCreatedtime(new Date());
		boardInfo.setModifiedtime(new Date());
		
		
		
		//삭제될 파일 아이디 리스트
		String deletefileAr[] =  null; 
		String deletefile = ServletRequestUtils.getStringParameter(request, "deletefilelist" , "");
		if(!deletefile.trim().equals("")){
			deletefileAr = deletefile.split(",");
		}
		
		try {
			
			//파일 업로드 구현 필요
			{
				boardInfo.setFilegroupid(multiFileUpload(request, boardInfo.getFilegroupid(), deletefileAr , Code.HLC_FILE_PATH));
			}
			
			
			if(saveMode.equals("modifyMode")){
				
				//내용 수정
				this.boardManagerImpl.modifyBoard(boardInfo);
			}else if(saveMode.equals("replyMode")){
				
				//답글 달기,
				this.boardManagerImpl.reply(boardInfo);
			}
			
			
			//화면이동
			mav.addObject("bbsid", boardInfo.getBbsid());
			mav.addObject("seq", boardInfo.getSeq());
			mav.addObject("viewType", "detailView");
			mav.setViewName("redirect:/common/board/getBoard.common");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 게시판 내용 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/removeBoard.common")
	public ModelAndView removeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> serachMap = new HashMap<String,String>();
		serachMap.put("", "");
		
		//TODO : 게시판 삭제기능 구현 필요. 게시판 삭제시. 그에 해당하는 
		//1. 댓글, 2. 답글 , 3. 첨부파일 모두 삭제 해야함.
		//파일이 실제 쓰여진 디렉토리의 파일 또한 삭제 되며, 휴지통 폴더로 이동 해야함.
		//삭제된 파일에 대한 로그를 남겨야 하는 지에대한 의문 있음.
		
		return new ModelAndView();
	}
	
	/**
	 * <doc>Image Upload</doc>
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void imgUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HashMap<Object,Object> map = new HashMap<Object, Object>();
		try{
			String fileId = this.imageUpload(request);
			map.put("fileId" , fileId);
			AjaxUtil.successWrite(response, map);
		}catch(Exception e){
			map.put("ErrMsg", e);
			AjaxUtil.failWrite(response, map);
			e.printStackTrace();
		}
	}
	
	/**
	 * Spring3.0 Annotation Test  
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/common/board/test.common", method=RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("@RequestMapping(value='/common/board/test')");
	}
	
}
