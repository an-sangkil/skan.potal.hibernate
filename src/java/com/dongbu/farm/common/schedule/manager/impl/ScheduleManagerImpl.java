/*
 * $Id: SchduleManagerImpl.java ,v 1.1 2011. 3. 7. 오후 6:08:56 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.common.schedule.ScheduleException;
import com.dongbu.farm.common.schedule.dao.IScheduleDao;
import com.dongbu.farm.common.schedule.manager.IScheduleManager;
import com.dongbu.farm.common.schedule.model.Schedule;

@Service
public class ScheduleManagerImpl implements IScheduleManager{
	
	@Autowired
	private IScheduleDao scheduleDaoImpl;  

	@Override
	public List<Schedule> getSchduleList(Map<String, String> searchMap) throws ScheduleException {
		
		return this.scheduleDaoImpl.getSchduleList(searchMap);
	}

	@Override
	public Schedule getSchedule(Map<String, String> searchMap) throws ScheduleException {
		
		return this.scheduleDaoImpl.getSchedule(searchMap);
	}

	@Override
	public void writeSchedule(Schedule schedule) throws ScheduleException {
		this.scheduleDaoImpl.writeSchedule(schedule);
		
	}

	@Override
	public void modifySchedule(Schedule schedule) throws ScheduleException {
		this.scheduleDaoImpl.modifySchedule(schedule);
		
	}

	@Override
	public void deleteSchedule(Schedule schedule) throws ScheduleException {
		this.scheduleDaoImpl.deleteSchedule(schedule);
		
	}
}
