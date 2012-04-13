/*
 * $Id: CommentDaoImpl.java ,v 1.1 2011. 3. 22. 오후 4:18:16 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.common.board.dao.ICommentDao;
import com.dongbu.farm.common.board.model.Comment;

/**
 * 댓글 다오 리파지토리 구현 클레스
 * @author skan
 *
 */
@Repository
public class CommentDaoImpl implements ICommentDao{
	
	private static String COMMENT_STATEMENTNAME = "common.comment.";
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	

	@Override
	public void writeComment(Comment comment) throws DataAccessException {
		
		//댓글이 등록된 번호의 최대값(+1)을 구한다.
		CaseInsensitiveMap maxSeqMap = (CaseInsensitiveMap)sqlMapClientTemplate.queryForObject(COMMENT_STATEMENTNAME + "writeCommentKey" , comment);
		int seq = Integer.parseInt(String.valueOf(maxSeqMap.get("SEQ")));
		
		comment.setSeq(seq);
		comment.setRef(seq);
		sqlMapClientTemplate.insert(COMMENT_STATEMENTNAME + "writeComment", comment);
	}


	@Override
	public void deleteComment(Comment comment) throws DataAccessException {
		sqlMapClientTemplate.delete(COMMENT_STATEMENTNAME + "deleteComment", comment);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentList(Map<String,String> searchMap) throws DataAccessException {
		return sqlMapClientTemplate.queryForList(COMMENT_STATEMENTNAME + "getCommentList" , searchMap);
	}


	@Override
	@Deprecated
	public void modifyComment(Comment comment)  throws DataAccessException  {
		// TODO Auto-generated method stub
	}


	@Override
	public void replyComment(Comment comment) throws DataAccessException {
		// TODO 댓글의 댓글 구현 필요.
		
//		댓글에 댓글을 달경우 필요함. 현 시스템에서는 사용 안함.  
//		구현하고자 할경우 메소드를 새로 생성하여 잘라내기 후 붙여넣기 하여 사용할것
//		int step = comment.getStep();
//		step++;
//		int lev = ??;  comment Model 설명 참고
		
	}

}
