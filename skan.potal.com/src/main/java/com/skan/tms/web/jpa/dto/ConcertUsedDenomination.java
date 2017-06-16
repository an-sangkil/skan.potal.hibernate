package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Description : 공연에서 사용중인 권종 종류 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class ConcertUsedDenomination implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 사용중인 권종 아이디. */
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer usedDenominationId;

	/** 공연관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertId" , nullable=true, insertable=true, updatable=true)
	private ConcertManagement concertManagement;

	/** 권종 관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="denominationId" , nullable=true, insertable=true, updatable=true)
	private Denomination denomination;
	
	
	
	@Transient private String denominationName;
	

	@Transient private String exposureTarget;
	
	public String getExposureTarget() {
		return exposureTarget;
	}

	public void setExposureTarget(String exposureTarget) {
		this.exposureTarget = exposureTarget;
	}

	@Transient private Integer discountRate;
	

	@Transient private String denominationId;
	
	
	/**
	 * 생성자.
	 */

	public String getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(String denominationId) {
		this.denominationId = denominationId;
	}

	public ConcertUsedDenomination() {
	}
	
	public String getDenominationName() {
		return denominationName;
	}

	public void setDenominationName(String denominationName) {
		this.denominationName = denominationName;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public ConcertUsedDenomination(
			String denominationId,
			Integer usedDenominationId,
			String denominationName,
			String exposureTarget,
			Integer discountRate
			) {
		this.denominationId = denominationId;
		this.usedDenominationId = usedDenominationId;
		this.denominationName = denominationName;
		this.exposureTarget = exposureTarget;
		this.discountRate = discountRate;
	}

	/**
	 * 사용중인 권종 아이디을 설정합니다..
	 * 
	 * @param usedDenominationId
	 *            사용중인 권종 아이디
	 */
	public void setUsedDenominationId(Integer usedDenominationId) {
		this.usedDenominationId = usedDenominationId;
	}

	/**
	 * 사용중인 권종 아이디을 가져옵니다..
	 * 
	 * @return 사용중인 권종 아이디
	 */
	public Integer getUsedDenominationId() {
		return this.usedDenominationId;
	}

	/**
	 * 공연관리을 설정합니다..
	 * 
	 * @param concertManagement
	 *            공연관리
	 */
	public void setConcertManagement(ConcertManagement concertManagement) {
		this.concertManagement = concertManagement;
	}

	/**
	 * 공연관리을 가져옵니다..
	 * 
	 * @return 공연관리
	 */
	public ConcertManagement getConcertManagement() {
		return this.concertManagement;
	}

	/**
	 * 권종 관리을 설정합니다..
	 * 
	 * @param denomination
	 *            권종 관리
	 */
	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}

	/**
	 * 권종 관리을 가져옵니다..
	 * 
	 * @return 권종 관리
	 */
	public Denomination getDenomination() {
		return this.denomination;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usedDenominationId == null) ? 0 : usedDenominationId.hashCode());
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
		ConcertUsedDenomination other = (ConcertUsedDenomination) obj;
		if (usedDenominationId == null) {
			if (other.usedDenominationId != null) {
				return false;
			}
		} else if (!usedDenominationId.equals(other.usedDenominationId)) {
			return false;
		}
		return true;
	}

}
