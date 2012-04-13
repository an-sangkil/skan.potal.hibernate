/*
 * $Id: ICommentDao.java ,v 1.1 2011. 3. 22. 오후 4:18:02 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.common.board.model.Comment;

/**
 * 댓글  다오 리파지토리 인터페이스
 * @author skan
 *
 */
public interface ICommentDao {
	
	/**
	 * 댓글 등록
	 * @param comment
	 * @throws DataAccessException
	 */
	public abstract void writeComment(Comment comment) throws DataAccessException;
	
	/**
	 * 댓글 삭제
	 * @param comment
	 * @throws DataAccessException
	 */
	public abstract void deleteComment(Comment comment) throws DataAccessException;
	
	/**
	 * 댓글 목록 가져오기
	 * @param comment
	 * @throws DataAccessException
	 */
	public abstract List<Comment> getCommentList(Map<String,String> searchMap) throws DataAccessException;
	
	/**
	 * 댓글 수정
	 * @param comment
	 * @throws DataAccessException
	 */
	@Deprecated
	public abstract void modifyComment(Comment comment) throws DataAccessException ;
	
	/**
	 * 댓글의 댓글
	 * @param comment
	 */
	public abstract void replyComment(Comment comment) throws DataAccessException ;


}
