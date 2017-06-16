/**
 * 
 */
package com.skan.com.util.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/**
 * Description : 
 *
 * @author skan
 * @since 2016. 5. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class DateUtils {
	public enum CalendarPattermn {
		
		CALENDER_TYPE_YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd hh:mm:ss"),
		CALENDER_TYPE_YYYY_MM_DD("yyyy-MM-dd"),
		CALENDER_TYPE_YYYY_MM_DD_COMMA("yyyy.MM.dd"),
		CALENDER_TYPE_YYYYMMDD("yyyyMMdd"),
		CALENDER_TYPE_YYYYMM("yyyyMM");

		private String pattern;
		
		private CalendarPattermn(String patternStr) {
			this.pattern=patternStr;
		}

		public String getPattern() {
			return pattern;
		}
	}
	
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
	
	/**
	 * 
	 * @param addMonth
	 * @return
	 * @throws Exception
	 */
	public static long addMonth(int addMonth) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();

		cal.add(Calendar.MONTH, addMonth);
		Date date = cal.getTime();
		
		return date.getTime();
	}
	
	/**
	 * 
	 * @param addMonth
	 * @return
	 * @throws Exception
	 */
	public static long addDay(int day) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();

		cal.add(Calendar.DATE, day);
		Date date = cal.getTime();
		
		return date.getTime();
	}
	
	
	/**
	 * 
	 * @param date
	 * @param addMonth
	 * @return
	 * @throws Exception
	 */
	public static long addMinute(Date date , int addMinute) throws Exception {

		GregorianCalendar cal = new GregorianCalendar();
		
		cal.setTime(date);
		cal.add(Calendar.MINUTE, addMinute);
		Date modifyDate = cal.getTime();
		
		return modifyDate.getTime();
	}
	
	public static String timeGap(long start , long end) {
		SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm:ss.SSSZ");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		long gap = end - start ;
		
		return sdf.format(new Date(gap)); 
	}
	
	public static String toDay(String timeFormat) {
		
		if(StringUtils.isEmpty(timeFormat)) {
			timeFormat = "yyyy-MM-dd hh:mm:ss";
		}
		
		SimpleDateFormat sdf =  new SimpleDateFormat(timeFormat);
		
		return sdf.format(new Date());
	}
	
	
	/**
	 * 년 월 일을 받아 Long 타입의 세컨즈로 변환.
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static long dateFormatSecond(int year , int month, int date) {
		
		ZoneId zoneId =  ZoneId.systemDefault();
		LocalDate today= LocalDate.of(year,month,date);
		
		return today.atStartOfDay(zoneId).toEpochSecond();
	}
	
	public static Calendar dateConvertCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
