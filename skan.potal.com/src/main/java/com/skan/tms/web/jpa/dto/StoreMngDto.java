/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <pre>
 * Class Name  : StoreMngDto.java
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
@Table(name="storemng")
@JsonIgnoreProperties(ignoreUnknown=true)

public class StoreMngDto {
	
	public StoreMngDto() {}
	
	public StoreMngDto(String storeId, String storeName) {
		super();
		this.storeId = storeId; 
		this.storeName = storeName;
	}

	public StoreMngDto(String storeId, String storeName, String storeManagerName, String businessManagerName) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeManagerName = storeManagerName;
		this.businessManagerName = businessManagerName;  
	}
	
	/**
	 * 스토어 아아이디
	 */
	@Id
	@Column(name="store_id", length=52)
	private String storeId;
	
	/**
	 * 스토어 이름
	 */
	@Column(name="store_name", length=64)
	@NotEmpty(message="스토어어 이름을 입력 하세요.")
	@Size(max=64)
	private String storeName = null;
	
	/**
	 * 메니저 아이디
	 */
	@Column(name="store_manager_id", length=32, nullable=true)
	private String storeManagerId;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.DETACH)
	@JoinColumn(name="store_manager_id",insertable=false, updatable=false,nullable=true)
	private UserDto storeManagerInfo;
	
	/**
	 * 매니저 이름
	 */
	@Transient
	private String storeManagerName;
	
	/**
	 * 
	 */
	@Column(name="business_manager_id", length=32)
	private String businessManagerId;
	
	/**
	 * 영업담당자 이름
	 */
	@Transient
	private String businessManagerName;
	
	
	public String getBusinessManagerId() {
		return businessManagerId;
	}

	public void setBusinessManagerId(String businessManagerId) {
		this.businessManagerId = businessManagerId;
	}
	
	public UserDto getStoreManagerInfo() {
		return storeManagerInfo;
	}

	public void setStoreManagerInfo(UserDto storeManagerInfo) {
		this.storeManagerInfo = storeManagerInfo;
	}
	
	public String getBusinessManagerName() {
		return businessManagerName;
	}

	public void setBusinessManagerName(String businessManagerName) {
		this.businessManagerName = businessManagerName;
	}

	public String getStoreManagerName() {
		return storeManagerName;
	}

	public void setStoreManagerName(String storeManagerName) {
		this.storeManagerName = storeManagerName;
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
	 * 가맹점 이름
	 * @return
	 */
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String store_name) {
		this.storeName = store_name;
	}

	/**
	 * 가맹점 담당자 아이디
	 * @return
	 */
	public String getStoreManagerId() {
		return storeManagerId;
		
	}

	public void setStoreManagerId(String store_manager_id) {
		this.storeManagerId = store_manager_id;
	}
	

}
