package com.skan.tms.mobile.common.code;

public enum DateTypeCode {
	
	
	TIME_TYPE, // 시간유형 
	PERIOD_TYPE, // 기간
	REPEAT_TYPE,//반복 타입
		EVERY_MONTH_TYPE,
		EVERY_WEEK_TYPE,
		
	PARENT_TYPE, // 부모유형
		SALE, // 할인관리
		HOLIDAY, // 휴일관리
		CAPACITY, // 정원관리
		
	SALE_PRICE_TYPE,// 할인율/금액 구분
		AMOUNT,//금액
		PERCENT, //할인율
	// 주차 
	WEEK_TYPE, //주 타입 
		FIRST_WEEK,
		SECOND_WEEK,
		THIRD_WEEK,
		FOURTH_WEEK,
		FIVETH_WEEK,
	
	// 요일
	DAY_OF_WEEK_TYPE, //요일 타입
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY ;
}
