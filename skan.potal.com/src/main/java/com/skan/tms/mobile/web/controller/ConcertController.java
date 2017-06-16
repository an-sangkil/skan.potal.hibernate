package com.knkcorp.tms.mobile.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knkcorp.tms.mobile.common.code.ConcertCode;
import com.knkcorp.tms.web.jpa.dto.ConcertHallManagement;
import com.knkcorp.tms.web.jpa.dto.ConcertManagement;
import com.knkcorp.tms.web.jpa.dto.QConcertManagement;
import com.knkcorp.tms.web.jpa.repository.ConcertHallManagementJpaRepository;
import com.knkcorp.tms.web.jpa.repository.ConcertManagementJpaRepository;

/**
 * 
 * Description : 공연 정보 
 * @author skan
 * @since  2017. 6. 15.
 * @version 
 *
 * Copyright (C) 2017 by KNK Corp. All right reserved.
 */
@Controller
public class ConcertController extends AbstractCommonController {
	
	
	@Autowired private ConcertManagementJpaRepository concertManagementJpaRepository;
	@Autowired private ConcertHallManagementJpaRepository concertHallManagementJpaRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 진행중인 공연 정보 목록
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/concert/concertList")
	public String concertList (HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		QConcertManagement qConcertManagement = QConcertManagement.concertManagement;
		List<ConcertManagement> concertManagements = (List<ConcertManagement>) concertManagementJpaRepository.findAll(qConcertManagement.concertStatus.eq(ConcertCode.CONCERT_START.name()));
		concertManagements.forEach(item -> {
			ConcertHallManagement concertHallManagement = concertHallManagementJpaRepository.findOne(item.getConcertHallId());
			item.setConcertHallManagement(concertHallManagement);
		});
		
		List<ConcertManagement> normalConcerts = concertManagements.stream()
																   .filter( p-> p.getConcertType().equals(ConcertCode.NOMAL_CONCERT.name())).collect(Collectors.toList());
		
		
		
		modelMap.put("normalConcerts", normalConcerts);
		modelMap.put("concertManagements", concertManagements);
		
		return "concert.concertList"; 
	}
	
	/**
	 * 공연정보 상세보기
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/concert/concertView")
	public String concertView(HttpServletRequest request , ModelMap modelMap) throws Exception {
		String concertId = ServletRequestUtils.getRequiredStringParameter(request, "concertId");
		ConcertManagement concertManagement =  concertManagementJpaRepository.findOne(concertId);
		ConcertHallManagement concertHallManagement = concertHallManagementJpaRepository.findOne(concertManagement.getConcertHallId());
		concertManagement.setConcertHallManagement(concertHallManagement);
		
		modelMap.put("concertManagement", concertManagement);
		
		return "concert.concertView"; 
	}
}
