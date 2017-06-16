package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.BoardDto;
import com.skan.tms.web.jpa.dto.BoardDto.BoardDtoPK;

public interface BoardJpaRepository extends JpaRepository<BoardDto, BoardDtoPK> ,QueryDslPredicateExecutor<BoardDto> {

	Page<BoardDto> findBySubjectContaining(String searchName, Pageable pageable);
	
	//Page<BoardDto> findBySubjectContainingAndBoardId(String searchName, Long BoardId , Pageable pageable);
	
	public List<BoardDto> findByBoardDtoPK_BoardId(Long boardId);

	public Page<BoardDto> findByBoardDtoPK_BoardId(Long boardId, Pageable pageable);

	
	

}
