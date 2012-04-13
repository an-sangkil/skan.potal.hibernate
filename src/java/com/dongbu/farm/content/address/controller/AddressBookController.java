/*
 * $Id: AddressBookController.java ,v 1.1 2011. 3. 31. 오전 10:22:26 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 31.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.content.address.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.dongbu.farm.common.ajax.AjaxUtil;
import com.dongbu.farm.common.utils.SessionUtils;
import com.dongbu.farm.common.utils.Utils;
import com.dongbu.farm.content.address.model.Address;
import com.dongbu.farm.content.address.model.Anniversary;
import com.dongbu.farm.content.address.service.IAddressBookService;

@Controller
public class AddressBookController extends MultiActionController{
	
	@Resource
	private IAddressBookService addressBookServiceImpl;
	                            
	private static final Log logger = LogFactory.getLog(AddressBookController.class);
	
	/**
	 * 연락처 정보 리스트 
	 * @param request
	 * @param response
	 * @return
	 * @throws AddressException
	 */
	@RequestMapping(value="/content/address/getAddressList.dongbu" , method = RequestMethod.GET)
	public ModelAndView getAddressBookList(HttpServletRequest request , HttpServletResponse response,  
										@RequestParam(value="user_name" , required =true ,defaultValue="") String user_name) throws AddressException{

		//모델 뷰 생성
		ModelAndView mav = new ModelAndView();
		
		//검색 맵 생성
		Map<String,String> searchMap = new HashMap<String,String>();
		try {
			
			String req_user_name = ServletRequestUtils.getStringParameter(request, "user_name");
			
			//검색 조건 키값 설정
			
			searchMap.put("user_name", Utils.decoding(user_name));
			
			//전체검색  기념일 관련 내용은 addressBookService 에서 처리
			List<Address> addressList = addressBookServiceImpl.getAddressBookList(searchMap);
			logger.info("getAddressBookList :: /content/address/getAddressList.dongbu");
			
			
			System.out.println("user_name: " + user_name);
			System.out.println("req_user_name: " + req_user_name);
			
			mav.addAllObjects(searchMap);
			mav.addObject("addressList", addressList);
			mav.setViewName("contents/addressMag/_tabAddressList_Ajaxup");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav; 
	}
	
	/**
	 * 선택된 연락처정보 AjaxRequest 비동기
	 * @param ads_mgt_no
	 * @param user_name
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/content/address/getAddressAjaxReq.dongbu" , method = RequestMethod.GET)
	public void getAddressBookAjaxReq(@RequestParam(value="ads_mgt_no" , required=true) String ads_mgt_no,
									  @RequestParam(value="user_name", required=false) String user_name ,
									  HttpServletResponse response) throws Exception{
		
		//모델 뷰 생성
		ModelAndView mav = new ModelAndView();
		
		//검색 맵 생성
		Map<Object,Object> searchMap = new HashMap<Object,Object>();
		HashMap<Object,Object> ajaxMap = new HashMap<Object,Object>();
		try {
			
			//검색 조건 키값 설정
			searchMap.put("ads_mgt_no", ads_mgt_no);
			searchMap.put("user_name", user_name);
			
			//전체검색  기념일 관련 내용은 addressBookService 에서 처리
			Address address= addressBookServiceImpl.getAddressBook(searchMap);
			logger.info("getAddressBookList :: /content/address/getAddressList.dongbu");
			
			mav.addObject("address", address);
			//mav.setViewName("contents/addressMag/addressBookManagement");
			
			ajaxMap.put("address", address);
			AjaxUtil.successWrite(response, ajaxMap);
			
			
		} catch (Exception e) {
			
			ajaxMap.put("ErrorMessage", e.toString());
			AjaxUtil.failWrite(response, ajaxMap);
			e.printStackTrace();
			
		}
	}
	/**
	 * 선택된 연락처정보 AjaxUpdate 비동기식
	 * @param ads_mgt_no
	 * @param user_name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/content/address/getAddressAjaxUp.dongbu")
	public ModelAndView getAddressBookAjaxUp(@RequestParam(value="ads_mgt_no" , required=true) String ads_mgt_no,
			   								 @RequestParam(value="user_name", required=false) String user_name) throws Exception{
		
		//모델 뷰 생성
		ModelAndView mav = new ModelAndView();
		
		//검색 맵 생성
		Map<Object,Object> searchMap = new HashMap<Object,Object>();
		try {
			
			//검색 조건 키값 설정
			searchMap.put("ads_mgt_no", ads_mgt_no);
			searchMap.put("user_name", user_name);
			
			
			//전체검색  기념일 관련 내용은 addressBookService 에서 처리
			Address address= addressBookServiceImpl.getAddressBook(searchMap);
			
			mav.addObject("address", address);
			mav.setViewName("contents/addressMag/_anniversaryDetailAjaxup");
			
			logger.info("getAddressBookList :: /content/address/getAddressList.dongbu");
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return mav;
	}
	
	/**
	 * 연락처 내용 저장
	 * @param request
	 * @param respose
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/content/address/writeAddress.dongbu" , method=RequestMethod.POST)
	public ModelAndView writeAddress(HttpServletRequest request, HttpServletResponse respose) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		Address address = new Address();
		try {
			
			
			//객체 바인딩
			bind(request, address);
			
			//기념일리스트 생성
			List<Anniversary> anniversaryList = new ArrayList<Anniversary>();
			
			//기념일 값 셋팅
			for(int i=0 ; i < address.getAnver_date().length; i++){
				Anniversary anniversary = new Anniversary();
				
				anniversary.setAnver_content(address.getAnver_content()[i]);
				anniversary.setAnver_date(address.getAnver_date()[i]);
				anniversary.setAnver_type_code(address.getAnver_type_code()[i]);
				anniversary.setCreator(SessionUtils.getUserID(request));
				anniversary.setCreatedtime(new Date());
				
				anniversaryList.add(anniversary);
			}
			
			//기본 주소 값에 기념일 값 추가
			address.setAnniversaryList(anniversaryList);
			
			//서비스 호출
			this.addressBookServiceImpl.writeAddress(address);
			
			//리턴 뷰 설정
			mav.setViewName("redirect:/content/address/getAddressList.dongbu");
			
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 연락처 정보 수정
	 * @param request
	 * @param respose
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/content/address/modifyAddress.dongbu" , method=RequestMethod.POST)
	public ModelAndView modifyAddress(HttpServletRequest request, HttpServletResponse respose) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		Address address = new Address();
		try {
			
			
			//객체 바인딩
			bind(request, address);
			
			//기념일리스트 생성
			List<Anniversary> anniversaryList = new ArrayList<Anniversary>();
			
			//기념일 값 셋팅
			for(int i=0 ; i < address.getAnver_date().length; i++){
				Anniversary anniversary = new Anniversary();
				
				anniversary.setAds_mgt_no(address.getAds_mgt_no());
				anniversary.setAnver_content(address.getAnver_content()[i]);
				anniversary.setAnver_date(address.getAnver_date()[i]);
				anniversary.setAnver_type_code(address.getAnver_type_code()[i]);
				try {
					anniversary.setAnver_seq(address.getAnver_seq()[i]);
				} catch (Exception e) {
					anniversary.setAnver_seq("empty");
					logger.info("랭스가 작숩니다~");
				}
				anniversary.setCreator(SessionUtils.getUserID(request));
				anniversary.setCreatedtime(new Date());
				anniversary.setModifier(SessionUtils.getUserID(request));
				anniversary.setModifiedtime(new Date());
				
				anniversaryList.add(anniversary);
			}
			
			//기본 주소 값에 기념일 값 추가
			address.setAnniversaryList(anniversaryList);
			
			//서비스 호출
			this.addressBookServiceImpl.modifyAddress(address);
			
			//리턴 뷰 설정
			mav.setViewName("redirect:/content/address/getAddressList.dongbu");
			
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 연락처 정보 + 기념일 정보 삭제
	 * @param ads_mgt_no
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/content/address/deleteAddress.dongbu", method = RequestMethod.POST)
	public ModelAndView deleteAddress(@RequestParam(value="ads_mgt_no", required = true ) String ads_mgt_no) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		Map<Object,Object> deleteMap = new HashMap<Object, Object>();
		try {
			deleteMap.put("ads_mgt_no", ads_mgt_no);
			
			this.addressBookServiceImpl.deleteAddress(deleteMap);
			
			mav.setViewName("redirect:/content/address/getAddressList.dongbu");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
}
