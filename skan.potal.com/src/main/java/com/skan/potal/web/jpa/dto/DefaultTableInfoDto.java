/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name  : DefaultTableInfoDto.java
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
public class DefaultTableInfoDto {
	
	@Id
	@Column(name="shop_id", length=52)
	private String shop_id;

	@Column(name="seat_number", length=3)
	private String seat_number;

	@Column(name="pepple_count")
	private int pepple_count;

	@Column(name="floor")
	private int floor;

	

	@Column(name="use_able", length=1)
	private String use_able;
	

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
	 * 인원수
	 * @return
	 */
	public int getPepple_count() {
		return pepple_count;
	}

	public void setPepple_count(int pepple_count) {
		this.pepple_count = pepple_count;
	}

	/**
	 * 층수
	 * @return
	 */
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * 황성화:y비활성화:n
	 * @return
	 */
	public String getUse_able() {
		return use_able;
	}

	public void setUse_able(String use_able) {
		this.use_able = use_able;
	}
}
