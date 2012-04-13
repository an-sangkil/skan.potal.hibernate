/*
 * $Id: CodeController.java ,v 1.1 2011. 3. 25. 오후 4:42:15 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.ajax.AjaxUtil;
import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.common.utils.SessionUtils;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.manager.ICodeManager;
import com.dongbu.farm.system.code.model.Code;

/**
 * 
 * @author skan
 *
 */
@Controller
public class CodeController extends RepositoryController{
	
	@Autowired
	private ICodeManager codeManagerImpl;
	
	/**
	 * 코드 상세보기
	 * @param request
	 * @param respons
	 * @return
	 * @throws CodeException
	 */
	@RequestMapping(value="/system/code/getCodeDetailAJU.system")
	public ModelAndView getCodeDetailAJU(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		ModelAndView mav = new ModelAndView();
		Map<String,String> searchMap = new HashMap<String,String>();
		try {
			
			String codeStr = ServletRequestUtils.getRequiredStringParameter(request, "code");

			
			searchMap.put("code", codeStr);
			
			Code code = this.codeManagerImpl.getCodeDetail(searchMap);
			
			mav.addObject("code", code);
			
			mav.setViewName("system/code/_codeDetailAjaxup");
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController getCodeDetail Error",e);
		}

		return mav;
	}
	
	/**
	 * 코드 상세보기
	 * @param request
	 * @param respons
	 * @return
	 * @throws CodeException
	 */
	@RequestMapping(value="/system/code/getCodeDetailAJR.system")
	public void getCodeDetailAJR(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		
		Map<String,String> searchMap = new HashMap<String,String>();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			String codeStr = ServletRequestUtils.getRequiredStringParameter(request, "code");

			
			searchMap.put("code", codeStr);
			
			Code code = this.codeManagerImpl.getCodeDetail(searchMap);
			ajaxMap.put("code", code);
			
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController getCodeDetail Error",e);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws CodeException
	 */
	@RequestMapping(value="/system/code/writeCodeAJR.system")
	public void writeCodeAJR(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		
		Code code = new Code();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			bind(request, code);
			
			code.setCreator(SessionUtils.getUserID(request));
			code.setCreatedtime(new Date());
			this.codeManagerImpl.writeCodeAJR(code);
			
			ajaxMap.put("code", code);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws CodeException
	 */
	@RequestMapping(value="/system/code/modifyCodeAJR.system")
	public void modifyCodeAJR(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		
		Code code = new Code();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			bind(request, code);
			
			code.setModifier(SessionUtils.getUserID(request));
			code.setModifiedtime(new Date());
			this.codeManagerImpl.modifyCodeAJR(code);
			
			ajaxMap.put("code", code);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
