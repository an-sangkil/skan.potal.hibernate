/*
 * $Id: ICodeManager.java ,v 1.1 2011. 3. 28. 오후 4:01:14 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category.manager;

import java.util.List;
import java.util.Map;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.AddressGroup;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.model.Code;

public interface ICategoryManager {
	
	/**
	 * 공통 코드관리 리스트
	 * @param searchMap
	 * @return
	 * @throws CodeException
	 */
	public abstract List<Code> getCodeList(Map<String, String> searchMap) throws CodeException;
	
	
	/**
	 * 주소록 그룹관리 코드 리스트
	 * @param searchMap
	 * @return
	 */
	public abstract List<AddressGroup> getAddressGroupCodeList(	Map<String, String> searchMap)throws CodeException;

	/**
	 * 그룹에 속한 연락처 정보(이름) 리스트
	 * @param searchMap
	 * @return
	 */
	public abstract List<Address> getAddressList(Map<String, String> searchMap) throws CodeException;
	

}
