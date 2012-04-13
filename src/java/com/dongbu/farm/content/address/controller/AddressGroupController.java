/*
 * $Id: CodeController.java ,v 1.1 2011. 3. 25. 오후 4:42:15 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.controller;

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
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.content.address.service.IAddressGroupService;
import com.dongbu.farm.system.code.CodeException;

/**
 * 주소록 그룹관리
 * 일반사용자가 사용할수 있기때문에 content package 에 두도록 한다.
 * @author skan
 *
 */
@Controller
public class AddressGroupController extends RepositoryController{
	
	@Autowired
	private IAddressGroupService addressGroupServiceImpl;
	
	/**
	 * 주소 그룹 코드 상세보기
	 * @param request
	 * @param respons
	 * @return
	 * @throws CodeException
	 */
	@RequestMapping(value="/content/address/getAddressGroupDetailAJU.dongbu")
	public ModelAndView getAddressGroupDetailAJU(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		Map<String,String> searchMap = new HashMap<String,String>();
		try {
			
			String codeStr = ServletRequestUtils.getRequiredStringParameter(request, "code");

			
			searchMap.put("code", codeStr);
			
			AddressGroup code = this.addressGroupServiceImpl.getCodeDetail(searchMap);
			
			mav.addObject("code", code);
			
			mav.setViewName("contents/addressMag/_addressGroupDetailAjaxup");
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController getCodeDetail Error",e);
		}

		return mav;
	}
	
	/**
	 * 주소 그룹 코드 상세보기
	 * @param request
	 * @param respons
	 * @return
	 * @throws CodeException
	 */
	@RequestMapping(value="/content/address/getAddressGroupDetailAJR.dongbu")
	public void getAddressGroupDetailAJR(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String,String> searchMap = new HashMap<String,String>();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			String codeStr = ServletRequestUtils.getRequiredStringParameter(request, "code");

			
			searchMap.put("code", codeStr);
			
			AddressGroup code = this.addressGroupServiceImpl.getCodeDetail(searchMap);
			ajaxMap.put("code", code);
			
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController getCodeDetail Error",e);
		}
	}
	
	/**
	 * 주소 그룹 그룹 추가
	 * @param request
	 * @param response
	 * @throws CodeException
	 */
	@RequestMapping(value="/content/address/writeAddressGroupAJR.dongbu")
	public void writeAddressGroupAJR(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		AddressGroup code = new AddressGroup();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			bind(request, code);
			
			code.setCreator(SessionUtils.getUserID(request));
			code.setCreatedtime(new Date());
			this.addressGroupServiceImpl.writeCodeAJR(code);
			
			ajaxMap.put("code", code);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 주소 그룹 그룹수정
	 * @param request
	 * @param response
	 * @throws CodeException
	 */
	@RequestMapping(value="/content/address/modifyAddressGroupAJR.dongbu")
	public void modifyAddressGroupAJR(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		
		AddressGroup code = new AddressGroup();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			bind(request, code);
			
			code.setModifier(SessionUtils.getUserID(request));
			code.setModifiedtime(new Date());
			this.addressGroupServiceImpl.modifyCodeAJR(code);
			
			ajaxMap.put("code", code);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
