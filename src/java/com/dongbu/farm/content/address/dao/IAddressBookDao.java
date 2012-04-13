/*
 * $Id: IAddressDao.java ,v 1.1 2011. 3. 31. 오전 10:25:00 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.Anniversary;

public interface IAddressBookDao {
	
	/**
	 * 전체 주소록 검색
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<Address> getAddressBookList(Map<String, String> searchMap) throws DataAccessException;
	
	/**
	 * 선택된 사람의 기념일 리스트 
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<Anniversary> getAnniversary(Map<Object, Object> searchMap)	throws DataAccessException;
	
	/**
	 * 기념일 하나의 정보
	 * @param ads_mgt_no
	 * @return
	 * @throws DataAccessException
	 */
	public abstract Anniversary getAnniversary(String ads_mgt_no) throws DataAccessException;
	
	/**
	 * 선택된 사람의 상세 정보
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract Address getAddressBook(Map<Object, Object> searchMap)throws DataAccessException;
	
	/**
	 * 주소록 정보저장
	 * @param address
	 * @return
	 * @throws DataAccessException
	 */
	public abstract String writeAddress(Address address) throws DataAccessException;
	
	/**
	 * 주소록 기념일 정보 저장
	 * @param anniversaryList
	 * @return 
	 * @throws DataAccessException
	 */
	public abstract String writeAnniversary(Anniversary anniversary)throws DataAccessException;
	
	/**
	 * 연락처 정보 수정
	 * @param address
	 * @return 
	 * @throws DataAccessException
	 */
	public abstract int modifyAddress(Address address) throws DataAccessException;
	
	/**
	 * 연락처 기념일 정보 수정
	 * @param anniversary
	 * @return 
	 * @throws DataAccessException
	 */
	public abstract int modifyAnniversary(Anniversary anniversary)throws DataAccessException;
	
	/**
	 * 연락처 기본정보 삭제
	 * @param deleteMap
	 * @throws DataAccessException
	 */
	public abstract void deleteAddress(Map<Object, Object> deleteMap) throws DataAccessException;
	
	/**
	 * 연락처 기념일 정보삭제
	 * @param deleteMap
	 */
	public abstract void deleteAnniversary(Map<Object, Object> deleteMap) throws DataAccessException;

}
