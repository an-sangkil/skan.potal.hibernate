/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.dao;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.dao.DataAccessException;

import com.dongbu.farm.common.board.model.BoardInfo;
import com.dongbu.farm.common.model.PaginatedList;

/**
 * 게시판 인터페이스 다오
 * @author ahn
 *
 */
public interface IBoardDao {
	
	/**
	 * 검색, 리스트
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public PaginatedList getBoardList(Map<String, String> searchMap) throws DataAccessException;
	
	/**
	 * 글쓰기
	 * @param boardInfo
	 * @return
	 * @throws DataAccessException
	 */
	public void writeBoard(BoardInfo boardInfo) throws DataAccessException;
	
	/**
	 * 상세내용 보기
	 * @param searchMap
	 * @param DataAccessException
	 * @return
	 */
	public BoardInfo getBoard(Map<String, String> searchMap)throws DataAccessException;
	
	/**
	 * 내용수정
	 * @param boardInfo
	 * @return
	 * @throws DataAccessException
	 */
	public int modifyBoard(BoardInfo boardInfo)throws DataAccessException;
	
	/**
	 * 답글 
	 * @param boardInfo
	 */
	public void replyBoard(BoardInfo boardInfo)throws DataAccessException;
	
	
	/**
	 * Insert Key 생성 
	 * @return
	 * @throws DataAccessException
	 */
	public CaseInsensitiveMap writeBoardKeyCreation() throws DataAccessException;


	/** 
	 * 조회수 증가
	 * @param searchMap
	 * @throws DataAccessException
	 */
	public void readCountIncrease(Map<String, String> searchMap) throws DataAccessException;

}
