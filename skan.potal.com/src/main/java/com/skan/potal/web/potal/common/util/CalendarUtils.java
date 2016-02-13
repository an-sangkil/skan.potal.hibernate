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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author ahn
 *
 */
public class CalendarUtils {
	
	public enum CalendarPattermn {
		
		CALENDER_TYPE_YYYY_MM_DD("yyyy-MM-dd");

		private String pattern;
		
		private CalendarPattermn(String patternStr) {
			this.pattern=patternStr;
		}

		public String getPattern() {
			return pattern;
		}
	}
	
	public final static String CALENDER_TYPE_YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 패턴에 의한 날짜를 String 형태로 반환한다.
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getToDayString(CalendarPattermn pattern) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern.getPattern());
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
	
	public static Date convertStringToDate(String date, CalendarPattermn calendarPattermn) throws Exception {
		if(StringUtils.isEmpty(date)){
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat ( calendarPattermn.getPattern());
		return formatter.parse(date); 
	}
	
	public static Date convertStringToDate(String date, Locale locale, CalendarPattermn calendarPattermn){
		
		SimpleDateFormat formatter = new SimpleDateFormat ( calendarPattermn.getPattern() , locale);
		ParsePosition pos = new ParsePosition ( 0 );
		
		return formatter.parse(date, pos); 
	}
	
	/**
	 * 날짜에 대한 유효성 검사
	 * 
	 * @param szDate
	 * @param szFormat
	 * @return
	 */
	public static boolean checkDate(String szDate, CalendarPattermn calendarPattermn) {

		boolean bResult = true;
		SimpleDateFormat oDateFormat = new SimpleDateFormat();

		oDateFormat.applyPattern(calendarPattermn.getPattern());
		oDateFormat.setLenient(false); // 엄밀하게 검사한다는 옵션 (반드시 있어야 한다)

		try {
			oDateFormat.parse(szDate);
		} catch (ParseException e) {
			bResult = false;
		}
		return bResult;
	}
	
	/**
	 * 날짜 더하기
	 * @param criteriaDate 기준 날짜
	 * @param addMonths	추가할 달수
	 */
	public static Date addDate(Date criteriaDate ,int addDays ,int addMonths ) {
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// criteriaDate = df.parse("2013-02-35");
		// 날짜 더하기
		Calendar cal = Calendar.getInstance();
		cal.setTime(criteriaDate);
		if (addDays != 0) {
			cal.add(Calendar.DATE, addDays);
		}
		if (addMonths != 0) {
			cal.add(Calendar.MONTH, addMonths);
		}
		// df.format(cal.getTime());
		return cal.getTime();
	}
	
}
