package com.knkcorp.tms.mobile.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knkcorp.auth.utils.AuthorizationCheck;
import com.knkcorp.auth.utils.SessionUtils;
import com.knkcorp.tms.mobile.common.code.CommonCode;
import com.knkcorp.tms.mobile.common.code.ConcertCode;
import com.knkcorp.tms.mobile.web.model.CommonObject;
import com.knkcorp.tms.mobile.web.model.ResponseMessage;
import com.knkcorp.tms.mobile.web.model.SeatGrade;
import com.knkcorp.tms.web.jpa.dto.ConcertManagement;
import com.knkcorp.tms.web.jpa.dto.ConcertTimeOrder;
import com.knkcorp.tms.web.jpa.dto.ConcertTimeOrderId;
import com.knkcorp.tms.web.jpa.dto.ConcertUsedDenomination;
import com.knkcorp.tms.web.jpa.dto.QConcertHallManagement;
import com.knkcorp.tms.web.jpa.dto.QConcertSeatGradePrice;
import com.knkcorp.tms.web.jpa.dto.QConcertUsedDenomination;
import com.knkcorp.tms.web.jpa.dto.QDenomination;
import com.knkcorp.tms.web.jpa.dto.QReservationInformation;
import com.knkcorp.tms.web.jpa.dto.QSeatGradeManagement;
import com.knkcorp.tms.web.jpa.dto.ReservationInformation;
import com.knkcorp.tms.web.jpa.repository.ConcertManagementJpaRepository;
import com.knkcorp.tms.web.jpa.repository.ReservationInformationJpaRepository;
import com.knkcorp.tms.web.jpa.repository.UserJpaRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * 
 * Description :
 * 
 * @author yh.lee
 * @since 2016. 11. 15.
 * @version
 *
 * 			Copyright (C) 2016 by KNK Corp. All right reserved.
 */
