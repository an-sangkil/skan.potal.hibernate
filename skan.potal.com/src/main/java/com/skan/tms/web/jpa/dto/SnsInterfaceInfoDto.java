/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : SnsInterfaceInfoDto.java
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
@Table(name="snsinterfaceinfo")
public class SnsInterfaceInfoDto {
	
	@EmbeddedId
	private SnsInterfaceInfoPK snsInterfaceInfoPK;
	
	
	@Column(name="service_provider", length=32)
	private String serviceProvider;	
	
	@Column(name="request_token", length=100)
	private String requestToken;		
	
	@Column(name="access_token", length=100)
	private String accessToken;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email" , insertable=false, updatable=false)
	private UserDto userDto;
	
	
	/**
	 * 서비스 제공자
	 * @param serviceProvider
	 */
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	/**
	 * 접근권한 인증을 위한 토큰값
	 * @param requestTokken
	 */
	public String getRequestTokken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	
	/**
	 * 서비스 자원 접근 토큰
	 * @param accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
