/**
 * <pre>
 * Class Name  : CodeController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 1.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 1.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skan.potal.web.potal.application.model.CmtbCode;

/**
 * @author ahn
 *
 */
@Controller
public class CodeController {
	
	@RequestMapping("code/code_list")
	private String codeList(HttpServletRequest request, ModelMap modelMap) {
		
		return "";
	}
	
	
	/**
	 * Code 입력 폼
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("code/form")
	private String codeForm(HttpServletRequest request, ModelMap modelMap) {
		
		return "";
	}
	
	/**
	 * 코드 저장
	 * @param cmtbCode
	 * @param bindingResult
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("code/insert")
	private String saveCode(@Valid CmtbCode cmtbCode, BindingResult bindingResult, HttpServletRequest request, ModelMap modelMap) {
		
		return "";
	}
	
	/**
	 * 코드 정보 수정
	 * @param cmtbCode
	 * @param bindingResult
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("code/modify")
	private String modifyCode(@Valid CmtbCode cmtbCode, BindingResult bindingResult, HttpServletRequest request, ModelMap modelMap) {
		
		return "";
	}
}
