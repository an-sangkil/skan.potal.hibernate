package com.skan.tms.web.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.SeatLevelChartManagement;

public interface SeatLevelChartManagementJpaRepository extends JpaRepository<SeatLevelChartManagement, String>, QueryDslPredicateExecutor<SeatLevelChartManagement> {

	Page<SeatLevelChartManagement> findByseatChartNameContaining(String searchName, Pageable pageable);

	SeatLevelChartManagement findByseatChartId(Integer seatChartId);

	
}
