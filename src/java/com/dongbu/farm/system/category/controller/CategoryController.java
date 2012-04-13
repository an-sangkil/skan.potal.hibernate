/*
 * $Id: CategoryController.java ,v 1.1 2011. 3. 28. 오후 3:45:03 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.category.manager.ICategoryManager;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.model.Code;

@Controller
public class CategoryController extends RepositoryController{
	
	private static Logger loger = Logger.getLogger(CategoryController.class);
	
	@Resource(name="categoryManagerImpl")
	private ICategoryManager categoryManagerImpl;
	
	/**
	 * 공통 코드 전체 검색 Tree 전용
	 * @param request
	 * @param response      
	 * @return
	 */
	@RequestMapping(value="/system/code/getCodeList.system")
	public ModelAndView getCodeList(HttpServletRequest request, HttpServletResponse response) throws CodeException{
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, String> searchMap = new HashMap<String,String>();
		
		try {
			
			String node     = ServletRequestUtils.getRequiredStringParameter(request, "node");
			
			String codeType = node.substring(node.indexOf("_")+1 , node.length() );
			
			if(codeType.equals("upper")){
				searchMap.put("code", node.substring( 0, node.indexOf("_")));
				searchMap.put("codeType", "upper");
			}else{
				searchMap.put("code", node);
				searchMap.put("codeType","child");
			}
			
			
			List<Code> CodeList = this.categoryManagerImpl.getCodeList(searchMap);
			
			mav.addObject("CodeList", CodeList);
			mav.setViewName("system/code/codeTreeNodes");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController ErrorException ", e);
		}
		
		return mav;
	}
	
	/**
	 * 주소록 코드관리 리스트  -- 
	 * @param request
	 * @param response
	 * @return
	 * @throws CodeException
	 */
	@RequestMapping(value="/system/code/{viewPage}/getAddressGroupCodeList.system")
	public ModelAndView getAddressGroupCodeList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String viewPage) throws CodeException{
		
		loger.info("\n\n\n RequestMapping ::: /system/code/{" + viewPage + "}/getAddressGroupCodeList.system  \n\n\n");
		
		ModelAndView mav = new ModelAndView();
			
		Map<String, String> searchMap = new HashMap<String,String>();
		
		try {
			
			String node     = ServletRequestUtils.getRequiredStringParameter(request, "node");
			
			String codeType = node.substring(node.indexOf("_")+1 , node.length() );
			
			if(codeType.equals("upper")){
				searchMap.put("code", node.substring( 0, node.indexOf("_")));
				searchMap.put("codeType", "upper");
			}else{
				searchMap.put("code", node);
				searchMap.put("codeType","child");
			}
			
			
			List<AddressGroup> addressGroupList = this.categoryManagerImpl.getAddressGroupCodeList(searchMap);
			List<Address> addressList = this.categoryManagerImpl.getAddressList(searchMap);
			
			
			mav.addObject("addressGroupList", addressGroupList);
			mav.addObject("addressList", addressList);
			
			mav.setViewName("contents/addressGroupManagement/"+viewPage);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CodeException("CodeController getAddressGroupCodeList() method ErrorException ", e);
		}
		
		return mav;
	}
	
}
