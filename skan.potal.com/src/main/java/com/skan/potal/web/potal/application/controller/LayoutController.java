/**
 * <pre>
 * Class Name  : LayoutController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 11.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 11.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ahn
 *
 */
@Controller
public class LayoutController {
	
	private final Logger logger = LoggerFactory.getLogger(LayoutController.class);
	
	@RequestMapping("layout/header_page")
	public String layout(HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		String menuID = ServletRequestUtils.getStringParameter(request, "menuCode" , "");
		logger.debug("menuId = {} " , menuID);
		
		modelMap.put("MENU_CODE", "ADDRESS");
		
		return "/layout/user/header";
	}
}
