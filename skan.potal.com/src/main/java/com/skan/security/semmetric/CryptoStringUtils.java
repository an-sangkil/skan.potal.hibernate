package com.skan.security.semmetric;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import com.skan.com.util.utils.HexUtils;
import com.skan.security.config.Config;
import com.skan.security.config.CryptoConfig;

/**
 * 
 * <pre>
 * Description : 대칭키 암호  
 *
 * @author skan
 * @since 2016. 8. 24.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class CryptoStringUtils {

	/**
	 * Byte 암호화
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] bytes) throws Exception {

		if(bytes == null) {
			return null;
		}
		
		String randomKey = KeyGenerators.string().generateKey();
		Config config = CryptoConfig.getConfig();
		BytesEncryptor bytesEncryptor = Encryptors.stronger(config.getSecretKey(), randomKey);
		
		byte[] encryptedByte = bytesEncryptor.encrypt(bytes);
		
		String temp = String.format("%s:%s:%s",
				config.getSecretKey(),
				randomKey,
				HexUtils.byteArrayToHex(encryptedByte)
				);
		
		return temp.getBytes();
	}

	/**
	 * Byte 복호화
	 * 
	 * @param encryptByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] encryptBytes) throws Exception {

		if(encryptBytes == null) {
			return null;
		}
		
		String encryptText = new String( encryptBytes, "UTF-8");
		
		String param[] = encryptText.split(":");
		String secertKey =  param[0];
		String salt      =  param[1];
		String hexCode   =  param[2];
		BytesEncryptor bytesEncryptor = Encryptors.stronger(secertKey, salt);
		byte[] decryptedText = bytesEncryptor.decrypt(HexUtils.hexToByteArray(hexCode));

		return decryptedText;
	}

	/**
	 * String 암호화
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str) throws Exception {
		
		if(str == null ) {
			return null;
		}
		
		String randomKey = KeyGenerators.string().generateKey();
		Config config = CryptoConfig.getConfig();

		TextEncryptor textEncryptor = Encryptors.text(config.getSecretKey(), randomKey);
		String encryptedText = textEncryptor.encrypt(str);
		
		
		String temp = String.format("%s:%s:%s",
				config.getSecretKey(),
				randomKey,
				HexUtils.convertStringToHex(encryptedText)
				);
		
		return temp;
	}

	/**
	 * String 복호화
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptStr) throws Exception {
		
		if(encryptStr == null ) {
			return null;
		}
		
		String param[] = encryptStr.split(":");
		String secertKey	 =  param[0];
		String saltRandomVar =  param[1];
		String hexCode   	 =  param[2];

		TextEncryptor textEncryptor = Encryptors.text(secertKey, saltRandomVar);
		String decryptedText = textEncryptor.decrypt(HexUtils.convertHexToString(hexCode));

		return decryptedText;
	}

}
