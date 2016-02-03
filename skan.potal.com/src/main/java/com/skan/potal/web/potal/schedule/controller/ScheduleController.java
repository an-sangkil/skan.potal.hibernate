/**
 * <pre>
 * Class Name  : ScheduleController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 12. 30.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 12. 30.
 * @version 
 *
 * Copyright (C) 2015 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.schedule.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;
import com.skan.potal.web.potal.common.util.CalendarUtils;
import com.skan.potal.web.potal.common.util.CalendarUtils.CalendarPattermn;
import com.skan.potal.web.potal.schedule.model.CmtbSchedule;
import com.skan.potal.web.potal.schedule.model.CmtbSchedulePK;
import com.skan.potal.web.potal.schedule.repository.CmtbScheduleRepository;

@Controller
public class ScheduleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private CmtbScheduleRepository cmtbScheduleRepository;
	@Autowired private SessionFactory sessionFactory;
	@Autowired private CmtbCodeRepository cmtbCodeRepository;
	
	/**
	 * 전체 조회
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/schdule/schdule_list")
	public String schduleList (HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		// 해당 달만큼만...!! 검색 between !
		
		List<CmtbSchedule> cmtbSchedules = cmtbScheduleRepository.findByUserId("testUser2");
		cmtbSchedules.forEach(items ->  {
			logger.trace("cmtbSchedule ToString = {}" , items.toString());
		} );
		
		String toDay = CalendarUtils.getToDayString(CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD);
		
		logger.trace("toDay .............. = {}" , toDay);
		
		modelMap.put("toDay", toDay);
		modelMap.put("cmtbSchedules", cmtbSchedules);
		
		return "/schdule/schdule_calendar.tiles";
	}
	
	/**
	 * 입력 / 수정화면 으로 이동 / 상세 보기
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/schdule/schdule_form")
	public String schduleForm(@RequestParam(name="schMgtNo" , required=false) Long schMgtNo ,  
			@RequestParam(name="schSeq" , required=false) Long schSeq , HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		if(schMgtNo != null && schMgtNo > 0 ) {
			
			// 데이터 조회 후
			CmtbSchedulePK cmtbSchedulePK = new CmtbSchedulePK();
			cmtbSchedulePK.setSchMgtNo(schMgtNo);
			cmtbSchedulePK.setSchSeq(schSeq);
			CmtbSchedule cmtbSchedule = cmtbScheduleRepository.findOne(cmtbSchedulePK);

			modelMap.put("cmtbSchedule", cmtbSchedule);
			
		} else {
			
			String startDate = ServletRequestUtils.getStringParameter(request, "startDate", "");
			String endDate  = ServletRequestUtils.getStringParameter(request, "endDate", "");
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "EEE MMM dd yyyy hh:mm:ss",Locale.ENGLISH );
			ParsePosition pos = new ParsePosition ( 0 );

			logger.trace("startDate = {} ", startDate );
			logger.trace("endDate = {} ", endDate );
			
			CmtbSchedule cmtbSchedule = new CmtbSchedule();
			cmtbSchedule.setStdDate(CalendarUtils.convertDateToString( formatter.parse ( startDate, pos ), CalendarUtils.CALENDER_TYPE_YYYY_MM_DD));
			pos.setIndex(0);
			cmtbSchedule.setEndDate(CalendarUtils.convertDateToString( formatter.parse ( endDate, pos ), CalendarUtils.CALENDER_TYPE_YYYY_MM_DD));
			
			modelMap.put("cmtbSchedule", cmtbSchedule);
		}
		
		List<CmtbCode> cmtbCodes = cmtbCodeRepository.findByUpperCode("00001");
		modelMap.put("cmtbCodes", cmtbCodes);
		
		logger.debug("");
		
		return "/schdule/schdule_form.tiles";
	}
	
	/**
	 * 신규저장
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/schdule/schdule_insert")
	public String schduleInsert(@Valid CmtbSchedule cmtbSchedule,BindingResult bindingResult, HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		String stdDate = ServletRequestUtils.getStringParameter(request, "stdDate");
		List<CmtbSchedule> cmtbSchedules = cmtbScheduleRepository.findByUserIdAndStdDate("testUser2" , stdDate);

		cmtbSchedule.setUserId("testUser2");
		cmtbSchedule.setGroupNo(1L);
		
		CmtbSchedulePK cmtbSchedulePK = new CmtbSchedulePK();
		if(CollectionUtils.isNotEmpty(cmtbSchedules)) {
			cmtbSchedulePK.setSchMgtNo(cmtbSchedules.get(0).getCmtbSchedulePK().getSchMgtNo());
			cmtbSchedulePK.setSchSeq(cmtbSchedules.size()+1L);
		} else {
			Session session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(CmtbSchedule.class);
			//criteria.add(Restrictions.eq("userId", userId))
			criteria.addOrder(Order.desc("cmtbSchedulePK.schMgtNo"));
			criteria.setMaxResults(1);
			CmtbSchedule cmtbScheduleOld = (CmtbSchedule) criteria.uniqueResult();
			
			cmtbSchedulePK.setSchMgtNo(cmtbScheduleOld != null ? cmtbScheduleOld.getCmtbSchedulePK().getSchMgtNo()+1L : 1L);
			cmtbSchedulePK.setSchSeq(1L);
		}
		
		if(bindingResult.hasErrors()) {
			modelMap.put("cmtbSchedule", cmtbSchedule);
			return "/schdule/schdule_form.tiles";
		}

		cmtbSchedule.setCmtbSchedulePK(cmtbSchedulePK);
		cmtbSchedule = cmtbScheduleRepository.save(cmtbSchedule);
		
		return "redirect:/schdule/schdule_list";
	}
	
	/**
	 * 수정
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/schdule/schdule_modify")
	public String schduleModify(@Valid CmtbSchedule cmtbSchedule, BindingResult bindingResult, 
			@RequestParam Long schMgtNo, @RequestParam Long schSeq,
			HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		cmtbSchedule.setUserId("testUser2");
		cmtbSchedule.setGroupNo(1L);
		
		if(bindingResult.hasErrors()) {
			modelMap.put("cmtbSchedule", cmtbSchedule);
			return "/schdule/schdule_form.tiles";
		}

		cmtbSchedule.setCmtbSchedulePK(new CmtbSchedulePK(schMgtNo, schSeq));
		cmtbSchedule = cmtbScheduleRepository.save(cmtbSchedule);
		
		return "redirect:/schdule/schdule_list";
	}
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CALENDAR");
	}
}
