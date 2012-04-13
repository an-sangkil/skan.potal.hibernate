/*
 * $Id: Address.java ,v 1.1 2011. 3. 31. 오전 10:26:35 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dongbu.farm.common.CommonElementAbst;

/**
 * 주소록 모델
 * @author skan
 *
 */
public class Address extends CommonElementAbst{
	

	/**
	 * 주소록 관리번호 
	 */
	private String ads_mgt_no;
	
	/**
	 * 상위그룹
	 */
	private String upper_code;
	
	/**
	 * 그룹정보
	 */
	private String group_code;
	
	/**
	 * 그룹정보 이름
	 */
	private String group_code_name;
	
	/**
	 * 이름
	 */
	private String user_name;
	
	/**
	 * 직책 이름
	 */
	private String position_name;
	
	/**
	 * 직책코드
	 */
	private String position_code;
	
	/**
	 * 회사명
	 */
	private String company_name;
	
	/**
	 * 집전화
	 */
	private String phone;
	
	
	/**
	 * 휴대전화
	 */
	private String cell_phone;
	/**
	 * 주소(우편번호)
	 */
	private String postcode;
	/**
	 * 주소(지역주소)
	 */
	private String district_address;
	/**
	 * 주소(상세주소)
	 */
	private String detail_address;
	/**
	 * 이메일 
	 */
	private String email;
	
	/**
	 * 메모
	 */
	private String memeo;
	
	/**
	 * 생일 및 기념일 정보
	 */
	private List<Anniversary> anniversaryList;
	
	
	/**
	 * 개인유알엘(홈페이지,블로그, 노트등등
	 */
	private String individual_URL; 
	/**
	 * 메모
	 */
	private String memo;
	/**
	 * 사진
	 */
	private String picture_uuid;
	
	
	
	private String anver_type_code[];
	private String anver_content[];
	private String anver_date[];
	private String anver_seq[];
	
	
	
	/**
	 * @return the group_code_name
	 */
	public String getGroup_code_name() {
		return group_code_name;
	}
	/**
	 * @param group_code_name the group_code_name to set
	 */
	public void setGroup_code_name(String group_code_name) {
		this.group_code_name = group_code_name;
	}
	/**
	 * @return the memeo
	 */
	public String getMemeo() {
		return memeo;
	}
	/**
	 * @param memeo the memeo to set
	 */
	public void setMemeo(String memeo) {
		this.memeo = memeo;
	}
	/**
	 * @return the anver_seq
	 */
	public String[] getAnver_seq() {
		return anver_seq;
	}
	/**
	 * @param anver_seq the anver_seq to set
	 */
	public void setAnver_seq(String[] anver_seq) {
		this.anver_seq = anver_seq;
	}
	/**
	 * @return the anver_type_code
	 */
	public String[] getAnver_type_code() {
		return anver_type_code;
	}
	/**
	 * @param anver_type_code the anver_type_code to set
	 */
	public void setAnver_type_code(String[] anver_type_code) {
		this.anver_type_code = anver_type_code;
	}
	/**
	 * @return the anver_content
	 */
	public String[] getAnver_content() {
		return anver_content;
	}
	/**
	 * @param anver_content the anver_content to set
	 */
	public void setAnver_content(String[] anver_content) {
		this.anver_content = anver_content;
	}
	/**
	 * @return the anver_date
	 */
	public String[] getAnver_date() {
		return anver_date;
	}
	/**
	 * @param anver_date the anver_date to set
	 */
	public void setAnver_date(String[] anver_date) {
		this.anver_date = anver_date;
	}
	
	
	
	/**
	 * @return the position_name
	 */
	public String getPosition_name() {
		return position_name;
	}
	/**
	 * @param position_name the position_name to set
	 */
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	/**
	 * @return the position_code
	 */
	public String getPosition_code() {
		return position_code;
	}
	/**
	 * @param position_code the position_code to set
	 */
	public void setPosition_code(String position_code) {
		this.position_code = position_code;
	}
	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}
	/**
	 * @param company_name the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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
	 * @return the group_code
	 */
	public String getGroup_code() {
		return group_code;
	}
	/**
	 * @param group_code the group_code to set
	 */
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the cell_phone
	 */
	public String getCell_phone() {
		return cell_phone;
	}
	/**
	 * @param cell_phone the cell_phone to set
	 */
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * @return the district_address
	 */
	public String getDistrict_address() {
		return district_address;
	}
	/**
	 * @param district_address the district_address to set
	 */
	public void setDistrict_address(String district_address) {
		this.district_address = district_address;
	}
	/**
	 * @return the detail_address
	 */
	public String getDetail_address() {
		return detail_address;
	}
	/**
	 * @param detail_address the detail_address to set
	 */
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the anniversaryList
	 */
	public List<Anniversary> getAnniversaryList() {
		return anniversaryList;
	}
	/**
	 * @param anniversaryList the anniversaryList to set
	 */
	public void setAnniversaryList(List<Anniversary> anniversaryList) {
		this.anniversaryList = anniversaryList;
	}
	/**
	 * @return the individual_URL
	 */
	public String getIndividual_URL() {
		return individual_URL;
	}
	/**
	 * @param individual_URL the individual_URL to set
	 */
	public void setIndividual_URL(String individual_URL) {
		this.individual_URL = individual_URL;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return the picture_uuid
	 */
	public String getPicture_uuid() {
		return picture_uuid;
	}
	/**
	 * @param picture_uuid the picture_uuid to set
	 */
	public void setPicture_uuid(String picture_uuid) {
		this.picture_uuid = picture_uuid;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		
	}
	
}
