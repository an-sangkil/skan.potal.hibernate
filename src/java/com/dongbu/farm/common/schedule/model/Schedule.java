/*
 * $Id: Schedule.java ,v 1.1 2011. 3. 7. 오후 6:06:12 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.model;

import com.dongbu.farm.common.CommonElementAbst;

/**
 * 일정계획 모델러
 * @author skan
 *
 */
public class Schedule extends CommonElementAbst{
	
	//sch : schedule;
	private String sch_mgt_no; 		//일정 관리번호
	private String sch_seq; 		//같은날의 번호
	private String sch_subject; 	//제목
	private String sch_content; 	//내용
	private String std_date;		//standard_date 기준날짜
	private String today_weather_code;	//오늘날씨
	
	private String min_degreespoint_code; //최고온도 (영상/영하) 구분
	private String min_temperature;	//최저온도
	private String max_degreespoint_code; //최고온도 (영상/영하) 구분
	private String max_temperature;	//최고온도
	
	
	/**
	 * @return the min_degreespoint_code
	 */
	public String getMin_degreespoint_code() {
		return min_degreespoint_code;
	}
	/**
	 * @param min_degreespoint_code the min_degreespoint_code to set
	 */
	public void setMin_degreespoint_code(String min_degreespoint_code) {
		this.min_degreespoint_code = min_degreespoint_code;
	}
	/**
	 * @return the max_degreespoint_code
	 */
	public String getMax_degreespoint_code() {
		return max_degreespoint_code;
	}
	/**
	 * @param max_degreespoint_code the max_degreespoint_code to set
	 */
	public void setMax_degreespoint_code(String max_degreespoint_code) {
		this.max_degreespoint_code = max_degreespoint_code;
	}
	/**
	 * @return the sch_seq
	 */
	public String getSch_seq() {
		return sch_seq;
	}
	/**
	 * @param sch_seq the sch_seq to set
	 */
	public void setSch_seq(String sch_seq) {
		this.sch_seq = sch_seq;
	}
	/**
	 * @return the sch_mgt_no
	 */
	public String getSch_mgt_no() {
		return sch_mgt_no;
	}
	/**
	 * @param sch_mgt_no the sch_mgt_no to set
	 */
	public void setSch_mgt_no(String sch_mgt_no) {
		this.sch_mgt_no = sch_mgt_no;
	}
	/**
	 * @return the sch_subject
	 */
	public String getSch_subject() {
		return sch_subject;
	}
	/**
	 * @param sch_subject the sch_subject to set
	 */
	public void setSch_subject(String sch_subject) {
		this.sch_subject = sch_subject;
	}
	/**
	 * @return the sch_content
	 */
	public String getSch_content() {
		return sch_content;
	}
	/**
	 * @param sch_content the sch_content to set
	 */
	public void setSch_content(String sch_content) {
		this.sch_content = sch_content;
	}
	/**
	 * @return the std_date
	 */
	public String getStd_date() {
		return std_date;
	}
	/**
	 * @param std_date the std_date to set
	 */
	public void setStd_date(String std_date) {
		this.std_date = std_date;
	}
	/**
	 * @return the today_weather_code
	 */
	public String getToday_weather_code() {
		return today_weather_code;
	}
	/**
	 * @param today_weather_code the today_weather_code to set
	 */
	public void setToday_weather_code(String today_weather_code) {
		this.today_weather_code = today_weather_code;
	}
	/**
	 * @return the max_temperature
	 */
	public String getMax_temperature() {
		return max_temperature;
	}
	/**
	 * @param max_temperature the max_temperature to set
	 */
	public void setMax_temperature(String max_temperature) {
		this.max_temperature = max_temperature;
	}
	/**
	 * @return the min_temperature
	 */
	public String getMin_temperature() {
		return min_temperature;
	}
	/**
	 * @param min_temperature the min_temperature to set
	 */
	public void setMin_temperature(String min_temperature) {
		this.min_temperature = min_temperature;
	}
}
