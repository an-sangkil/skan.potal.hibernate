/*
 * $Id: asd.java ,v 1.1 2010. 9. 9. 오전 11:10:05 dongbu  Exp $
 * created by    : An Sang Kil
 * creation-date : 2010. 9. 9.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.mail.model;

import java.util.Properties;

public class MailContents {
	
	protected Properties promotionMailContents = null;
	
	protected Properties commonMailContents = null;
	
	/**
	 * XML applicationContext-mail.xml Bean 에서 Key 가 맞는 기본 값을 가져온다.
	 * @param key
	 * @return
	 */
	public String getPromotionMailContents(String key){
		String result = promotionMailContents.getProperty(key);
		return result;
	}

	/**
	 * @return the promotionMailContents
	 */
	public Properties getPromotionMailContents() {
		return promotionMailContents;
	}

	/**
	 * @param promotionMailContents the promotionMailContents to set
	 */
	public void setPromotionMailContents(Properties promotionMailContents) {
		this.promotionMailContents = promotionMailContents;
	}
	
	/**
	 * XML applicationContext-mail.xml Bean 에서 Key 가 맞는 기본 값을 가져온다.
	 * @param key
	 * @return
	 */
	public String getCommonMailContents(String key){
		String result = (String) commonMailContents.get(key);
		return result;
 	}

	/**
	 * @return the commonMailContents
	 */
	public Properties getCommonMailContents() {
		return commonMailContents;
	}

	/**
	 * @param commonMailContents the commonMailContents to set
	 */
	public void setCommonMailContents(Properties commonMailContents) {
		this.commonMailContents = commonMailContents;
	}
	
	
}
