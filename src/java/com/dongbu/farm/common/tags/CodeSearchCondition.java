/*
 * $Id: CodeSearchCondition.java ,v 1.1 2011. 3. 29. 오후 8:00:54 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 29.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.tags;

public class CodeSearchCondition {
	
	/**
	 * 그룹코드
	 */
	private String groupCode;	
	/**
	 * 다국어 KOR/CHN/ENG
	 */
	private String lang;
	

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	

}
