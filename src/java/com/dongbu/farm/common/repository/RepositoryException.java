/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 11.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository;

public class RepositoryException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RepositoryException(){
		super();
	}
	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
	public RepositoryException(String message) {
		super(message);
	}
	public RepositoryException(Throwable cause) {
		super(cause);
	}
}
