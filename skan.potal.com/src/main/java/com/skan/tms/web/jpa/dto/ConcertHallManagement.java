package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 공연장 관리 모델 클래스.
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class ConcertHallManagement implements Serializable, Comparator<ConcertHallManagement> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 공연장 아이디. */
	@Id
	@Column(length=64)
	private String concertHallId;

	/** 공연장명. */
	@NotEmpty
	@Column(length=128, unique=true)
	private String concertHallName;

	/** 좌석수. */
	@Column
	@Min(value=1)@Max(value=10000)
	@NotNull
	private Integer seatCount;

	/** 공연장 전화번호.{국가번호}{지역}{앞자리}{뒷자리} */
	@Column(length=13)
	@Size(min=1, max=14)
	@Pattern(regexp="^\\d{2,3}\\d{2,3}\\d{3,4}\\d{4}$", message="전화번호를 입력해 주세요. {국가번호}{지역}{앞자리}{뒷자리}")
	private String hallPhoneNumber;

	/** 설명. */
	@Lob
	@Column
	private String explanation;

	/** 상태(활성화/비활성화). */
	@Column
	private Boolean status;
	
	/** 등록자. */
	@Column(length=32,updatable=false)
	private String creator;

	/** 등록시간. */
	@Column(updatable=false)
	private Date creationTime;


	/** 기본 좌석 정보 목록. */
	@OneToMany(mappedBy="concertHallManagement" , fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private Set<BasicSeat> basicSeatSet;

	/** 좌석 생성 목록. */
	@OneToMany(mappedBy="concertHallManagement" , fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private Set<SeatCreationManagement> seatCreationManagement;

	/** 좌석 등급 배치도 관리 목록. */
	@OneToMany(mappedBy="concertHallManagement", fetch=FetchType.LAZY)
	private Set<SeatLevelChartManagement> seatLevelChartManagementSet;

	/**
	 * 생성자.
	 */
	public ConcertHallManagement() {
		this.basicSeatSet = new HashSet<BasicSeat>();
		this.seatLevelChartManagementSet = new HashSet<SeatLevelChartManagement>();
	}

	/**
	 * 공연장 아이디을 설정합니다..
	 * 
	 * @param hallId
	 *            공연장 아이디
	 */
	public void setConcertHallId(String hallId) {
		this.concertHallId = hallId;
	}

	/**
	 * 공연장 아이디을 가져옵니다..
	 * 
	 * @return 공연장 아이디
	 */
	public String getConcertHallId() {
		return this.concertHallId;
	}

	/**
	 * 공연장명을 설정합니다..
	 * 
	 * @param hallName
	 *            공연장명
	 */
	public void setConcertHallName(String hallName) {
		this.concertHallName = hallName;
	}

	/**
	 * 공연장명을 가져옵니다..
	 * 
	 * @return 공연장명
	 */
	public String getConcertHallName() {
		return this.concertHallName;
	}

	/**
	 * 좌석수을 설정합니다..
	 * 
	 * @param seatCount
	 *            좌석수
	 */
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	/**
	 * 좌석수을 가져옵니다..
	 * 
	 * @return 좌석수
	 */
	public Integer getSeatCount() {
		return this.seatCount;
	}

	/**
	 * 공연장 전화번호을 설정합니다..
	 * 
	 * @param hallPhoneNumber
	 *            공연장 전화번호
	 */
	public void setHallPhoneNumber(String hallPhoneNumber) {
		this.hallPhoneNumber = hallPhoneNumber;
	}

	/**
	 * 공연장 전화번호을 가져옵니다..
	 * 
	 * @return 공연장 전화번호
	 */
	public String getHallPhoneNumber() {
		return this.hallPhoneNumber;
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
	 * 상태(활성화/비활성화)을 설정합니다..
	 * 
	 * @param status
	 *            상태(활성화/비활성화)
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 상태(활성화/비활성화)을 가져옵니다..
	 * 
	 * @return 상태(활성화/비활성화)
	 */
	public Boolean getStatus() {
		return this.status;
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
	 * 기본 좌석 정보 목록을 설정합니다..
	 * 
	 * @param basicSeatSet
	 *            기본 좌석 정보 목록
	 */
	public void setBasicSeatSet(Set<BasicSeat> basicSeatSet) {
		this.basicSeatSet = basicSeatSet;
	}

	/**
	 * 기본 좌석 정보를 추가합니다..
	 * 
	 * @param basicSeat
	 *            기본 좌석 정보
	 */
	public void addBasicSeat(BasicSeat basicSeat) {
		this.basicSeatSet.add(basicSeat);
	}

	/**
	 * 기본 좌석 정보 목록을 가져옵니다..
	 * 
	 * @return 기본 좌석 정보 목록
	 */
	public Set<BasicSeat> getBasicSeatSet() {
		return this.basicSeatSet;
	}

	/**
	 * 좌석 등급 배치도 관리 목록을 설정합니다..
	 * 
	 * @param seatLevelChartManagementSet
	 *            좌석 등급 배치도 관리 목록
	 */
	public void setSeatLevelChartManagementSet(Set<SeatLevelChartManagement> seatLevelChartManagementSet) {
		this.seatLevelChartManagementSet = seatLevelChartManagementSet;
	}

	/**
	 * 좌석 등급 배치도 관리를 추가합니다..
	 * 
	 * @param seatLevelChartManagement
	 *            좌석 등급 배치도 관리
	 */
	public void addSeatLevelChartManagement(SeatLevelChartManagement seatLevelChartManagement) {
		this.seatLevelChartManagementSet.add(seatLevelChartManagement);
	}

	/**
	 * 좌석 등급 배치도 관리 목록을 가져옵니다..
	 * 
	 * @return 좌석 등급 배치도 관리 목록
	 */
	public Set<SeatLevelChartManagement> getSeatLevelChartManagementSet() {
		return this.seatLevelChartManagementSet;
	}
	

	public Set<SeatCreationManagement> getSeatCreationManagement() {
		
		if(seatCreationManagement != null) {
			Set<SeatCreationManagement> set = new TreeSet<>(new SeatCreationManagement());
			set.addAll(this.seatCreationManagement);
			
			return set;
		} else {
			return seatCreationManagement;
		}
		
		
	}

	public void setSeatCreationManagement(Set<SeatCreationManagement> seatCreationManagement) {
		this.seatCreationManagement = seatCreationManagement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concertHallId == null) ? 0 : concertHallId.hashCode());
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
		ConcertHallManagement other = (ConcertHallManagement) obj;
		if (concertHallId == null) {
			if (other.concertHallId != null) {
				return false;
			}
		} else if (!concertHallId.equals(other.concertHallId)) {
			return false;
		}
		return true;
	}

	@Override
	public int compare(ConcertHallManagement o1, ConcertHallManagement o2) {
//		String en1 = ((ConcertHallManagement)o1).engName;
//	    String en2 = ((ConcertHallManagement)o2).engName;
//	    return en1.compareTo(en2); // ascending 정렬
		return 0;
	}
	
	
	

}
