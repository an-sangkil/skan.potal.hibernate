/*
 * $Id: BoardCode.java ,v 1.1 2011. 3. 18. 오후 4:54:50 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 18.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common;

import java.io.Serializable;

public class Code implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2904480775732340306L;
	//synchronized
	/**
	 * OOO 파일 폴더이름
	 */
	public static final String HLC_FILE_PATH = "HSCL";
	
	
	
	//******** 			일반코드 			*********//
	
	
	/**
	 * 일정관리
	 */
	public static final String DONGBU_CODE_SCHEDULE = "SCH";
	
	/**
	 * 주소록 관리
	 */
	public static final String DONGBU_CODE_ADDRESS = "ADS";
	
	/**
	 * 게시판
	 */
	public static final String DONGBU_CODE_BOARD = "BOR";
	
	
	
	
	//******** 			권한 코드 			*********//
	
	/**
	 * 시스템관리자
	 */
	public static final String SYSTEM_ADMIN = "S_ADMIN";
	
	/**
	 * 일반 관리자
	 */
	public static final String NOMAL_ADMIN = "N_ADMIN";

}
