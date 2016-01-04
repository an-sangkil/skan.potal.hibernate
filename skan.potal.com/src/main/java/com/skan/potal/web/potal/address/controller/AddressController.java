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

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.address.model.HmMngAddress;
import com.skan.potal.web.potal.address.repository.HmMngAddressRepository;

/**
 * @author ahn
 *
 */
@Controller
public class AddressController {
	
	private final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired HmMngAddressRepository hmMngAddressRepository;
	
	@RequestMapping("/address/address_list")
	public String addressList(@RequestParam Integer page, 
			@RequestParam Integer size,
			@RequestParam Direction direction ,HttpServletResponse response , ModelMap modelMap) throws Exception {
		
		
		Page<HmMngAddress> hmMngAddressPage =  hmMngAddressRepository.findAll(new PageRequest(1, 10, direction , "hmMgNum"));
		logger.debug("pageInfo = " , hmMngAddressPage);
		
		modelMap.put("hmMngAddressPage", hmMngAddressPage);
		modelMap.addAttribute("returnName", "hahahaha~");
		
		return "";
	}
	
}
