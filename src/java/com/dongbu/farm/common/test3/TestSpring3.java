/*
 * $Id: Test3.java ,v 1.1 2011. 2. 14. 오후 4:34:21 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 2. 14.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.test3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestSpring3{
	

	/**
	 * @TestUrl : http://skan.example.com:28080/hello.common?one=1&two=2 
	 * @RequestParam : @RequestParam(value = "pageNumber",required=false) value=넘어오는 param 명 
	 *                 required= true or false 반드시 요구 하는지 안하는지  ->넘겨오는 param 이 없을때 설정이 false이면 null을 리턴.
	 *                 defalutValue= 디폴트벨류.
	 */
	@RequestMapping(value="/hello.common" , method=RequestMethod.GET)
	public String hello(@RequestParam(value="one") String query,
			@RequestParam(value="two", required=false) int param ) {
 
		String message = "Hello, Spring 3.0!";
		System.out.println(message);
		
		//System.out.println(query +"/" + param);
		
		return "";
	}
	
	

	/**
	 * @TestURL : http://skan.example.com:28080/aaa/bbb/hello.common?one=1&two=2
	 * @PathVariable {param1} = aaa
	 *               {param2} = bbb
	 *               URL Path 로 받은 값을 parameter 로 사용 할 수 있다.
	 * @참고사이트 : http://blog.naver.com/jazz1234k?Redirect=Log&logNo=40123935649                
	 */
	@RequestMapping(value="/{param1}/{param2}/hello.common" , method=RequestMethod.GET)
	public String helloTwo(@RequestParam(value="one") String query,
						   @RequestParam(value="two", required=false) int param ,
						   @PathVariable String param1 , @PathVariable String param2) {
 
		String message = "Hello, Spring 3.0!";
		System.out.println(message);
		
		System.out.println(query +"/" + param);
		
		return "";
	}
}
