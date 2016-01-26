package com.skan.potal.web.potal.application.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 코드 PK Class
 * @author skan
 *
 */
@Embeddable
public class CmtbCodeId implements Serializable {
	
	
	public CmtbCodeId() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 841812616897421032L;

	/** 코드 관리번호. */
	private String codeMgtNo;

	/** 코드. */
	private String code;

	public String getCodeMgtNo() {
		return codeMgtNo;
	}

	public void setCodeMgtNo(String codeMgtNo) {
		this.codeMgtNo = codeMgtNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((codeMgtNo == null) ? 0 : codeMgtNo.hashCode());
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
		CmtbCodeId other = (CmtbCodeId) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (codeMgtNo == null) {
			if (other.codeMgtNo != null)
				return false;
		} else if (!codeMgtNo.equals(other.codeMgtNo))
			return false;
		return true;
	}
	
}
