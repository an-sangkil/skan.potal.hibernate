/*
 * $Id: ICommentManager.java ,v 1.1 2011. 3. 22. 오후 4:15:25 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.manager;

import java.util.List;
import java.util.Map;

import com.dongbu.farm.common.board.model.Comment;

/**
 * 댓글 메니저 인터페이스
 * @author skan
 *
 */
public interface ICommentManager {
	/**
	 * 댓글 등록
	 * @param comment
	 * @throws Exception
	 */
	public abstract void writeComment(Comment comment) throws Exception;
	
	/**
	 * 댓글 삭제
	 * @param comment
	 * @throws Exception
	 */
	public abstract void deleteComment(Comment comment) throws Exception;
	
	/**
	 * 댓글 목록 가져오기
	 * @param comment
	 * @return 
	 * @throws Exception
	 */
	public abstract List<Comment> getCommentList(Map<String,String> searchMap) throws Exception;
	
	/**
	 * 댓글 수정
	 * @param comment
	 * @throws Exception
	 */
	@Deprecated
	public abstract void modifyComment(Comment comment) throws Exception;
	
	/**
	 * 댓글의 댓글
	 * @param comment
	 * @throws Exception
	 */
	public abstract void replyComment(Comment comment) throws Exception;
	
}
