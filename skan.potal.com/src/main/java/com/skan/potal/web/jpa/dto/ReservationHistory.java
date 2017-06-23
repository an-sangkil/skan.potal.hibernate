package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Description : TMS 좌석 예매 내역 모델 클래스.
 * 
 * @author skan
 * @since 2016. 10. 19.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class ReservationHistory implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 예매 아이디. */
	@Id
	@Column(length = 64)
	private String reservationId;

	/** 결재 트랜젝션 아이디. */
	@Column(length = 32)
	private String tid;

	/** 상품 거래번호 */
	@Column(length = 64)
	private String orderIdx;

	/** 공연 아이디. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concertId", referencedColumnName = "concertId")
	private ConcertManagement concertManagement;

	/** 예매자. */
	@Column(length = 32)
	private String ticketBuyer;

	/** 예매자 이름. */
	@Column(length = 32)
	private String ticketBuyerName;

	/** 공연 시간(순서) 관리번호. */
	@Column(length = 64)
	private String concertOrderId;

	/** 공연날짜. */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column
	private Date concertDate;

	/** 회차. */
	@Column
	private Integer st;

	/** 사용포인트. */
	@Column
	private Long usedPoint;

	/** 할인가격. */
	@Column
	private BigDecimal discountPrice;

	/** 일반가격. */
	@Column
	private BigDecimal normalPrice;

	/** 결재금액 (일반가격-할인가격-사용포인트). */
	@Column
	private BigDecimal totalPrice;
	
	/** 수수료 */
	private BigDecimal commissionPrice;

	/** 예매상태(예매완료, 예매 취소, 시간초과 등). */
	@Column(length = 32)
	private String reservationState;

	/** 예약타입(사용자예약/관리자예약). */
	@Column(length = 128)
	private String reservationType;

	/** 결제종류. */
	@Column(length = 64)
	private String paymentType;
	
	/** 온라인샵 오프라인샵*/
	@Column(length = 16)
	private String shopType;

	/** 발권 횟수. */
	@Column
	private Integer ticketingCount;

	/** 휴대전화번호. */
	@Column(length = 11)
	private String cellPhoneNumber;

	/** 주소. */
	@Column(length = 521)
	private String address;

	/** 우편번호. */
	@Column(length = 5)
	private String zipcode;

	/** 취소사유 */
	@Column(updatable=true, length=64)
	private String cancelReason;

	/** 취소 좌석 정보 */
	@Column(updatable=true, columnDefinition="MEDIUMTEXT")
	private String cancelSeatInfo;
	
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

	/** 등록자. */
	@Column(length = 32, updatable = false)
	private String creator;

	/** 등록시간. */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(updatable = false)
	private Date creationTime;

	/** 수정자. */
	@Column(length = 32)
	private String modifier;

	/** 수정시간. */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column
	private Date modifiedTime;
	
	/** 공연 시간 시작 시간 (24시 hh:mm:ss). */
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column
	private Date concertStartTime;
	
	
	/**현금영수증 거래번호*/
	private String cashReceiptTransactionNumber;
	/**현금영수증 승인번호*/
	private String cashReceiptApprovalNumber;
	/**현금영수증 승인시간*/
	private String cashReceiptApprovalTime;
	/**현금영수증 등록상태코드*/
	private String cashReceiptState;
	

	/** 좌석 예매 정보 목록. */
	@OneToMany(mappedBy = "reservationHistory", fetch = FetchType.LAZY)
	private Set<ReservationInformation> reservationInformationSet;
	
	/**
	 * 생성자.
	 */
	public ReservationHistory() {
		this.reservationInformationSet = new HashSet<ReservationInformation>();
	}
	
	public String getCashReceiptTransactionNumber() {
		return cashReceiptTransactionNumber;
	}


	public void setCashReceiptTransactionNumber(String cashReceiptTransactionNumber) {
		this.cashReceiptTransactionNumber = cashReceiptTransactionNumber;
	}


	public String getCashReceiptApprovalNumber() {
		return cashReceiptApprovalNumber;
	}


	public void setCashReceiptApprovalNumber(String cashReceiptApprovalNumber) {
		this.cashReceiptApprovalNumber = cashReceiptApprovalNumber;
	}


	public String getCashReceiptApprovalTime() {
		return cashReceiptApprovalTime;
	}


	public void setCashReceiptApprovalTime(String cashReceiptApprovalTime) {
		this.cashReceiptApprovalTime = cashReceiptApprovalTime;
	}


	public String getCashReceiptState() {
		return cashReceiptState;
	}


	public void setCashReceiptState(String cashReceiptState) {
		this.cashReceiptState = cashReceiptState;
	}


	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public Date getConcertStartTime() {
		return concertStartTime;
	}

	public void setConcertStartTime(Date concertStartTime) {
		this.concertStartTime = concertStartTime;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelSeatInfo() {
		return cancelSeatInfo;
	}

	public void setCancelSeatInfo(String cancelSeatInfo) {
		this.cancelSeatInfo = cancelSeatInfo;
	}

	public ReservationHistory(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getOrderIdx() {
		return orderIdx;
	}

	public void setOrderIdx(String orderIdx) {
		this.orderIdx = orderIdx;
	}

	public ConcertManagement getConcertManagement() {
		return concertManagement;
	}

	public void setConcertManagement(ConcertManagement concertManagement) {
		this.concertManagement = concertManagement;
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
	 * 발권 횟수을 설정합니다..
	 * 
	 * @param ticketingCount
	 *            발권 횟수
	 */
	public void setTicketingCount(Integer ticketingCount) {
		this.ticketingCount = ticketingCount;
	}

	/**
	 * 발권 횟수을 가져옵니다..
	 * 
	 * @return 발권 횟수
	 */
	public Integer getTicketingCount() {
		return this.ticketingCount;
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
	
	/**
	 * 가상계좌을 설정합니다..
	 * 
	 * @param virtualAccount
	 *            가상계좌
	 */
	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	/**
	 * 가상계좌을 가져옵니다..
	 * 
	 * @return 가상계좌
	 */
	public String getVirtualAccount() {
		return this.virtualAccount;
	}

	/**
	 * 은행명을 설정합니다..
	 * 
	 * @param bankName
	 *            은행명
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 은행명을 가져옵니다..
	 * 
	 * @return 은행명
	 */
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * 입금자명을 설정합니다..
	 * 
	 * @param remitter 
	 *            입금자명
	 */
	public void setRemitter (String remitter ) {
		this.remitter  = remitter ;
	}

	/**
	 * 입금자명을 가져옵니다..
	 * 
	 * @return 입금자명
	 */
	public String getRemitter () {
		return this.remitter ;
	}

	/**
	 * 실제 입금 금액을 설정합니다..
	 * 
	 * @param ipgmMnyx 
	 *            실제 입금 금액
	 */
	public void setIpgmMnyx (BigDecimal ipgmMnyx ) {
		this.ipgmMnyx  = ipgmMnyx ;
	}

	/**
	 * 실제 입금 금액을 가져옵니다..
	 * 
	 * @return 실제 입금 금액
	 */
	public BigDecimal getIpgmMnyx () {
		return this.ipgmMnyx ;
	}

	/**
	 * 가상계좌입금처리결과 code을 설정합니다..
	 * 
	 * @param opCd
	 *            가상계좌입금처리결과 code
	 */
	public void setOpCd(String opCd) {
		this.opCd = opCd;
	}

	/**
	 * 가상계좌입금처리결과 code을 가져옵니다..
	 * 
	 * @return 가상계좌입금처리결과 code
	 */
	public String getOpCd() {
		return this.opCd;
	}

	/**
	 * 입금통보건에대한 고유값을 설정합니다..
	 * 
	 * @param noticeId
	 *            입금통보건에대한 고유값
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	/**
	 * 입금통보건에대한 고유값을 가져옵니다..
	 * 
	 * @return 입금통보건에대한 고유값
	 */
	public String getNoticeId() {
		return this.noticeId;
	}

	/**
	 * 현금영수증승인번호을 설정합니다..
	 * 
	 * @param cashApprovalNo 
	 *            현금영수증승인번호
	 */
	public void setCashApprovalNo (String cashApprovalNo ) {
		this.cashApprovalNo  = cashApprovalNo ;
	}

	/**
	 * 현금영수증승인번호을 가져옵니다..
	 * 
	 * @return 현금영수증승인번호
	 */
	public String getCashApprovalNo () {
		return this.cashApprovalNo ;
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
		result = prime * result + ((reservationId == null) ? 0 : reservationId.hashCode());
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
		ReservationHistory other = (ReservationHistory) obj;
		if (reservationId == null) {
			if (other.reservationId != null) {
				return false;
			}
		} else if (!reservationId.equals(other.reservationId)) {
			return false;
		}
		return true;
	}

}
