package com.skan.auth.provider.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

/**
 * Description : 권한없는 사용자가 접근시, 403 AccessDeinedException 이 발생할때  처리할 핸들러.
 *               AccessDeniedHandlerImpl을 직접 사용하여 현재 핸들러는 사용하지 않는다. 
 *               추후 변경하거나 커스텀시에 해당 핸들러를 수정하여 사용할것. 
 * @author skan
 * @since 2016. 9. 27.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		
		
	}

}
