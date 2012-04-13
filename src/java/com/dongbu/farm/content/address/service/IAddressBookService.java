/*
 * $Id: IAddressService.java ,v 1.1 2011. 3. 31. 오전 10:23:53 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.content.address.model.Address;

public interface IAddressBookService {
	
	/**
	 * 전체 주소록
	 * @param searchMap
	 * @return
	 * @throws Exception
	 */
	public abstract List<Address> getAddressBookList(Map<String, String> searchMap) throws Exception ;
	
	/**
	 * 선택된 사람의 상세 정보
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract Address getAddressBook(Map<Object, Object> searchMap) throws Exception;
	
	/**
	 * 주소록 정보저장
	 * @param address
	 * @throws Exception
	 */
	public abstract void writeAddress(Address address)throws Exception;
	
	/**
	 * 연락처 수정
	 * @param address
	 * @throws Exception
	 */
	public abstract void modifyAddress(Address address)throws Exception;
	
	/**
	 * 연락처 정보 삭제 + 기념일정보 삭제
	 * @param deleteMap
	 * @throws Exception
	 */
	public abstract void deleteAddress(Map<Object, Object> deleteMap)throws Exception;

}
