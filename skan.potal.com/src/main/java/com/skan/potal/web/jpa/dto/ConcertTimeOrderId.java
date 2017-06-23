package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Description : 공연순서(회차) composite - id 클래스. 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Embeddable
public class ConcertTimeOrderId implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 공연 시간(순서) 관리번호. */
	@Column(length=64)
	private String concertOrderId;

	/** 공연날짜. */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date concertDate;

	/**
	 * 생성자.
	 */
	public ConcertTimeOrderId() {
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
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concertOrderId == null) ? 0 : concertOrderId.hashCode());
		result = prime * result + ((concertDate == null) ? 0 : concertDate.hashCode());
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
		ConcertTimeOrderId other = (ConcertTimeOrderId) obj;
		if (concertOrderId == null) {
			if (other.concertOrderId != null) {
				return false;
			}
		} else if (!concertOrderId.equals(other.concertOrderId)) {
			return false;
		}
		if (concertDate == null) {
			if (other.concertDate != null) {
				return false;
			}
		} else if (!concertDate.equals(other.concertDate)) {
			return false;
		}
		return true;
	}

}
