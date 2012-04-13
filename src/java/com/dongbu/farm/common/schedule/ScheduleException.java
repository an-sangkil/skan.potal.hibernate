/*
 * $Id: SchduleException.java ,v 1.1 2011. 3. 10. 오전 9:52:27 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 10.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule;

public class ScheduleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ScheduleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScheduleException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ScheduleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ScheduleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
