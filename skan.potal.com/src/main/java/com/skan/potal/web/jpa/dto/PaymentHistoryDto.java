/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name  : PaymentHistoryDto.java
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
public class PaymentHistoryDto {
	
	@Id
	@Column(name="uid", length=52)
	private String uid;
	
	@Column(name="payment_type", length=5)
	private String payment_type;
	
	@Column(name="payment")
	private long payment;
	
	@Column(name="error_code", length=32)
	private String error_code;
	
	@Column(name="error_comment", length=1280)
	private String error_comment;
	
	@Column(name="creation_time")
	private Date creation_time;

	
	/**
	 * 주문결제 아이디 결제 유일키값
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 결제타입
	 * @return
	 */
	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	/**
	 * 결제금액
	 * @return
	 */
	public long getPayment() {
		return payment;
	}

	public void setPayment(long payment) {
		this.payment = payment;
	}

	/**
	 * 오류코드
	 * @return
	 */
	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * 오류 내용
	 * @return
	 */
	public String getError_comment() {
		return error_comment;
	}

	public void setError_comment(String error_comment) {
		this.error_comment = error_comment;
	}

	/**
	 * 등록시간
	 * @return
	 */
	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	
}
