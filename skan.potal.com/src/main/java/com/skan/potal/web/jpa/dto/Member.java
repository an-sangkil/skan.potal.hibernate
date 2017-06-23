package com.skan.potal.web.jpa.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : Member.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 2. 24.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 2. 24.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */

@Entity
@Table
public class Member {
	
	@Id
	private String user_id;
	private String user_name;
	private String password;			
	private String dept_code;			//부서코드
	private String dept_name;			//부서이름
	private String e_mail;				//이메일
	private String zip_code;			//우편번호
	private String address;				//주소
	private String phone_number;		//전화번호
	private String mobile_phone_number;	//휴대전화번호
	private String ssn_number;			//주민등록번호
	private String gender;				//성별(M:남자, F:여성)
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getMobile_phone_number() {
		return mobile_phone_number;
	}
	public void setMobile_phone_number(String mobile_phone_number) {
		this.mobile_phone_number = mobile_phone_number;
	}
	public String getSsn_number() {
		return ssn_number;
	}
	public void setSsn_number(String ssn_number) {
		this.ssn_number = ssn_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
