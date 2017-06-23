/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name  : CouponDto.java
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
public class CouponDto {
	
	@Id
	@Column(name="cupon_number", length=52)
	private String cupon_number;
	
	@Column(name="cupon_amount")
	private long cupon_amount;

	@Column(name="copon_type", length=5)
	private String copon_type;
	
	@Column(name="status", length=5)
	private String status;
	
	@Column(name="start_date")
	private Date start_date;
	
	@Column(name="end_date")
	private Date end_date;

	

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
	
	/**
	 * 쿠폰 가격
	 * @return
	 */
	public long getCupon_amount() {
		return cupon_amount;
	}

	public void setCupon_amount(long cupon_amount) {
		this.cupon_amount = cupon_amount;
	}

	/**
	 * 쿠폰 타입
	 * @return
	 */
	public String getCopon_type() {
		return copon_type;
	}

	public void setCopon_type(String copon_type) {
		this.copon_type = copon_type;
	}

	/**
	 * 상태(쿠폰 사용 유무)
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 유효기간 시작일
	 * @return
	 */
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	/**
	 * 유효기간 종료일
	 * @return
	 */
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	
}
