package com.skan.tms.web.jpa.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Description : 기본 좌석 정보 모델 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class BasicSeat implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 기본 좌석 정보 composite - id. */
	@EmbeddedId
	private BasicSeatId basicSeatId;

	/** 층수 (1층, 2층). */
	private Integer floor;

	/** 구역명. */
	private String areaName;

	/** 열명칭 . */
	private String rowName;

	/** 좌석번호. */
	private Integer seatNumber;

	/** 절차 설명. */
	private String description;
	
	/** 공연장 관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertHallId", nullable=true, updatable=false, insertable=false )
	private ConcertHallManagement concertHallManagement;

	/**
	 * 생성자.
	 */
	public BasicSeat() {
	}

	/**
	 * 기본 좌석 정보 composite - id을 설정합니다..
	 * 
	 * @param basicSeatId
	 *            기본 좌석 정보 composite - id
	 */
	public void setBasicSeatId(BasicSeatId basicSeatId) {
		this.basicSeatId = basicSeatId;
	}

	/**
	 * 기본 좌석 정보 composite - id을 가져옵니다..
	 * 
	 * @return 기본 좌석 정보 composite - id
	 */
	public BasicSeatId getBasicSeatId() {
		return this.basicSeatId;
	}

	/**
	 * 공연장 관리을 설정합니다..
	 * 
	 * @param concertHallManagement
	 *            공연장 관리
	 */
	public void setConcertHallManagement(ConcertHallManagement concertHallManagement) {
		this.concertHallManagement = concertHallManagement;
	}

	/**
	 * 공연장 관리을 가져옵니다..
	 * 
	 * @return 공연장 관리
	 */
	@JsonIgnore
	public ConcertHallManagement getConcertHallManagement() {
		return this.concertHallManagement;
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
	 * 절차 설명을 설정합니다..
	 * 
	 * @param description
	 *            절차 설명
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	/**
	 * 절차 설명을 가져옵니다..
	 * 
	 * @return 절차 설명
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicSeatId == null) ? 0 : basicSeatId.hashCode());
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
		BasicSeat other = (BasicSeat) obj;
		if (basicSeatId == null) {
			if (other.basicSeatId != null) {
				return false;
			}
		} else if (!basicSeatId.equals(other.basicSeatId)) {
			return false;
		}
		return true;
	}

}
