package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Description : TMS 좌석 예매 정보 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class ReservationInformation implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 좌석예매 관리번호(자동증가). */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reserveSeq;

	/** 좌석 예매 내역. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reservationId",updatable=true, insertable=true, nullable=true)
	private ReservationHistory reservationHistory;

	/** 공연순서(회차). */
	@ManyToOne(fetch=FetchType.LAZY 
			//,cascade=CascadeType.DETACH
			)
	
	@JoinColumns(
			value={
					@JoinColumn(name="concertOrderId", referencedColumnName="concertOrderId" ,updatable=false, insertable=true, nullable=true)
					,@JoinColumn(name="concertDate", referencedColumnName="concertDate" ,updatable=false, insertable=true, nullable=true)
					//,@JoinColumn(name="st",updatable=false, insertable=true, nullable=true)
				}
			)
	private ConcertTimeOrder concertTimeOrder;
	
	/** 회차. */
	@Column(updatable=false)
	private Integer st;

	/** 공연관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertId",updatable=false, insertable=true, nullable=true)
	private ConcertManagement concertManagement;

	/** 티켓 종류 권종 종류. */
	@Column(length=64)
	private String denominationId;
	
	/** 예매자. */
	@Column(length=32)
	private String ticketBuyer;

	/** 좌석 상태(비어있음/예매중/예매완료/취소). */
	@Column(length=24)
	private String seatStatus;

	/** 예매시간. */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Column
	private Date reserveTime;

	/** 층수 (1층, 2층). */
	@Column(updatable=false)
	private Integer floor;

	/** 구역명. */
	@Column(length=64,updatable=false)
	private String areaName;

	/** 열명칭 . */
	@Column(length=64,updatable=false)
	private String rowName;

	/** 좌석번호. */
	@Column(updatable=false)
	private Integer seatNumber;

	/** 좌석 등급 아이디. */
	@Column(updatable=false)
	private Integer gradeId;

	/** 상태 (정상/홀딩/고장). */
	@Column(length=12,updatable=false)
	private String status;
	
	
	/**
	 * 색상
	 */
	@Transient private String gradeColor;
	
	@Transient private String gradeName;
	
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Transient private String concertOrderId;
	
	@Transient private Date concertDate;

	@Transient private String reservationId;
	

	@Transient private String concertId;
	
	

	@Transient private BigDecimal price;

	@Transient private String concertHallName;
	
	@Transient private String denominationName;
	
	@Transient private Integer discountRate;
	
	@Transient private Date concertStartTime;
	
	
	
	
	
	public Date getConcertStartTime() {
		return concertStartTime;
	}

	public void setConcertStartTime(Date concertStartTime) {
		this.concertStartTime = concertStartTime;
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

	public String getConcertHallName() {
		return concertHallName;
	}

	public void setConcertHallName(String concertHallName) {
		this.concertHallName = concertHallName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getConcertId() {
		return concertId;
	}

	public void setConcertId(String concertId) {
		this.concertId = concertId;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getGradeColor() {
		return gradeColor;
	}

	public void setGradeColor(String gradeColor) {
		this.gradeColor = gradeColor;
	}

	public String getConcertOrderId() {
		return concertOrderId;
	}

	public void setConcertOrderId(String concertOrderId) {
		this.concertOrderId = concertOrderId;
	}

	public Date getConcertDate() {
		return concertDate;
	}

	public void setConcertDate(Date concertDate) {
		this.concertDate = concertDate;
	}
	
	/** 권종 정보 목록. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="denominationId",updatable=false, insertable=false, nullable=true)
	private Denomination denomination;


	public Denomination getDenomination() {
		return denomination;
	}

	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}

	/**
	 * 생성자.
	 */
	public ReservationInformation() {
	}
	
	public ReservationInformation(
			Long reserveSeq,
			String areaName,
			String denominationId,
			Integer floor,
			Integer gradeId,
			Date reserveTime,
			String rowName,
			Integer seatNumber,
			String seatStatus,
			Integer st,
			String status,
			String ticketBuyer,
			String concertId,
			Date concertDate,		
			String concertOrderId,
			String reservationId,
			String gradeColor,
			String gradeName,
			BigDecimal price,
			String concertHallName) {
		
		
		this.reserveSeq = reserveSeq;
		this.areaName = areaName;
		this.denominationId = denominationId;
		this.floor = floor;
		this.gradeId = gradeId;
		this.reserveTime = reserveTime;
		this.rowName = rowName;
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
		this.st = st;
		this.status = status;
		this.ticketBuyer =ticketBuyer;
		this.concertId = concertId;
		this.concertOrderId = concertOrderId;
		this.concertDate = concertDate;
		this.reservationId = reservationId;
		this.gradeColor = gradeColor;
		this.gradeName = gradeName;
		this.price = price;
		this.concertHallName = concertHallName;
	}
	
	public ReservationInformation(
			Long reserveSeq,
			String areaName,
			String denominationId,
			Integer floor,
			Integer gradeId,
			Date reserveTime,
			String rowName,
			Integer seatNumber,
			String seatStatus,
			Integer st,
			String status,
			String ticketBuyer,
			String concertId,
			Date concertDate,
			Date concertStartTime,
			String concertOrderId,
			String reservationId,
			String gradeName,
			String denominationName,
			Integer discountRate,
			BigDecimal price) {
		
		
		this.reserveSeq = reserveSeq;
		this.areaName = areaName;
		this.denominationId = denominationId;
		this.floor = floor;
		this.gradeId = gradeId;
		this.reserveTime = reserveTime;
		this.rowName = rowName;
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
		this.st = st;
		this.status = status;
		this.ticketBuyer =ticketBuyer;
		this.concertId = concertId;
		this.concertOrderId = concertOrderId;
		this.concertDate = concertDate;
		this.concertStartTime = concertStartTime;
		this.reservationId = reservationId;
		this.gradeName = gradeName;
		this.denominationName = denominationName;
		this.discountRate = discountRate;
		this.price = price;
	}
	


	/**
	 * 좌석예매 관리번호(자동증가)을 설정합니다..
	 * 
	 * @param reserveSeq
	 *            좌석예매 관리번호(자동증가)
	 */
	public void setReserveSeq(Long reserveSeq) {
		this.reserveSeq = reserveSeq;
	}

	/**
	 * 좌석예매 관리번호(자동증가)을 가져옵니다..
	 * 
	 * @return 좌석예매 관리번호(자동증가)
	 */
	public Long getReserveSeq() {
		return this.reserveSeq;
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


	/**
	 * 좌석 예매 내역을 설정합니다..
	 * 
	 * @param reservationHistory
	 *            좌석 예매 내역
	 */
	public void setReservationHistory(ReservationHistory reservationHistory) {
		this.reservationHistory = reservationHistory;
	}

	/**
	 * 좌석 예매 내역을 가져옵니다..
	 * 
	 * @return 좌석 예매 내역
	 */
	public ReservationHistory getReservationHistory() {
		return this.reservationHistory;
	}

	/**
	 * 공연순서(회차)을 설정합니다..
	 * 
	 * @param concertOrder
	 *            공연순서(회차)
	 */
	public void setConcertTimeOrder(ConcertTimeOrder concertTimeOrder) {
		this.concertTimeOrder = concertTimeOrder;
	}

	/**
	 * 공연순서(회차)을 가져옵니다..
	 * 
	 * @return 공연순서(회차)
	 */
	public ConcertTimeOrder getConcertTimeOrder() {
		return this.concertTimeOrder;
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

	public String getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(String denominationId) {
		this.denominationId = denominationId;
	}

	/**
	 * 예매자을 설정합니다..
	 * 
	 * @param ticketBuyer
	 *            예매자
	 */
	public void setTicketBuyer(String ticketBuyer) {
		this.ticketBuyer = ticketBuyer;
	}

	/**
	 * 예매자을 가져옵니다..
	 * 
	 * @return 예매자
	 */
	public String getTicketBuyer() {
		return this.ticketBuyer;
	}

	/**
	 * 좌석 상태(비어있음/예매중/예매완료/취소)을 설정합니다..
	 * 
	 * @param seatStatus
	 *            좌석 상태(비어있음/예매중/예매완료/취소)
	 */
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	/**
	 * 좌석 상태(비어있음/예매중/예매완료/취소)을 가져옵니다..
	 * 
	 * @return 좌석 상태(비어있음/예매중/예매완료/취소)
	 */
	public String getSeatStatus() {
		return this.seatStatus;
	}

	/**
	 * 예매시간을 설정합니다..
	 * 
	 * @param reserveTime
	 *            예매시간
	 */
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	/**
	 * 예매시간을 가져옵니다..
	 * 
	 * @return 예매시간
	 */
	public Date getReserveTime() {
		return this.reserveTime;
	}

	/**
	 * 층수 (1층, 2층)을 설정합니다..
	 * 
	 * @param floor
	 *            층수 (1층, 2층)
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	/**
	 * 층수 (1층, 2층)을 가져옵니다..
	 * 
	 * @return 층수 (1층, 2층)
	 */
	public Integer getFloor() {
		return this.floor;
	}

	/**
	 * 구역명을 설정합니다..
	 * 
	 * @param areaName
	 *            구역명
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 구역명을 가져옵니다..
	 * 
	 * @return 구역명
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * 열명칭 을 설정합니다..
	 * 
	 * @param rowName
	 *            열명칭 
	 */
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	/**
	 * 열명칭 을 가져옵니다..
	 * 
	 * @return 열명칭 
	 */
	public String getRowName() {
		return this.rowName;
	}

	/**
	 * 좌석번호을 설정합니다..
	 * 
	 * @param seatNumber
	 *            좌석번호
	 */
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * 좌석번호을 가져옵니다..
	 * 
	 * @return 좌석번호
	 */
	public Integer getSeatNumber() {
		return this.seatNumber;
	}

	/**
	 * 좌석 등급 아이디을 설정합니다..
	 * 
	 * @param gradeId
	 *            좌석 등급 아이디
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * 좌석 등급 아이디을 가져옵니다..
	 * 
	 * @return 좌석 등급 아이디
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}

	/**
	 * 상태 (정상/홀딩/고장)을 설정합니다..
	 * 
	 * @param status
	 *            상태 (정상/홀딩/고장)
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 상태 (정상/홀딩/고장)을 가져옵니다..
	 * 
	 * @return 상태 (정상/홀딩/고장)
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reserveSeq == null) ? 0 : reserveSeq.hashCode());
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
		ReservationInformation other = (ReservationInformation) obj;
		if (reserveSeq == null) {
			if (other.reserveSeq != null) {
				return false;
			}
		} else if (!reserveSeq.equals(other.reserveSeq)) {
			return false;
		}
		return true;
	}

}
