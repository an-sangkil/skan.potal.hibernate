package com.skan.potal.web.potal.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.potal.hibernate.application.model.Schedule;


/**
 * SAMPLE
 * @author ask
 *
 */
//public interface ScheduleRepository extends JpaRepository<CmtbSchedule, Long> ,QueryDslPredicateExecutor<CmtbSchedule> {
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{	
}