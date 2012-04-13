/*
 * $Id: MemberInfo.java ,v 1.1 2010. 12. 1. 오후 3:48:53 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2010. 12. 1.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login.model;

public class Member{
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

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param userId the user_id to set
	 */
	public void setUser_id(String userId) {
		user_id = userId;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param userName the user_name to set
	 */
	public void setUser_name(String userName) {
		user_name = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the dept_code
	 */
	public String getDept_code() {
		return dept_code;
	}
	/**
	 * @param deptCode the dept_code to set
	 */
	public void setDept_code(String deptCode) {
		dept_code = deptCode;
	}
	/**
	 * @return the dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}
	/**
	 * @param deptName the dept_name to set
	 */
	public void setDept_name(String deptName) {
		dept_name = deptName;
	}
	/**
	 * @return the e_mail
	 */
	public String getE_mail() {
		return e_mail;
	}
	/**
	 * @param eMail the e_mail to set
	 */
	public void setE_mail(String eMail) {
		e_mail = eMail;
	}
	/**
	 * @return the zip_code
	 */
	public String getZip_code() {
		return zip_code;
	}
	/**
	 * @param zipCode the zip_code to set
	 */
	public void setZip_code(String zipCode) {
		zip_code = zipCode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}
	/**
	 * @param phoneNumber the phone_number to set
	 */
	public void setPhone_number(String phoneNumber) {
		phone_number = phoneNumber;
	}
	/**
	 * @return the mobile_phone_number
	 */
	public String getMobile_phone_number() {
		return mobile_phone_number;
	}
	/**
	 * @param mobilePhoneNumber the mobile_phone_number to set
	 */
	public void setMobile_phone_number(String mobilePhoneNumber) {
		mobile_phone_number = mobilePhoneNumber;
	}
	/**
	 * @return the ssn_number
	 */
	public String getSsn_number() {
		return ssn_number;
	}
	/**
	 * @param ssnNumber the ssn_number to set
	 */
	public void setSsn_number(String ssnNumber) {
		ssn_number = ssnNumber;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberModel [address=" + address + ", dept_code=" + dept_code
				+ ", dept_name=" + dept_name + ", e_mail=" + e_mail
				+ ", gender=" + gender + ", mobile_phone_number="
				+ mobile_phone_number + ", password=" + password
				+ ", phone_number=" + phone_number + ", ssn_number="
				+ ssn_number + ", user_id=" + user_id + ", user_name="
				+ user_name + ", zip_code=" + zip_code + "]";
	}
}
