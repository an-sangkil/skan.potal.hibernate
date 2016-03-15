/**
 * <pre>
 * Class Name  : HmCattlePK.java
 * Description : 검색에서 사용하기 위한 PK Class   
 *               @IdClass 상용
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 11.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 11.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.cattle.dto;

import java.io.Serializable;

/**
 * @author ahn
 *
 */
public class HmCattlePK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5840225421722021428L;
	private String entityDiscernNo;

	/**
	 * 
	 */
	public HmCattlePK() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param entityDiscernNo
	 */
	public HmCattlePK(String entityDiscernNo) {
		super();
		this.entityDiscernNo = entityDiscernNo;
	}

	public String getEntityDiscernNo() {
		return entityDiscernNo;
	}

	public void setEntityDiscernNo(String entityDiscernNo) {
		this.entityDiscernNo = entityDiscernNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityDiscernNo == null) ? 0 : entityDiscernNo.hashCode());
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
		HmCattlePK other = (HmCattlePK) obj;
		if (entityDiscernNo == null) {
			if (other.entityDiscernNo != null)
				return false;
		} else if (!entityDiscernNo.equals(other.entityDiscernNo))
			return false;
		return true;
	}
	
	
	
}
