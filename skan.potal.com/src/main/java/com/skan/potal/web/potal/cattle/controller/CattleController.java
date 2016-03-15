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

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysema.query.jpa.impl.JPAQuery;
import com.skan.potal.web.potal.cattle.dto.HmCattleBuyInfo;
import com.skan.potal.web.potal.cattle.dto.HmCattleCalfRecode;
import com.skan.potal.web.potal.cattle.dto.HmCattleCalfRecodeId;
import com.skan.potal.web.potal.cattle.dto.HmCattleChildbirthRecode;
import com.skan.potal.web.potal.cattle.dto.HmCattleChildbirthRecodeId;
import com.skan.potal.web.potal.cattle.dto.HmCattleCureInfo;
import com.skan.potal.web.potal.cattle.dto.HmCattleCureInfoId;
import com.skan.potal.web.potal.cattle.dto.HmCattleRegister;
import com.skan.potal.web.potal.cattle.dto.HmCattleSellStoreInfo;
import com.skan.potal.web.potal.cattle.dto.QHmCattleCalfRecode;
import com.skan.potal.web.potal.cattle.dto.QHmCattleChildbirthRecode;
import com.skan.potal.web.potal.cattle.dto.QHmCattleCureInfo;
import com.skan.potal.web.potal.cattle.dto.QHmCattleRegister;
import com.skan.potal.web.potal.cattle.repository.CattleBuyInfoRepository;
import com.skan.potal.web.potal.cattle.repository.CattleCalfRecodeRepository;
import com.skan.potal.web.potal.cattle.repository.CattleChildbirthRecodeRepository;
import com.skan.potal.web.potal.cattle.repository.CattleCureInfoRepository;
import com.skan.potal.web.potal.cattle.repository.CattleRegisterRepository;
import com.skan.potal.web.potal.cattle.repository.CattleSellStoreInfoRepository;
import com.skan.potal.web.potal.common.util.CalendarUtils;
import com.skan.potal.web.potal.common.util.CalendarUtils.CalendarPattermn;
import com.skan.potal.web.potal.common.util.PageUtils;

/**
 * 개체 기록 관리
 * @author ahn
 *
 */
@Controller
public class CattleController {
	
	private final Logger logger = LoggerFactory.getLogger(CattleController.class);
	
	@Autowired CattleRegisterRepository 		cattleRegisterRepository;  
	@Autowired CattleBuyInfoRepository 			cattleBuyInfoRepository; 
	@Autowired CattleCalfRecodeRepository 		cattleCalfRecodeRepository; 
	@Autowired CattleChildbirthRecodeRepository cattleChildbirthRecodeRepository; 
	@Autowired CattleCureInfoRepository 		cattleCureInfoRepository; 
	@Autowired CattleSellStoreInfoRepository 	cattleSellStoreInfoRepository;
	
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
		
		Page<HmCattleRegister> hmCattlePage = cattleRegisterRepository.buildPage(query, query, new PageRequest(page, size, sort));
		
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
	
