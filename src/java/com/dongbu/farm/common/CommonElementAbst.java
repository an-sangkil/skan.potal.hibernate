/*
 * $Id: DefaultAttribute.java ,v 1.1 2011. 3. 7. 오후 6:18:26 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common;

import java.util.Date;

/**
 * 기본 엘리멘트 모델러
 * @author skan
 *
 */
public abstract class CommonElementAbst {
	
	//default Attribute
	private String creator;		//작성자
	private Date createdtime;	//작성시간
	private String modifier;	//수정자
	private Date modifiedtime;	//수정시간
	
	
	
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
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
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
}
