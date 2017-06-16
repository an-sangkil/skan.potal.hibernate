package com.skan.auth.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * Description :  스프링 시큐리티에서 커스텀으로 수행되는 컨트롤러, 로그인페이지 및 성공후 이동 페이지에 대한 내역이 담겨있다.
 * Modification Information
 *
 * @author skan
 * @since 2016. 9. 8.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
public class LoginController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 로그인 페이지 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String loginPage (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info(" TMS MOBILE GOGO LOGIN PAGE!!!!");
		
		return "login.login";
	}
	
	/**
	 * 로그인에 성공한 후 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("loginSuccess")
	public String loginPageSuccess (HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("Login Success");
		
		return "redirect:/admin/store/storeList";
	}
	
}
