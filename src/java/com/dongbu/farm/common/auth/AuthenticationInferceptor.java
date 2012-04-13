/*
 * $Id: AuthenticationInferceptor.java ,v 1.1 2011. 3. 30. 오후 6:34:48 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 30.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dongbu.farm.login.model.Member;

public class AuthenticationInferceptor extends HandlerInterceptorAdapter {
	
	private static Log logger = LogFactory.getLog(AuthenticationInferceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 끝난뒤 
		// 클라이언트의 요청을 처리한 뒤에 실행
		// 컨트롤러 처리도중이나 view 생성과정 중에 예외가 발생하더라도 실행된다.
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// view (jsp)page 로 forward 되기전에 실행.
		// 컨트롤러 실행도중 예외 발생시 실행되지 않음
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// controller 실행 요청전
		
		HttpSession session = arg0.getSession();
		
		Member member = (Member)session.getAttribute("member");
		
		if(member == null){
			logger.info("Authentification failed!!!");
			//정상적인 세션이 아닌 것으로 간주하여 리퀘스트는 종료된다.
			
			//디스페쳐로 익셥션을 전달하며, 어느 URL로 뷰를 만들지 정의해 준다
			String url = "redirect:/error/error-authentification-failed.jsp";
			throw new ModelAndViewDefiningException(new ModelAndView(url));
			
		}
		
		
		return true;
	}

}
