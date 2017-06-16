package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.skan.tms.web.jpa.dto.SeatCreationManagement;
import com.skan.tms.web.jpa.dto.SeatCreationManagement.SeatCreationManagementPK;

public interface SeatCreationManagementJpaRepository extends JpaRepository<SeatCreationManagement, SeatCreationManagementPK>, QueryDslPredicateExecutor<SeatCreationManagement> {

		@Transactional
		void deleteBySeatCreationManagementPK_ConcertHallId(String concertHallId);

}
