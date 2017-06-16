package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.skan.tms.web.jpa.dto.BasicSeat;
import com.skan.tms.web.jpa.dto.BasicSeatId;

public interface BasicSeatJpaRepository extends JpaRepository<BasicSeat, BasicSeatId> ,QueryDslPredicateExecutor<BasicSeat> {
	
	@Transactional
	void deleteByBasicSeatId_concertHallId(String concertHallId);
}
