/*
 * $Id: AddressServiceImpl.java ,v 1.1 2011. 3. 31. 오전 10:23:17 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.dongbu.farm.common.Code;
import com.dongbu.farm.common.utils.IdGenerator;
import com.dongbu.farm.content.address.dao.IAddressBookDao;
import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.Anniversary;

@Service
public class AddressBookServiceImpl implements IAddressBookService{
	
	
	@Resource(name="addressBookDaoImpl")
	private IAddressBookDao addressBookDaoImpl;
	

	/**
	 * @Required 선언으로 인해 xml 파일에 property 값에 꼭 addressBookDaoImpl을 설정 해줘야 서버 로딩시 오류 없음. (빈으로 등록되어 있지 않으면 안됨) 
	 * @param addressBookDaoImpl the addressBookDaoImpl to set
	 */
	@Required
	public void setAddressBookDaoImpl(IAddressBookDao addressBookDaoImpl) {
		this.addressBookDaoImpl = addressBookDaoImpl;
	}


	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.content.address.service.IAddressBookService#getAddressBookList(java.util.Map)
	 */
	@Override
	public List<Address> getAddressBookList(Map<String, String> searchMap)  throws Exception{
		
		//일반 주소록 의 전체 내용을 가져온다.
		List<Address> addressBookList = this.addressBookDaoImpl.getAddressBookList(searchMap);
		
		return addressBookList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.content.address.service.IAddressBookService#getAddressBook(java.util.Map)
	 */
	@Override
	public Address getAddressBook(Map<Object, Object> searchMap) throws Exception {
		
		Address  address  = new Address();
		//상세정보
		address = this.addressBookDaoImpl.getAddressBook(searchMap);
		//기념일 리스트
		address.setAnniversaryList(this.addressBookDaoImpl.getAnniversary(searchMap));
		return address;
	}


	@Override
	public void writeAddress(Address address) throws Exception {
		
		//key 값 생성
		String key_value = IdGenerator.generate(Code.DONGBU_CODE_ADDRESS);

		
		//주소록 정보저장
		address.setAds_mgt_no(key_value);
		this.addressBookDaoImpl.writeAddress(address);
		
		
		
		//int index = 0;
		for (Anniversary anniversary : address.getAnniversaryList()) {
			anniversary.setAds_mgt_no(key_value);
			//anniversary.setAnver_seq(String.valueOf(index));
			//index++;
			
			//기념일 정보 저장
			this.addressBookDaoImpl.writeAnniversary(anniversary);
		}
		
	}


	@Override
	public void modifyAddress(Address address) throws Exception {

		//key 값
		String key_value = address.getAds_mgt_no();

		
		//삭제 맵 생성
		Map<Object,Object> deleteMap = new HashMap<Object,Object>();
		deleteMap.put("ads_mgt_no", key_value);
		
		
		//연락처 정보 수정
		this.addressBookDaoImpl.modifyAddress(address);
				
		
		//2. 반복문을 돌면서 하나씩 비교한다. 하지만 적은 갯수가 오니 0 이 아닌경우가 없네. 
		//2.1비교한 내용의 카운트 수가 0이 아닌경우 혹은 null 이 아닌경우는 해당 내용을 update 한다.
		Map<Object,Object> searchMap = new HashMap<Object,Object>();
		searchMap.put("ads_mgt_no", key_value);
		
		
		//기존 데이터를 검색 해온다.
		List<Anniversary> anniversaryList = this.addressBookDaoImpl.getAnniversary(searchMap);
		
		
		//기존 데이터 존재 여부  
		//false 일경우 DB에 INSERT 한다.
		boolean insExist = false;
		
		//
		int index = 0;
		
		//이중 포문의 시작. 
		//시작은 화면에서 보내준 기념일의 값을 기준으로 반복문을 시작한다.
		for (Anniversary addressAnniversary: address.getAnniversaryList()) {
			for (Anniversary anniversary : anniversaryList) {
				if(addressAnniversary.getAds_mgt_no().equals(anniversary.getAds_mgt_no()) && addressAnniversary.getAnver_seq().equals(anniversary.getAnver_seq()) ){
					//같은 값이면 업데이트 한다.
					this.addressBookDaoImpl.modifyAnniversary(addressAnniversary);
					insExist = true;
					break ;
				}
			}
			
			if(!insExist){
				
				//새로운 값 저장후 anver_seq 값 리턴
				String anver_seq = this.addressBookDaoImpl.writeAnniversary(addressAnniversary);
				
				//empty 로 비어 있던 값을 저장된 anver_key 값으로 교체 한다. 
				addressAnniversary.setAnver_seq(anver_seq);
				address.getAnniversaryList().set(index , addressAnniversary);
			}
			insExist = false;
			index++;
		}
		
		//저장된 값을 다시 불러오기 위해 list clear()
		anniversaryList.clear();
		
		//새로 저장된 기념일 정보를 불러온다.
		anniversaryList = this.addressBookDaoImpl.getAnniversary(searchMap);
		
		//데이터 존재 여부 체크 false 면 delete Method 호출.
		boolean delExist = false;
		
		//2.2삭제하기 위한 다시한번 반복문
		//2.2.1.위와 반대로 시작은 저장된 값을 기준으로 반복문을 시작하고 , 
		//2.2.2.안에서 도는 반복문에 화면에서 보내준 값을 반복문으로 설정한다.
		for (Anniversary anniversary : anniversaryList) {
			for (Anniversary addressAnniversary : address.getAnniversaryList()) {
				if(anniversary.getAds_mgt_no().equals(addressAnniversary.getAds_mgt_no()) && addressAnniversary.getAnver_seq().equals(anniversary.getAnver_seq()) ){
					delExist = true;
					break;
				}
			}
			
			if(!delExist){
				//삭제
				deleteMap.clear();
				deleteMap.put("ads_mgt_no",anniversary.getAds_mgt_no());
				deleteMap.put("anver_seq", anniversary.getAnver_seq());
				this.addressBookDaoImpl.deleteAnniversary(deleteMap);
			}
			delExist = false;
		}		
		
		
		/*
		//CASE 2
		//간단하게 기념일 정보를 삭재한뒤 재저장 하는 방법 이 있으나,  
		//modify 정보가 남지 않기 때문에 사용하지 않는다. 
		this.addressBookDaoImpl.deleteAnniversary(deleteMap);
		
		int index = 0;
		for (Anniversary anniversary : address.getAnniversaryList()) {
			anniversary.setAds_mgt_no(key_value);
			anniversary.setAnver_seq(String.valueOf(index));
			index++;
			
			//기념일 정보 저장
			this.addressBookDaoImpl.writeAnniversary(anniversary);
		}*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.content.address.service.IAddressBookService#deleteAddress(java.util.Map)
	 */
	@Override
	public void deleteAddress(Map<Object, Object> deleteMap) throws Exception {
		
		this.addressBookDaoImpl.deleteAddress(deleteMap);
		this.addressBookDaoImpl.deleteAnniversary(deleteMap);
		
	}
}
