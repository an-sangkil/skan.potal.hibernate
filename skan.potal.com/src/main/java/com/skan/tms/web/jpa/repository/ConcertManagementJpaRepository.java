package com.skan.tms.web.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.tms.web.jpa.dto.ConcertManagement;

public interface ConcertManagementJpaRepository extends JpaRepository<ConcertManagement, String> , QueryDslPredicateExecutor<ConcertManagement> {

	Page<ConcertManagement> findByConcertNameContaining(String searchName, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value="update concertManagement set concertStartDate = :concertStartDate , concertEndDate = :concertEndDate where concertid = :concertId", nativeQuery=true)
	void updateConcertPeriod(@Param(value="concertStartDate") java.sql.Date concertStartDate,@Param(value="concertEndDate") java.sql.Date concertEndDate,@Param(value = "concertId")  String concertId);
}
