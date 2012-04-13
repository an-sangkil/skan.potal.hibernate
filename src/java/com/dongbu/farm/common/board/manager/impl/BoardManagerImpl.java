/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.manager.impl;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.common.board.dao.IBoardDao;
import com.dongbu.farm.common.board.manager.IBoardManager;
import com.dongbu.farm.common.board.model.BoardInfo;
import com.dongbu.farm.common.model.PaginatedList;

/**
 * 게시판 메니져 클레스
 * @author ahn
 *
 */
@Service
public class BoardManagerImpl implements IBoardManager{
	
	@Autowired
	private IBoardDao boardDaoImpl;

	/**
	 * @param boardDaoImpl the boardDaoImpl to set
	 */
	
	public void setBoardDaoImpl(IBoardDao boardDaoImpl) {
		this.boardDaoImpl = boardDaoImpl;
	}
	@Override
	public PaginatedList getBoardList(Map<String, String> searchMap)
			throws Exception {
		return this.boardDaoImpl.getBoardList(searchMap);
	}
	@Override
	public String writeBoard(BoardInfo boardInfo) throws Exception {
		
		//Insert Key 값을 구한다.
		CaseInsensitiveMap cim =  this.boardDaoImpl.writeBoardKeyCreation();
		boardInfo.setSeq(Integer.parseInt((String.valueOf(cim.get("seq")))));
		boardInfo.setRef(Integer.parseInt((String.valueOf(cim.get("seq")))));
		
		//실제 데이터를 입력 한다.
		this.boardDaoImpl.writeBoard(boardInfo);
		return String.valueOf(boardInfo.getSeq());
	}
	@Override
	public BoardInfo getBoard(Map<String, String> searchMap) throws Exception {
		
		//상세검색
		BoardInfo boardInfo = this.boardDaoImpl.getBoard(searchMap);
		return boardInfo;
	}
	
	@Override
	public int modifyBoard(BoardInfo boardInfo) throws Exception {
		int succOrfail = this.boardDaoImpl.modifyBoard(boardInfo);
		return succOrfail; 
	}
	
	@Override
	public void reply(BoardInfo boardInfo) throws Exception{
		//답글
		this.boardDaoImpl.replyBoard(boardInfo);
	}
	
	@Override
	public void readCountIncrease(Map<String, String> searchMap)throws Exception {
		//조회수 증가
		this.boardDaoImpl.readCountIncrease(searchMap);
	}
}
