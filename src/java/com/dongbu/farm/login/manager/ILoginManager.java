/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 6. 22.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login.manager;

import java.util.Map;

import com.dongbu.farm.login.model.Member;

public interface ILoginManager {
	
	/**
	 * <pre>로그인 정보를 가져 온다.</pre>
	 * @param searchMap : map
	 * @return
	 */
	public Member getLogin(Map<String, String> searchMap) throws Exception;

}
