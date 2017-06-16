/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name  : TakePointDto.java
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
public class TakePointDto {
	
	@Id
	@Column(name="email", length=32)
	private String email;
	
	@Column(name="point")
	private long point;
	
	@Column(name="document", length=256)
	private String document;
	
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
	 * 포인트
	 * @return
	 */
	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	/**
	 * 포인트  획득 내역
	 * @return
	 */
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
}
