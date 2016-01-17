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
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.address.model.HmAddressInfo;
import com.skan.potal.web.potal.address.model.HmAddressInfoId;
import com.skan.potal.web.potal.address.model.HmAddressPhone;
import com.skan.potal.web.potal.address.model.HmAddressPhoneId;
import com.skan.potal.web.potal.address.model.HmEmailInfo;
import com.skan.potal.web.potal.address.model.HmEmailInfoId;
import com.skan.potal.web.potal.address.model.HmMngAddress;
import com.skan.potal.web.potal.address.repository.HmAddressInfoRepository;
import com.skan.potal.web.potal.address.repository.HmAddressPhoneRepository;
import com.skan.potal.web.potal.address.repository.HmEmailInfoRepository;
import com.skan.potal.web.potal.address.repository.HmMngAddressRepository;
import com.skan.potal.web.potal.common.code.ActionStateCode;
import com.skan.potal.web.potal.common.util.HibernateSessionFactory;
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
	@Autowired SessionFactory sessionFactory; 
	@Autowired HibernateSessionFactory hibernateSessionFactory;

	@RequestMapping("/address/address_list")
	public String addressList(@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchType,
			@RequestParam(required=false , defaultValue="") String searchName,
			HttpServletResponse response , ModelMap modelMap) throws Exception {
		
		Sort sort = new Sort(
				new org.springframework.data.domain.Sort.Order(Direction.ASC, "name") 
				//,new Order(Direction.DESC , "hmMgNum")
				);  
		Page<HmMngAddress> hmMngAddressPage ;
		if(StringUtils.isNotEmpty(searchName)) {
			hmMngAddressPage =  hmMngAddressRepository.findByNameContaining(searchName, new PageRequest(page, size, sort));
		} else {
			hmMngAddressPage =  hmMngAddressRepository.findAll(new PageRequest(page, size, sort));
		}
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
		modelMap.put("searchName", searchName);
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

		// 주소 상세정보
		Criteria criteria = hibernateSessionFactory.getSession().createCriteria(HmAddressInfo.class);
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmAddressInfoId.hmMngAddress.hmMgNum"));
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmAddressInfoId.hmAdNo"));
		criteria.setMaxResults(1);
		HmAddressInfo hmAddressInfoOld = (HmAddressInfo) criteria.uniqueResult();
		
		HmAddressInfoId HmAddressInfoId = new HmAddressInfoId(hmMngAddress);
		HmAddressInfoId.setHmAdNo(hmAddressInfoOld == null ? 1L : hmAddressInfoOld.getHmAddressInfoId().getHmAdNo()+1);
		hmAddressInfo.setHmAddressInfoId(HmAddressInfoId);
		hmAddressInfoRepository.save(hmAddressInfo);
		
		// 2. 이메일 정보
		criteria = hibernateSessionFactory.getSession().createCriteria(HmEmailInfo.class);
		criteria.add(Restrictions.eq("hmEmailInfoId.hmMngAddress.hmMgNum", hmMngAddress.getHmMgNum()));
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmEmailInfoId.hmMngAddress.hmMgNum"));
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmEmailInfoId.hmEmNo"));
		criteria.setMaxResults(1);
		HmEmailInfo hmEmailInfoOld = (HmEmailInfo) criteria.uniqueResult();
		
		HmEmailInfoId hmEmailInfoId = new HmEmailInfoId();
		hmEmailInfoId.setHmMngAddress(hmMngAddress);
		hmEmailInfoId.setHmEmNo(hmEmailInfoOld == null ? 1L : hmEmailInfoOld.getHmEmailInfoId().getHmEmNo() + 1L);
		hmEmailInfo.setHmEmailInfoId(hmEmailInfoId);
		
		hmEmailInfoRepository.save(hmEmailInfo);
		
		// 3. 휴대폰 정보
		criteria = hibernateSessionFactory.getSession().createCriteria(HmAddressPhone.class);
		criteria.add(Restrictions.eq("hmAddressPhoneId.hmMngAddress.hmMgNum", hmMngAddress.getHmMgNum()));
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmAddressPhoneId.hmMngAddress.hmMgNum"));
		criteria.addOrder(org.hibernate.criterion.Order.desc("hmAddressPhoneId.hmPhoNo"));
		criteria.setMaxResults(1);
		HmAddressPhone hmAddressPhoneOld = (HmAddressPhone) criteria.uniqueResult();
		
		HmAddressPhoneId hmAddressPhoneId = new HmAddressPhoneId();
		hmAddressPhoneId.setHmMngAddress(hmMngAddress);
		hmAddressPhoneId.setHmPhoNo(hmAddressPhoneOld == null ? 1L : hmAddressPhoneOld.getHmAddressPhoneId().getHmPhoNo() + 1L);
		
		hmAddressPhone.setHmAddressPhoneId(hmAddressPhoneId);
		hmAddressPhoneRepository.save(hmAddressPhone);
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
	
	@ModelAttribute
	public void paggingInfo(@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchType,
			@RequestParam(required=false , defaultValue="") String searchName,
			ModelMap modelMap) {
		// TODO
		
	}
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "ADDRESS");
	}
}
