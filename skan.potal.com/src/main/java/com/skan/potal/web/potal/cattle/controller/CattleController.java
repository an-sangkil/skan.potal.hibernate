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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.type.descriptor.java.CalendarDateTypeDescriptor;
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

import com.mysema.query.jpa.impl.JPAQuery;
import com.skan.potal.web.potal.cattle.model.HmCattleRegister;
import com.skan.potal.web.potal.cattle.model.QHmCattleBuyInfo;
import com.skan.potal.web.potal.cattle.model.QHmCattleChildbirthRecode;
import com.skan.potal.web.potal.cattle.model.QHmCattleRegister;
import com.skan.potal.web.potal.cattle.repository.CattleRepository;
import com.skan.potal.web.potal.common.util.CalendarUtils;
import com.skan.potal.web.potal.common.util.CalendarUtils.CalendarPattermn;
import com.skan.potal.web.potal.common.util.PageUtils;

/**
 * @author ahn
 *
 */
@Controller
public class CattleController {
	
	private final Logger logger = LoggerFactory.getLogger(CattleController.class);
	
	@Autowired private CattleRepository cattleRepository;
	@Autowired private EntityManager entityManager;
	
	@RequestMapping("cattle/cattle_list")
	public String cattleList(@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchEntityDiscernNo,
			@RequestParam(required=false , defaultValue="") String searchGender,
			@RequestParam(required=false , defaultValue="") String expectedDateConfinementFrom,
			@RequestParam(required=false , defaultValue="") String expectedDateConfinementTo,
			@RequestParam(required=false , defaultValue="") String searchBirthDayFrom,
			@RequestParam(required=false , defaultValue="") String searchBirthDayTo,
			ModelMap modelMap,
			HttpServletRequest request) throws Exception {
		
		Sort sort = new Sort(
				new org.springframework.data.domain.Sort.Order(Direction.ASC, "birthDay"),
				new Order(Direction.DESC , "entityDiscernNo")
				);
		
		QHmCattleRegister hmCattleRegister = QHmCattleRegister.hmCattleRegister;
		JPAQuery query = new JPAQuery(entityManager);
		query.from(hmCattleRegister);
		query.leftJoin(QHmCattleRegister.hmCattleRegister.hmCattleChildbirthRecodeSet, QHmCattleChildbirthRecode.hmCattleChildbirthRecode);
		//.leftJoin(QHmCattleRegister.hmCattleRegister.hmCattleBuyInfoSet, QHmCattleBuyInfo.hmCattleBuyInfo);
		
		{	
			// 개체 번호
			if(StringUtils.isNotEmpty(searchEntityDiscernNo)) {
				query.where(QHmCattleRegister.hmCattleRegister.entityDiscernNo.eq(searchEntityDiscernNo));
			}
			
			// 성별
			if(StringUtils.isNotEmpty(searchGender)) {
				query.where(QHmCattleRegister.hmCattleRegister.gender.eq(searchGender));
				//query.where(QHmCattleRegister.hmCattleRegister.gender.eq("황소").or(QHmCattleRegister.hmCattleRegister.gender.eq("암소")));
			}
			
			// 출생일
			if(StringUtils.isNotEmpty(searchBirthDayFrom) || StringUtils.isNotEmpty(searchBirthDayTo)) {
				query.where(QHmCattleRegister.hmCattleRegister.birthDay
						.between(
								CalendarUtils.convertStringToDate(searchBirthDayFrom, CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD), 
								CalendarUtils.convertStringToDate(searchBirthDayTo, CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD)) 
						);
			}
			
			// 분만 예정일
			if(StringUtils.isNotEmpty(expectedDateConfinementFrom) || StringUtils.isNotEmpty(expectedDateConfinementTo)) {
				query.where(QHmCattleChildbirthRecode.hmCattleChildbirthRecode.expectedDateConfinement
									.between(
											CalendarUtils.convertStringToDate(expectedDateConfinementFrom, CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD), 
											CalendarUtils.convertStringToDate(expectedDateConfinementTo, CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD)) 
									);
			}
		}
		
		Page<HmCattleRegister> hmCattlePage = cattleRepository.buildPage(query, query, new PageRequest(page, size, sort));
		
		PageUtils pageUtils = new PageUtils();
		int current = hmCattlePage.getNumber() + 1;
		int begin = pageUtils.pagingBegin(current);
	    int end = pageUtils.pagingEnd(hmCattlePage.getTotalPages());
	    
		logger.debug("pageInfo = " , hmCattlePage);

		modelMap.put( "current"      , current );
		modelMap.put( "begin"        , begin );
		modelMap.put( "end"          , end );
		modelMap.put( "hmCattlePage" , hmCattlePage );
		
		modelMap.put( "searchEntityDiscernNo" , searchEntityDiscernNo );
		modelMap.put( "searchGender"          , searchGender );
		modelMap.put( "searchBirthDayFrom"    , searchBirthDayFrom );
		modelMap.put( "searchBirthDayTo"      , searchBirthDayTo );
		
		modelMap.put( "searchGender" , searchGender );
		
		return "/cattle/cattle_list.tiles";
	}
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CATTLE");
	}
}
