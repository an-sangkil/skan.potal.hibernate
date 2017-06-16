package com.skan.security.hash.salt;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.util.StringUtils;

/**
 * Description : Sha-256 메세지 다이제스트 Salt 로 보안 강화.  160bit hash 
 * @author skan
 * @since 2016. 9. 26.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class DigestUtils {
	
	private static final int STRENGTH = 256;
	private static final int SALT_LENGTH = 16;
	private static final int ITERATION_COUNT = 10000;
	
	public static String encodePassword(String rawPassword) {
		
		if(StringUtils.isEmpty(rawPassword)){
			return null;
		}
		
		// SHA Hash 초기화
		ShaPasswordEncoder spe = new ShaPasswordEncoder(STRENGTH);
		spe.setEncodeHashAsBase64(true);
		spe.setIterations(ITERATION_COUNT);
		
		String salt = KeyGenerators.string().generateKey();
		
		String encodeText = spe.encodePassword(rawPassword, salt);
		return String.format( "%s%s", salt, encodeText );
	}
	
	/**
	 * 메세지 다이제스트 동일 여부 확인.
	 * @param encPass	암호화
	 * @param rawPass	원본 
	 * @return
	 */
	public static boolean  isPasswordValid(String encPass, String rawPass) {
		
		if(StringUtils.isEmpty(encPass) && StringUtils.isEmpty(rawPass)){
			return false;
		}
		
		// SHA Hash 초기화
		ShaPasswordEncoder spe = new ShaPasswordEncoder(STRENGTH);
		spe.setEncodeHashAsBase64(true);
		spe.setIterations(ITERATION_COUNT);
		
		// salt 추출 
		String salt = encPass.substring(0, SALT_LENGTH);
		encPass =  encPass.substring(SALT_LENGTH,encPass.length());
		return spe.isPasswordValid(encPass, rawPass, salt);
		
	}
	
}
