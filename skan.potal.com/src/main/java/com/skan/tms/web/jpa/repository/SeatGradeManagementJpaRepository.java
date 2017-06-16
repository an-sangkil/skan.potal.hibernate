package com.skan.tms.web.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.SeatGradeManagement;

public interface SeatGradeManagementJpaRepository extends JpaRepository<SeatGradeManagement, String>, QueryDslPredicateExecutor<SeatGradeManagement> {

	Page<SeatGradeManagement> findBygradeNameContaining(String searchName, Pageable pageable);

	SeatGradeManagement findBygradeId(Integer gradeId);
	
	
}
