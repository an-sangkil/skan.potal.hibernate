package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * Description : 공연별 좌석 등급 가격 모델 클래스.
 * 
 * @author skan
 * @since 2016. 10. 19.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */

@Entity
@Table
public class ConcertSeatGradePrice implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seatGradePriceSeq;
	
	/** 공연관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertId" , updatable=false, insertable=true)
	private ConcertManagement concertManagement;

	/** 좌석 등급관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gradeId",referencedColumnName="grade_id", updatable=false, insertable=true)
	private SeatGradeManagement seatGradeManagement;

	/** 가격 (max 1,000,000). */
	@Digits(integer=7,fraction=0)
	@Column
	private BigDecimal price;

	/**
	 * 생성자.
	 */
	public ConcertSeatGradePrice() {
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
	 * 좌석 등급관리을 설정합니다..
	 * 
	 * @param seatGradeManagement
	 *            좌석 등급관리
	 */
	public void setSeatGradeManagement(SeatGradeManagement seatGradeManagement) {
		this.seatGradeManagement = seatGradeManagement;
	}

	/**
	 * 좌석 등급관리을 가져옵니다..
	 * 
	 * @return 좌석 등급관리
	 */
	public SeatGradeManagement getSeatGradeManagement() {
		return this.seatGradeManagement;
	}

	/**
	 * 가격 (max 1,000,000)을 설정합니다..
	 * 
	 * @param price
	 *            가격 (max 1,000,000)
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 가격 (max 1,000,000)을 가져옵니다..
	 * 
	 * @return 가격 (max 1,000,000)
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	public Integer getSeatGradePriceSeq() {
		return seatGradePriceSeq;
	}

	public void setSeatGradePriceSeq(Integer seatGradePriceSeq) {
		this.seatGradePriceSeq = seatGradePriceSeq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatGradePriceSeq == null) ? 0 : seatGradePriceSeq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcertSeatGradePrice other = (ConcertSeatGradePrice) obj;
		if (seatGradePriceSeq == null) {
			if (other.seatGradePriceSeq != null)
				return false;
		} else if (!seatGradePriceSeq.equals(other.seatGradePriceSeq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConcertSeatGradePrice [seatGradePriceSeq=" + seatGradePriceSeq + ", concertManagement="
				+ concertManagement + ", seatGradeManagement=" + seatGradeManagement + ", price=" + price + "]";
	}
}
