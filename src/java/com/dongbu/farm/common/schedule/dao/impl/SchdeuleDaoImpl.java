/*
 * $Id: SchduleDaoImpl.java ,v 1.1 2011. 3. 7. 오후 6:10:20 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.common.schedule.dao.IScheduleDao;
import com.dongbu.farm.common.schedule.model.Schedule;

@Repository
public class SchdeuleDaoImpl implements IScheduleDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	private static String SCHDULE_STATEMENTNAME = "common.schdule."; 

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getSchduleList(Map<String, String> searchMap)throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList(SCHDULE_STATEMENTNAME + "getSchduleList" , searchMap);
	}

	@Override
	public Schedule getSchedule(Map<String, String> searchMap) throws DataAccessException {
		return (Schedule) this.sqlMapClientTemplate.queryForObject(SCHDULE_STATEMENTNAME + "getSchedule" , searchMap);
	}

	@Override
	public void writeSchedule(Schedule schedule) throws DataAccessException {
		
		//STD_DATE 필드의 값중 현재 등록하려는 스케줄의 최대 증가값을 가져온다.
		String seq = (String)this.sqlMapClientTemplate.queryForObject(SCHDULE_STATEMENTNAME + "writeScheduleKey" , schedule.getStd_date());

		//증가된값을 셋팅
		schedule.setSch_seq(seq);
		
		this.sqlMapClientTemplate.insert(SCHDULE_STATEMENTNAME + "writeSchedule" , schedule);
	}

	@Override
	public void modifySchedule(Schedule schedule) throws DataAccessException {
		this.sqlMapClientTemplate.update(SCHDULE_STATEMENTNAME + "modifySchedule" , schedule);		
	}

	@Override
	public void deleteSchedule(Schedule schedule) throws DataAccessException {
		this.sqlMapClientTemplate.delete(SCHDULE_STATEMENTNAME + "deleteSchedule" , schedule);		
	}
	
}
