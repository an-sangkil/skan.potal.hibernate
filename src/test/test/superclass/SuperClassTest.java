package test.superclass;
/*
 * $Id: SuperClassTest.java ,v 1.1 2010. 9. 8. 오후 2:48:11 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */

public class SuperClassTest {
	public static void main(String []ages){
		SuperClassTestB sup_C_B = new SuperClassTestB();
		
		SuperClassTestA sup_C_A = new SuperClassTestA();
		
		System.out.println("---------------------------------------------");
		sup_C_B.CharacterTestB("");
		System.out.println("---------------------------------------------");
		sup_C_B.CharacterTestA();
		
		System.out.println("#############################################");
		
		sup_C_A.CharacterTestA();
		System.out.println("---------------------------------------------");
		sup_C_A.CharacterTestB("");
	}
}
