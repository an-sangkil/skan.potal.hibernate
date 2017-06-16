package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

 /**
  * Description : 공연순서(회차) 모델 클래스. 
  * @author skan
  * @since 2016. 10. 19.
  * @version 
  *
  * Copyright (C) 2016 by SKAN Corp. All right reserved.
  */
@Entity
@Table
public class ConcertTimeOrder implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 공연순서(회차) composite - id. */
	@EmbeddedId private ConcertTimeOrderId concertTimeOrderId;

	/** 
	 * 공연관리. FORIN KEY로 이용한다.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertId" , insertable=true, updatable=false)
	private ConcertManagement concertManagement;

	/** 공연 시간 시작 시간 (24시 hh:mm:ss). */
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column
	private Date concertStartTime;
	
	/** 공연 시간 종료 시간 (24시 hh:mm:ss). */
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column
	private Date concertEndTime;
	
	/** 회차. */
	@Column
	private Integer st;
	
	/** 팔린 좌석 수 */
	@Transient private Long buyedTicketCount;
	/** 총 좌석 수 */
	@Transient private Long totalTicketCount;
	

	/** 좌석 등급 배치 아이디. */
	@Column
	private Integer seatChartId;

	/** 좌석 예매 정보 목록. */
	@OneToMany(mappedBy="concertTimeOrder", fetch=FetchType.LAZY ,cascade=CascadeType.REMOVE)
	private Set<ReservationInformation> reservationInformationSet;

	/**
	 * 생성자.
	 */
	public ConcertTimeOrder() {
		this.reservationInformationSet = new HashSet<ReservationInformation>();
	}

	/**
	 * 공연순서(회차) composite - id을 설정합니다..
	 * 
	 * @param concertOrderId
	 *            공연순서(회차) composite - id
	 */
	public void setConcertTimeOrderId(ConcertTimeOrderId concertTimeOrderId) {
		this.concertTimeOrderId = concertTimeOrderId;
	}

	/**
	 * 공연순서(회차) composite - id을 가져옵니다..
	 * 
	 * @return 공연순서(회차) composite - id
	 */
	public ConcertTimeOrderId getConcertTimeOrderId() {
		return this.concertTimeOrderId;
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
	 * 공연 시간(24시)을 설정합니다..
	 * 
	 * @param concertTime
	 *            공연 시간(24시)
	 */
	public void setConcertStartTime(Date concertStartTime) {
		this.concertStartTime = concertStartTime;
	}

	/**
	 * 공연 시간(24시)을 가져옵니다..
	 * 
	 * @return 공연 시간(24시)
	 */
	public Date getConcertStartTime() {
		return this.concertStartTime;
	}
	
	public Date getConcertEndTime() {
		return concertEndTime;
	}

	public void setConcertEndTime(Date concertEndTime) {
		this.concertEndTime = concertEndTime;
	}

	/**
	 * 좌석 등급 배치 아이디을 설정합니다..
	 * 
	 * @param seatChartId
	 *            좌석 등급 배치 아이디
	 */
	public void setSeatChartId(Integer seatChartId) {
		this.seatChartId = seatChartId;
	}

	/**
	 * 좌석 등급 배치 아이디을 가져옵니다..
	 * 
	 * @return 좌석 등급 배치 아이디
	 */
	public Integer getSeatChartId() {
		return this.seatChartId;
	}

	/**
	 * 좌석 예매 정보 목록을 설정합니다..
	 * 
	 * @param reservationInformationSet
	 *            좌석 예매 정보 목록
	 */
	public void setReservationInformationSet(Set<ReservationInformation> reservationInformationSet) {
		this.reservationInformationSet = reservationInformationSet;
	}

	/**
	 * 좌석 예매 정보를 추가합니다..
	 * 
	 * @param reservationInformation
	 *            좌석 예매 정보
	 */
	public void addReservationInformation(ReservationInformation reservationInformation) {
		this.reservationInformationSet.add(reservationInformation);
	}

	/**
	 * 좌석 예매 정보 목록을 가져옵니다..
	 * 
	 * @return 좌석 예매 정보 목록
	 */
	public Set<ReservationInformation> getReservationInformationSet() {
		return this.reservationInformationSet;
	}
	
	/**
	 * 회차을 설정합니다..
	 * 
	 * @param st
	 *            회차
	 */
	public void setSt(Integer st) {
		this.st = st;
	}

	/**
	 * 회차을 가져옵니다..
	 * 
	 * @return 회차
	 */
	public Integer getSt() {
		return this.st;
	}
	

	public Long getBuyedTicketCount() {
		return buyedTicketCount;
	}

	public void setBuyedTicketCount(Long buyedTicketCount) {
		this.buyedTicketCount = buyedTicketCount;
	}

	public Long getTotalTicketCount() {
		return totalTicketCount;
	}

	public void setTotalTicketCount(Long totalTicketCount) {
		this.totalTicketCount = totalTicketCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concertTimeOrderId == null) ? 0 : concertTimeOrderId.hashCode());
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
		ConcertTimeOrder other = (ConcertTimeOrder) obj;
		if (concertTimeOrderId == null) {
			if (other.concertTimeOrderId != null) {
				return false;
			}
		} else if (!concertTimeOrderId.equals(other.concertTimeOrderId)) {
			return false;
		}
		return true;
	}

}
