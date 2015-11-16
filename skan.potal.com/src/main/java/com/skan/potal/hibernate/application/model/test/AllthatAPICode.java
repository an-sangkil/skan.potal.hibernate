package com.skan.potal.hibernate.application.model.test;

/**
 * 올뎃 API 분류 코드 관리
 *  
 * @author skan
 *
 */
public enum AllthatAPICode implements CommonCode  {
	
	
	/////////////////////////////////////////////////////////////////////
	// API Disivation Code
	/////////////////////////////////////////////////////////////////////
	MERGE_WHEREHOUSE("출고지/반송지 등록 수정"), 
	MERGE_PRODUCTS("상품 등록 수정");
	
	private String message;
	/////////////////////////////////////////////////////////////////////
	// API Log File Name
	/////////////////////////////////////////////////////////////////////
	public static String ALLTHAT_GET_TOKEN="getToken";						// 토큰 생성 및 조회
	public static String ALLTHAT_GET_MALL="getMalls";						// 몰 조회
	public static String ALLTHAT_GET_CATEGORIES="getCategories";			// 카테고리 조회
	public static String ALLTHAT_GET_CODE="getCodes";						// 코드 조회
	public static String ALLTHAT_UPDATE_WAREHOUSES="updateWareHouses";		// 출고지/반송지 수정
	public static String ALLTHAT_GET_WAREHOUSES ="getWarehouses";			// 출고지/반송지 검색
	public static String ALLTHAT_GET_PRODUCTS ="getProducts";				// 상품정보 검색
	
	private AllthatAPICode(String message) {
		this.message   = message;
	}
	
	@Override
	public String getText() {
		return this.message;
	}
	
	
}
