/*
 * $Id: CategoryManagerImpl.java ,v 1.1 2011. 3. 28. 오후 4:02:37 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.category.dao.ICategoryDao;
import com.dongbu.farm.system.category.manager.ICategoryManager;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.model.Code;

/**
 * 카테고리 서비스 메니져
 * @author skan
 *
 */
@Service(value="categoryManagerImpl")
public class CategoryManagerImpl implements ICategoryManager {
	
	@Autowired
	private ICategoryDao categoryDaoImpl;
	
	@Override
	public List<Code> getCodeList(Map<String, String> searchMap) throws CodeException {
		return this.categoryDaoImpl.getCodeList(searchMap);
	}

	@Override
	public List<AddressGroup> getAddressGroupCodeList(Map<String, String> searchMap) throws CodeException {
		
		return this.categoryDaoImpl.getAddressGroupCodeList(searchMap);
	}

	@Override
	public List<Address> getAddressList(Map<String, String> searchMap)	throws CodeException {
		return  this.categoryDaoImpl.getAddressList(searchMap);
	}

}
