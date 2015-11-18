package com.skan.potal.web.potal.application.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skan.potal.hibernate.application.model.Schedule;
import com.skan.potal.web.potal.application.service.ScheduleService;

@Controller
public class ScheduleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping("/schdule/schdule_list")
	public String schduleList (@Valid 
			Schedule schdule, BindingResult bindingResult, HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		if(bindingResult.hasErrors()) {
			
			logger.debug("attribute error");
			
			return "/schdule/schdule_calendar.tiles";
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//List<? extends Map<?, ?>> schduleList = schduleDao.selectList(schdule, Schdule.class, dataMap);
		//logger.debug(schduleList.toString());
		
		return "/schdule/schdule_calendar.tiles";
	}
}
