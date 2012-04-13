package test.superclass;
/*
 * $Id: SuperClassTestB.java ,v 1.1 2010. 9. 8. 오후 2:11:34 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */

public class SuperClassTestB extends SuperClassTestA{
	public SuperClassTestB() {
		// TODO Auto-generated constructor stub
		System.out.println("constructor B");
	}
	
	@Override
	public void CharacterTestA(){
		System.out.println("Child Class");
	}
	
	@Override
	public void CharacterTestB(String name){
		super.CharacterTestA();
		super.CharacterTestB(name);
	}
	
}