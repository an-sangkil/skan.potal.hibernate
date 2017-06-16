package com.skan.tms.mobile.common.code;

import java.util.Set;

import com.skan.tms.web.jpa.dto.PermissionDto;


/**
 * Description : 	권한관리 코드 
 * 					데이터 베이스에서 사용하는 코드명과  일치 하여야 한다. 
 * @author skan
 * @since 2016. 9. 28.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public enum AuthorizationCode {
	SYSTEM_MANAGER("시스템 관리자")
	,GENERAL_MANAGER("일반 관리자")
	//,BUSINESS_MANAGER("영업담당자")
	//,STORE_MANAGER("스토어담당자")
	//,SHOPERM_MANAGER("샵담당자")
	,DEFAULT_USER("일반 사용자")
	,PAID_MEMBER("유료 사용자")
	,INDIVIDUAL_MEMBER("유료 사용자") 	
	,FAMILY_MEMBER("유료 사용자") 		
	,GROUP_MEMBER("유료 사용자")
	,TEENAGER_MEMBER("유료 사용자")

	//코드관리		
	,PERM_CODE_MANAGEMENT_COMMON("공통코드 관리")	
	,PERM_CODE_MANAGEMENT_COMMON_ADD("공통코드 추가")
	,PERM_CODE_MANAGEMENT_COMMON_DELETE("공통코드 삭제")
	,PERM_CODE_MANAGEMENT_COMMON_VIEW("공통코드 상세보기")
	,PERM_CODE_MANAGEMENT_CATEGORY("카테고리 코드 관리")
	,PERM_CODE_MANAGEMENT_CATEGORY_ADD("카테고리 코드 추가")
	,PERM_CODE_MANAGEMENT_CATEGORY_DELETE("카테고리 코드 삭제")
	,PERM_CODE_MANAGEMENT_CATEGORY_VIEW("카테고리 코드 상세보기")
	//스토어관리		
	,PERM_STORE_MANAGEMENT_ADD("가맹점 추가")
	,PERM_STORE_MANAGEMENT_DELETE("가맹점 삭제")	
	,PERM_STORE_MANAGEMENT_VIEW("가맹점 상세보기")
	//상점관리		
	,PERM_SHOPERM_MANAGEMENT_ADD("상점 추가")
	,PERM_SHOPERM_MANAGEMENT_DELETE("상점 삭제")	
	,PERM_SHOPERM_MANAGEMENT_VIEW("상점 상세보기")	
	//권한관리		
	,PERM_PERMISSION_MANAGEMENT_ADD("권한 추가")
	,PERM_PERMISSION_MANAGEMENT_DELETE("권한 삭제")	
	,PERM_PERMISSION_MANAGEMENT_VIEW("권한 상세보기")	
	//모니터링		
	,PERM_MONITORING_REALTIME("실시간 좌석 현황")	
	,PERM_MONITORING_STATS("좌석 통계")	
	//좌석관리		
	,PERM_SHOPERM_DETAIL_SEAT("상점상세좌석셋팅")	
	,PERM_SEAT_MANAGEMENT_STATE("좌석현황")	
	,PERM_SEAT_RESERVATION("좌석예약내역")
	,PERM_SEAT_CANCEL("좌석취소내역")
	,PERM_SEAT_CANCEL_AWAITER("취소좌석대기자내역")
	//사용자 정보관리		
	,PERM_USER_INFORMATION("사용자 기본 정보")
	,PERM_MEMBER_DETAIL_VIEW("회원정보 상세보기")			
	,PERM_MEMBER_DETAIL_MODIFY("회원정보 수정")		
	,PERM_MEMBER_DETAIL_DELETE("회원정보 삭제")			
	,PERM_MEMBER_DETAIL_ADD("회원정보 추가")			
	,PERM_EMAIL_SEND("이메일 발송")			
	,PERM_SMS_SEND("SMS 발송")			

	//사용자 신용관리		
	,PERM_USER_CREDIT_RATING("사용자 신용등급 관리")	
	,PERM_USER_BLACKLIST("블랙리스트 관리");	

	
	// 회원 신용관리 그룹
	public static AuthorizationCode GROUOP_USER_CREDIT_PERM[] = {PERM_USER_CREDIT_RATING, PERM_USER_BLACKLIST};
	// 회원 정보관리 그룹 
	public static AuthorizationCode GROUOP_MEMBER_PERM[] = {PERM_USER_INFORMATION,PERM_MEMBER_DETAIL_VIEW,PERM_MEMBER_DETAIL_MODIFY,PERM_MEMBER_DETAIL_DELETE,PERM_MEMBER_DETAIL_ADD,PERM_EMAIL_SEND,PERM_SMS_SEND};
	
	public static AuthorizationCode GROUOP_SEAT_PERM[] = {
			PERM_SHOPERM_DETAIL_SEAT
			,PERM_SEAT_MANAGEMENT_STATE
			,PERM_SEAT_RESERVATION
			,PERM_SEAT_CANCEL
			,PERM_SEAT_CANCEL_AWAITER
	};
	
	//코드관리 그룹
	public static AuthorizationCode GROUOP_CODE_MANAGEMENT_PERM[] = {
		PERM_CODE_MANAGEMENT_COMMON	
		,PERM_CODE_MANAGEMENT_COMMON_ADD
		,PERM_CODE_MANAGEMENT_COMMON_DELETE
		,PERM_CODE_MANAGEMENT_COMMON_VIEW
		,PERM_CODE_MANAGEMENT_CATEGORY
		,PERM_CODE_MANAGEMENT_CATEGORY_ADD
		,PERM_CODE_MANAGEMENT_CATEGORY_DELETE
		,PERM_CODE_MANAGEMENT_CATEGORY_VIEW
	};
	
	//스토어관리 그룹		
	public static AuthorizationCode GROUOP_STORE_MANAGEMENT_PERM[] = {
		PERM_STORE_MANAGEMENT_ADD
		,PERM_STORE_MANAGEMENT_DELETE	
		,PERM_STORE_MANAGEMENT_VIEW
	};
	//상점관리 그룹		
	public static AuthorizationCode GROUOP_SHOP_MANAGEMENT_PERM[] = {
		PERM_SHOPERM_MANAGEMENT_ADD
		,PERM_SHOPERM_MANAGEMENT_DELETE	
		,PERM_SHOPERM_MANAGEMENT_VIEW	
	};
	
	//권한관리 그룹		
	public static AuthorizationCode GROUOP_AUTHORITY_MANAGEMENT_PERM[] = {
		PERM_PERMISSION_MANAGEMENT_ADD
		,PERM_PERMISSION_MANAGEMENT_DELETE	
		,PERM_PERMISSION_MANAGEMENT_VIEW
	};
	
	private String codeName;

	AuthorizationCode (String codeName) {
		this.codeName = codeName;
	}
	
	/**
	 * 역할 코드 이름
	 * @return
	 */
	public String getCodeName() {
		return codeName;
	}
	
	/**
	 * Group 내속한 Permission 확인
	 * 	 비교대상 String 
	 * 
	 * @param authorizationCodes
	 * @param permissionCode		
	 * @return 거짓, 진실
	 */
	public static boolean isPermission(AuthorizationCode[] authorizationCodes, String permissionCode){
		for (AuthorizationCode authorizationCode : authorizationCodes) {
			System.out.println(authorizationCode);
			if(authorizationCode.toString() == permissionCode){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Group 내속한 Permission 확인
	 * 		비교대상 ENUM 
	 * @param authorizationCodes
	 * @param permissionCode
	 * @return 거짓, 진실
	 */
	public static boolean isPermission(AuthorizationCode[] authorizationCodes, AuthorizationCode permissionCode){
		for (AuthorizationCode authorizationCode : authorizationCodes) {
			System.out.println(authorizationCode);
			if(authorizationCode == permissionCode){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPermission(AuthorizationCode authorizationCode , Set<PermissionDto> permissionDtos) {
		
		long count = permissionDtos.stream().filter(permission -> 
				permission.getPermissionId().equals(authorizationCode.toString())
		).count();
		
		if (count >= 1) {
			return true;
		}
		return false;
	}
	
	// Test
//	public static void main(String[] args) {
//		boolean isPerm = isPermission(AuthorizationCode.GROUOP_AUTHORITY_MANAGEMENT_PERM, PERM_SHOPERM_MANAGEMENT_DELETE);
//		System.out.println(isPerm);
//	}
	
	
}
