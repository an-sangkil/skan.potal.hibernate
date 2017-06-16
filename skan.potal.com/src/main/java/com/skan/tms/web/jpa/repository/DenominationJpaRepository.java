package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.Denomination;

public interface DenominationJpaRepository extends JpaRepository<Denomination, String>, QueryDslPredicateExecutor<Denomination> {

	Page<Denomination> findByDenominationNameContaining(String searchName, Pageable pageable);

	List<Denomination> findByDenominationNameContainingOrderByCreationTimeDesc(String searchName);

}
