package com.skan.tms.web.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.BoardManagementDto;

public interface BoardManagementJpaRepository extends JpaRepository<BoardManagementDto, Long> ,QueryDslPredicateExecutor<BoardManagementDto> {

	Page<BoardManagementDto> findByBoardNameContaining(String searchName, Pageable pageRequest);

}
