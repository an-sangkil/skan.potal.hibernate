/*
 * $Id: CategoryDaoImpl.java ,v 1.1 2011. 3. 28. 오후 4:03:12 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.category.dao.ICategoryDao;
import com.dongbu.farm.system.code.model.Code;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	private static final String CATEGORY_STATEMENTNAME = "system.category.";
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Code> getCodeList(Map<String, String> searchMap) throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList(CATEGORY_STATEMENTNAME + "getCodeList", searchMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressGroup> getAddressGroupCodeList(Map<String, String> searchMap)
			throws DataAccessException {
		
		return this.sqlMapClientTemplate.queryForList(CATEGORY_STATEMENTNAME + "getAddressGroupCodeList", searchMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressList(Map<String, String> searchMap)	throws DataAccessException {
		
		return this.sqlMapClientTemplate.queryForList(CATEGORY_STATEMENTNAME + "getAddressList", searchMap);
	}

}
