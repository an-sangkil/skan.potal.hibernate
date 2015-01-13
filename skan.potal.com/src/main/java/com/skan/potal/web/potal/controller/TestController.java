package com.skan.potal.web.potal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import mulity.thread.skan.model.User;
import mulity.thread.skan.thread.SfBlockRuner;
import mulity.thread.skan.thread.task.SfBlockQueue;
import mulity.thread.skan.utils.StateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skan.potal.web.potal.dao.TestDao;

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
@Scope("singleton")
public class TestController {
	
	//@Autowired
	//private TestDao testDao;
	
	//BlockingQueue<User> queue = new ArrayBlockingQueue<User>(2);
	BlockingQueue<User> queue = new LinkedBlockingQueue<>();
	
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
		//List<String> testList = testDao.testSelect();
		//for (String string : testList) {
		//	System.out.println(string);
		//}
	}
	
	@RequestMapping(value={"/test2"})
	public void test2 (HttpServletRequest request, @Valid @ModelAttribute User user, @RequestParam(required=false , defaultValue="" ) Integer key) {
		
		/*User user = new User();
		user.setUserId("1234");
		user.setUserName("kkkk");
		System.out.println("hihi~");*/
		
		
		@SuppressWarnings("unchecked")
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		
		StateUtils stateUtils = StateUtils.getInstance();
		stateUtils.putItem(key, user);
		
		sf.setItem(user,queue );
		//System.out.println("================ CONTROLLER ===================");
		//System.out.println("stateUtils.getItemSize() = " + stateUtils.getItemSize());
		
		try {
				//System.out.println("RUNNER  - START");
				sf.runner(queue);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	@RequestMapping(value={"/test2Ex"})
	public void test2Ex(HttpServletRequest request) {
		
		StateUtils stateUtils = StateUtils.getInstance();
		
		User user = (User)stateUtils.getItem(5);
		System.out.println(queue.remove(user)); 
		
	}
}
