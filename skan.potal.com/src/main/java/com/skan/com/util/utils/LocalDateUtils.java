package com.skan.com.util.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Description : 
 * @author skan
 * @since  2016. 12. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class LocalDateUtils {
	
	/**
	 * java.util.Date 를 java.time.LocalDate 로 변환한다.
	 * @param date
	 * @return
	 */
	public static LocalDate dateConvertToLocalDate(Date date){
		java.util.Date newDate = date;
		if(date instanceof java.sql.Date){
			newDate = new java.util.Date(date.getTime());
		}
		LocalDate localDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}
	
	/**
	 * java.time.LocalDate 를 java.util.Date 변환
	 * @param localDate
	 * @return
	 */
	public static Date localDateConvertToDate(LocalDate localDate){
		
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	/**
	 * 오늘 날짜에서 한달간의 기간별 날짜 구해오기 form ~ to date
	 * @return
	 */
	public static List<LocalDate> periodOneMonthLocalDate() {
		LocalDate localDate = LocalDate.now();
		LocalDate beforDate = localDate.minusMonths(1L);
		
		List<LocalDate> days = new LinkedList<>();
		while(!beforDate.isAfter(localDate)){
			beforDate = beforDate.plusDays(1);
			days.add(beforDate);
		}
		return days;
	}
	
	/**
	 * 기간별 날짜 구해오기 form ~ to date
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static List<LocalDate> periodLocalDate(LocalDate fromDate , LocalDate toDate) {
		List<LocalDate> days = new LinkedList<>();
		while(!fromDate.isAfter(toDate)){
			days.add(fromDate);
			fromDate = fromDate.plusDays(1);
		}
		return days;
	}
	
}
