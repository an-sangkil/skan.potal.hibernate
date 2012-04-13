/*
 * $Id: Code.java ,v 1.1 2011. 3. 25. 오후 4:45:42 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.model;

import com.dongbu.farm.common.CommonElementAbst;

/**
 * 코드관리 모델
 * @author skan
 *
 */
public class Code extends CommonElementAbst {
	
	private String code_mgt_no; 	 //코드 고유 관리번호
	private String upper_code; 		//부모 구릅 아이디
	private String upper_code_name; //보모 구릅 아이디 이름
	private String code_seq;		//구릅안에서의 시퀀스
	private String code;			//코드명 5자리 ex:P0001
	private String code_name;		//코드 한글명
	private String code_comment;	//코드설명
	private String cls_type;		//folder[false] 혹은 file[true] 표시
	private String use_check;		//사용여부
	
	public Code(String code, String code_name){
		super();
		this.code = code;
		this.code_name = code_name;
	}
	
	public Code() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the use_check
	 */
	public String getUse_check() {
		return use_check;
	}
	/**
	 * @param use_check the use_check to set
	 */
	public void setUse_check(String use_check) {
		this.use_check = use_check;
	}
	/**
	 * @return the upper_code_name
	 */
	public String getUpper_code_name() {
		return upper_code_name;
	}
	/**
	 * @param upper_code_name the upper_code_name to set
	 */
	public void setUpper_code_name(String upper_code_name) {
		this.upper_code_name = upper_code_name;
	}
	/**
	 * @return the cls_type
	 */
	public String getCls_type() {
		return cls_type;
	}
	/**
	 * @param cls_type the cls_type to set
	 */
	public void setCls_type(String cls_type) {
		this.cls_type = cls_type;
	}
	/**
	 * @return the code_mgt_no
	 */
	public String getCode_mgt_no() {
		return code_mgt_no;
	}
	/**
	 * @param code_mgt_no the code_mgt_no to set
	 */
	public void setCode_mgt_no(String code_mgt_no) {
		this.code_mgt_no = code_mgt_no;
	}
	/**
	 * @return the upper_code
	 */
	public String getUpper_code() {
		return upper_code;
	}
	/**
	 * @param upper_code the upper_code to set
	 */
	public void setUpper_code(String upper_code) {
		this.upper_code = upper_code;
	}
	/**
	 * @return the code_comment
	 */
	public String getCode_comment() {
		return code_comment;
	}
	/**
	 * @param code_comment the code_comment to set
	 */
	public void setCode_comment(String code_comment) {
		this.code_comment = code_comment;
	}
	/**
	 * @return the code_seq
	 */
	public String getCode_seq() {
		return code_seq;
	}
	/**
	 * @param code_seq the code_seq to set
	 */
	public void setCode_seq(String code_seq) {
		this.code_seq = code_seq;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the code_name
	 */
	public String getCode_name() {
		return code_name;
	}
	/**
	 * @param code_name the code_name to set
	 */
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	
	
}
