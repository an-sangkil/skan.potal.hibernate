/*
 * $Id: Comment.java ,v 1.1 2011. 3. 22. 오후 1:53:21 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 22.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.model;

import com.dongbu.farm.common.CommonElementAbst;

/**
 * 댓글 모델러
 * @author skan
 *
 */
public class Comment extends CommonElementAbst {
	
	
	private String bbsid;
	private int 	parent_seq;
	private	int		seq;	//댓글 번호 PK
	private	int		ref;	//부모 글 번호
	
	/**
	 * 댓글의 댓글이 달린경우 순서를 정하기위한 엘리멘트 ++ 증감
	 */
	private	int		step;	//부모게시글 안의 글 순서
	
	/**
	 * 댓글의 깊이는 게시판과 달리 0/1로 만 구분됨.
	 */
	private int 	lev;    //댓글의 깊이
	private String content; //내용
	
	
	/**
	 * @return the lev
	 */
	public int getLev() {
		return lev;
	}

	/**
	 * @param lev the lev to set
	 */
	public void setLev(int lev) {
		this.lev = lev;
	}

	/**
	 * @return the bbsid
	 */
	public String getBbsid() {
		return bbsid;
	}

	/**
	 * @param bbsid the bbsid to set
	 */
	public void setBbsid(String bbsid) {
		this.bbsid = bbsid;
	}

	/**
	 * @return the parent_seq
	 */
	public int getParent_seq() {
		return parent_seq;
	}

	/**
	 * @param parent_seq the parent_seq to set
	 */
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return the ref
	 */
	public int getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(int ref) {
		this.ref = ref;
	}

	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
