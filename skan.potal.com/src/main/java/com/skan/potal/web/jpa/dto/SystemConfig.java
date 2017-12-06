package com.skan.potal.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Description : 시스템 환경설정 모델 클래스. 
 * @author skan
 * @since  2016. 12. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class SystemConfig implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 설정 아이디. */
	@Id
	@Column(length=64 ,updatable=false)
	private String configId;

	/** 설정 이름. */
	@Column(length=128 ,updatable=false)
	private String configName;
	
	/** 설정값. */
	@Column(length=512)
	private String configValue;
	

	/** 등록자. */
	@Column(length=32 ,updatable=false)
	private String creator;

	/** 등록시간. */
	@Column(updatable=false)
	private Date creationTime;

	/** 수정자. */
	@Column(length=32, updatable=false)
	private String modifier;

	/** 수정시간. */
	@Column(updatable=false)
	private Date modifiedTime;

	/**
	 * 생성자.
	 */
	public SystemConfig() {
	}
	
	
	public String getConfigName() {
		return configName;
	}


	public void setConfigName(String configName) {
		this.configName = configName;
	}


	/**
	 * 설정 아이디을 설정합니다..
	 * 
	 * @param configId
	 *            설정 아이디
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * 설정 아이디을 가져옵니다..
	 * 
	 * @return 설정 아이디
	 */
	public String getConfigId() {
		return this.configId;
	}

	/**
	 * 설정값을 설정합니다..
	 * 
	 * @param configValue
	 *            설정값
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	/**
	 * 설정값을 가져옵니다..
	 * 
	 * @return 설정값
	 */
	public String getConfigValue() {
		return this.configValue;
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
	 * 수정자을 설정합니다..
	 * 
	 * @param modifier
	 *            수정자
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * 수정자을 가져옵니다..
	 * 
	 * @return 수정자
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * 수정시간을 설정합니다..
	 * 
	 * @param modifiedTime
	 *            수정시간
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * 수정시간을 가져옵니다..
	 * 
	 * @return 수정시간
	 */
	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
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
		SystemConfig other = (SystemConfig) obj;
		if (configId == null) {
			if (other.configId != null) {
				return false;
			}
		} else if (!configId.equals(other.configId)) {
			return false;
		}
		return true;
	}

}

