package com.skan.security.hash.salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description :  BCryptPasswordEncoder 를 이용한 Salt 
 * 
 * @author skan
 * @since 2016. 9. 26.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class SaltUtils {
	
	static int STRENGTH = 10;
	static String algorithm = "SHA1PRNG"; 
	
	
	
	public static String encode (String rawPassword) {
		try {
			// random number 제네레이터 (PRNG) 알고리즘을 구현하는 SecureRandom 오브젝트
			SecureRandom secureRandom = SecureRandom.getInstance(algorithm);
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(STRENGTH,secureRandom);
			return bCrypt.encode(rawPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static  boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			SecureRandom secureRandom;
			secureRandom = SecureRandom.getInstance(algorithm);
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(STRENGTH,secureRandom);
			return bCrypt.matches(rawPassword, encodedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
}
