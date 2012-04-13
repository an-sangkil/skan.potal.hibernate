/*
 * $Id: AddressGroupDaoImpl.java ,v 1.1 2011. 4. 7. 오후 7:45:30 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 4. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.content.address.model.AddressGroup;

@Repository
public class AddressGroupDaoImpl implements IAddressGroupDao {
	
	@Resource
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	private static String CODE_STATEMENT = "content.addressGroup.";

	@Override
	public AddressGroup getCodeDetail(Map<String,String> searchMap) throws DataAccessException {
		return (AddressGroup) this.sqlMapClientTemplate.queryForObject(CODE_STATEMENT + "getCodeDetail" , searchMap);
	}

	@Override
	public void writeCodeAJR(AddressGroup code) throws DataAccessException {
		this.sqlMapClientTemplate.insert(CODE_STATEMENT + "writeCode", code);
	}

	@Override
	public int modifyCodeAJR(AddressGroup code) throws DataAccessException {
		return this.sqlMapClientTemplate.update(CODE_STATEMENT + "modifyCode", code);
	}
}
