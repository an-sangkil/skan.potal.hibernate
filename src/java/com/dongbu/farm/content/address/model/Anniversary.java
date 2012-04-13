/*
 * $Id: Anniversary.java ,v 1.1 2011. 3. 31. 오전 10:43:52 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.model;

import com.dongbu.farm.common.CommonElementAbst;

/**
 * 기념일 관련 모델
 * @author skan
 *
 */
public class Anniversary extends CommonElementAbst {
	
	/**
	 * 주소록 관리번호 
	 */
	private String ads_mgt_no;
	
	/**
	 * 순번 
	 */
	private String anver_seq;
	
	/**
	 * 기념일 종류 코드
	 */
	private String anver_type_code;
	
	/**
	 * 기념일 종류 이름
	 */
	private String anver_type_name;
	
	/**
	 * 기념일 내용
	 */
	private String anver_content;
	
	/**
	 * 기념일 날짜
	 */
	private String anver_date;
	
	
	
	
	/**
	 * @return the anver_type_name
	 */
	public String getAnver_type_name() {
		return anver_type_name;
	}

	/**
	 * @param anver_type_name the anver_type_name to set
	 */
	public void setAnver_type_name(String anver_type_name) {
		this.anver_type_name = anver_type_name;
	}

	/**
	 * @return the anver_seq
	 */
	public String getAnver_seq() {
		return anver_seq;
	}

	/**
	 * @param anver_seq the anver_seq to set
	 */
	public void setAnver_seq(String anver_seq) {
		this.anver_seq = anver_seq;
	}

	/**
	 * @return the ads_mgt_no
	 */
	public String getAds_mgt_no() {
		return ads_mgt_no;
	}

	/**
	 * @param ads_mgt_no the ads_mgt_no to set
	 */
	public void setAds_mgt_no(String ads_mgt_no) {
		this.ads_mgt_no = ads_mgt_no;
	}

	/**
	 * @return the anver_type_code
	 */
	public String getAnver_type_code() {
		return anver_type_code;
	}

	/**
	 * @param anver_type_code the anver_type_code to set
	 */
	public void setAnver_type_code(String anver_type_code) {
		this.anver_type_code = anver_type_code;
	}

	/**
	 * @return the anver_content
	 */
	public String getAnver_content() {
		return anver_content;
	}

	/**
	 * @param anver_content the anver_content to set
	 */
	public void setAnver_content(String anver_content) {
		this.anver_content = anver_content;
	}

	/**
	 * @return the anver_date
	 */
	public String getAnver_date() {
		return anver_date;
	}

	/**
	 * @param anver_date the anver_date to set
	 */
	public void setAnver_date(String anver_date) {
		this.anver_date = anver_date;
	}
	
	
}
