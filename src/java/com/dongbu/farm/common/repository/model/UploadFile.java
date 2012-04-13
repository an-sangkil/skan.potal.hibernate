/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 158800937022761150L;
	
	
	/**
	 * 파일 그룹 아이디
	 */
	private String group_id;
	/**
	 * 파일 아이디
	 */
	private String file_id;
	/**
	 * 파일의 인풋 이름 ex. <input type="file" name="file3"/> 일 경우 인풋 이름은 file3 이다.
	 */
	private String file_input_name;
	/**
	 * 실제 파일 명
	 */			   
	private String original_file_name;
	/**
	 * 서버에 저장된 파일의 경로
	 */
	private String file_path;
	/**
	 * 바로 다운로드 할 수 있는 url
	 */
	private String down_url;
	/**
	 * 파일 크기
	 */
	private long file_size;
	
	private String creater;
	private String creater_name;
	private Date createdtime;
	
	
	/**
	 * 컨텐츠 타입
	 */
	private String content_type;
	private InputStream inputStream;
	
	
	/**
	 * @return the group_id
	 */
	public String getGroup_id() {
		return group_id;
	}



	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



	/**
	 * @return the file_id
	 */
	public String getFile_id() {
		return file_id;
	}



	/**
	 * @param file_id the file_id to set
	 */
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}



	/**
	 * @return the file_input_name
	 */
	public String getFile_input_name() {
		return file_input_name;
	}



	/**
	 * @param file_input_name the file_input_name to set
	 */
	public void setFile_input_name(String file_input_name) {
		this.file_input_name = file_input_name;
	}


	/**
	 * @return the original_file_name
	 */
	public String getOriginal_file_name() {
		return original_file_name;
	}



	/**
	 * @param original_file_name the original_file_name to set
	 */
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}



	/**
	 * @return the file_path
	 */
	public String getFile_path() {
		return file_path;
	}



	/**
	 * @param file_path the file_path to set
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}



	/**
	 * @return the down_url
	 */
	public String getDown_url() {
		return down_url;
	}



	/**
	 * @param down_url the down_url to set
	 */
	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}



	/**
	 * @return the file_size
	 */
	public long getFile_size() {
		return file_size;
	}



	/**
	 * @param file_size the file_size to set
	 */
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}



	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}



	/**
	 * @param creater the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}



	/**
	 * @return the creater_name
	 */
	public String getCreater_name() {
		return creater_name;
	}



	/**
	 * @param creater_name the creater_name to set
	 */
	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
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
	 * @return the content_type
	 */
	public String getContent_type() {
		return content_type;
	}



	/**
	 * @param content_type the content_type to set
	 */
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}



	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}



	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
