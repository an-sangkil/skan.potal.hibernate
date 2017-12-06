package com.skan.potal.web.jpa.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class Name  : Schdule.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 8. 7.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 8. 7.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Entity
@Table
public class Schedule {
	
	@NotNull
	private String schMgtNo;
	@NotNull
	@Id
	private long schSeq;
	@NotNull
	private String schSubject; 
	private String schContent; 
	private String stdDate; 
	private String todayWeatherCode; 
	private String minDegreespointCode; //최소 온도 영상 영하 선택 
	private String maxDegreespointCode; //최대 온도 영상 영하 선택
	private long minTemperature; 
	private long maxTemperature; 
	private Date createTime; 
	private Date modifyTime; 
	private String publicYN; 
	private String userId; 
	private long groupNo;
	/**
	 * @return the schMgtNo
	 */
	public String getSchMgtNo() {
		return schMgtNo;
	}
	/**
	 * @param schMgtNo the schMgtNo to set
	 */
	public void setSchMgtNo(String schMgtNo) {
		this.schMgtNo = schMgtNo;
	}
	/**
	 * @return the schSeq
	 */
	public long getSchSeq() {
		return schSeq;
	}
	/**
	 * @param schSeq the schSeq to set
	 */
	public void setSchSeq(long schSeq) {
		this.schSeq = schSeq;
	}
	/**
	 * @return the schSubject
	 */
	public String getSchSubject() {
		return schSubject;
	}
	/**
	 * @param schSubject the schSubject to set
	 */
	public void setSchSubject(String schSubject) {
		this.schSubject = schSubject;
	}
	/**
	 * @return the schContent
	 */
	public String getSchContent() {
		return schContent;
	}
	/**
	 * @param schContent the schContent to set
	 */
	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}
	/**
	 * @return the stdDate
	 */
	public String getStdDate() {
		return stdDate;
	}
	/**
	 * @param stdDate the stdDate to set
	 */
	public void setStdDate(String stdDate) {
		this.stdDate = stdDate;
	}
	/**
	 * @return the todayWeatherCode
	 */
	public String getTodayWeatherCode() {
		return todayWeatherCode;
	}
	/**
	 * @param todayWeatherCode the todayWeatherCode to set
	 */
	public void setTodayWeatherCode(String todayWeatherCode) {
		this.todayWeatherCode = todayWeatherCode;
	}
	/**
	 * @return the minDegreespointCode
	 */
	public String getMinDegreespointCode() {
		return minDegreespointCode;
	}
	/**
	 * @param minDegreespointCode the minDegreespointCode to set
	 */
	public void setMinDegreespointCode(String minDegreespointCode) {
		this.minDegreespointCode = minDegreespointCode;
	}
	/**
	 * @return the maxDegreespointCode
	 */
	public String getMaxDegreespointCode() {
		return maxDegreespointCode;
	}
	/**
	 * @param maxDegreespointCode the maxDegreespointCode to set
	 */
	public void setMaxDegreespointCode(String maxDegreespointCode) {
		this.maxDegreespointCode = maxDegreespointCode;
	}
	/**
	 * @return the minTemperature
	 */
	public long getMinTemperature() {
		return minTemperature;
	}
	/**
	 * @param minTemperature the minTemperature to set
	 */
	public void setMinTemperature(long minTemperature) {
		this.minTemperature = minTemperature;
	}
	/**
	 * @return the maxTemperature
	 */
	public long getMaxTemperature() {
		return maxTemperature;
	}
	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(long maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * @return the publicYN
	 */
	public String getPublicYN() {
		return publicYN;
	}
	/**
	 * @param publicYN the publicYN to set
	 */
	public void setPublicYN(String publicYN) {
		this.publicYN = publicYN;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the groupNo
	 */
	public long getGroupNo() {
		return groupNo;
	}
	/**
	 * @param groupNo the groupNo to set
	 */
	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}
	
}
