/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.manager;

import java.util.Map;

import com.dongbu.farm.common.board.model.BoardInfo;
import com.dongbu.farm.common.model.PaginatedList;

/**
 * 게시판 메니저 인터페이스
 * @author ahn
 *
 */
public interface IBoardManager {
	
	/**
	 * 검색, 리스트
	 * @param searchMap
	 * @return
	 * @throws Exception
	 */
	public PaginatedList getBoardList(Map<String, String> searchMap) throws Exception ;
	
	/**
	 * 글쓰기
	 * @param boardInfo
	 * @return 
	 * @throws Exception
	 */
	public String writeBoard(BoardInfo boardInfo) throws Exception;
	
	/**
	 * 상세내용보기
	 * @param searchMap
	 * @return
	 */
	public BoardInfo getBoard(Map<String, String> searchMap) throws Exception;
	
	/**
	 * 내용 수정
	 * @param boardInfo
	 * @throws Exception
	 */
	public int modifyBoard(BoardInfo boardInfo) throws Exception;
	
	/**
	 * 답글
	 * @param boardInfo
	 * @throws Exception
	 */
	public void reply(BoardInfo boardInfo)throws Exception;

	/**
	 * 조회수 증가
	 * @param searchMap
	 * @throws Exception
	 */
	public void readCountIncrease(Map<String, String> searchMap) throws Exception;

}
