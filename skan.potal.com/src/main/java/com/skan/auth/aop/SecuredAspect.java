package com.skan.auth.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.skan.auth.utils.RolesAllowedUtil;
import com.skan.potal.common.code.AuthorizationCode;
import com.skan.potal.web.jpa.repository.RoleJpaRepository;

/**
 * 
 * Description : @SKANSecured 가선언된 method 의 인가 허가 처리 
 * 					ENUM TYPE을 사용하기 위한 내역
 * 
 * 				 AOP 정리 문서 참고 :  https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte:fdl:aop:aspectj
 * 
 * @author skan
 * @since 2016. 11. 9.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Aspect
@Component
public class SecuredAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired RoleJpaRepository roleJpaRepository;
	
	private static final String ROLE_PRIFIX = "ROLE_";

	@Pointcut("@annotation(com.skan.auth.annotation.CustomizingSecured)")
	public void pointcut() {
		logger.info("pointcut INFO");
	}

	@Before(value = "pointcut()")
	public void beforTargetMethod(JoinPoint joinPoint) {
		logger.debug("beforTargetMethod INFO");
		Class<?> clazz = joinPoint.getTarget().getClass();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		logger.debug("클레스 정보 = {}", clazz);
		logger.trace("메소드 정보 = {}", className + "." + methodName + " executed.");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Arrays.stream(methodSignature.getMethod().getAnnotations()).forEach(
				item -> {
					logger.trace("annotation name = {}", item.getClass().getSimpleName());
				});
		
		// 접속된 사용자의 ROLE, PERMISSION 조회
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<AuthorizationCode> roleTypes = RolesAllowedUtil.getAuthorizationCodesAllowedFromAnnotations(methodSignature.getMethod().getAnnotations());
		boolean exceptionCheck = false;
        for (AuthorizationCode authorizationCode : roleTypes) {
        	
        	logger.debug("authorizationCode = {}" , authorizationCode);
        	//authentication.getAuthorities().forEach(item -> {logger.debug("접속한 사용자가 기진 ROLE = {} ",item.getAuthority());});
        	long count = authentication.getAuthorities().stream().filter(item -> 
        			item.getAuthority().equals(ROLE_PRIFIX+authorizationCode.toString())).count();
        	if(count < 1) {
        		exceptionCheck = true;
        	}
        	
        	// TODO 혹은 이곳에서 Permission 조회 후 인증 여부 판단한다.
        	
		}
        
        if(exceptionCheck){
        	throw new AccessDeniedException("권한이 없습니다.");
        }
	}
}
