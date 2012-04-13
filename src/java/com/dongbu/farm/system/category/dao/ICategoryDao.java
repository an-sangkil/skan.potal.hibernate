/*
 * $Id: ICategoryDao.java ,v 1.1 2011. 3. 28. 오후 4:02:51 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.code.model.Code;

public interface ICategoryDao {

	public abstract List<Code> getCodeList(Map<String, String> searchMap) throws DataAccessException;

	public abstract List<AddressGroup> getAddressGroupCodeList(Map<String, String> searchMap)throws DataAccessException;
	
	/**
	 * 그룹에 속한 유저 리스트 (연락처 정보 리스트)
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<Address> getAddressList(Map<String, String> searchMap)throws DataAccessException;

}
