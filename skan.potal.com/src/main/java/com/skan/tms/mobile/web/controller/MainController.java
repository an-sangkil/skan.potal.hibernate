package com.knkcorp.tms.mobile.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knkcorp.tms.mobile.common.code.ConcertCode;
import com.knkcorp.tms.web.jpa.dto.ConcertHallManagement;
import com.knkcorp.tms.web.jpa.dto.ConcertManagement;
import com.knkcorp.tms.web.jpa.dto.QConcertManagement;
import com.knkcorp.tms.web.jpa.dto.UserDto;
import com.knkcorp.tms.web.jpa.repository.ConcertHallManagementJpaRepository;
import com.knkcorp.tms.web.jpa.repository.ConcertManagementJpaRepository;
import com.knkcorp.tms.web.jpa.repository.UserJpaRepository;
import com.knkcorp.tms.web.mybatis.repository.UserMapper;

/**
 * 
 * Description : 
 * @author skan
 * @since  2017. 6. 14.
 * @version 
 *
 * Copyright (C) 2017 by KNK Corp. All right reserved.
 */
@Controller
public class MainController extends AbstractCommonController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserMapper userMapper;
	@Autowired UserJpaRepository userJpaRepository;
	@Autowired private ConcertManagementJpaRepository concertManagementJpaRepository;
	@Autowired private ConcertHallManagementJpaRepository concertHallManagementJpaRepository;
	
	
	/**
	 * 	메인페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	private String mainPageIndex (HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		logger.debug("WELCOME ~ ~  KNK Ticket Management System MOBILE !!! ");
		QConcertManagement qConcertManagement = QConcertManagement.concertManagement;
		List<ConcertManagement> concertManagements = (List<ConcertManagement>) concertManagementJpaRepository.findAll(qConcertManagement.concertStatus.eq(ConcertCode.CONCERT_START.name()));
		
		concertManagements.forEach(item -> {
			ConcertHallManagement concertHallManagement = concertHallManagementJpaRepository.findOne(item.getConcertHallId());
			item.setConcertHallManagement(concertHallManagement);
		});
		
		modelMap.put("concertManagements", concertManagements);
		
		return "tms.main";
	}
	
	/**
	 * 소개
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/intro")
	private String mainIntro (HttpServletRequest request , ModelMap modelMap) throws Exception {
		logger.debug("intro Page");
		return "intro.intro";
	}
	
	/**
	 * 안내 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/info")
	private String mainInfo (HttpServletRequest request , ModelMap modelMap) throws Exception {
		logger.debug("intro Page");
		return "info.info";
	}
}
