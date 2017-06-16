package com.skan.tms.mobile.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Description : 
 * 				 Controller 에서 발생되는 공통으로 사용하는 Error 헨들러.
 * 				 에러가 발생하면 이곳에서 버블링되어 해당 내역을 처리 하도록 한다.
 * 				 
 * @author skan
 * @since 2016. 9. 12.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class CustomHandlerExceptionResolver extends SimpleMappingExceptionResolver {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public CustomHandlerExceptionResolver() {
        // Enable logging by providing the name of the logger to use
        setWarnLogCategory(CustomHandlerExceptionResolver.class.getName());
    }

	@Override
	public String buildLogMessage(Exception e, HttpServletRequest req) {
		return "MVC exception: " + e.getLocalizedMessage();
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		// Call super method to get the ModelAndView
		ModelAndView mav = super.doResolveException(request, response, handler, exception);

		//error
		
		logger.error(handler.getClass().getName()  , exception); 
		
		
		// Make the full URL available to the view - note ModelAndView uses
		// addObject()
		// but Model uses addAttribute(). They work the same.
		mav.addObject("url", request.getRequestURL());
		return mav;
	}
}
