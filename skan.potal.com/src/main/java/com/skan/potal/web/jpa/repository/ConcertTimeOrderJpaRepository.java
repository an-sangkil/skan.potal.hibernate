package com.skan.tms.web.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ConcertTimeOrder;
import com.skan.tms.web.jpa.dto.ConcertTimeOrderId;

public interface ConcertTimeOrderJpaRepository extends JpaRepository<ConcertTimeOrder, ConcertTimeOrderId> , QueryDslPredicateExecutor<ConcertTimeOrder> {
	/**
	 * 콘서트 아이디로 
	 * 콘서트 시간별 목록 검색
	 * @param concertId
	 * @return
	 */
	public List<ConcertTimeOrder> findByConcertManagement_concertId(String concertId);

	/**
	 * 콘서트 아이디로 관련된 내역 삭제
	 * @param concertId
	 */
	@Transactional
	public void deleteByConcertManagement_ConcertId(String concertId);
	
}