@Controller
public class ReservationInformationConctoller extends AbstractCommonController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationInformationConctoller.class);

	@Autowired
	ReservationInformationJpaRepository reservationInformationJpaRepository;
	@Autowired
	UserJpaRepository userJpaRepository;
	@Autowired
	ConcertManagementJpaRepository concertManagementJpaRepository;

	@RequestMapping("/tms/reservationInformation/reservationInformationView")
	public String reservationInformationView(ModelMap modelMap) {
		
		return "admin.tms.reservationInformation.reservationInformationView";

	}
	

	@RequestMapping("/tms/reservationInformation/reservationInformationSeat")
	@ResponseBody
	public Map<String,List<?>> ReservationInformationView(
			@RequestParam(value = "concertOrderId", required = false, defaultValue = "") String concertOrderId
			,@RequestParam(value = "textDate", required = false, defaultValue = "") String textDate
			,@RequestParam(value = "concertId", required = false, defaultValue = "") String concertId
			) throws ParseException {

		Map<String,List<?>> modelMap = new HashMap<>(); 

		JPAQuery<?> query = this.getJPAQueryInstance();
		JPAQuery<?> query2 = this.getJPAQueryInstance();

		QReservationInformation qReservationInformation = QReservationInformation.reservationInformation;
		QSeatGradeManagement qSeatGradeManagement = QSeatGradeManagement.seatGradeManagement;
		QConcertSeatGradePrice qConcertSeatGradePrice = QConcertSeatGradePrice.concertSeatGradePrice;
		QConcertHallManagement qConcertHallManagement = QConcertHallManagement.concertHallManagement;

		ReservationInformation reservationInformation = new ReservationInformation();
		ConcertTimeOrder concertTimeOrder = new ConcertTimeOrder();
		ConcertTimeOrderId concertTimeOrderId = new ConcertTimeOrderId();

		// 관리번호
		//String concertOrderId = "47eba32f-d8b3-4bbb-8dc4-a6147f1e6db8";

		// 공연날짜
		//String textDate = "2016-11-10";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date concertDate = format.parse(textDate);

		// 관리번호, 공연날짜 셋팅
		concertTimeOrderId.setConcertOrderId(concertOrderId);
		concertTimeOrderId.setConcertDate(concertDate);
		concertTimeOrder.setConcertTimeOrderId(concertTimeOrderId);
		reservationInformation.setConcertTimeOrder(concertTimeOrder);

		// ReservationInformation, SeatGradeManagement.gradeColor
		ConstructorExpression<ReservationInformation> constructor = Projections.constructor(ReservationInformation.class, 
				qReservationInformation.reserveSeq, 
				qReservationInformation.areaName, 
				qReservationInformation.denominationId, 
				qReservationInformation.floor, 
				qReservationInformation.gradeId,
				qReservationInformation.reserveTime, 
				qReservationInformation.rowName,
				qReservationInformation.seatNumber, 
				qReservationInformation.seatStatus, 
				qReservationInformation.st, 
				qReservationInformation.status, 
				qReservationInformation.ticketBuyer,
				qReservationInformation.concertManagement.concertId, 
				qReservationInformation.concertTimeOrder.concertTimeOrderId.concertDate, 
				qReservationInformation.concertTimeOrder.concertTimeOrderId.concertOrderId, 
				qReservationInformation.reservationHistory.reservationId,
				JPAExpressions.select(qSeatGradeManagement.gradeColor).from(qSeatGradeManagement).where(qSeatGradeManagement.gradeId.eq(qReservationInformation.gradeId)),
				JPAExpressions.select(qSeatGradeManagement.gradeName).from(qSeatGradeManagement).where(qSeatGradeManagement.gradeId.eq(qReservationInformation.gradeId)),
				JPAExpressions.select(qConcertSeatGradePrice.price).from(qConcertSeatGradePrice).where(qConcertSeatGradePrice.seatGradeManagement.gradeId.eq(qReservationInformation.gradeId)
																								.and(qConcertSeatGradePrice.concertManagement.concertId.eq(concertId))
																								),
				JPAExpressions.select(qConcertHallManagement.concertHallName).from(qConcertHallManagement).where(qConcertHallManagement.concertHallId.eq(qReservationInformation.concertManagement.concertHallId))
				);

		List<ReservationInformation> reservationInformations = query.select(constructor).from(qReservationInformation).where(QReservationInformation.reservationInformation.concertTimeOrder.concertTimeOrderId.eq(concertTimeOrderId)).fetch();
		
		
		//사용중인 티켓
		QConcertUsedDenomination qConcertUsedDenomination = QConcertUsedDenomination.concertUsedDenomination;
		QDenomination qDenomination = QDenomination.denomination;
		ConstructorExpression<ConcertUsedDenomination> constructor2 = Projections.constructor(ConcertUsedDenomination.class, 
				qConcertUsedDenomination.denomination.denominationId,
				qConcertUsedDenomination.usedDenominationId,
				JPAExpressions.select(qDenomination.denominationName).from(qDenomination).where(qDenomination.denominationId.eq(qConcertUsedDenomination.denomination.denominationId)),
				JPAExpressions.select(qDenomination.exposureTarget).from(qDenomination).where(qDenomination.denominationId.eq(qConcertUsedDenomination.denomination.denominationId)),
				JPAExpressions.select(qDenomination.discountRate).from(qDenomination).where(qDenomination.denominationId.eq(qConcertUsedDenomination.denomination.denominationId))
				);
		List<ConcertUsedDenomination> concertUsedDenomination = query2
																.select(constructor2)
																.from(qConcertUsedDenomination)
																.where(QConcertUsedDenomination.concertUsedDenomination.concertManagement.concertId.eq(concertId))
																.fetch();

		
		Map<String, List<ReservationInformation>> seatReservationInfos =reservationInformations.stream().collect(Collectors.groupingBy(
																							 ReservationInformation:: getGradeName  , Collectors.toList() )
																					);
		
		List<SeatGrade> seatGrade = new ArrayList<>();
		seatReservationInfos.forEach((k,v)->{
				
				// 좌석 등급 별 전체 좌석 
				long bySeatLevelTotalCount =  v.size();
				// 판매된 좌석 
				long bySeatLevelCompleteCount =  v.stream().filter(x -> {
								return x.getSeatStatus().equals(ConcertCode.SEAT_BOOKING_COMPLETED.name());
							}).count();
				// 구역 색상
				String bySeatLevelColor = v.size() > 0 ? v.get(0).getGradeColor() : "";			
				logger.debug("등급명 ={} bySeatLevelTotalCount = {}  bySeatLevelCompleteCount={}  bySeatLevelColor={}" , k, bySeatLevelTotalCount, bySeatLevelCompleteCount, bySeatLevelColor);
				seatGrade.add(new SeatGrade(bySeatLevelTotalCount,bySeatLevelCompleteCount,bySeatLevelColor,k));
			}
		);
		
		
		modelMap.put("seatGradeInformation", seatGrade);
		modelMap.put("concertUsedDenomination", concertUsedDenomination);
		modelMap.put("reservationInformations", reservationInformations);

		return modelMap;
	}
	
	
	/**
	 * 가예매 하기 
	 * @param reserveSeq
	 * @return
	 */
	@RequestMapping("tms/reservationInformation/reservationInformationSave")
	@Transactional(transactionManager="transactionManager")
	public ResponseEntity<?>  ReservationInformationSave(
			@RequestParam(value="reserveSeq", required=false) Long[] reserveSeq,
			@RequestParam(value="concertId", required=false) String concertId
		){
					
		HttpStatus httpStatus;	
		CommonObject<String> commonObject = new ResponseMessage<>();
		Date today = new Date();
		
		
		if(reserveSeq == null) {
			commonObject.setStateCode(CommonCode.FAIL);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		String userEmail = SessionUtils.getSessionUserEmail();
		
		
		try {
			List<ReservationInformation> reservationInformations = new ArrayList<>();
			for(int i = 0; i < reserveSeq.length; i++){
				ReservationInformation reservationInformation = new ReservationInformation();
				reservationInformation.setReserveSeq(reserveSeq[i]);
				reservationInformation.setSeatStatus(ConcertCode.SEAT_BOOKING.name());
				reservationInformation.setTicketBuyer(userEmail);
				reservationInformation.setReserveTime(today);
				reservationInformations.add(reservationInformation);
				
				ReservationInformation reservationInformationData = reservationInformationJpaRepository.findOne(reserveSeq[i]);
				
				if(reservationInformationData.getSeatStatus().equals(ConcertCode.SEAT_BOOKING.name()) && !userEmail.equals(reservationInformationData.getTicketBuyer())) {
					commonObject.setResponseMessage("이미 다른사용자가 예매중입니다.");
					throw new Exception("이미 다른사용자가 예매중입니다.");
				}
				if(reservationInformationData.getSeatStatus().equals(ConcertCode.SEAT_BOOKING_COMPLETED.name())) {
					commonObject.setResponseMessage("이미 다른사용자가 예매 하였습니다.");
					throw new Exception("이미 다른사용자가 예매 하였습니다.");
				}
				
				//티켓 구매 제한
				ConcertManagement concertManagement = concertManagementJpaRepository.findOne(concertId);
				BooleanBuilder booleanBuilder = new BooleanBuilder();
				booleanBuilder.and(QReservationInformation.reservationInformation.ticketBuyer.eq(SessionUtils.getSessionUserEmail()).and(QReservationInformation.reservationInformation.concertManagement.concertId.eq(concertId))); 
				List<ReservationInformation> reservationInformationCount = (List<ReservationInformation>) reservationInformationJpaRepository.findAll(booleanBuilder);
								
				if(!AuthorizationCheck.isAdmin(SessionUtils.getSessionDetailObject().getUserDto())){
					if(reservationInformationCount.size() + reserveSeq.length > concertManagement.getMaxBuyTicketCount()){
						commonObject.setResponseMessage("공연당 티켓 구매 횟수를 초과했습니다.");
						throw new Exception("공연당 티켓 구매 횟수를 초과했습니다.");
					}
				}
			}
			
			reservationInformationJpaRepository.save(reservationInformations);
			commonObject.setStateCode(CommonCode.SUCCESS);
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			logger.error("가예매 에러 = {}" , e);
			
			commonObject.setStateCode(CommonCode.FAIL);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<CommonObject<String>>(commonObject,httpStatus);
	}
	
}
