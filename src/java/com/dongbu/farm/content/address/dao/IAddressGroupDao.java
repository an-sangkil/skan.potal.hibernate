/*
 * $Id: IAddressGroupDao.java ,v 1.1 2011. 4. 7. 오후 7:44:58 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 4. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.content.address.model.AddressGroup;

public interface IAddressGroupDao {
	public abstract AddressGroup getCodeDetail(Map<String,String> searchMap) throws DataAccessException;

	public abstract void writeCodeAJR(AddressGroup code)throws DataAccessException;

	public abstract int modifyCodeAJR(AddressGroup code)throws DataAccessException;
	
}
