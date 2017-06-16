package com.skan.auth.bean;

import java.util.List;

import com.skan.tms.web.jpa.dto.UserDto;

/**
 * 
 * <pre>
 * Class Name  : SKANSession.java
 * Description : 세션 정보  
 * </pre>
 *
 * @author skan
 * @since 2016. 9. 5.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class SKANSession {
	
	private UserDto userDto;
	private String remoteAddress;
	/**
	 * 롤정보
	 */
	private List<String> roles;
	/**
	 * 퍼미션 정보
	 */
	private List<String> permissions;

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
}
