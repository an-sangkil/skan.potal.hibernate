package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description : 권종 관리 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="denomination")
@JsonIgnoreProperties(ignoreUnknown=true, value={"concertUsedDenominationSet"})
public class Denomination implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 권종아이디. */
	@Id
	@Column(length=64)
	private String denominationId;

	/** 권종 이름. */
	@Column(unique=true,nullable=false,length=128)
	@NotEmpty
	private String denominationName;

	/** 할인율 %. */
	@Column(columnDefinition="int default 0")
	@NotNull
	//@Size(min=0,max=100)
	@Max(value=100)
	private Integer discountRate;

	/** 최소 티켓 구매 횟수. */
	@Column(columnDefinition="int default 1")
	@NotNull
	//@Pattern(regexp="[1-500]")
	@Max(value=500)
	private Integer minTicketCount;
	
	/** 최대 티켓 구매 횟수. */
	@Column(columnDefinition="int default 1")
	@NotNull
	//@Pattern(regexp="[1-500]")
	@Max(value=500)
	private Integer maxTicketCount;
	
	/**
	 * 티켓 노출 대상
	 */
	@NotEmpty
	@Column(length=256)
	private String exposureTarget;

	/** 등록자. */
	@Column(length=32,updatable=false)
	private String creator;

	/** 등록시간. */
	@Column(updatable=false)
	private Date creationTime;
	
	/** 설명. */
	@Lob
	@Column
	private String explanation;
	
	/** 공연에서 사용중인 권종 종류 목록. */
	@OneToMany(mappedBy="denomination", fetch=FetchType.LAZY)
	private Set<ConcertUsedDenomination> concertUsedDenominationSet;

	/**
	 * 생성자.
	 */
	public Denomination() {
		this.concertUsedDenominationSet = new HashSet<ConcertUsedDenomination>();
	}
	public Denomination(String denominationId) {
		this.denominationId = denominationId;
	}
	
	public String getExposureTarget() {
		return exposureTarget;
	}

	public void setExposureTarget(String exposureTarget) {
		this.exposureTarget = exposureTarget;
	}

	/**
	 * 권종아이디을 설정합니다..
	 * 
	 * @param denomitionId
	 *            권종아이디
	 */
	public void setDenominationId(String denominationId) {
		this.denominationId = denominationId;
	}

	/**
	 * 권종아이디을 가져옵니다..
	 * 
	 * @return 권종아이디
	 */
	public String getDenominationId() {
		return this.denominationId;
	}

	/**
	 * 권종 이름을 설정합니다..
	 * 
	 * @param denominationName
	 *            권종 이름
	 */
	public void setDenominationName(String denominationName) {
		this.denominationName = denominationName;
	}

	/**
	 * 권종 이름을 가져옵니다..
	 * 
	 * @return 권종 이름
	 */
	public String getDenominationName() {
		return this.denominationName;
	}

	/**
	 * 할인율 %을 설정합니다..
	 * 
	 * @param discountRate
	 *            할인율 %
	 */
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * 할인율 %을 가져옵니다..
	 * 
	 * @return 할인율 %
	 */
	public Integer getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * 최소 최대 티켓 구매 횟수을 설정합니다..
	 * 
	 * @param minMaxTicketCount
	 *            최소 최대 티켓 구매 횟수
	 */
	public void setMinTicketCount(Integer minTicketCount) {
		this.minTicketCount = minTicketCount;
	}

	/**
	 * 최소 최대 티켓 구매 횟수을 가져옵니다..
	 * 
	 * @return 최소 최대 티켓 구매 횟수
	 */
	public Integer getMinTicketCount() {
		return this.minTicketCount;
	}

	public Integer getMaxTicketCount() {
		return maxTicketCount;
	}

	public void setMaxTicketCount(Integer maxTicketCount) {
		this.maxTicketCount = maxTicketCount;
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
	 * 설명을 설정합니다..
	 * 
	 * @param explanation
	 *            설명
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * 설명을 가져옵니다..
	 * 
	 * @return 설명
	 */
	public String getExplanation() {
		return this.explanation;
	}

	/**
	 * 공연에서 사용중인 권종 종류 목록을 설정합니다..
	 * 
	 * @param concertUsedDenominationSet
	 *            공연에서 사용중인 권종 종류 목록
	 */
	public void setConcertUsedDenominationSet(Set<ConcertUsedDenomination> concertUsedDenominationSet) {
		this.concertUsedDenominationSet = concertUsedDenominationSet;
	}

	/**
	 * 공연에서 사용중인 권종 종류를 추가합니다..
	 * 
	 * @param concertUsedDenomination
	 *            공연에서 사용중인 권종 종류
	 */
	public void addConcertUsedDenomination(ConcertUsedDenomination concertUsedDenomination) {
		this.concertUsedDenominationSet.add(concertUsedDenomination);
	}

	/**
	 * 공연에서 사용중인 권종 종류 목록을 가져옵니다..
	 * 
	 * @return 공연에서 사용중인 권종 종류 목록
	 */
	public Set<ConcertUsedDenomination> getConcertUsedDenominationSet() {
		return this.concertUsedDenominationSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denominationId == null) ? 0 : denominationId.hashCode());
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
		Denomination other = (Denomination) obj;
		if (denominationId == null) {
			if (other.denominationId != null) {
				return false;
			}
		} else if (!denominationId.equals(other.denominationId)) {
			return false;
		}
		return true;
	}

}
