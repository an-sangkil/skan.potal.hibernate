/*
 * $Id: IAddressGroupService.java ,v 1.1 2011. 4. 7. 오후 7:34:07 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 4. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.service;

import java.util.Map;

import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.code.CodeException;

public interface IAddressGroupService {
	public abstract AddressGroup getCodeDetail(Map<String,String> searchMap) throws CodeException;

	public abstract void writeCodeAJR(AddressGroup code)throws CodeException;
	
	public abstract int modifyCodeAJR(AddressGroup code)throws CodeException;
}
