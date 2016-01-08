/**
 * <pre>
 * Class Name  : AddressController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 4.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 4.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.address.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.address.model.HmAddressInfo;
import com.skan.potal.web.potal.address.model.HmAddressPhone;
import com.skan.potal.web.potal.address.model.HmEmailInfo;
import com.skan.potal.web.potal.address.model.HmMngAddress;
import com.skan.potal.web.potal.address.repository.HmAddressInfoRepository;
import com.skan.potal.web.potal.address.repository.HmAddressPhoneRepository;
import com.skan.potal.web.potal.address.repository.HmEmailInfoRepository;
import com.skan.potal.web.potal.address.repository.HmMngAddressRepository;
import com.skan.potal.web.potal.common.code.ActionStateCode;
import com.skan.potal.web.potal.common.util.PageUtils;

/**
 * @author ahn
 *
 */
@Controller
public class AddressController {
	
	private final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired HmMngAddressRepository hmMngAddressRepository;
	@Autowired HmEmailInfoRepository hmEmailInfoRepository;
	@Autowired HmAddressPhoneRepository hmAddressPhoneRepository; 
	@Autowired HmAddressInfoRepository hmAddressInfoRepository;

	@RequestMapping("/address/address_list")
	public String addressList(@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction ,HttpServletResponse response , ModelMap modelMap) throws Exception {
		
		Sort sort = new Sort(
				//new org.springframework.data.domain.Sort.Order(Direction.ASC, "name"), 
				new Order(Direction.DESC , "hmMgNum")
				);  
		
		Page<HmMngAddress> hmMngAddressPage =  hmMngAddressRepository.findAll(new PageRequest(page, size, sort));
		hmMngAddressPage.getTotalPages();
		
		PageUtils pageUtils = new PageUtils();
		int current = hmMngAddressPage.getNumber() + 1;
		//int begin = Math.max(1, current - 5);
		int begin = pageUtils.pagingBegin(current);
	    int end = pageUtils.pagingEnd(hmMngAddressPage.getTotalPages());
	    
		logger.debug("pageInfo = " , hmMngAddressPage);
		
		modelMap.put("current", current);
		modelMap.put("begin", begin);
		modelMap.put("end", end);
		modelMap.put("hmMngAddressPage", hmMngAddressPage);
		
		return "/address/address_list.tiles";
	}
	
	@RequestMapping("/address/address_insert")
	public String addressInsert(@Valid HmMngAddress hmMngAddress,
			BindingResult bindingResult1,
			@Valid HmEmailInfo hmEmailInfo,
			BindingResult bindingResult2,
			@Valid HmAddressPhone hmAddressPhone,
			BindingResult bindingResult3,
			@Valid HmAddressInfo hmAddressInfo,
			BindingResult bindingResult4,
			HttpServletResponse response , ModelMap modelMap) throws Exception {
		
		logger.debug("test address insert");
		if(bindingResult1.hasErrors()) {
			modelMap.put(ActionStateCode.MESSAGE.name(), ActionStateCode.FAIL);
			return "/address/address_form.tiles";
		}
		if(bindingResult2.hasErrors()) {
			modelMap.put(ActionStateCode.MESSAGE.name(), ActionStateCode.FAIL);
			return "/address/address_form.tiles";
		}
		if(bindingResult3.hasErrors()) {
			modelMap.put(ActionStateCode.MESSAGE.name(), ActionStateCode.FAIL);
			return "/address/address_form.tiles";
		}
		if(bindingResult4.hasErrors()) {
			modelMap.put(ActionStateCode.MESSAGE.name(), ActionStateCode.FAIL);
			return "/address/address_form.tiles";
		}
		
		// 1. 주소 기본정보
		hmMngAddressRepository.save(hmMngAddress);
		//    주소 상세정보
//		hmAddressInfo.setHmMngAddress(hmMngAddress);
//		hmAddressInfoRepository.save(hmAddressInfo);
//		
//		// 2. 이메일 정보 
//		hmEmailInfo.setHmMngAddress(hmMngAddress);
//		hmEmailInfoRepository.save(hmEmailInfo);
//		
//		// 3. 휴대폰 정보
//		hmAddressPhone.setHmMngAddress(hmMngAddress);
//		hmAddressPhoneRepository.save(hmAddressPhone);
		modelMap.put(ActionStateCode.MESSAGE.name(), ActionStateCode.SUCCESS);
		
		return "redirect:/address/address_list";
	}
	
	@RequestMapping("/address/address_form")
	public String addressForm(
			@RequestParam(required=false , defaultValue="") String hmMgNum,
			HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		if( StringUtils.isNotEmpty(hmMgNum)) {
			HmMngAddress hmMngAddress =  hmMngAddressRepository.findOne(org.apache.commons.lang.math.NumberUtils .createLong(hmMgNum) );
			
			modelMap.put("hmMngAddress", hmMngAddress);
		}
		
		
		return "/address/address_form.tiles";
	}
}
