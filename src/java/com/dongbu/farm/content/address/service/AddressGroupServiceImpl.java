/*
 * $Id: AddressGroupServiceImpl.java ,v 1.1 2011. 4. 7. 오후 7:34:57 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 4. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongbu.farm.content.address.dao.IAddressGroupDao;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.code.CodeException;

@Service
public class AddressGroupServiceImpl implements IAddressGroupService{

	@Resource
	private IAddressGroupDao addressGroupDaoImpl;
	
	@Override
	public AddressGroup getCodeDetail(Map<String,String> searchMap) throws CodeException {
		
		return this.addressGroupDaoImpl.getCodeDetail(searchMap);
	}

	@Override
	public void writeCodeAJR(AddressGroup code) throws CodeException {
		this.addressGroupDaoImpl.writeCodeAJR(code);
	}

	@Override
	public int modifyCodeAJR(AddressGroup code) throws CodeException {
		return this.addressGroupDaoImpl.modifyCodeAJR(code);
	}

}
