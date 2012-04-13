/*
 * $Id: LoginException.java ,v 1.1 2010. 12. 1. 오후 4:08:40 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2010. 12. 1.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.login;

public class LoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LoginException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public LoginException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LoginException(Throwable cause) {
		super(cause);
	}

}
