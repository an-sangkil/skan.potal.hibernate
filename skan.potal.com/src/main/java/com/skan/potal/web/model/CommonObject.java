package com.skan.tms.mobile.web.model;

import com.skan.tms.mobile.common.code.CommonCode;

/**
 * Description : 공통 모델에서 사용할 오브젝트
 * 				  응답에 대한 에러/성공 메세지와 코드를 담고 있다.  
 * @author skan
 * @since 2016. 9. 21.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public abstract class CommonObject<T> {
	
	/** 응답시 넘겨줄 객체 를 제네릭으로 선언하여 사용한다. */
	private T responseObject;
	
	/** 응답 메세지 */
	private String responseMessage;
	
	/**
	 * 상태코드
	 * {
	 * 		SUCCESS : 성공
	 * 		FAIL	: 실패
	 * } 
	 * 
	 */
	private CommonCode stateCode;
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public CommonCode getStateCode() {
		return stateCode;
	}

	public void setStateCode(CommonCode stateCode) {
		this.stateCode = stateCode;
	}
	
}
