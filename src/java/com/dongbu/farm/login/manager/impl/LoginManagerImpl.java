/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 6. 22.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.login.dao.ILoginDao;
import com.dongbu.farm.login.manager.ILoginManager;
import com.dongbu.farm.login.model.Member;

@Service
public class LoginManagerImpl implements ILoginManager {
	
	@Autowired
	private ILoginDao loginDaoImpl;
	
	/*
	 * 로그인 정보를 가져 온다.
	 * (non-Javadoc)
	 * @see com.dongbu.farm.login.manager.ILoginManager#getLogin(java.util.Map)
	 */
	public Member getLogin(Map<String, String> searchMap) throws Exception {
		
		return loginDaoImpl.getLogin(searchMap);
	}
	
	

}
