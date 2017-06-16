package com.skan.tms.web.jpa.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ConcertSeatGradePrice;

public interface ConcertSeatGradePriceJpaRepository extends JpaRepository<ConcertSeatGradePrice, Integer>, QueryDslPredicateExecutor<ConcertSeatGradePrice> {
	
	@Transactional
	void deleteByConcertManagement_ConcertId(String concertId);
	List<ConcertSeatGradePrice> findByConcertManagement_ConcertId(String concertId);

}
