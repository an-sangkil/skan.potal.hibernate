package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ReservationHistory;

/**
 * 
 * Description : 좌석 예매 내역 히스토리   
 * @author skan
 * @since  2016. 11. 15.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface ReservationHistoryJpaRepository extends JpaRepository<ReservationHistory, String>, QueryDslPredicateExecutor<ReservationHistory> {
	
	

}
