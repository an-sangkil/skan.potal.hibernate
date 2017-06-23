package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Description : TMS 좌석 예매 내역 로그 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
public class ReservationHistoryLog implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 히스토리 넘버 자동 증가. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reservationHistoryLogSeq;

	/** 예매 아이디. */
	private String reservationId;

	/** 결재 트랜젝션 아이디. */
	private String tid;
	
	/** 상품 거래번호 */
	@Column(length=64)
	private String orderIdx;

	/** 공연 아이디. */
	private String concertId;
	
	/** 공연 아이디. */
	private String concertName;

	/** 예매자. */
	private String ticketBuyer;

	/** 예매자 이름. */
	private String ticketBuyerName;

	/** 공연 시간(순서) 관리번호. */
	private String concertOrderId;

	/** 공연날짜. */
	private Date concertDate;

	/** 회차. */
	private Integer st;

	/** 사용포인트. */
	private Long usedPoint;

	/** 할인가격. */
	private BigDecimal discountPrice;

	/** 일반가격. */
	private BigDecimal normalPrice;

	/** 결재금액 (일반가격-할인가격-사용포인트). */
	private BigDecimal totalPrice;
	
	/** 수수료 */
	private BigDecimal commissionPrice;

	/** 예매상태(예매완료, 예매 취소, 시간초과 등). */
	private String reservationState;

	/** 예약타입(사용자예약/관리자예약/예매취소). */
	private String reservationType;
	
	/** 온라인샵 오프라인샵*/
	@Column(length = 16)
	private String shopType;

	/** 결재종류. */
	private String paymentType;

	/** 등록시간. */
	private Date creationTime;
	
	/**성공/실패 에 따른 메시지 */
	private String message;
	
	/**성공/실패 타입*/
	private String logType;
	
	/** 취소사유 */
	@Column(updatable=true, length=64)
	private String cancelReason;

	/** 취소 좌석 정보 */
	@Column(updatable=true, columnDefinition="MEDIUMTEXT")
	private String seatInfo;
	
	/** 휴대전화번호. */
	@Column(length = 11)
	private String cellPhoneNumber;

	/** 주소. */
	@Column(length = 521)
	private String address;

	/** 우편번호. */
	@Column(length = 5)
	private String zipcode;
	
	/** 등록자. */
	@Column(length = 32, updatable = false)
	private String creator;
	
	/** 공연 시간 시작 시간 (24시 hh:mm:ss). */
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column
	private Date concertStartTime;
	
	/** 가상계좌. */
	@Column(updatable=false,length=32)
	private String virtualAccount;

	/** 은행명. */
	private String bankName;

	/** 입금자명. */
	private String remitter ;

	/** 실제 입금 금액. */
	private BigDecimal ipgmMnyx ;

	/** 가상계좌입금처리결과 code. */
	private String opCd;

	/** 입금통보건에대한 고유값. */
	private String noticeId;

	/** 현금영수증승인번호. */
	private String cashApprovalNo ;
	
	
	public String getVirtualAccount() {
		return virtualAccount;
	}

	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRemitter() {
		return remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	public BigDecimal getIpgmMnyx() {
		return ipgmMnyx;
	}

	public void setIpgmMnyx(BigDecimal ipgmMnyx) {
		this.ipgmMnyx = ipgmMnyx;
	}

	public String getOpCd() {
		return opCd;
	}

	public void setOpCd(String opCd) {
		this.opCd = opCd;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getCashApprovalNo() {
		return cashApprovalNo;
	}

	public void setCashApprovalNo(String cashApprovalNo) {
		this.cashApprovalNo = cashApprovalNo;
	}

	public Date getConcertStartTime() {
		return concertStartTime;
	}

	public void setConcertStartTime(Date concertStartTime) {
		this.concertStartTime = concertStartTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getSeatInfo() {
		return seatInfo;
	}

	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getConcertName() {
		return concertName;
	}

	public void setConcertName(String concertName) {
		this.concertName = concertName;
	}

	/**
	 * 생성자.
	 */
	public ReservationHistoryLog() {
	}

	public String getOrderIdx() {
		return orderIdx;
	}

	public void setOrderIdx(String orderIdx) {
		this.orderIdx = orderIdx;
	}

	/**
	 * 히스토리 넘버 자동 증가을 설정합니다..
	 * 
	 * @param reservationHistoryLogSeq
	 *            히스토리 넘버 자동 증가
	 */
	public void setReservationHistoryLogSeq(Long reservationHistoryLogSeq) {
		this.reservationHistoryLogSeq = reservationHistoryLogSeq;
	}

	/**
	 * 히스토리 넘버 자동 증가을 가져옵니다..
	 * 
	 * @return 히스토리 넘버 자동 증가
	 */
	public Long getReservationHistoryLogSeq() {
		return this.reservationHistoryLogSeq;
	}

	/**
	 * 예매 아이디을 설정합니다..
	 * 
	 * @param reservationId
	 *            예매 아이디
	 */
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * 예매 아이디을 가져옵니다..
	 * 
	 * @return 예매 아이디
	 */
	public String getReservationId() {
		return this.reservationId;
	}

	/**
	 * 결재 트랜젝션 아이디을 설정합니다..
	 * 
	 * @param tid
	 *            결재 트랜젝션 아이디
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * 결재 트랜젝션 아이디을 가져옵니다..
	 * 
	 * @return 결재 트랜젝션 아이디
	 */
	public String getTid() {
		return this.tid;
	}

	/**
	 * 공연 아이디을 설정합니다..
	 * 
	 * @param concertId
	 *            공연 아이디
	 */
	public void setConcertId(String concertId) {
		this.concertId = concertId;
	}

	/**
	 * 공연 아이디을 가져옵니다..
	 * 
	 * @return 공연 아이디
	 */
	public String getConcertId() {
		return this.concertId;
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
	 * 예매자 이름을 설정합니다..
	 * 
	 * @param ticketBuyerName
	 *            예매자 이름
	 */
	public void setTicketBuyerName(String ticketBuyerName) {
		this.ticketBuyerName = ticketBuyerName;
	}

	/**
	 * 예매자 이름을 가져옵니다..
	 * 
	 * @return 예매자 이름
	 */
	public String getTicketBuyerName() {
		return this.ticketBuyerName;
	}

	/**
	 * 공연 시간(순서) 관리번호을 설정합니다..
	 * 
	 * @param concertOrderId
	 *            공연 시간(순서) 관리번호
	 */
	public void setConcertOrderId(String concertOrderId) {
		this.concertOrderId = concertOrderId;
	}

	/**
	 * 공연 시간(순서) 관리번호을 가져옵니다..
	 * 
	 * @return 공연 시간(순서) 관리번호
	 */
	public String getConcertOrderId() {
		return this.concertOrderId;
	}

	/**
	 * 공연날짜을 설정합니다..
	 * 
	 * @param concertDate
	 *            공연날짜
	 */
	public void setConcertDate(Date concertDate) {
		this.concertDate = concertDate;
	}

	/**
	 * 공연날짜을 가져옵니다..
	 * 
	 * @return 공연날짜
	 */
	public Date getConcertDate() {
		return this.concertDate;
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
	 * 사용포인트을 설정합니다..
	 * 
	 * @param usedPoint
	 *            사용포인트
	 */
	public void setUsedPoint(Long usedPoint) {
		this.usedPoint = usedPoint;
	}

	/**
	 * 사용포인트을 가져옵니다..
	 * 
	 * @return 사용포인트
	 */
	public Long getUsedPoint() {
		return this.usedPoint;
	}

	/**
	 * 할인가격을 설정합니다..
	 * 
	 * @param discountPrice
	 *            할인가격
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	/**
	 * 할인가격을 가져옵니다..
	 * 
	 * @return 할인가격
	 */
	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	/**
	 * 일반가격을 설정합니다..
	 * 
	 * @param normalPrice
	 *            일반가격
	 */
	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}

	/**
	 * 일반가격을 가져옵니다..
	 * 
	 * @return 일반가격
	 */
	public BigDecimal getNormalPrice() {
		return this.normalPrice;
	}

	/**
	 * 결재금액 (일반가격-할인가격-사용포인트)을 설정합니다..
	 * 
	 * @param totalPrice
	 *            결재금액 (일반가격-할인가격-사용포인트)
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * 결재금액 (일반가격-할인가격-사용포인트)을 가져옵니다..
	 * 
	 * @return 결재금액 (일반가격-할인가격-사용포인트)
	 */
	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * 예매상태(예매완료, 예매 취소, 시간초과 등)을 설정합니다..
	 * 
	 * @param reservationStatus
	 *            예매상태(예매완료, 예매 취소, 시간초과 등)
	 */
	public void setReservationState(String reservationState) {
		this.reservationState = reservationState;
	}

	/**
	 * 예매상태(예매완료, 예매 취소, 시간초과 등)을 가져옵니다..
	 * 
	 * @return 예매상태(예매완료, 예매 취소, 시간초과 등)
	 */
	public String getReservationState() {
		return this.reservationState;
	}

	/**
	 * 예약타입(사용자예약/관리자예약)을 설정합니다..
	 * 
	 * @param reservationType
	 *            예약타입(사용자예약/관리자예약)
	 */
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	/**
	 * 예약타입(사용자예약/관리자예약)을 가져옵니다..
	 * 
	 * @return 예약타입(사용자예약/관리자예약)
	 */
	public String getReservationType() {
		return this.reservationType;
	}

	/**
	 * 결재종류을 설정합니다..
	 * 
	 * @param paymentType
	 *            결재종류
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 결재종류을 가져옵니다..
	 * 
	 * @return 결재종류
	 */
	public String getPaymentType() {
		return this.paymentType;
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
	 * 휴대전화번호을 설정합니다..
	 * 
	 * @param cellPhoneNumber
	 *            휴대전화번호
	 */
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	/**
	 * 휴대전화번호을 가져옵니다..
	 * 
	 * @return 휴대전화번호
	 */
	public String getCellPhoneNumber() {
		return this.cellPhoneNumber;
	}

	/**
	 * 주소을 설정합니다..
	 * 
	 * @param address
	 *            주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 주소을 가져옵니다..
	 * 
	 * @return 주소
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 우편번호을 설정합니다..
	 * 
	 * @param zipcode
	 *            우편번호
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 우편번호을 가져옵니다..
	 * 
	 * @return 우편번호
	 */
	public String getZipcode() {
		return this.zipcode;
	}
	
	
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public BigDecimal getCommissionPrice() {
		return commissionPrice;
	}

	public void setCommissionPrice(BigDecimal commissionPrice) {
		this.commissionPrice = commissionPrice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservationHistoryLogSeq == null) ? 0 : reservationHistoryLogSeq.hashCode());
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
		ReservationHistoryLog other = (ReservationHistoryLog) obj;
		if (reservationHistoryLogSeq == null) {
			if (other.reservationHistoryLogSeq != null) {
				return false;
			}
		} else if (!reservationHistoryLogSeq.equals(other.reservationHistoryLogSeq)) {
			return false;
		}
		return true;
	}
	

	public void convertToReservationHistory(ReservationHistory reservationHistory ) {
		/** 예매 아이디. */
		this.reservationId = reservationHistory.getReservationId();

		/** 결재 트랜젝션 아이디. */
		this.tid = reservationHistory.getTid();
		
		this.orderIdx =  reservationHistory.getOrderIdx();

		/** 공연 아이디. */
		this.concertId = reservationHistory.getConcertManagement().getConcertId();

		/** 예매자. */
		this.ticketBuyer = reservationHistory.getTicketBuyer();

		/** 예매자 이름. */
		this.ticketBuyerName = reservationHistory.getTicketBuyerName();

		/** 공연 시간(순서) 관리번호. */
		this.concertOrderId = reservationHistory.getConcertOrderId();

		/** 공연날짜. */
		this.concertDate = reservationHistory.getConcertDate();

		/** 회차. */
		this.st = reservationHistory.getSt();

		/** 사용포인트. */
		this.usedPoint = reservationHistory.getUsedPoint();

		/** 할인가격. */
		this.discountPrice = reservationHistory.getDiscountPrice();

		/** 일반가격. */
		this.normalPrice = reservationHistory.getNormalPrice();

		/** 결재금액 (일반가격-할인가격-사용포인트). */
		this.totalPrice = reservationHistory.getTotalPrice();
		
		/** 수수료  */
		this.commissionPrice = reservationHistory.getCommissionPrice();

		/** 예매상태(예매완료, 예매 취소, 시간초과 등). */
		this.reservationState = reservationHistory.getReservationState();

		/** 예약타입(사용자예약/관리자예약). */
		this.reservationType =  reservationHistory.getReservationType();

		/** 결재종류. */
		this.paymentType = reservationHistory.getPaymentType();
		/**취소 사유 정보*/
		this.cancelReason =  reservationHistory.getCancelReason();
		/**취소좌석 정보*/
		this.seatInfo = reservationHistory.getCancelSeatInfo();
		/**콘서트명*/
		this.concertName = reservationHistory.getConcertManagement().getConcertName();
		
		/**콘서트 시작 시간 */
		this.concertStartTime = reservationHistory.getConcertStartTime();
		
		this.shopType =  reservationHistory.getShopType();
		
		this.virtualAccount = reservationHistory.getVirtualAccount();

		/** 은행명. */
		this.bankName = reservationHistory.getBankName();

		/** 입금자명. */
		this.remitter = reservationHistory.getRemitter();

		/** 실제 입금 금액. */
		this.ipgmMnyx = reservationHistory.getIpgmMnyx();

		/** 가상계좌입금처리결과 code. */
		this.opCd = reservationHistory.getOpCd();

		/** 입금통보건에대한 고유값. */
		this.noticeId = reservationHistory.getNoticeId();

		/** 현금영수증승인번호. */
		this.cashApprovalNo = reservationHistory.getCashApprovalNo();

		
		/**성공/실패 에 따른 메시지 */
		//this.message = "";
		/**성공/실패 타입*/
		//this.logType = "";
		
	}
	
}
