package com.skan.potal.common.code;

/**
 * 
 * Description : 결제 와 관련된 코드 ENUM
 * @author skan
 * @since  2017. 2. 10.
 * @version 
 *
 * Copyright (C) 2017 by SKAN Corp. All right reserved.
 */
public enum PaymentCode {

		
	/** 예약상태 */
	PAYMENT_STATE("예약상태"),
		/** 결제완료 */
		PAYMENT_IN_COMPLATE("결제완료"),
		/** 결제진행중 */
		PAYMENT_IN_PROGRESS("결제진행중"),
		/** 결제취소 */
		PAYMENT_IN_CANCEL("결제취소"),
	
	/**결제 수단 */	
	PAYMENT_TYPE("결재수단"),
		CARD("신용카드"),
		CASH("현금"),
		MOBILE("모바일"),
		VIRTUAL_ACCOUNT("가상계좌"),
		EASY_PAY("간편결재"),
		ACCOUNT_TRRANSFER("계좌이체"),
		ON_SITE("현장 결제"),
		;
	
	
	private String codeName;

	PaymentCode (String codeName) {
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
	 * 결재수단
	 */
	public static PaymentCode GROUOP_PAYMENT_TYPE[] = {CARD, CASH,VIRTUAL_ACCOUNT, EASY_PAY , ACCOUNT_TRRANSFER};
	
}
