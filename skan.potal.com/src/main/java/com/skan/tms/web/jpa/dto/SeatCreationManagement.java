package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Description : 좌석 생성 모델 클래스 
 * @author skan
 * @since 2016. 10. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table
public class SeatCreationManagement implements Serializable , Comparator<SeatCreationManagement> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	@Embeddable
	public static class SeatCreationManagementPK implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public SeatCreationManagementPK(){}
		
		public SeatCreationManagementPK(String concertHallId){
			this.concertHallId=concertHallId;
		}
		
		public SeatCreationManagementPK(String concertHallId,Integer seq){
			this.concertHallId=concertHallId;
			this.seq =seq;
		}
		
		@Column(length=64)
		private String concertHallId;
		
		private Integer seq;

		public String getConcertHallId() {
			return concertHallId;
		}

		public void setConcertHallId(String concertHallId) {
			this.concertHallId = concertHallId;
		}

		public Integer getSeq() {
			return seq;
		}

		public void setSeq(Integer seq) {
			this.seq = seq;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((concertHallId == null) ? 0 : concertHallId.hashCode());
			result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
			SeatCreationManagementPK other = (SeatCreationManagementPK) obj;
			if (concertHallId == null) {
				if (other.concertHallId != null)
					return false;
			} else if (!concertHallId.equals(other.concertHallId))
				return false;
			if (seq == null) {
				if (other.seq != null)
					return false;
			} else if (!seq.equals(other.seq))
				return false;
			return true;
		}
		
	}
	
	@EmbeddedId
	private SeatCreationManagementPK seatCreationManagementPK;

	/** 층수 (1층, 2층). */
	private Integer floor;

	/** 구역명. */
	private String areaName;

	/** 열명칭 . */
	private String rowName;

	/** 좌석수. */
	private Integer seatCount;
	
	private Integer startNo;
	private Integer endNo;
	
	/** 공연장 관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertHallId", referencedColumnName="concertHallId",nullable=true, insertable=false, updatable=false)
	private ConcertHallManagement concertHallManagement;

	/**
	 * 생성자.
	 */
	public SeatCreationManagement() {
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
	public ConcertHallManagement getConcertHallManagement() {
		return this.concertHallManagement;
	}

	public Integer getStartNo() {
		return startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
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
	 * 좌석수을 설정합니다..
	 * 
	 * @param seatCount
	 *            좌석수
	 */
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	/**
	 * 좌석수을 가져옵니다..
	 * 
	 * @return 좌석수
	 */
	public Integer getSeatCount() {
		return this.seatCount;
	}

	public SeatCreationManagementPK getSeatCreationManagementPK() {
		return seatCreationManagementPK;
	}

	public void setSeatCreationManagementPK(SeatCreationManagementPK seatCreationManagementPK) {
		this.seatCreationManagementPK = seatCreationManagementPK;
	}
	
	@Override
	public int compare(SeatCreationManagement o1, SeatCreationManagement o2) {
		Integer en1 = ((SeatCreationManagement) o1).seatCreationManagementPK.seq;
		Integer en2 = ((SeatCreationManagement) o2).seatCreationManagementPK.seq;
		return en1.compareTo(en2); // ascending 정렬
	}
}
