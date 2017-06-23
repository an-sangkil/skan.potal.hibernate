package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ApiKeyManagement;

public interface ApiKeyManagementJpaRepository extends JpaRepository<ApiKeyManagement, Integer> , QueryDslPredicateExecutor<ApiKeyManagement> {

}
