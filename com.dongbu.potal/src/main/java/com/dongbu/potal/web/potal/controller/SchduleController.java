package com.dongbu.potal.web.potal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongbu.potal.web.potal.dao.SchduleDao;

@Controller
public class SchduleController {
	
	
	@Autowired
	private SchduleDao schduleDao;
	
	@RequestMapping("/rtest")
	public @ResponseBody List<String> test (HttpServletRequest request, ModelMap modelMap) {
		
		System.out.println("aaa12");
		List<String > aa = new ArrayList<String>();
		aa.add("sss");
		
		return aa; 
	}
	
	@RequestMapping("/test")
	public void test (HttpServletRequest request) {
		
		System.out.println("aaa");System.out.println("aaa");
		List<String> testList = schduleDao.testSelect();
		for (String string : testList) {
			System.out.println(string);
		}
	}
}
