package com.skan.com.util.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeUtils {
	
	/**
	 * Date -> LocalDateTime
	 * SqlDate 로 들어온 오브젝트는 util.date AutoBoxing 하여 사용.
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime convertToLocalDateTime(Date date) {
		
		java.util.Date newDate = date;
		if(date instanceof java.sql.Date){
			newDate = new java.util.Date(date.getTime());
		}
		
		ZonedDateTime zdt =   newDate.toInstant().atZone(ZoneId.systemDefault());
		return zdt.toLocalDateTime(); 
	}
	
	/**
	 * LocalDateTime -> Date
	 * @param localDateTime
	 * @return
	 */
	public static Date convertToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); 
	}
	
	public static LocalTime convertToLocalTime(Date date) {
		ZonedDateTime zdt =   date.toInstant().atZone(ZoneId.systemDefault());
		return zdt.toLocalTime(); 
	}
	
	/**
	 * 현재 날짜시간을 원하는 패턴으로 리턴 String
	 * @param pattern
	 * @return
	 */
	public static String convertToStringNowDate(String pattern) {
		LocalDateTime toLocalDateTime= LocalDateTime.now();
		return toLocalDateTime.format(DateTimeFormatter.ofPattern(pattern)).toString();
	}
	
	/**
	 * 원하는 날짜와 패턴으로 리턴 String
	 * @param localDateTime
	 * @param pattern
	 * @return
	 */
	public static String convertToStringDate(LocalDateTime localDateTime , String pattern) {
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern)).toString();
	}
}
