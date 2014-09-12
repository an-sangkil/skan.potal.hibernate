package com.dongbu.potal.web.potal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongbu.potal.web.potal.dao.TestDao;

/**
 * <pre>
 * Class Name  : TestController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 8. 7.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 8. 7.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Controller
public class TestController {
	
	@Autowired
	private TestDao testDao;
	
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
}
