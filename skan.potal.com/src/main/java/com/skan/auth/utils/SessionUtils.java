package com.skan.auth.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.skan.auth.bean.SKANSession;

/**
 * Description : 세션 유틸리티.
 * @author skan
 * @since 2016. 9. 20.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class SessionUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);
	
	/**
	 * 사용자 이메일 
	 * @return
	 */
	public static String getSessionUserEmail() {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SKANSession knkSession =  (SKANSession) authentication.getDetails();
			return knkSession.getUserDto().getEmail();
			
		} catch (Exception e) {
			logger.error("사용자 세션 이메일 불러오기 에러 = {}" , e);
			return null;
		}
		
		
	}
	
	
	public static SKANSession getSessionDetailObject(HttpSession httpSession) {
		SKANSession knkSession = null;
		try {
			SecurityContext securityContext = (SecurityContext)httpSession.getAttribute( "SPRING_SECURITY_CONTEXT" );
			Authentication authentication = securityContext.getAuthentication();
			knkSession =  (SKANSession) authentication.getDetails();
		} catch (Exception e) {
			logger.error("사용자 세션 불러오기 에러 = {}" , e);
		}
		
		return knkSession;
		
	}
	
	public static SKANSession getSessionDetailObject() {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SKANSession knkSession =  (SKANSession) authentication.getDetails();
			return knkSession;
		}catch (Exception e) {
			logger.error("사용자 세션 불러오기 에러 = {}" , e);
			return null;
		}
	}
	
	
	/**
	 * 접속된 사용자의 롤정보를 가져온다.
	 * 
	 * @return java.util.list
	 */
	public static List<SimpleGrantedAuthority> getGrantedAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		List<SimpleGrantedAuthority> authoritys = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
		return authoritys;
	}
	
	
}
