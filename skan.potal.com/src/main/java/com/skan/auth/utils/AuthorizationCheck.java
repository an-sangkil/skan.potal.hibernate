package com.skan.auth.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.skan.tms.mobile.common.code.AuthorizationCode;
import com.skan.tms.web.jpa.dto.UserDto;

/**
 * Description : 접속 허용 (인가 체크)  
 * @author skan
 * @since 2016. 9. 20.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class AuthorizationCheck {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationCheck.class);
	
	private static final String ROLE_ = "ROLE_";
	/**
	 * 관리자 여부 확인 
	 * @return true or false boolean value
	 */
	public static boolean isAdmin (UserDto userDto) {
		try {
			if(userDto.isAdmin()) {
				return true;
			}
		} catch (Exception e) {
			logger.error("admincheck error", e);
		}
		return false;
	}
	
	/**
	 * 시스템 담당자 체크 
	 * 시스템담당자가 맞는 경유 TRUE RETURN;
	 * 
	 * @return  
	 */
	public static boolean isSystemManager () {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			@SuppressWarnings("unchecked")
			List<SimpleGrantedAuthority> authoritys = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
			
			if(!authoritys.isEmpty()) {
				long count = authoritys.stream().filter(p-> p.getAuthority().equals(ROLE_ + AuthorizationCode.SYSTEM_MANAGER.name())).count();
				if (count != 0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			logger.error("isSystemManager check error", e);
		}
		return false;
	}
	
	/**
	 * 영업 담당자
	 * @return
	 */
	public static boolean isBusinessManager () {
		return false;
	}
	
	
	/**
	 * 스토어 메니저
	 * @return
	 */
	public static boolean isStoreManager () {
		return false;
	}
	
	
	/**
	 * 샵 메니저
	 * @return
	 */
	public static boolean isShopManager () {
		return false;
	}
	
	
	
}
