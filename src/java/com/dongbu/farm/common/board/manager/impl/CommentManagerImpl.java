/*
 * $Id: CommentManagerImpl.java ,v 1.1 2011. 3. 22. 오후 4:15:47 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.common.board.dao.ICommentDao;
import com.dongbu.farm.common.board.manager.ICommentManager;
import com.dongbu.farm.common.board.model.Comment;

/**
 * 댓글 서비스 메니저 구현 클레스
 * @author skan
 *
 */
@Service
public class CommentManagerImpl implements ICommentManager {
	
	@Autowired
	private ICommentDao commentDao;
	

	@Override
	public void writeComment(Comment comment) throws Exception {
		this.commentDao.writeComment(comment);
	}


	@Override
	public void deleteComment(Comment comment) throws Exception {
		this.commentDao.deleteComment(comment);
	}


	@Override
	public List<Comment> getCommentList(Map<String,String> searchMap) throws Exception {
		return this.commentDao.getCommentList(searchMap);
		
	}


	@Override
	@Deprecated
	public void modifyComment(Comment comment) throws Exception {
		this.commentDao.modifyComment(comment);
	}


	@Override
	public void replyComment(Comment comment) throws Exception {
		this.commentDao.replyComment(comment);
	}

}
