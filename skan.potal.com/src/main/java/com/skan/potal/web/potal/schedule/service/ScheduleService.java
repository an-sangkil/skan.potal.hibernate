package com.skan.potal.web.potal.schedule.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.skan.potal.web.potal.common.model.Schedule;
import com.skan.potal.web.potal.common.service.BaseService;
import com.skan.potal.web.potal.schedule.dao.ScheduleDao;


/**
 * <pre>
 * Class Name  : SchduleService.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 8. 7.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 8. 7.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */

@Service
public class ScheduleService implements BaseService<Schedule, Long>{

	@Autowired ScheduleDao scheduleDao;

	@Override
	public Schedule insert(Schedule t) {
		return scheduleDao.save(t);
	}

	@Override
	public List<Schedule> findAllDatas() {
		return scheduleDao.findAll();
	}

	@Override
	public Schedule findData(Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Schedule> pageFindDatas(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteData(Schedule t) {
		scheduleDao.delete(t);
	}

	@Override
	public List<Schedule> findAllDatas(Iterator<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
