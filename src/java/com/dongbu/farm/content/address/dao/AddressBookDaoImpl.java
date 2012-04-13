/*
 * $Id: AddressDaoImpl.java ,v 1.1 2011. 3. 31. 오전 10:24:33 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.Anniversary;

/**
 * 
 * @author skan
 *
 */
@Repository 
@Transactional
@SuppressWarnings("unchecked")
public class AddressBookDaoImpl implements IAddressBookDao{
	
	private final String ADDRESS_STATEMENTNAME = "content.address.";
	
	@Resource
	private SqlMapClientTemplate sqlMapClientTemplate;

	@Override
	public List<Address> getAddressBookList(Map<String, String> searchMap) throws DataAccessException {
		return sqlMapClientTemplate.queryForList(ADDRESS_STATEMENTNAME + "getAddressBookList" , searchMap);
	}
	
	@Override
	public Address getAddressBook(Map<Object, Object> searchMap) throws DataAccessException { 
		
		return (Address) sqlMapClientTemplate.queryForObject(ADDRESS_STATEMENTNAME + "getAddressBook" ,searchMap );
	}
	
	@Override
	public List<Anniversary> getAnniversary(Map<Object, Object> searchMap) throws DataAccessException{
		
		return sqlMapClientTemplate.queryForList(ADDRESS_STATEMENTNAME + "getAnniversary" , searchMap);
	}

	@Override
	public String writeAddress(Address address) throws DataAccessException {
		
		return (String) this.sqlMapClientTemplate.insert(ADDRESS_STATEMENTNAME + "writeAddress" , address);
	}

	@Override
	public String writeAnniversary(Anniversary anniversary) throws DataAccessException {
		return (String)this.sqlMapClientTemplate.insert(ADDRESS_STATEMENTNAME + "writeAnniversary" , anniversary);
	}

	@Override
	public int modifyAddress(Address address) throws DataAccessException {
		return this.sqlMapClientTemplate.update(ADDRESS_STATEMENTNAME + "modifyAddress", address);
		
	}

	@Override
	public int modifyAnniversary(Anniversary anniversary) throws DataAccessException {
		return this.sqlMapClientTemplate.update(ADDRESS_STATEMENTNAME + "modifyAnniversary", anniversary);
	}

	@Override
	public void deleteAddress(Map<Object, Object> deleteMap) throws DataAccessException {
		this.sqlMapClientTemplate.delete(ADDRESS_STATEMENTNAME + "deleteAddress" , deleteMap);
		
	}

	@Override
	public void deleteAnniversary(Map<Object, Object> deleteMap) throws DataAccessException {
		this.sqlMapClientTemplate.delete(ADDRESS_STATEMENTNAME + "deleteAnniversary", deleteMap );
	}

	@Override
	@Deprecated
	public Anniversary getAnniversary(String ads_mgt_no) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
