package com.skan.tms.mobile.web.model;

/**
 * 
 * Description : 관리자 화면 > 좌석, 잔여좌석
 * @author skan
 * @since  2017. 5. 26.
 * @version 
 *
 * Copyright (C) 2017 by SKAN Corp. All right reserved.
 */
public class SeatGrade {
	private String gradeName;
	private String gradeColor;
	private long bySeatLevelTotalCount;
	private long bySeatLevelCompleteCount;
	
	public SeatGrade(long bySeatLevelTotalCount, long bySeatLevelCompleteCount, String gradeColor, String gradeName) {
		this.gradeColor=gradeColor;
		this.gradeName=gradeName;
		this.bySeatLevelCompleteCount=bySeatLevelCompleteCount;
		this.bySeatLevelTotalCount=bySeatLevelTotalCount;
		
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeColor() {
		return gradeColor;
	}
	public void setGradeColor(String gradeColor) {
		this.gradeColor = gradeColor;
	}
	public long getBySeatLevelTotalCount() {
		return bySeatLevelTotalCount;
	}
	public void setBySeatLevelTotalCount(long bySeatLevelTotalCount) {
		this.bySeatLevelTotalCount = bySeatLevelTotalCount;
	}
	public long getBySeatLevelCompleteCount() {
		return bySeatLevelCompleteCount;
	}
	public void setBySeatLevelCompleteCount(long bySeatLevelCompleteCount) {
		this.bySeatLevelCompleteCount = bySeatLevelCompleteCount;
	}
}