/**
 * <pre>
 * Class Name  : CattleController.java
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
package com.skan.potal.web.potal.cattle.controller;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.cattle.model.HmCattleRegister;
import com.skan.potal.web.potal.cattle.repository.CattleRepository;
import com.skan.potal.web.potal.common.util.PageUtils;

/**
 * @author ahn
 *
 */
@Controller
public class CattleController {
	
	private final Logger logger = LoggerFactory.getLogger(CattleController.class);
	
	@Autowired private CattleRepository cattleRepository;
	
	@RequestMapping("cattle/cattle_list")
	public String cattleList(@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchType,
			@RequestParam(required=false , defaultValue="") String searchName,
			ModelMap modelMap,
			HttpServletRequest request) throws Exception {
		
		Sort sort = new Sort(
				new org.springframework.data.domain.Sort.Order(Direction.ASC, "birthDay"),
				new Order(Direction.DESC , "entityDiscernNo")
				);
		
		Page<HmCattleRegister> hmCattlePage = cattleRepository.findAll(new PageRequest(page, size, sort));
		
		
		PageUtils pageUtils = new PageUtils();
		int current = hmCattlePage.getNumber() + 1;
		//int begin = Math.max(1, current - 5);
		int begin = pageUtils.pagingBegin(current);
	    int end = pageUtils.pagingEnd(hmCattlePage.getTotalPages());
	    
		logger.debug("pageInfo = " , hmCattlePage);
		
		modelMap.put("current", current);
		modelMap.put("begin", begin);
		modelMap.put("end", end);
		modelMap.put("searchName", searchName);
		modelMap.put("hmCattlePage", hmCattlePage);
		
		return "/cattle/cattle_list.tiles";
	}
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CATTLE");
	}
}
