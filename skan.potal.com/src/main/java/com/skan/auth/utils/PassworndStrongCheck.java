package com.skan.auth.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description : 패스워드 강약 체크
 * 
 * @author skan
 * @since 2016. 10. 4.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class PassworndStrongCheck {

	public enum Password {
		STRONG, HIGH, MIDDLE, WRONG, LOW;
	}

	public Password checkPassword(String inPassword) {

		boolean specialCharSet = false;
		boolean characterSet = false;
		boolean numberSet = false;

		List<Boolean> list = new ArrayList<>();

		for (char ch : inPassword.toCharArray()) {

			Character Character = new Character(ch);
			Character.toString();

			specialCharSet = true;
			characterSet = true;
			numberSet = true;

		}

		list.add(specialCharSet);
		list.add(characterSet);
		list.add(numberSet);

		System.out.println(list.size());
		switch (list.size()) {
		case 3:
			return Password.STRONG;
		case 2:

			return Password.MIDDLE;
		case 1:
		case 0:
		default:
			return Password.LOW;

		}
	}

	// Verison 1.0
	private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String sChar = "~!@#$%^&*()_+=-{}:";
	private static final String intChar = "0123456789";
	private static Random r = new Random();
	private static String pass = "";

	public String passwornGenerator() {
		System.out.println("Generating pass...");
		while (pass.length() != 16) {
			int rPick = r.nextInt(4);
			if (rPick == 0) {
				int spot = r.nextInt(25);
				pass += dCase.charAt(spot);
			} else if (rPick == 1) {
				int spot = r.nextInt(25);
				pass += uCase.charAt(spot);
			} else if (rPick == 2) {
				int spot = r.nextInt(7);
				pass += sChar.charAt(spot);
			} else if (rPick == 3) {
				int spot = r.nextInt(9);
				pass += intChar.charAt(spot);
			}
		}
		System.out.println("Generated Pass: " + pass);
		
		return pass;
	}

	// public static void main(String[] args) throws Exception {
	// PassworndStrongCheck passworndStrongCheck = new PassworndStrongCheck();
	// Password ps = passworndStrongCheck.checkPassword("1234qwer");
	// System.out.println(ps);
	// }

}
