/**
 * <pre>
 * Class Name  : UUIDUtils.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 12. 30.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 12. 30.
 * @version 
 *
 * Copyright (C) 2015 by SKAN.COMPANY All right reserved.
 */
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
	
	/**
	 * 패턴에 의한 날짜를 String 형태로 반환한다.
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getToDayString(String pattern) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 지정된 날짜와, 지정된 패턴으로 형태 변형한다.
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