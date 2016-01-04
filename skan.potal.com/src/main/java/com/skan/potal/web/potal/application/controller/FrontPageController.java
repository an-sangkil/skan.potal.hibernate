/**
 * <pre>
 * Class Name  : FrontPageController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 2.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 2.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ahn
 *
 */
@Controller
public class FrontPageController {

	@RequestMapping("main_page")
	public String mainPage() throws Exception {
		
		
		
		return "home/main_page.tiles";
	}
	
}
