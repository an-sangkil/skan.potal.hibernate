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
 * Class Name  : ReservationDto.java
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
public class ReservationDto {
	
	@Id
	@Column(name="uid", length=50)
	private String uid;	
	
	@Column(name="email", length=32)
	private String email;
	
	@Column(name="shop_id", length=52)
	private String shop_id;
	
	@Column(name="seat_number", length=3)
	private String seat_number;
	
	@Column(name="book_number", length=52)
	private String book_number;

	@Column(name="start_time")
	private Date start_time;
	
	@Column(name="end_time")
	private Date end_time;
	
	@Column(name="status", length=5)
	private String status;
	
	@Column(name="pay_amount")
	private long pay_amount;
	
	@Column(name="reservation_date")
	private Date reservation_date;
	
	@Column(name="creation_time")
	private Date creation_time;
	
	/**
	 * 기본 PK(예약번호)
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

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
	 * 좌석번호
	 * @return
	 */
	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}

	/**
	 * 예약번호
	 * @return
	 */
	public String getBook_number() {
		return book_number;
	}

	public void setBook_number(String book_number) {
		this.book_number = book_number;
	}

	/**
	 * 시작시간
	 * @return
	 */
	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * 종료시간
	 * @return
	 */
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * 예매상태
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 예약 금액
	 * @return
	 */
	public long getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(long pay_amount) {
		this.pay_amount = pay_amount;
	}

	/**
	 * 예약날짜
	 * @return
	 */
	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
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
