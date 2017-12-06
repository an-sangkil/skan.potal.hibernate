package com.skan.potal.common.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description : 
 * 				컨드롤러에서 발생되는 에러에 대해서만 처리된다.  <b>WAS 영역은 별도</b>
 * 			
 * 				HTTP Status 403,404, 500 에러에 대한 정의
 * 				WARN, SimpleMappingExceptionResolver와 겹칠수 있으니 주의.
 *  
 * @author skan
 * @since 2016. 10. 7.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = {RuntimeException.class} )
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e){
		ModelAndView mv = new ModelAndView();
		logger.error("403 FORBIDDEN ", e);
		
		mv.addObject("ex", e);
		mv.setViewName("common/error/error_403");
		
		return mv;
	}
	
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public ModelAndView handleConflicrt(HttpServletRequest request, Exception e){
		
		ModelAndView mv = new ModelAndView();
		logger.error("INTERNAL_SERVER_ERROR",e);
		
		mv.addObject("ex", e);
		mv.setViewName("common/error/error_500");
		
		return mv;
	}
	
}
