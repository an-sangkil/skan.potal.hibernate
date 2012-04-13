/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 6. 21.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.login.LoginException;
import com.dongbu.farm.login.manager.ILoginManager;
import com.dongbu.farm.login.model.Member;

@Controller
public class LoginController extends RepositoryController{

	private static final Log loggerLog = LogFactory.getLog(LoginController.class);
	
	@Resource(name="loginManagerImpl")
	private ILoginManager loginManagerImpl;
	
	
	/**
	 * 로그인이 성공하면 Session을 생성해 준다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/setSessiongSetting.login")
	public ModelAndView setSessiongSetting(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession(true);
		try{
			
			/*if(session != null && session.isNew()){
				session.invalidate();
				new ModelAndView("login/login");
			}*/
			String user_id   = ServletRequestUtils.getStringParameter(request, "user_id", "");
			Map<String, String> searchMap = new HashMap<String,String>();
			searchMap.put("user_id", user_id);
			Member member = new Member(); 
			member = loginManagerImpl.getLogin(searchMap);
			
			/*
			 * loginProc.jsp에서 parameter 를 넘길때  LoginDto를 전부 넘겨 받아 이곳에서 Requset.getAttribute 해서 사용하면 좀더 좋을거 같음.
			 * 현재는 DAO 를 한번더 사용해서 Session 에 값을 셋팅 한다.
			 */
			if(member != null ){
				session.setAttribute("member", member);
				//지속시간 설정, Web.xml에서 설정 하지 않은경우..사용.
				session.setMaxInactiveInterval(3600);
			}
			return new ModelAndView("mainPage");
		}catch (Exception e) {
			loggerLog.error("login failse", new LoginException("login failse", e));
			return null;
		}
		
	}
	
	/**
	 * 로그인 체크를 위해 DB정보와 마춰보기위한 준비작업임 
	 * 실제 체크는 LoginProc.jsp에서 한다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/getLogin.login")
	public ModelAndView getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String user_id  = ServletRequestUtils.getStringParameter(request, "user_id" , "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
		Map<String, String> searchMap = new HashMap<String,String>();
		searchMap.put("user_id", user_id);
		
		Member member = loginManagerImpl.getLogin(searchMap);
		    
		request.setAttribute("password", password);
		request.setAttribute("user_id" , user_id);
		request.setAttribute("member", member);
		
		return new ModelAndView("login/loginProc");
	}
	
	/**
	 * Session 삭제
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.invalidate();
		
		response.sendRedirect("");
	}
}
