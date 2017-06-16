package com.skan.security;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import com.skan.com.util.utils.HexUtils;
import com.skan.security.semmetric.CryptoStringUtils;

/*
 * 제품 인증서 파일 생성.
 */
public class AuthenticationCretificate {

	public void cretificateFileCreate() {

		String cretificateFileName = "SKANCorp.crt";
		String securityKeyStr = "SKANCrop";

		try (FileOutputStream fos = new FileOutputStream(new File(cretificateFileName));) {

			// 암호화
			String encryptStr = CryptoStringUtils.encrypt(securityKeyStr);

			// 헥사
			String hexCodeStr = HexUtils.convertStringToHex(encryptStr);

			// Base64
			byte[] base64Byte = Base64.getEncoder().encode(hexCodeStr.getBytes("utf-8"));

			// XXX 인코딩

			// 파일 쓰기
			fos.write(base64Byte);
			fos.flush();
			fos.close();

			System.out.println("******************************************");
			System.out.println("*                SKANCorp                 *");
			System.out.println("*         암호화 파일을 생성 하였습니다                                    *");
			System.out.println("******************************************");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
