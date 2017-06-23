/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name  : BusinessMngDto.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 29.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 29.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
public class BusinessMngDto {
	
	@Id
	@Column(name="bussiness_mng_id", length=32)
	private String bussiness_mng_id;
	
	@Column(name="bussiness_person_charge", length=32)
	private String bussiness_person_charge;
	

	/**
	 * 영업담당자 아이디
	 * @return
	 */
	public String getBussiness_mng_id() {
		return bussiness_mng_id;
	}

	public void setBussiness_mng_id(String bussiness_mng_id) {
		this.bussiness_mng_id = bussiness_mng_id;
	}

	/**
	 * 영업담당자 이름
	 * @return
	 */
	public String getBussiness_person_charge() {
		return bussiness_person_charge;
	}

	public void setBussiness_person_charge(String bussiness_person_charge) {
		this.bussiness_person_charge = bussiness_person_charge;
	}

	
}
