/*
 * $Id: asdasd.java ,v 1.1 2011. 3. 24. 오후 5:50:45 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 24.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.spbeancall;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class SpringBeanCallWeb {
	public static Object getBean(final HttpServletRequest request,
			final String beanId) throws Exception {

		Object beanObject = null;
		HttpSession hs    = request.getSession();
		ServletContext sc = hs.getServletContext();  
		WebApplicationContext webApplicationContext;

		
		/*
		// DispatcherServlet으로 로딩된 context를 가져 온다.
		webApplicationContext = RequestContextUtils.getWebApplicationContext(request);
		// 빈을 검색해서 해당 빈 오브젝트를 가져 온다.
		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}*/

		// ContextLoaderListener으로 로딩된 context를 가져 온다.
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);

		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}
		
		return beanObject;
	}
}
