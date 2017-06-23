/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : PointDto.java
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
@Table
public class PointDto {
	
	@Id
	@Column(name="email", length=32)
	private String email;
	
	@Column(name="total_point")
	private long total_point;

	
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
	 * 총 포인트(점수)
	 * @return
	 */
	public long getTotal_point() {
		return total_point;
	}

	public void setTotal_point(long total_point) {
		this.total_point = total_point;
	}

	
}
