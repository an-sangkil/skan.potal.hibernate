package com.dongbu.potal.web.potal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongbu.potal.web.potal.bean.Schdule;
import com.dongbu.potal.web.potal.dao.SchduleDao;

@Controller
public class SchduleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private SchduleDao schduleDao;
	
	@RequestMapping("/schdule/schdule_list")
	public String schduleList (@Valid Schdule schdule, BindingResult bindingResult, HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		if(bindingResult.hasErrors()) {
			
			logger.debug("attribute error");
			
			return "/schdule/schdule_calendar.tiles";
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<? extends Map<?, ?>> schduleList = schduleDao.selectList(schdule, Schdule.class, dataMap);
		logger.debug(schduleList.toString());
		
		return "/schdule/schdule_calendar.tiles";
	}
}
