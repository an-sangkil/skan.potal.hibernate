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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

 /**
  * Description : TMS 좌석 등급관리 모델 클래스. 
  * @author skan
  * @since 2016. 10. 19.
  * @version 
  *
  * Copyright (C) 2016 by SKAN Corp. All right reserved.
  */

@Entity
@Table(name="seatgrademanagement")
public class SeatGradeManagement implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 좌석 등급 아이디. */
	@Id
	@Column(name="grade_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer gradeId;

	/** 순서. */
	
	@Column(name="order_seq")
	private Integer orderSeq;

	/** 등급명. */
	@Column(name="grade_name",length=24)
	private String gradeName;

	/** 등급 색상. */
	@Column(name="grade_color",length=12)
	private String gradeColor;
	
	/** 공연별 좌석 등급 가격 목록. */
	@OneToMany(mappedBy="seatGradeManagement", fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ConcertSeatGradePrice> consertSeatGradePriceSet;

	/**
	 * 생성자.
	 */
	public SeatGradeManagement() {
		this.consertSeatGradePriceSet = new HashSet<ConcertSeatGradePrice>();
	}
	
	public SeatGradeManagement(Integer gradeId) {
		this.gradeId = gradeId;
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
	 * 순서을 설정합니다..
	 * 
	 * @param orderSeq
	 *            순서
	 */
	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 순서을 가져옵니다..
	 * 
	 * @return 순서
	 */
	public Integer getOrderSeq() {
		return this.orderSeq;
	}

	/**
	 * 등급명을 설정합니다..
	 * 
	 * @param gradeName
	 *            등급명
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * 등급명을 가져옵니다..
	 * 
	 * @return 등급명
	 */
	public String getGradeName() {
		return this.gradeName;
	}

	/**
	 * 등급 색상을 설정합니다..
	 * 
	 * @param gealeColor
	 *            등급 색상
	 */
	public void setGradeColor(String gealeColor) {
		this.gradeColor = gealeColor;
	}

	/**
	 * 등급 색상을 가져옵니다..
	 * 
	 * @return 등급 색상
	 */
	public String getGradeColor() {
		return this.gradeColor;
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
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
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
		SeatGradeManagement other = (SeatGradeManagement) obj;
		if (gradeId == null) {
			if (other.gradeId != null) {
				return false;
			}
		} else if (!gradeId.equals(other.gradeId)) {
			return false;
		}
		return true;
	}

}
