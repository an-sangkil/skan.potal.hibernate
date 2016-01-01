package com.skan.potal.web.potal.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author ahn
 *
 */
public class CalendarUtils {
	
	public final static String CALENDER_TYPE_YYYY_MM_DD = "yyyy-MM-dd";
	
	public static String getToDayString(String pattern) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertDateToString(Date date, String pattern){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
	
}
