package com.skan.tms.web.jpa.dto;

/**
 * 
 * Description : API 키관리 모델  
 * @author skan
 * @since  2016. 12. 1.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * API 키관리 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table
public class ApiKeyManagement implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 번호. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer no;

	/** 사이트키. */
	@Column(length=128)
	private String siteKey;

	/** 암호화키값. */
	@Column(length=1024)
	private String secureKey;

	/** 시작일. */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date startDate;

	/** 종료일. */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date endDate;

	/** 등록자. */
	@Column(length=32)
	private String creator;

	/** 등록시간. */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date creationTime;

	/**
	 * 생성자.
	 */
	public ApiKeyManagement() {
	}

	/**
	 * 번호을 설정합니다..
	 * 
	 * @param no
	 *            번호
	 */
	public void setNo(Integer no) {
		this.no = no;
	}

	/**
	 * 번호을 가져옵니다..
	 * 
	 * @return 번호
	 */
	public Integer getNo() {
		return this.no;
	}

	/**
	 * 사이트키을 설정합니다..
	 * 
	 * @param siteKey
	 *            사이트키
	 */
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	/**
	 * 사이트키을 가져옵니다..
	 * 
	 * @return 사이트키
	 */
	public String getSiteKey() {
		return this.siteKey;
	}

	/**
	 * 암호화키값을 설정합니다..
	 * 
	 * @param secureKey
	 *            암호화키값
	 */
	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

	/**
	 * 암호화키값을 가져옵니다..
	 * 
	 * @return 암호화키값
	 */
	public String getSecureKey() {
		return this.secureKey;
	}

	/**
	 * 시작일을 설정합니다..
	 * 
	 * @param startDate
	 *            시작일
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 시작일을 가져옵니다..
	 * 
	 * @return 시작일
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * 종료일을 설정합니다..
	 * 
	 * @param endDate
	 *            종료일
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 종료일을 가져옵니다..
	 * 
	 * @return 종료일
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * 등록자을 설정합니다..
	 * 
	 * @param creator
	 *            등록자
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 등록자을 가져옵니다..
	 * 
	 * @return 등록자
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 등록시간을 설정합니다..
	 * 
	 * @param creationTime
	 *            등록시간
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * 등록시간을 가져옵니다..
	 * 
	 * @return 등록시간
	 */
	public Date getCreationTime() {
		return this.creationTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ApiKeyManagement other = (ApiKeyManagement) obj;
		if (no == null) {
			if (other.no != null) {
				return false;
			}
		} else if (!no.equals(other.no)) {
			return false;
		}
		return true;
	}

}
