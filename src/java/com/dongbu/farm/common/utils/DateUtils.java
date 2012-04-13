/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 7.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat sfLong = new SimpleDateFormat("yyyyMMddHHmmss");
	static SimpleDateFormat msfLong = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat sfShort = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat sfShort2 = new SimpleDateFormat("yyMM");
	static SimpleDateFormat sfShort3 = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat sfShort4 = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat yy = new SimpleDateFormat("yyyy");
	static SimpleDateFormat mm = new SimpleDateFormat("MM");
	
	/**
	 * yyyyMMddHHmmss is Return
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date){
		String result = null;
		
		if(date != null ){
			result = sfLong.format(date);
		}
		return result;
	}
	
	/**
	 * MSTYPE yyyy-MM-dd hh:mm:ss return
	 * @param date
	 * @return
	 */
	public static String getMSDateTimeString(Date date){
		String result = null;
		
		if(date != null ){
			result = msfLong.format(date);
		}
		return result;
	}
	
}
