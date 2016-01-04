package com.skan.potal.web.potal.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CmtbSchedulePK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636888593136605005L;

	public CmtbSchedulePK() {
		super();
	}

	/**
	 * @param schMgtNo
	 * @param schSeq
	 */
	public CmtbSchedulePK(Long schMgtNo, Long schSeq) {
		super();
		this.schMgtNo = schMgtNo;
		this.schSeq = schSeq;
	}

	/** 일정관리번호. */
	@Column
	private Long schMgtNo;

	/** 같은날의 순번. */
	@Column
	private Long schSeq;

	/**
	 * 일정관리번호을 설정합니다..
	 * 
	 * @param schMgtNo
	 *            일정관리번호
	 */
	public void setSchMgtNo(Long schMgtNo) {
		this.schMgtNo = schMgtNo;
	}

	/**
	 * 일정관리번호을 가져옵니다..
	 * 
	 * @return 일정관리번호
	 */
	public Long getSchMgtNo() {
		return this.schMgtNo;
	}

	/**
	 * 같은날의 순번을 설정합니다..
	 * 
	 * @param schSeq
	 *            같은날의 순번
	 */
	public void setSchSeq(Long schSeq) {
		this.schSeq = schSeq;
	}

	/**
	 * 같은날의 순번을 가져옵니다..
	 * 
	 * @return 같은날의 순번
	 */
	public Long getSchSeq() {
		return this.schSeq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((schMgtNo == null) ? 0 : schMgtNo.hashCode());
		result = prime * result + ((schSeq == null) ? 0 : schSeq.hashCode());
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
		CmtbSchedulePK other = (CmtbSchedulePK) obj;
		if (schMgtNo == null) {
			if (other.schMgtNo != null)
				return false;
		} else if (!schMgtNo.equals(other.schMgtNo))
			return false;
		if (schSeq == null) {
			if (other.schSeq != null)
				return false;
		} else if (!schSeq.equals(other.schSeq))
			return false;
		return true;
	}
	
}
