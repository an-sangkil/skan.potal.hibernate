package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.ConcertUsedDenomination;

/**
 * 
 * Description : 공연에서 사용중인 권종 종류 
 * @author skan
 * @since  2016. 11. 1.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface ConcertUsedDenominationJpaRepository extends JpaRepository<ConcertUsedDenomination, String> , QueryDslPredicateExecutor<ConcertUsedDenomination> {
	
	/**
	 * 콘서트 아이디로 해당되는 티켓 종류 삭제 
	 * @param concertId
	 */
	@org.springframework.transaction.annotation.Transactional
	void deleteByConcertManagement_ConcertId(String concertId);

}
