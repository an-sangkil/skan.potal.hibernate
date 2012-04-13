package test.superclass;
/*
 * $Id: SuperClassTest.java ,v 1.1 2010. 9. 8. 오후 2:10:43 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */

public class SuperClassTestA {
	@SuppressWarnings("unused")

	private String characterAName;
	
	public SuperClassTestA() {
		System.out.println("constructor A");
	}
	
	public void CharacterTestA() {
		characterAName = "parent Name Case 1";
		System.out.println(characterAName);
	}
	
	public void CharacterTestB(String name) {
		this.CharacterTestA();
		
		if(name.equals("")){
			name = "parent Name Case 2";
		}
		System.out.println(name);
		
	}
}
