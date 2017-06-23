package com.skan.tms.web.jpa.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

/**
 * Description : 기본 좌석 정보 composite - id 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Embeddable
public class BasicSeatId implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 공연장 관리. */
	@Column(length=64)
	private String concertHallId;


	/** 좌석 아이디. */
	@Column
	private Integer seatId;

	/**
	 * 생성자.
	 */
	public BasicSeatId() {
	}

	public BasicSeatId(String concertHallId) {
		this.concertHallId = concertHallId;
	}

	public String getConcertHallId() {
		return concertHallId;
	}



	public void setConcertHallId(String concertHallId) {
		this.concertHallId = concertHallId;
	}

	/**
	 * 좌석 아이디을 설정합니다..
	 * 
	 * @param seatId
	 *            좌석 아이디
	 */
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	/**
	 * 좌석 아이디을 가져옵니다..
	 * 
	 * @return 좌석 아이디
	 */
	public Integer getSeatId() {
		return this.seatId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concertHallId == null) ? 0 : concertHallId.hashCode());
		result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
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
		BasicSeatId other = (BasicSeatId) obj;
		if (concertHallId == null) {
			if (other.concertHallId != null) {
				return false;
			}
		} else if (!concertHallId.equals(other.concertHallId)) {
			return false;
		}
		if (seatId == null) {
			if (other.seatId != null) {
				return false;
			}
		} else if (!seatId.equals(other.seatId)) {
			return false;
		}
		return true;
	}

}
