package com.skan.tms.mobile.common.code;

public enum ReservationCode {
	/** 매장 구분 */
	SHOP_TYPE("매장 구분"),
		ONLINE_SHOP("온라인 매장"),
		OFFLINE_SHOP("오프라인 매장"),

		
	/** 판매 채널*/
	CHANNEL_TYPE("판매채널 "),
		CHANNEL_ONSITE("현장"),
		CHANNEL_MOBILE("모바일"),
		CHANNEL_WEB("웹"),
		CHANNEL_TABLET("테블릿"),
	
		
	/** 예약상태 */
	RESERVATION_STATE("예약상태"),
		/** 예약중 */
		RESERVATION_IN_PROGRESS("예약중"),
		/** 예약완료 */
		RESERVATION_IN_COMPLATE("예약완료"),
		/** 예약취소 */
		RESERVATION_IN_CANCEL("예약취소"),
	
	/** 예약 타입  */
	RESERVATION_TYPE("예약타입"),
		RESERVATION_NOMAL("일반"),
		RESERVATION_ADVISER("상담원"),
		RESERVATION_GROUP("단체"),
		RESERVATION_ADVISER_GROUP("상담원 단체"),
	
		
	
	
	// 예약 RESPONSE
	RESERVATION_SUCCESS("예약 성공") , 
	RESERVATION_FAIL("예약 실패");
	
	private String codeName;

	ReservationCode (String codeName) {
		this.codeName = codeName;
	}
	
	/**
	 * 역할 코드 이름
	 * @return
	 */
	public String getCodeName() {
		return codeName;
	}
	
	@Deprecated
	public static String isPermission(AuthorizationCode[] authorizationCodes, String permissionCode){
		
		return "";
	}
	
	public static ReservationCode SHOP_TYPE_GROUP[]= {ONLINE_SHOP,OFFLINE_SHOP};
}
