package com.skan.potal.web.potal.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.potal.hibernate.application.model.Schedule;

/**
 * SAMPLE
 * @author ask
 *
 */
public interface ScheduleDao extends JpaRepository<Schedule, Long>  {
	
}