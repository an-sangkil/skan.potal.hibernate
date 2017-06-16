package com.skan.tms.mobile.web.model;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> extends CommonObject<T> {
	
	/**
	 * Http 상태 Result 
	 */
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	/**
	 * Http 상태 Result
	 * @param httpStatus
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
