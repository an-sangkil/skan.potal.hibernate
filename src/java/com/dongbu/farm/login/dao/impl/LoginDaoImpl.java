/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 6. 22.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.login.dao.ILoginDao;
import com.dongbu.farm.login.model.Member;

@Repository
public class LoginDaoImpl implements ILoginDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate; 
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.login.dao.ILoginDao#getLogin(java.util.Map)
	 */
	public Member getLogin(Map<String, String> searchMap) throws DataAccessException {
		
		return (Member) sqlMapClientTemplate.queryForObject("login.getLogin", searchMap);
	}

}
