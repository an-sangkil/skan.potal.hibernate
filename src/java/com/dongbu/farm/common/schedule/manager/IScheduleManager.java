/*
 * $Id: ISchduleManager.java ,v 1.1 2011. 3. 7. 오후 6:08:31 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.manager;

import java.util.List;
import java.util.Map;

import com.dongbu.farm.common.schedule.ScheduleException;
import com.dongbu.farm.common.schedule.model.Schedule;

public interface IScheduleManager {
	
	/**
	 * 한달 영역안의 전체 일정 모두 표시
	 * @param searchMap
	 * @return
	 * @throws ScheduleException
	 */
	public List<Schedule> getSchduleList(Map<String,String> searchMap) throws ScheduleException;
	
	/**
	 * 선택한 날짜의 일정 및 메모
	 * @param searchMap
	 * @return
	 * @throws ScheduleException
	 */
	public Schedule getSchedule(Map<String,String> searchMap) throws ScheduleException;
	
	/**
	 * 스케쥴 등록
	 * @param schedule
	 * @throws ScheduleException
	 */
	public abstract void writeSchedule(Schedule schedule)throws ScheduleException;

	/**
	 * 스케줄 수정
	 * @param schedule
	 * @throws ScheduleException
	 */
	public abstract void modifySchedule(Schedule schedule)throws ScheduleException;
	
	/**
	 * 스케중 삭제
	 * @param schedule
	 * @throws ScheduleException
	 */
	public void deleteSchedule(Schedule schedule)throws ScheduleException;
}
