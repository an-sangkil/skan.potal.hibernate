package com.skan.potal.common.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description : 
 * 				 1. WAS 에서 발생한   에러페이지에 대한 이동.  RESPONSE_STATUS_CODE 정보
 * 				 2.	ErrorConfig.java 참조
 * 				 
 * @author skan
 * @since 2016. 10. 10.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
public class CustomErrorController 
//implements org.springframework.boot.autoconfigure.web.ErrorController
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * 400=Bad Request.
	 * 401=Unauthorized
	 * 404=Not Found.
	 * 405=Method Not Allowed
	 * 500=Internal Server Error
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping("/errors/{code}")
	public ModelAndView errorPage(@PathVariable String code, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		logger.debug("errorPage = {}" , request);
		logger.debug("request.getRequestURI() = {}" , request.getRequestURI());
		
		mv.addObject("message", code);
		mv.setViewName("/common/error/error"+"_"+ code);
		return mv;
		
	}
}
