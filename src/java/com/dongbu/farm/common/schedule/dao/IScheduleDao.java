/*
 * $Id: IScheduleDao.java ,v 1.1 2011. 3. 7. 오후 6:09:46 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.common.schedule.model.Schedule;

public interface IScheduleDao {
	
	/**
	 * 해당하는 월의 전체 스케쥴 리스트
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<Schedule> getSchduleList(Map<String, String> searchMap) throws DataAccessException;
	
	/**
	 * 선택된 하나의 스케쥴 상세 검색
	 * @param searchMap
	 * @return
	 * @throws DataAccessException
	 */
	public abstract Schedule getSchedule(Map<String, String> searchMap) throws DataAccessException;
	
	/**
	 * 스케줄 저장
	 * @param schedule
	 * @throws DataAccessException
	 */
	public abstract void writeSchedule(Schedule schedule)throws DataAccessException;

	/**
	 * 스케줄 수정
	 * @param schedule
	 * @throws DataAccessException
	 */
	public abstract void modifySchedule(Schedule schedule)throws DataAccessException;
	
	/**
	 * 스케줄 삭제
	 * @param schedule
	 * @throws DataAccessException
	 */
	public abstract void deleteSchedule(Schedule schedule)throws DataAccessException;

}
