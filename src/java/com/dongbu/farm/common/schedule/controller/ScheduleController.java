/*
 * $Id: SchduleController.java ,v 1.1 2011. 3. 7. 오후 6:06:47 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 7.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.schedule.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.ajax.AjaxUtil;
import com.dongbu.farm.common.repository.controller.RepositoryController;
import com.dongbu.farm.common.schedule.ScheduleException;
import com.dongbu.farm.common.schedule.manager.IScheduleManager;
import com.dongbu.farm.common.schedule.model.Schedule;
import com.dongbu.farm.common.utils.IdGenerator;
import com.dongbu.farm.common.utils.SessionUtils;
import com.dongbu.farm.common.utils.Utils;

@Controller
public class ScheduleController extends RepositoryController{
	
	@Autowired
	private IScheduleManager scheduleManagerImpl;
	
	private static Log logger = LogFactory.getLog(ScheduleController.class);
	
	/**
	 * 한달 영역안의 전체 일정 모두 표시
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException 
	 * @throws SchduleException
	 */
	@RequestMapping(value="/common/schedule/getSchduleList.common")
	public ModelAndView getSchduleList(HttpServletRequest request , HttpServletResponse response) throws ScheduleException, ServletRequestBindingException{
		
		//모델뷰 객채생성
		ModelAndView mav = new ModelAndView();

		try {
			
			//파라미터 값 셋팅
			String std_year  = ServletRequestUtils.getStringParameter(request, "std_year");
			String std_month = ServletRequestUtils.getStringParameter(request, "std_month");
			

			//초기 상태의 경우 오늘 날짜 기준의 년도와 달 셋팅
			if(Utils.strValue(std_year).equals("") || Utils.strValue(std_month).equals("")){
				
				
				java.util.Calendar cal = java.util.Calendar.getInstance();
				std_year = String.valueOf(cal.get(java.util.Calendar.YEAR));
				std_month = String.valueOf(Utils.numberType(cal.get(java.util.Calendar.MONTH) + 1));
				
				
			}else{
				
			
				// 함수를 사용하기 위해 형변환한다. 
				// govtMove 함수를 사용하여 이동된 년도와 월의 값이 올바른지 확인 후 Map<String,String>로 리턴
				Map<String,String> govtMap = Utils.govtMove(Integer.parseInt(std_year), Integer.parseInt(std_month));
				
				//화면에서 가공 되지 않은 Calender.MONTH 의 값이 오기 때문에 실제 달수로 바꾸기 위해 +1을 해준다.
				int int_std_month = Integer.parseInt(govtMap.get("monthStr")) + 1;
				std_month  = String.valueOf(Utils.numberType(int_std_month));
				std_year   = govtMap.get("yearStr");
				
				
			}
			
			
			logger.info("server std_month 가공된 parameter : " + std_month);
			
			
			Map<String, String> searchMap = new HashMap<String,String>();
			searchMap.put("std_year" , std_year);
			searchMap.put("std_month" , std_month);
			
			
			//1. 스케줄 리스트 검색
			List<Schedule> scheduleList = this.scheduleManagerImpl.getSchduleList(searchMap);
			
			
			//스케줄  날짜별로 맵으로 재구성
			Map<String,List<Schedule>> scMap = scheduleDaybydayList(scheduleList, std_year, std_month);
			
			
			
			//2. 저장 혹은 수정한경우 해당 내용을 다시 불러온다.
			Schedule schedule = new Schedule();
			{
				String sch_mgt_no  = ServletRequestUtils.getStringParameter(request, "sch_mgt_no" , "");
				String sch_seq = ServletRequestUtils.getStringParameter(request, "sch_seq" , "");
				
				searchMap.put("sch_mgt_no", sch_mgt_no);
				searchMap.put("sch_seq", sch_seq);
				
				//저장된 값, 혹은 수정된 스케쥴을 불러온다.
				if(!sch_mgt_no.equals("") && !sch_seq.equals(""))
					schedule = this.scheduleManagerImpl.getSchedule(searchMap);
			}
			
			
			//
			mav.addAllObjects(searchMap);
			mav.addObject("scheduleList", scheduleList);
			mav.addObject("schedule", schedule);
			mav.addObject("scMap", scMap);
			mav.setViewName("/common/schedule/scheduleView");

		} catch (Exception e) {
			
			throw new ScheduleException("Schdule getSchduleList......Error", e);
			
		}
		
		return  mav;
	}
	
	/**
	 * 날짜 별로 Map<String,List<Schedule>>에  List<Schedule> 형태로 담는다.
	 * @param list
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public Map<String,List<Schedule>> scheduleDaybydayList(List<Schedule> list , String year , String month) throws Exception{
		
		Map<String,List<Schedule>> scMap = new HashMap<String,List<Schedule>>();   
		try {
			
			java.util.Calendar cal = java.util.Calendar.getInstance();
			int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

			String dateStr = year + "-" + month + "-";
			
			for (int index = 1; index <= endDay; index++) {
				List<Schedule> scheduleDayTypeList = new ArrayList<Schedule>();
				
				for (Schedule schedule : list) {
					if(schedule.getStd_date().equals((dateStr+Utils.numberType(index)).trim())){
						scheduleDayTypeList.add(schedule);
						scMap.put((dateStr+Utils.numberType(index)).trim(), scheduleDayTypeList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scMap;
	}
	
	
	
	
	
	/**
	 * 선택한 날짜의 일정 및 메모 내용 가져오기
	 * @param request
	 * @param response
	 * @throws SchduleException
	 */
	@RequestMapping(value="/common/schedule/getSchdule.common")
	public void getSchdule(HttpServletRequest request , HttpServletResponse response) throws ScheduleException{
		
		//모델뷰 객채생성
		ModelAndView mav = new ModelAndView();
		
		
		Map<String, String> searchMap = new HashMap<String,String>();
		
		try {

			String sch_mgt_no  = ServletRequestUtils.getRequiredStringParameter(request, "sch_mgt_no");
			String sch_seq = ServletRequestUtils.getRequiredStringParameter(request, "sch_seq");
			
			searchMap.put("sch_mgt_no", sch_mgt_no);
			searchMap.put("sch_seq", sch_seq);
			
			Schedule schedule = this.scheduleManagerImpl.getSchedule(searchMap);
			
			
			HashMap<Object, Object> ajaxMap = new HashMap<Object, Object>();
			
			ajaxMap.put("searchMap", searchMap);
			ajaxMap.put("schedule", schedule);
			
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			
			throw new ScheduleException("Schdule getSchduleList......Error", e);
			
		}
	}
	
	/**
	 * 스케쥴 등록
	 * @param request
	 * @param response
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value="/common/schedule/writeSchedule.common")
	public void writeSchedule(HttpServletRequest request , HttpServletResponse response) throws ScheduleException{
		
		//모델뷰 객채생성
		ModelAndView mav = new ModelAndView();
		
		//binding 객체생성
		Schedule schedule = new Schedule();
		
		try {
			bind(request, schedule);
			
			schedule.setSch_mgt_no(IdGenerator.generate("SCHDULE"));
			schedule.setCreatedtime(new Date());
			schedule.setCreator(SessionUtils.getUserID(request));
			
			this.scheduleManagerImpl.writeSchedule(schedule);
			
			//mav.setViewName("redirect:/common/schedule/getSchduleList.common" );

			HashMap<Object, Object> ajaxMap = new HashMap<Object, Object>();
			ajaxMap.put("schedule", schedule);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			
			//throw new ScheduleException("Schdule writeSchedule......Error", e);
			e.printStackTrace();
			
		}
		//return mav;
	}
	
	/**
	 * 스케줄 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value="/common/schedule/modifySchedule.common")
	public void modifySchedule(HttpServletRequest request , HttpServletResponse response) throws ScheduleException{
		//모델뷰 객채생성
		ModelAndView mav = new ModelAndView();
		
		Schedule schedule = new Schedule();
		
		try {
			bind(request, schedule);
			
			schedule.setModifiedtime(new Date());
			schedule.setModifier(SessionUtils.getUserID(request));
			
			this.scheduleManagerImpl.modifySchedule(schedule);
			
			
			//mav.setViewName("redirect:/common/schedule/getSchduleList.common" );
			HashMap<Object, Object> ajaxMap = new HashMap<Object, Object>();
			ajaxMap.put("schedule", schedule);
			AjaxUtil.successWrite(response, ajaxMap);
			
		} catch (Exception e) {
			
			throw new ScheduleException("Schdule modifySchedule......Error", e);
			
		}
		//return mav;
	}
	
	/**
	 * 스케줄 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value="/common/schedule/deleteSchedule.common")
	public ModelAndView deleteSchedule(HttpServletRequest request , HttpServletResponse response) throws ScheduleException{
		//모델뷰 객채생성
		ModelAndView mav = new ModelAndView();
		
		Schedule schedule = new Schedule();
		
		try {
			
			bind(request, schedule);
			this.scheduleManagerImpl.deleteSchedule(schedule);
			mav.setViewName("redirect:/common/schedule/getSchduleList.common" );
			
		} catch (Exception e) {
			
			throw new ScheduleException("Schdule deleteSchedule......Error", e);
			
		}
		return mav;
	}
}
