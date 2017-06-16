package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description : 공연관리 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class ConcertManagement implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 공연 아이디. */
	@Id
	@Column(length=64)
	private String concertId;

	/** 공연명. */
	@Column(length=512)
	@Size(min=1,max=512)
	@NotEmpty
	private String concertName;

	/** 공연부제. */
	@Column(length=512)
	@Size(min=1,max=512)
	private String concertSubTitle;
	
	/** 공연장 아이디 */
	@Column(length=64)
	@NotEmpty
	private String concertHallId;
	
	/** 문의처. */
	@Size(min=1,max=128)
	@Column(length=128)
	private String contactUs;
	
	/** 주최. */
	@Size(min=1,max=128)
	@Column(length=128)
	private String host;

	/** 주관. */
	@Size(min=1,max=128)
	@Column(length=128)
	private String conduct;

	/** 후원. */
	@Size(min=1,max=512)
	@Column(length=512)
	private String support;

	/** 공연종류(초대공연/기획공연/대관공연). */
	@NotNull
	@Column(length=36)
	private String concertType;

	/** 온라인판매. */
	@Column
	private Boolean onlineSale;

	/** 오프라인판매. */
	@Column
	private Boolean offlineSale;

	/** 공연상태(대기/진행중/종료). */
	@NotBlank
	@Column(length=36)
	private String concertStatus;
	
	/** 자동으로 공연 오픈(true/false). */
	@Column
	private Boolean autoConsertOpen;

	/** 공연 오픈 시간. */
	@Column(nullable=true)
	private Long concertOpenTime;

	/** 공연 종료 시간. */
	@Column(nullable=true)
	private Long concertCloseTime;
	
	/** 메인이미지 파일아이디. */
	@Column(length=64)
	private String mainImageFileId;

	/** 서브이미지 파일 아이디. */
	@Column(length=64)
	private String subImageFileId;

	/** 공연시작 날짜. */
	@Column(updatable=false)
	private Date concertStartDate;
	
	/** 공연종료 날짜. */
	@Column(updatable=false)
	private Date concertEndDate;
	
	/**
	 * 내용
	 */
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column
	private byte[] contents;
	
	/** 등록자. */
	@Column(length=32,updatable=false)
	private String creator;

	/** 등록시간. */
	@Column(updatable=false)
	private Date creationTime;

	/** 1인당구매제한. */
	@Column
	private Integer maxBuyTicketCount;
	
	/** 티켓 하단 메세지 */
	@Column
	private String ticketMessage;

	@Transient
	private ConcertHallManagement concertHallManagement;

	public String getTicketMessage() {
		return ticketMessage;
	}
	public void setTicketMessage(String ticketMessage) {
		this.ticketMessage = ticketMessage;
	}

	/** 공연순서(회차) 목록. */
	@OneToMany(mappedBy="concertManagement" , fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ConcertTimeOrder> concertOrderSet;

	/** 공연에서 사용중인 권종 종류 목록. */
	@OneToMany(mappedBy="concertManagement" , fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ConcertUsedDenomination> concertUsedDenominationSet;

	/** 공연별 좌석 등급 가격 목록. */
	@OneToMany(mappedBy="concertManagement" , fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ConcertSeatGradePrice> consertSeatGradePriceSet;
	
	
	/** 좌석 예매 정보 목록. */
	@OneToMany(mappedBy="concertManagement" , fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ReservationInformation> reservationInformationSet;

	/**
	 * 생성자.
	 */
	public ConcertManagement() {
		this.concertOrderSet = new HashSet<ConcertTimeOrder>();
		this.concertUsedDenominationSet = new HashSet<ConcertUsedDenomination>();
		this.consertSeatGradePriceSet = new HashSet<ConcertSeatGradePrice>();
		this.reservationInformationSet = new HashSet<ReservationInformation>();
	}
	public ConcertManagement(String concertId) {
		this.concertId = concertId;
	}
	
	
	public ConcertHallManagement getConcertHallManagement() {
		return concertHallManagement;
	}
	public void setConcertHallManagement(ConcertHallManagement concertHallManagement) {
		this.concertHallManagement = concertHallManagement;
	}
	public String getContents() {
		if(contents != null) {
			return new String(contents);
		}
		return null;
	}
	public void setContents(byte[] contents) {
		this.contents = contents;
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

	public String getConcertHallId() {
		return concertHallId;
	}

	public void setConcertHallId(String concertHallId) {
		this.concertHallId = concertHallId;
	}

	/**
	 * 공연명을 설정합니다..
	 * 
	 * @param concertName
	 *            공연명
	 */
	public void setConcertName(String concertName) {
		this.concertName = concertName;
	}

	/**
	 * 공연명을 가져옵니다..
	 * 
	 * @return 공연명
	 */
	public String getConcertName() {
		return this.concertName;
	}

	/**
	 * 공연부제을 설정합니다..
	 * 
	 * @param concertSubTitle
	 *            공연부제
	 */
	public void setConcertSubTitle(String concertSubTitle) {
		this.concertSubTitle = concertSubTitle;
	}

	/**
	 * 공연부제을 가져옵니다..
	 * 
	 * @return 공연부제
	 */
	public String getConcertSubTitle() {
		return this.concertSubTitle;
	}

	/**
	 * 주최을 설정합니다..
	 * 
	 * @param host
	 *            주최
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 주최을 가져옵니다..
	 * 
	 * @return 주최
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 * 주관을 설정합니다..
	 * 
	 * @param conduct
	 *            주관
	 */
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	/**
	 * 주관을 가져옵니다..
	 * 
	 * @return 주관
	 */
	public String getConduct() {
		return this.conduct;
	}

	/**
	 * 후원을 설정합니다..
	 * 
	 * @param support
	 *            후원
	 */
	public void setSupport(String support) {
		this.support = support;
	}

	/**
	 * 후원을 가져옵니다..
	 * 
	 * @return 후원
	 */
	public String getSupport() {
		return this.support;
	}

	/**
	 * 공연종류(초대공연/기획공연/대관공연)을 설정합니다..
	 * 
	 * @param concertType
	 *            공연종류(초대공연/기획공연/대관공연)
	 */
	public void setConcertType(String concertType) {
		this.concertType = concertType;
	}

	/**
	 * 공연종류(초대공연/기획공연/대관공연)을 가져옵니다..
	 * 
	 * @return 공연종류(초대공연/기획공연/대관공연)
	 */
	public String getConcertType() {
		return this.concertType;
	}

	/**
	 * 온라인판매을 설정합니다..
	 * 
	 * @param onlineSale
	 *            온라인판매
	 */
	public void setOnlineSale(Boolean onlineSale) {
		this.onlineSale = onlineSale;
	}

	/**
	 * 온라인판매을 가져옵니다..
	 * 
	 * @return 온라인판매
	 */
	public Boolean getOnlineSale() {
		return this.onlineSale;
	}

	/**
	 * 오프라인판매을 설정합니다..
	 * 
	 * @param offlineSale
	 *            오프라인판매
	 */
	public void setOfflineSale(Boolean offlineSale) {
		this.offlineSale = offlineSale;
	}

	/**
	 * 오프라인판매을 가져옵니다..
	 * 
	 * @return 오프라인판매
	 */
	public Boolean getOfflineSale() {
		return this.offlineSale;
	}

	/**
	 * 공연상태(대기/진행중/종료)을 설정합니다..
	 * 
	 * @param concertStatus
	 *            공연상태(대기/진행중/종료)
	 */
	public void setConcertStatus(String concertStatus) {
		this.concertStatus = concertStatus;
	}

	/**
	 * 공연상태(대기/진행중/종료)을 가져옵니다..
	 * 
	 * @return 공연상태(대기/진행중/종료)
	 */
	public String getConcertStatus() {
		return this.concertStatus;
	}

	/**
	 * 자동으로 공연 오픈(true/false)을 설정합니다..
	 * 
	 * @param autoConsertOpen
	 *            자동으로 공연 오픈(true/false)
	 */
	public void setAutoConsertOpen(Boolean autoConsertOpen) {
		this.autoConsertOpen = autoConsertOpen;
	}

	/**
	 * 자동으로 공연 오픈(true/false)을 가져옵니다..
	 * 
	 * @return 자동으로 공연 오픈(true/false)
	 */
	public Boolean getAutoConsertOpen() {
		return this.autoConsertOpen;
	}

	/**
	 * 공연 오픈 시간을 설정합니다..
	 * 
	 * @param concertOpenTime
	 *            공연 오픈 시간
	 */
	public void setConcertOpenTime(Long concertOpenTime) {
		this.concertOpenTime = concertOpenTime;
	}

	/**
	 * 공연 오픈 시간을 가져옵니다..
	 * 
	 * @return 공연 오픈 시간
	 */
	public Long getConcertOpenTime() {
		return this.concertOpenTime;
	}

	/**
	 * 공연 종료 시간을 설정합니다..
	 * 
	 * @param concertCloseTime
	 *            공연 종료 시간
	 */
	public void setConcertCloseTime(Long concertCloseTime) {
		this.concertCloseTime = concertCloseTime;
	}

	/**
	 * 공연 종료 시간을 가져옵니다..
	 * 
	 * @return 공연 종료 시간
	 */
	public Long getConcertCloseTime() {
		return this.concertCloseTime;
	}

	/**
	 * 메인이미지 파일아이디을 설정합니다..
	 * 
	 * @param fileGroupId
	 *            메인이미지 파일아이디
	 */
	public void setMainImageFileId(String mainImageFileId) {
		this.mainImageFileId = mainImageFileId;
	}

	/**
	 * 메인이미지 파일아이디을 가져옵니다..
	 * 
	 * @return 메인이미지 파일아이디
	 */
	public String getMainImageFileId() {
		return this.mainImageFileId;
	}

	/**
	 * 서브이미지 파일 아이디을 설정합니다..
	 * 
	 * @param subImageFileId
	 *            서브이미지 파일 아이디
	 */
	public void setSubImageFileId(String subImageFileId) {
		this.subImageFileId = subImageFileId;
	}

	/**
	 * 서브이미지 파일 아이디을 가져옵니다..
	 * 
	 * @return 서브이미지 파일 아이디
	 */
	public String getSubImageFileId() {
		return this.subImageFileId;
	}

	/**
	 * 공연시작 날짜을 설정합니다..
	 * 
	 * @param concertStartDate
	 *            공연시작 날짜
	 */
	public void setConcertStartDate(Date concertStartDate) {
		this.concertStartDate = concertStartDate;
	}
	
	/**
	 * 공연시작 날짜을 가져옵니다..
	 * 
	 * @return 공연시작 날짜
	 */
	public Date getConcertStartDate() {
		return this.concertStartDate;
	}

	/**
	 * 공연종료 날짜을 설정합니다..
	 * 
	 * @param consertEndDate
	 *            공연종료 날짜
	 */
	public void setConcertEndDate(Date concertEndDate) {
		this.concertEndDate = concertEndDate;
	}

	/**
	 * 공연종료 날짜을 가져옵니다..
	 * 
	 * @return 공연종료 날짜
	 */
	public Date getConcertEndDate() {
		return this.concertEndDate;
	}
	
	public String getContactUs() {
		return contactUs;
	}

	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}

	///////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////
	/**
	 * 공연순서(회차) 목록을 설정합니다..
	 * 
	 * @param concertOrderSet
	 *            공연순서(회차) 목록
	 */
	public void setConcertOrderSet(Set<ConcertTimeOrder> concertOrderSet) {
		this.concertOrderSet = concertOrderSet;
	}

	/**
	 * 공연순서(회차)를 추가합니다..
	 * 
	 * @param concertOrder
	 *            공연순서(회차)
	 */
	public void addConcertOrder(ConcertTimeOrder concertOrder) {
		this.concertOrderSet.add(concertOrder);
	}

	/**
	 * 공연순서(회차) 목록을 가져옵니다..
	 * 
	 * @return 공연순서(회차) 목록
	 */
	public Set<ConcertTimeOrder> getConcertOrderSet() {
		return this.concertOrderSet;
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
	 * 공연별 좌석 등급 가격 목록을 설정합니다..
	 * 
	 * @param consertSeatGradePriceSet
	 *            공연별 좌석 등급 가격 목록
	 */
	public void setConsertSeatGradePriceSet(Set<ConcertSeatGradePrice> consertSeatGradePriceSet) {
		this.consertSeatGradePriceSet = consertSeatGradePriceSet;
	}

	/**
	 * 공연별 좌석 등급 가격를 추가합니다..
	 * 
	 * @param consertSeatGradePrice
	 *            공연별 좌석 등급 가격
	 */
	public void addConsertSeatGradePrice(ConcertSeatGradePrice consertSeatGradePrice) {
		this.consertSeatGradePriceSet.add(consertSeatGradePrice);
	}

	/**
	 * 공연별 좌석 등급 가격 목록을 가져옵니다..
	 * 
	 * @return 공연별 좌석 등급 가격 목록
	 */
	public Set<ConcertSeatGradePrice> getConsertSeatGradePriceSet() {
		return this.consertSeatGradePriceSet;
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
	 * 
	 * 
	 * @return 1인당 티켓 구매 제한
	 */ 
	public Integer getMaxBuyTicketCount() {
		return maxBuyTicketCount;
	}
	public void setMaxBuyTicketCount(Integer maxBuyTicketCount) {
		this.maxBuyTicketCount = maxBuyTicketCount;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concertId == null) ? 0 : concertId.hashCode());
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
		ConcertManagement other = (ConcertManagement) obj;
		if (concertId == null) {
			if (other.concertId != null) {
				return false;
			}
		} else if (!concertId.equals(other.concertId)) {
			return false;
		}
		return true;
	}

}