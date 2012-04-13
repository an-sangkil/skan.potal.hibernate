/*
 * $Id: CategoryException.java ,v 1.1 2011. 3. 28. 오후 3:43:12 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 28.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.category;

public class CategoryException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3707291876275846717L;

	/**
	 * 
	 */
	public CategoryException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CategoryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CategoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CategoryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
