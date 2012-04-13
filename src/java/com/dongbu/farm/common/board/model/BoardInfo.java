/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board.model;

import java.util.Date;

public class BoardInfo {
	private	String	bbsid;					//게시판 아이디
	private	int		seq;					//게시판번호 PK
	private	int		ref;					//부모글 번호
	private	int		step;					//부모게시글 안의 글 순서
	private	int		lev;					//답글의 깊이
	private	String	writer	;				//작성자이름
	private	String	writerid	;			//작성자아이디
	private	String	subject	;				//게시글 제목
	private	String	content	;				//게시글 본문
	private String  filegroupid;			//파일 그룹아이디
	private	String	maskname	;			//??
	private	Date	createdtime	;			//작성날짜
	private String  modifier;
	private Date	modifiedtime;			//수정날짜
	private	int		readcnt	;				//읽은 숫자
	private	String	ip;						//작성자 IP
	private	String	hassecret;				//비밀글 여부 (Y:비밀글,N:일반글)
	
	
	/**
	 * @return the filegroupid
	 */
	public String getFilegroupid() {
		return filegroupid;
	}
	/**
	 * @param filegroupid the filegroupid to set
	 */
	public void setFilegroupid(String filegroupid) {
		this.filegroupid = filegroupid;
	}
	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return the modifiedtime
	 */
	public Date getModifiedtime() {
		return modifiedtime;
	}
	/**
	 * @param modifiedtime the modifiedtime to set
	 */
	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
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
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}
	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	/**
	 * @return the writerid
	 */
	public String getWriterid() {
		return writerid;
	}
	/**
	 * @param writerid the writerid to set
	 */
	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	/**
	 * @return the maskname
	 */
	public String getMaskname() {
		return maskname;
	}
	/**
	 * @param maskname the maskname to set
	 */
	public void setMaskname(String maskname) {
		this.maskname = maskname;
	}
	/**
	 * @return the createdtime
	 */
	public Date getCreatedtime() {
		return createdtime;
	}
	/**
	 * @param createdtime the createdtime to set
	 */
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	/**
	 * @return the readcnt
	 */
	public int getReadcnt() {
		return readcnt;
	}
	/**
	 * @param readcnt the readcnt to set
	 */
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the hassecret
	 */
	public String getHassecret() {
		return hassecret;
	}
	/**
	 * @param hassecret the hassecret to set
	 */
	public void setHassecret(String hassecret) {
		this.hassecret = hassecret;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardInfo [bbsid=" + bbsid + ", content=" + content
				+ ", createdtime=" + createdtime + ", hassecret=" + hassecret
				+ ", ip=" + ip + ", lev=" + lev + ", maskname=" + maskname
				+ ", readcnt=" + readcnt + ", ref=" + ref + ", seq=" + seq
				+ ", step=" + step + ", subject=" + subject + ", writer="
				+ writer + ", writerid=" + writerid + "]";
	}
}
