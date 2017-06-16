package com.knkcorp.tms.mobile.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knkcorp.auth.utils.SessionUtils;
import com.knkcorp.tms.mobile.common.code.ReservationCode;
import com.knkcorp.tms.web.jpa.dto.QReservationHistory;
import com.knkcorp.tms.web.jpa.dto.ReservationHistory;
import com.knkcorp.tms.web.jpa.repository.ReservationHistoryJpaRepository;
import com.knkcorp.tms.web.jpa.repository.ReservationInformationJpaRepository;
import com.querydsl.core.BooleanBuilder;
/**
 * 
 * Description : 마이페이지 공연 예매 내역 
 * @author skan
 * @since  2017. 6. 15.
 * @version 
 *
 * Copyright (C) 2017 by KNK Corp. All right reserved.
 */
@Controller
public class MyPageController extends AbstractCommonController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ReservationInformationJpaRepository reservationInformationJpaRepository;
	@Autowired ReservationHistoryJpaRepository reservationHistoryJpaRepository; 
	
	
	/**
	 *  마이페이지
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("mypage")
	public String myPage(HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		logger.trace("mypage connect .... ");
		
		return "redirect:/mypage/ticketingList";
	}
	
	/**
	 * 예매 내역
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("mypage/ticketingList")
	public String myPageTicketingList(HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		String userEmail = SessionUtils.getSessionUserEmail();
		if(userEmail == null) {
			return "redirect:/login";
		}
		
		// Sorting 설정
		Sort sort = new Sort(
				new Order(Direction.DESC , "creationTime").nullsFirst()
				);
		
		
		// 검색 조건 설정  초기화
		QReservationHistory qReservationHistory = QReservationHistory.reservationHistory;
		BooleanBuilder bb = new BooleanBuilder();
		bb.and(qReservationHistory.ticketBuyer.eq(userEmail));
		List<ReservationHistory> reservationHistorys  =  (List<ReservationHistory>) reservationHistoryJpaRepository.findAll(bb, sort);
		
		List<ReservationHistory> complateReservations =  reservationHistorys.stream().filter(p-> p.getReservationState().equals(ReservationCode.RESERVATION_IN_COMPLATE.name())).collect(Collectors.toList());
		List<ReservationHistory> cancelReservations =  reservationHistorys.stream().filter(p-> p.getReservationState().equals(ReservationCode.RESERVATION_IN_CANCEL.name())).collect(Collectors.toList());
		
		modelMap.put("reservationHistorys" , reservationHistorys);
		modelMap.put("complateReservations", complateReservations);
		modelMap.put("cancelReservations"  , cancelReservations);
		
		return "mypage.ticketingList";
	}
	
	/**
	 * 예매 정보 상세 보기 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("mypage/ticketingView")
	public String myPageTicketingView(HttpServletRequest request ,
			@RequestParam(required=true ) String reservationId,
			ModelMap modelMap) throws Exception {
		
		ReservationHistory reservationHistory = this.reservationHistoryJpaRepository.findOne(reservationId);
		modelMap.put("reservationHistory", reservationHistory);
		
		return "mypage.ticketingView";
	}
	
}
