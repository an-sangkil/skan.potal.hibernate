/*
 * $Id: CommentController.java ,v 1.1 2011. 3. 22. 오후 4:31:08 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.controller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongbu.farm.common.ajax.AjaxUtil;
import com.dongbu.farm.common.board.manager.ICommentManager;
import com.dongbu.farm.common.board.model.Comment;
import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.common.utils.SessionUtils;

/**
 * 댓글 컨트롤러 구현 클래스
 * @author skan
 *
 */
@Controller
public class CommentController extends RepositoryController{

	@Resource(name="commentManagerImpl")
	private ICommentManager commentManagerImpl;
	
	
	/**
	 * 댓글 등록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/common/comment/writeComment.common")//, method=RequestMethod.POST)
	public void writeComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Comment comment = new Comment();
		
		//기본정보 binding
		bind(request, comment);

		comment.setCreatedtime(new Date());
		comment.setCreator(SessionUtils.getUserID(request));
		
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		
		try {
			this.commentManagerImpl.writeComment(comment);
			
			ajaxMap.put("comment", comment);
			AjaxUtil.successWrite(response, ajaxMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 댓글 삭제
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/common/comment/deleteComment.common")//, method=RequestMethod.POST)
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Comment comment = new Comment();
		
		//기본정보 binding
		bind(request, comment);
		
		comment.setCreatedtime(new Date());
		comment.setCreator(SessionUtils.getUserID(request));
		
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		
		try {
			this.commentManagerImpl.deleteComment(comment);
			ajaxMap.put("comment", comment);
			AjaxUtil.successWrite(response, ajaxMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
