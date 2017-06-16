package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ReservationHistoryLog;

/**
 * 좌석 예매 내역 로그 
 * Description : 
 * @author skan
 * @since  2016. 11. 23.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface ReservationHistoryLogJpaRepository extends JpaRepository<ReservationHistoryLog, Long>, QueryDslPredicateExecutor<ReservationHistoryLog> {

}
