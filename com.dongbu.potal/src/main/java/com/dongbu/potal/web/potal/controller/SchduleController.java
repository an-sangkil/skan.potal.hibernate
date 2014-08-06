package com.dongbu.potal.web.potal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongbu.potal.web.potal.bean.Schdule;
import com.dongbu.potal.web.potal.dao.SchduleDao;
import com.dongbu.potal.web.potal.dao.TestDao;

@Controller
public class SchduleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private TestDao testDao;
	
	@Autowired
	private SchduleDao schduleDao;
	
	@RequestMapping("/rtest")
	public @ResponseBody List<String> test (HttpServletRequest request, ModelMap modelMap) {
		
		System.out.println("aaa12");
		List<String > aa = new ArrayList<String>();
		aa.add("sss");
		
		return aa; 
	}
	
	@RequestMapping(value={"/test","/testTo"} , params=("type=test"))
	public void test (HttpServletRequest request) {
		
		System.out.println("aaa");System.out.println("aaa");
		List<String> testList = testDao.testSelect();
		for (String string : testList) {
			System.out.println(string);
		}
	}
	
	@RequestMapping("/schdule/schdule_list")
	public String schduleList (HttpServletRequest request) throws Exception {
		
		Schdule schdule = new Schdule();
		schdule.setTitle("aaaa");
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<? extends Map<?, ?>> schduleList = schduleDao.selectList(schdule, Schdule.class, dataMap);
		logger.debug(schduleList.toString());
		
		
		return "/schdule/schdule_calendar.tiles";
	}
}
