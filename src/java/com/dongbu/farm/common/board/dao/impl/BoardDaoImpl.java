/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.dao.impl;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.common.board.dao.IBoardDao;
import com.dongbu.farm.common.board.model.BoardInfo;
import com.dongbu.farm.common.model.PageInfo;
import com.dongbu.farm.common.model.PaginatedList;

@Repository
public class BoardDaoImpl implements IBoardDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	private static String BOARD_STATEMENTNAME = "common.board.";
	
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList getBoardList(Map<String, String> searchMap)
			throws DataAccessException {
		
		int totalNumberOfEntries = (Integer)sqlMapClientTemplate.queryForObject(BOARD_STATEMENTNAME + "getBoardTotalEntries" , searchMap);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPagingPage(Integer.parseInt(searchMap.get("pagingpage")));
		pageInfo.setPagingNumberPer(Integer.parseInt(searchMap.get("pagingnumberper")));
		pageInfo.setTotalNumberOfEntries(totalNumberOfEntries);
		
		PaginatedList pagList = new PaginatedList(pageInfo);
		
		searchMap.put("startpoint", String.valueOf(pageInfo.getStartPoint()));
		searchMap.put("endpoint"  , String.valueOf(pageInfo.getEndPoint()));
		
		pagList.setList(sqlMapClientTemplate.queryForList(BOARD_STATEMENTNAME + "getBoardList" , searchMap));
		return pagList;
	}

	@Override
	public void writeBoard(BoardInfo boardInfo) throws DataAccessException {
		sqlMapClientTemplate.insert(BOARD_STATEMENTNAME + "writeBoard", boardInfo);
	}
	
	//insert key 생성
	public CaseInsensitiveMap writeBoardKeyCreation() throws DataAccessException {
		CaseInsensitiveMap cim = (CaseInsensitiveMap) sqlMapClientTemplate.queryForObject(BOARD_STATEMENTNAME+"writeBoardKey");
		//this.writeBoard(boardInfo);
		return cim;
	}

	@Override
	public BoardInfo getBoard(Map<String, String> searchMap) {
		return (BoardInfo) sqlMapClientTemplate.queryForObject(BOARD_STATEMENTNAME + "getBoard" , searchMap);
	}

	@Override
	public int modifyBoard(BoardInfo boardInfo) throws DataAccessException {
		
		return sqlMapClientTemplate.update(BOARD_STATEMENTNAME + "modifyBoard" , boardInfo);
	}

	@Override
	public void replyBoard(BoardInfo boardInfo) throws DataAccessException {
		
		// bbsid 와 Reference key 가 같으면서 step 값이 높은 값은 현재 step 값에서 모두 +1을 해준다. 
		sqlMapClientTemplate.update(BOARD_STATEMENTNAME +"replyBoardCntUpdate", boardInfo);
		
		//현재 lev 값과 step 값을 setting
		int step = boardInfo.getStep();
		int lev  = boardInfo.getLev();
		
		CaseInsensitiveMap maxSeqMap = (CaseInsensitiveMap)sqlMapClientTemplate.queryForObject(BOARD_STATEMENTNAME + "writeBoardKey");
		
		int maxSeq = Integer.parseInt(String.valueOf(maxSeqMap.get("SEQ")));
		
		//reply 등록(현재값 +1);
		step++;
		lev++;
		
		
		boardInfo.setSeq(maxSeq);
		boardInfo.setStep(step);
		boardInfo.setLev(lev);
		sqlMapClientTemplate.insert(BOARD_STATEMENTNAME +"replyBoard", boardInfo);
	}

	@Override
	public void readCountIncrease(Map<String, String> searchMap) throws DataAccessException {
		sqlMapClientTemplate.update(BOARD_STATEMENTNAME + "readCountIncrease", searchMap);
	}
}
