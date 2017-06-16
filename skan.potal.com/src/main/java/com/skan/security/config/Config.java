/**
 * 
 */
package com.skan.security.config;

/**
 * <pre>
 * Description : 암호화 모델에 사용될 기본 클래스 
 * 				 
 * @author skan
 * @since 2016. 5. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class Config {
	
	public static final String DEFAULT_SECURITY_KEY = "SKAN_Corporation";
	public static final int DEFAULT_SALT = 16;
	
	private String secretKey;
	private int  saltLength;
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public int getSaltLength() {
		return saltLength;
	}
	public void setSaltLength(int saltLength) {
		this.saltLength = saltLength;
	}
}
