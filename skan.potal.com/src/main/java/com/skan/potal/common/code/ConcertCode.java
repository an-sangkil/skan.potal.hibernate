package com.skan.tms.mobile.common.code;

/**
 * 
 * Description : 공연코드
 * @author skan
 * @since  2016. 10. 26.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public enum ConcertCode {

	//
	SEAT_LEVEL_STATUS("좌석등급 배치도 상태"),
		ACTIVATION("활성화"),
		DISABLED("비활성화"),
		NOMAL("정상"),
		HOLDING("홀딩"),
		BROKEN("고장"),
	CONCERT_TYPE("공연종류"),
		NOMAL_CONCERT("일반공연"),
		INVITATION_CONCERT("초대공연"),
		PLAN_CONCERT("기획공연"),
		RENT_CONCERT("대관공연"),
	CONCERT_STATUS("공연상태"),
		CONCERT_START("시작"),
		CONCERT_END("종료"),
		CONCERT_WAIT("대기"),
	PAYMENT_TYPE("결재수단"),
		CARD("신용카드"),
		CASH("현금"),
		VIRTUAL_ACCOUNT("가상계좌"),
		EASY_PAY("간편결재"),
		ACCOUNT_TRRANSFER("계좌이체"),
	SEAT_STATUS("좌석 상태"),
		SEAT_BOOKING("예매중"),
		SEAT_BOOKING_COMPLETED("예매완료"),
		SEAT_EMPTY("비어있음");

	
	
	private String codeName;

	ConcertCode (String codeName) {
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
	 * 좌석 등급 배치도 상태 그룹
	 */
	public static ConcertCode GROUOP_SEAT_LEVEL_STATUS[] = {ACTIVATION, DISABLED,NOMAL,HOLDING,BROKEN};
	
	/**
	 * 공연종류 그룹
	 */
	public static ConcertCode GROUOP_CONCERT_TYPE[] = {NOMAL_CONCERT, INVITATION_CONCERT,PLAN_CONCERT,RENT_CONCERT};
	
	/**
	 * 공연상태
	 */
	public static ConcertCode GROUOP_CONCERT_STATUS[] = {CONCERT_START, CONCERT_END,CONCERT_WAIT};
	
	/**
	 * 결재수단
	 */
	public static ConcertCode GROUOP_PAYMENT_TYPE[] = {CARD, CASH,VIRTUAL_ACCOUNT, EASY_PAY , ACCOUNT_TRRANSFER};
	
}
