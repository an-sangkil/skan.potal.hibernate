/*
 * $Id: BoardException.java ,v 1.1 2010. 12. 1. 오후 4:16:08 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2010. 12. 1.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.board;

public class BoardException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6630285438027823789L;

	/**
	 * 
	 */
	public BoardException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BoardException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BoardException(Throwable cause) {
		super(cause);
	}
}
