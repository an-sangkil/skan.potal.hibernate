/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : UserCouponInfoDto.java
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
@Table(name="usercouponinfo")
public class UserCouponInfoDto {
	
	@Id
	@Column(name="email", length=32)
	private String email;

	@Column(name="cupon_number", length=52)
	private String cupon_number;

	
	/**
	 * 사용자 아이디
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 쿠폰번호
	 * @return
	 */
	public String getCupon_number() {
		return cupon_number;
	}

	public void setCupon_number(String cupon_number) {
		this.cupon_number = cupon_number;
	}

	
}
