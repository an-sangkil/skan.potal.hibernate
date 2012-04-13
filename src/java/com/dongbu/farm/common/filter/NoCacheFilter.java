/*
 * $Id: NoCacheFilter.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
 * created by    : 최인호
 * creation-date : 2005. 11. 17.
 * =========================================================
 * Copyright (c) 2005 Miracom, Inc. All rights reserved.
 */
package com.dongbu.farm.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 클라이언트에서 캐쉬를 사용하지 않도록 response에 설정하는 필터.
 * @author Jiwoong Lee
 * @version $Id: NoCacheFilter.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
 */
/**
 * @web.filter name="NoCache Filter"
 * @web.filter-mapping url-pattern="/*"
 */
public class NoCacheFilter implements Filter {
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "No-cache");
		chain.doFilter(request, response);
	}
}
