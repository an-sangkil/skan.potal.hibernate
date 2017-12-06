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
 * Class Name  : CreditRatingDto.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 18.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 18.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="creditrating")
public class CreditRatingDto {
	
	@Id
	@Column(name="email", length=32)
	private String email;
	
	private int resrvationCount;	
	
	private int cancelCount;
	
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
	 * 예약 횟수
	 * @param resrvationCount
	 */
	public int getResrvationCount() {
		return resrvationCount;
	}
	public void setResrvationCount(int resrvationCount) {
		this.resrvationCount = resrvationCount;
	}
	

	/**
	 * 취소 횟수
	 * @param cancelCount
	 */
	public int getCancelCount() {
		return cancelCount;
	}
	public void setCancelCount(int cancelCount) {
		this.cancelCount = cancelCount;
	}
	
}
