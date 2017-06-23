package com.skan.potal.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.jpa.dto.ApiKeyManagement;

public interface ApiKeyManagementJpaRepository extends JpaRepository<ApiKeyManagement, Integer> , QueryDslPredicateExecutor<ApiKeyManagement> {

}
