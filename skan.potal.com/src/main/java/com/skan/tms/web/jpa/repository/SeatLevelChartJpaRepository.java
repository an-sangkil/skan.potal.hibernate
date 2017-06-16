package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.SeatLevelChart;

public interface SeatLevelChartJpaRepository extends JpaRepository<SeatLevelChart, Integer>, QueryDslPredicateExecutor<SeatLevelChart> {

	List<SeatLevelChart> findBySeatLevelChartManagement_SeatChartId(Integer integer);

}
