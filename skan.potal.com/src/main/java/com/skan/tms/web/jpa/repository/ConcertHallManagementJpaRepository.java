package com.skan.tms.web.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ConcertHallManagement;

/**
 * 
 * Description : 공연장 관리  
 * @author skan
 * @since  2016. 10. 26.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface ConcertHallManagementJpaRepository extends JpaRepository<ConcertHallManagement, String>, QueryDslPredicateExecutor<ConcertHallManagement> {

	Page<ConcertHallManagement> findByConcertHallNameContaining(String searchName, Pageable pageable);

}
