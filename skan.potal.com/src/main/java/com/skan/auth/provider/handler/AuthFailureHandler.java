package com.skan.auth.provider.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


/**
 * Description : 로그인 실패 사용자에 대한 처리  
 * @author skan
 * @since 2016. 9. 9.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		logger.error("사용자 로그인 실패 LOG = {} " , exception);
		
		String message = "아이디 또는 비밀번호를 다시 확인하세요. \n 아이언헤드에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.";
		
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		dispatcher.forward(request, response);
		
	}

}