	/**
	 * 개체 관리 저장 
	 * @param hmCattleRegister
	 * @param bindingResult1
	 * @param hmCattleBuyInfo
	 * @param bindingResult2
	 * @param hmCattleCalfRecode
	 * @param bindingResult3
	 * @param hmCattleChildbirthRecode
	 * @param bindingResult4
	 * @param hmCattleCureInfo
	 * @param bindingResult5
	 * @param hmCattleSellStoreInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cattle/cattle_form") 
	public String cattleForm(@Valid HmCattleRegister hmCattleRegister , BindingResult bindingResult1  
			,@Valid HmCattleBuyInfo hmCattleBuyInfo , BindingResult bindingResult2
			,@Valid HmCattleCalfRecode hmCattleCalfRecode , BindingResult bindingResult3
			,@Valid HmCattleChildbirthRecode hmCattleChildbirthRecode , BindingResult bindingResult4
			,@Valid HmCattleCureInfo hmCattleCureInfo , BindingResult bindingResult5
			,@Valid HmCattleSellStoreInfo hmCattleSellStoreInfo, BindingResult bindingResult6) throws Exception { 
		
		//1. 기본정보 
		cattleRegisterRepository.save(hmCattleRegister);
		
		if(StringUtils.isNotEmpty(hmCattleBuyInfo.getBuyStoreName())){

			
			if(bindingResult2.hasErrors()){
				return "";
			}
			//2. 구입정보 
			cattleBuyInfoRepository.save(hmCattleBuyInfo);
		}
		
		
		// 3 ~ 5 번 데이터 삭제후 등록 
		//3. 송아지 기록
		QHmCattleCalfRecode qhmCattleCalfRecode = QHmCattleCalfRecode.hmCattleCalfRecode;
		JPAQuery query = new JPAQuery(entityManager);
		Long thno = query.from(qhmCattleCalfRecode)
				.where(QHmCattleCalfRecode.hmCattleCalfRecode.hmCattleCalfRecodeId.hmCattleRegister.entityDiscernNo.eq(hmCattleRegister.getEntityDiscernNo()) )
				.singleResult(QHmCattleCalfRecode.hmCattleCalfRecode.hmCattleCalfRecodeId.thNo.max());
		
		HmCattleCalfRecodeId hmCattleCalfRecodeId = new HmCattleCalfRecodeId();
		// TODO : BUG 수정 or 추가 ? 멀티 저장 없이 한번만 저장함. 이후 수정 필요
		hmCattleCalfRecodeId.setThNo(thno == null ? 1L : thno);
		hmCattleCalfRecodeId.setHmCattleRegister(hmCattleRegister);
		hmCattleCalfRecode.setHmCattleCalfRecodeId(hmCattleCalfRecodeId);
		cattleCalfRecodeRepository.save(hmCattleCalfRecode);
		
		//4. 분만기록
		query = new JPAQuery(entityManager);
		Long thno2 = query.from(QHmCattleChildbirthRecode.hmCattleChildbirthRecode)
		.where(QHmCattleChildbirthRecode.hmCattleChildbirthRecode.hmCattleChildbirthRecodeId.hmCattleRegister.entityDiscernNo.eq(hmCattleRegister.getEntityDiscernNo()))
		.singleResult(QHmCattleChildbirthRecode.hmCattleChildbirthRecode.hmCattleChildbirthRecodeId.thNo.max());
		
		HmCattleChildbirthRecodeId hmCattleChildbirthRecodeId = new HmCattleChildbirthRecodeId();
		hmCattleChildbirthRecodeId.setHmCattleRegister(hmCattleRegister);
		// TODO : BUG 수정 or 추가 ? 멀티 저장 없이 한번만 저장함. 이후 수정 필요
		hmCattleChildbirthRecodeId.setThNo(thno2 == null ? 1L : thno2);
		hmCattleChildbirthRecode.setHmCattleChildbirthRecodeId(hmCattleChildbirthRecodeId);
		cattleChildbirthRecodeRepository.save(hmCattleChildbirthRecode);
		
		//5. 질병치료 기록 
		if(StringUtils.isNotEmpty(hmCattleCureInfo.getDiseaseName())){
			query = new JPAQuery(entityManager);
			Long thno3 = query.from(QHmCattleCureInfo.hmCattleCureInfo)
			.where( QHmCattleCureInfo.hmCattleCureInfo.hmCattleCureInfoId.hmCattleRegister.entityDiscernNo.eq(hmCattleRegister.getEntityDiscernNo()))
			.uniqueResult(QHmCattleCureInfo.hmCattleCureInfo.hmCattleCureInfoId.thNo.max());
			
			HmCattleCureInfoId hmCattleCureInfoId = new HmCattleCureInfoId();
			hmCattleCureInfoId.setHmCattleRegister(hmCattleRegister);
			// TODO : BUG 수정 or 추가 ? 멀티 저장 없이 한번만 저장함. 이후 수정 필요
			hmCattleCureInfoId.setThNo(thno3 == null ? 1L : thno3);
			hmCattleCureInfo.setHmCattleCureInfoId(hmCattleCureInfoId);
			cattleCureInfoRepository.save(hmCattleCureInfo);         
		}
		//6. 판매정보
		
		if(StringUtils.isNotEmpty(hmCattleSellStoreInfo.getStoreName())) {
			hmCattleSellStoreInfo.setEntityDiscernNo(hmCattleRegister.getEntityDiscernNo());
			cattleSellStoreInfoRepository.save(hmCattleSellStoreInfo);     
		}
		
		return "/cattle/cattle_form.tiles";
	}
	
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CATTLE");
	}
}
