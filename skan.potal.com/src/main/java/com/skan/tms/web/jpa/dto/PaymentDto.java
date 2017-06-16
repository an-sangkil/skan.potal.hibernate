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
 * Class Name  : PaymentDto.java
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
public class PaymentDto {
	
	@Id
	@Column(name="uid", length=52)
	private String uid;
	
	@Column(name="shop_id", length=52)
	private String shop_id;
	
	@Column(name="shop_name", length=32)
	private String shop_name;
	
	@Column(name="payment_amount")
	private long payment_amount;

	@Column(name="payment_type", length=5)
	private String payment_type;
	
	@Column(name="status", length=5)
	private String status;
	
	@Column(name="email", length=32)
	private String email;
	
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
	 * 상점(가게)아이디
	 * @return
	 */
	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	/**
	 * 가맹점 이름
	 * @return
	 */
	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	/**
	 * 결제금액
	 * @return
	 */
	public long getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(long payment_amount) {
		this.payment_amount = payment_amount;
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
	 * 처리상태
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 예약자 정보
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
