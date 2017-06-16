package com.skan.com.util.utils;

import java.security.SecureRandom;

public class NumberUtils {
	
	/**
	 * 난수 생성기 
	 * @param length  난수 길이 
	 * @return
	 */
	public static String randonNumberStr(int length) {
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length ; i++) {
			SecureRandom secureRandom = new SecureRandom();
			int c = secureRandom.nextInt(10);
			sb.append(c);
		}
		return sb.toString();
	}
}
