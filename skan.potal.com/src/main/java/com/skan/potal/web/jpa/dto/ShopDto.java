/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * <pre>
 * Class Name  : ShopDto.java
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
public class ShopDto {
	
	public ShopDto() {}

	public ShopDto(String shopId, String shopName, String shopManagerName, String businessManagerName) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopManagerName = shopManagerName;
		this.businessManagerName = businessManagerName;
	}
	
	
	@Id
	@Column(name="shop_id", length=52)
	private String shopId;
	
	@Column(name="store_id", length=52)
	private String storeId;
	
	@Column(name="shop_name", length=64)
	private String shopName;
	
	@Column(name="shop_address", length=256)
	private String shopAddress;
	
	@Column(name="shop_phone_number", length=128)
	private String shopPhoneNumber;
	
	@Column(name="shop_manager_id", length=32)
	private String shopManagerId;
	
	@Column(name="creation_time")
	private Date creationTime;
	
	@Column(name="category_code", length=5)
	private String categoryCode;
	
	@Column(name="over_time")
	private long overTime;
	
	@Column(name="min_time")
	private long minTime;
	
	@Column(name="max_time")
	private long maxTime;
	
	@Column(name="creator", length=32)
	private String creator;
	
	@Column(name="modified_time")
	private Date modifiedTime;
	
	@Column(name="modified", length=32)
	private String modified;
	
	/**
	 * 매니저 이름
	 */
	@Transient
	private String shopManagerName;
	
	
	/**
	 * 영업담당자 아이디
	 */
	private String businessManagerId;
	
	/**
	 * 영업담당자 이름
	 */
	@Transient
	private String businessManagerName;
	

	
	/**
	 * 상점(가게)아이디
	 * @return
	 */
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shop_id) {
		this.shopId = shop_id;
	}

	/**
	 * 가맹점 아이디
	 * @return
	 */
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String store_id) {
		this.storeId = store_id;
	}

	/**
	 * 상점 이름
	 * @return
	 */
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shop_name) {
		this.shopName = shop_name;
	}

	/**
	 * 상점 위치
	 * @return
	 */
	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shop_address) {
		this.shopAddress = shop_address;
	}

	/**
	 * 상점 전화번호
	 * @return
	 */
	public String getShopPhoneNumber() {
		return shopPhoneNumber;
	}

	public void setShopPhoneNumber(String shop_phone_number) {
		this.shopPhoneNumber = shop_phone_number;
	}

	/**
	 * 샵 매니저 아이디
	 * @return
	 */
	public String getShopManagerId() {
		return shopManagerId;
	}

	public void setShopManagerId(String shop_manager_id) {
		this.shopManagerId = shop_manager_id;
	}

	/**
	 * 등록시간
	 * @return
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creation_time) {
		this.creationTime = creation_time;
	}

	/**
	 * 카테고리코드
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String category_code) {
		this.categoryCode = category_code;
	}

	/**
	 * 오버타임
	 * @return
	 */
	public long getOverTime() {
		return overTime;
	}

	public void setOverTime(long over_time) {
		this.overTime = over_time;
	}

	/**
	 * 테이블 최소 점유 시간
	 * @return
	 */
	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long min_time) {
		this.minTime = min_time;
	}

	/**
	 * 테이블 최대 점유 시간
	 * @return
	 */
	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long max_time) {
		this.maxTime = max_time;
	}

	/**
	 * 등록자
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 수정시간
	 * @return
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modified_time) {
		this.modifiedTime = modified_time;
	}

	/**
	 * 수정자
	 * @return
	 */
	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public String getShopManagerName() {
		return shopManagerName;
	}

	public void setShopManagerName(String shopManagerName) {
		this.shopManagerName = shopManagerName;
	}
	
	public String getBusinessManagerName() {
		return businessManagerName;
	}

	public void setBusinessManagerName(String businessManagerName) {
		this.businessManagerName = businessManagerName;
	}

	public String getBusinessManagerId() {
		return businessManagerId;
	}

	public void setBusinessManagerId(String businessManagerId) {
		this.businessManagerId = businessManagerId;
	}

	
}
