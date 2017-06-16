package com.skan.com.util.utils;

/**
 * <pre>
 * Class Name  : StringHexUtils.java
 * Description : 핵사 코드변환기 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 19.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 19.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class HexUtils {

	/**
	 * hex to byte[]
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	/**
	 * byte[] to hex
	 * 
	 * @param ba
	 * @return
	 */
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	/**
	 * 핵사코드 변환 String => hex code
	 * 
	 * @param str
	 * @return
	 */
	public static String convertStringToHex(String str) {
		
		if(str == null) {
			return null;
		}
		
		char[] chars = str.toCharArray();
		StringBuffer hexSb = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			hexSb.append(Integer.toHexString((int) chars[i]));
		}

		return hexSb.toString();
	}
	
	/**
	 * 핵사코드를  -> 스트링으로 변환한다.
	 * 핵사 코드 변환 Hex <=> Decimal <=> Char
	 * 
	 * @param hexCode
	 * @return
	 */
	public static String convertHexToString(String hexCode) {
		
		if(hexCode == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < hexCode.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hexCode.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
		}
		return sb.toString();
	}

}
