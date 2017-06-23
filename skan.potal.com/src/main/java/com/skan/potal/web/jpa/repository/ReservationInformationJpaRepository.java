package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skan.tms.web.jpa.dto.ConcertTimeOrder;
import com.skan.tms.web.jpa.dto.ReservationInformation;

/**
 * 
 * Description : 좌석예매정보 JPA  R
 * @author skan
 * @since  2016. 11. 7.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface ReservationInformationJpaRepository extends JpaRepository<ReservationInformation, Long>, QueryDslPredicateExecutor<ReservationInformation> {
	
	/**
	 * 
	 * @param concertId, ticketBuyer
	 * @return
	 */
	//public List<ReservationInformation> findByReservationInformation_concertIdAndTicketBuyer(String concertId, String ticketBuyer);
	
	/**
	 * 예매내역 조회 
	 * 
	 * @param reserveSeq
	 * @param ticketBuyer
	 * @return
	 */
	public ReservationInformation findByReserveSeqAndTicketBuyer(Long reserveSeq, String ticketBuyer);
	
	/**
	 * 좌석 등급 업데이트 및 상태변경(홀딩, 고장 , 정상 )
	 * @param reserveSeq
	 * @param status
	 * @param gradeId
	 */
	@Transactional(transactionManager="transactionManager")
	@Modifying
	@Query(value="update reservationinformation set status = :status , gradeId = :gradeId  where reserveSeq = :reserveSeq", nativeQuery=true)
	void updateStatus(@Param(value="reserveSeq") Long reserveSeq, @Param(value = "status") String status,  @Param(value = "gradeId") Integer gradeId);
	
	/**
	 * 좌석 상태를 변경한다.
	 * 	case 1  가예매, 비어있음, 예매완료
	 * 	case 2 가예매 및 가예매 상태를 풀기 위한 작업
	 * 
	 * @param reserveSeq
	 * @param seatBooking
	 */
	@Transactional(transactionManager="transactionManager")
	@Modifying
	@Query(value="update reservationinformation set seatStatus = :seatStatus , ticketBuyer = :ticketBuyer  where reserveSeq = :reserveSeq ", nativeQuery=true)
	void updateSeatStatus(@Param(value="reserveSeq")Long reserveSeq, @Param("ticketBuyer") String ticketBuyer, @Param("seatStatus") String seatBooking);
	
	/**
	 * 예매 취소 시 reservationId 연관 끊기 ReservationHistory 
	 * @param reserveSeq
	 */
	@Transactional(transactionManager="transactionManager")
	@Modifying
	@Query(value="update reservationinformation set reservationId = null where reserveSeq = :reserveSeq ", nativeQuery=true)
	void updateReservationId_NullProcess(@Param("reserveSeq") Long reserveSeq);

}
