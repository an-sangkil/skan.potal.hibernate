package com.skan.potal.hibernate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skan.potal.hibernate.model.User;
import com.skan.potal.hibernate.service.UserService;

/**
 * <pre>
 * Class Name  : HibernateTestController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 1. 5.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 1. 5.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Controller
public class HibernateTestController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="htest01")
	public void hTestSave(@Valid User user,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		user.setUsername("ansangkil");
		user.setName("haha~~");
		
		this.userService.insertUser(user);
	}
	
	@RequestMapping(value="htest02")
	public @ResponseBody List<User> hTestFindAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> user = userService.findAllUsers();
		for (User o : user) {
			System.out.println(o);
		}
		return user;
	}
	
	@RequestMapping(value="htest03")
	public 
	//@ResponseBody 
	String hTestPagination(HttpServletRequest request, HttpServletResponse response , ModelMap modeMap) throws Exception {
		Page<User> userPaging = userService.findUser(new PageRequest(1, 10, Direction.ASC , "id"));
		
		
		modeMap.put("userPaging", userPaging);
		return "/test/paging/pagingTest";
	}

}
