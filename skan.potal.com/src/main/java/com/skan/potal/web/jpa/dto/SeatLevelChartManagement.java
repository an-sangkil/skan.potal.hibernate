package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

 /**
  * Description : 좌석 등급 배치도 관리 모델 클래스. 
  * @author skan
  * @since 2016. 10. 19.
  * @version 
  *
  * Copyright (C) 2016 by SKAN Corp. All right reserved.
  */

@Entity
@Table
public class SeatLevelChartManagement implements Serializable, Comparator<SeatLevelChartManagement> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 좌석 등급 배치 아이디. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seatChartId;

	/** 좌석등급 배치도 이름. */
	private String seatChartName;

	/** 좌석등급 배치도 설명. */
	private String seatChartExplain;

	/** 좌석 등급 배치도 목록. */
	@OneToMany(mappedBy="seatLevelChartManagement", fetch=FetchType.LAZY)
	private Set<SeatLevelChart> seatLevelChartSet;
	
	
	/** 공연장 관리. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concertHallId", referencedColumnName="concertHallId",nullable=true, insertable=true, updatable=true)
	private ConcertHallManagement concertHallManagement;

	/**
	 * 생성자.
	 */
	public SeatLevelChartManagement() {
		this.seatLevelChartSet = new HashSet<SeatLevelChart>();
	}

	/**
	 * 좌석 등급 배치 아이디을 설정합니다..
	 * 
	 * @param seatChartId
	 *            좌석 등급 배치 아이디
	 */
	public void setSeatChartId(Integer seatChartId) {
		this.seatChartId = seatChartId;
	}

	/**
	 * 좌석 등급 배치 아이디을 가져옵니다..
	 * 
	 * @return 좌석 등급 배치 아이디
	 */
	public Integer getSeatChartId() {
		return this.seatChartId;
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

	/**
	 * 좌석등급 배치도 이름을 설정합니다..
	 * 
	 * @param seatChartName
	 *            좌석등급 배치도 이름
	 */
	public void setSeatChartName(String seatChartName) {
		this.seatChartName = seatChartName;
	}

	/**
	 * 좌석등급 배치도 이름을 가져옵니다..
	 * 
	 * @return 좌석등급 배치도 이름
	 */
	public String getSeatChartName() {
		return this.seatChartName;
	}

	/**
	 * 좌석등급 배치도 설명을 설정합니다..
	 * 
	 * @param seatChartExplain
	 *            좌석등급 배치도 설명
	 */
	public void setSeatChartExplain(String seatChartExplain) {
		this.seatChartExplain = seatChartExplain;
	}

	/**
	 * 좌석등급 배치도 설명을 가져옵니다..
	 * 
	 * @return 좌석등급 배치도 설명
	 */
	public String getSeatChartExplain() {
		return this.seatChartExplain;
	}

	/**
	 * 좌석 등급 배치도 목록을 설정합니다..
	 * 
	 * @param seatLevelChartSet
	 *            좌석 등급 배치도 목록
	 */
	public void setSeatLevelChartSet(Set<SeatLevelChart> seatLevelChartSet) {
		this.seatLevelChartSet = seatLevelChartSet;
	}

	/**
	 * 좌석 등급 배치도를 추가합니다..
	 * 
	 * @param seatLevelChart
	 *            좌석 등급 배치도
	 */
	public void addSeatLevelChart(SeatLevelChart seatLevelChart) {
		this.seatLevelChartSet.add(seatLevelChart);
	}

	/**
	 * 좌석 등급 배치도 목록을 가져옵니다..
	 * 
	 * @return 좌석 등급 배치도 목록
	 */
	public Set<SeatLevelChart> getSeatLevelChartSet() {
		return this.seatLevelChartSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatChartId == null) ? 0 : seatChartId.hashCode());
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
		SeatLevelChartManagement other = (SeatLevelChartManagement) obj;
		if (seatChartId == null) {
			if (other.seatChartId != null) {
				return false;
			}
		} else if (!seatChartId.equals(other.seatChartId)) {
			return false;
		}
		return true;
	}

	@Override
	public int compare(SeatLevelChartManagement o1, SeatLevelChartManagement o2) {
//		String en1 = ((SeatLevelChartManagement)o1).;
//	    String en2 = ((SeatLevelChartManagement)o2).engName;
//	    return en1.compareTo(en2); // ascending 정렬
		return 0;
	}

}
