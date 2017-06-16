/**
 * <pre>
 * Class Name  : DomesticAccountBookController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 31.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 31.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.accountbook.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.accountbook.converter.DomesticAccountBookConvert;
import com.skan.potal.web.potal.accountbook.dto.DomesticAccountBook;
import com.skan.potal.web.potal.accountbook.dto.QDomesticAccountBook;
import com.skan.potal.web.potal.accountbook.model.DomesticAccountBookModel;
import com.skan.potal.web.potal.accountbook.repository.DomesticAccountBookRepository;
import com.skan.potal.web.potal.accountbook.service.DomesticAccountBookService;
import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;
import com.skan.potal.web.potal.application.model.QCmtbCode;
import com.skan.potal.web.potal.common.util.CalendarUtils;
import com.skan.potal.web.potal.common.util.CalendarUtils.CalendarPattermn;
import com.skan.potal.web.potal.common.util.UUIDUtils;

/**
 * 수입 지출
 * @author skan
 *
 */
@Controller
public class DomesticAccountBookController {
	
	private final Logger logger = LoggerFactory.getLogger(DomesticAccountBookController.class);
	
	@Autowired private CmtbCodeRepository cmtbCodeRepository;
	@Autowired private DomesticAccountBookRepository domesticAccountBookRepository;
	@Autowired private DomesticAccountBookService domesticAccountBookService;
	@Autowired private DomesticAccountBookConvert domesticAccountBookConvert;
	
	/**
	 * 조회
	 * @param fromDate
	 * @param toDate
	 * @param searchType
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/domestic_account_book/list")
	public String  domesticAccountBookList(
			@RequestParam(defaultValue="", required=false) String fromDate ,
			@RequestParam(defaultValue="", required=false) String toDate,
			@RequestParam(defaultValue="", required=false) String searchType,
			ModelMap modelMap) throws Exception {
		
		// 정렬 조건
		// Sort sort = new Sort(
		// new org.springframework.data.domain.Sort.Order(Direction.DESC,
		// "businessDay")
		// //,new Order(Direction.DESC , "hmMgNum")
		// );
		
		// 기본 : 해당년 01.01 ~ 오늘까지					 > form ~ to 자동 계산
		// 오늘을 기준으로 : 일주일 , 1개월 , 3개월, 6개월, 1년    > from ~ to 자동 계산
		// 기간 지정 : from ~ to
		// BASIC
		// 1WEEK
		// 1MONTHS
		// 3MONTHS
		// 6MONTHS
		// 1YEARS
		// PERIOD
		Date from = new Date();
		Date to = null;
		if(StringUtils.isEmpty(searchType) || StringUtils.equals(searchType,"BASIC")){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			//if(StringUtils.isEmpty(fromDate)) {
				from = dateFormat.parse(calendar.get(Calendar.YEAR)+"-01-01");
			//}
			//if(StringUtils.isEmpty(toDate)){
				to = dateFormat.parse(CalendarUtils.getToDayString(CalendarPattermn.CALENDER_TYPE_YYYY_MM_DD));
			//}
			
		} else if(StringUtils.equals(searchType, "1WEEK"))   {
			
			from = CalendarUtils.addDate(new Date(), -7, 0);
			to = new Date();
			
		} else if(StringUtils.equals(searchType, "1MONTHS")) {
			from = CalendarUtils.addDate(new Date(), 0, -1);
			to = new Date();
		} else if(StringUtils.equals(searchType, "3MONTHS")) {
			from = CalendarUtils.addDate(new Date(), 0, -3);
			to = new Date();
		} else if(StringUtils.equals(searchType, "6MONTHS")) {
			from = CalendarUtils.addDate(new Date(), 0, -6);
			to = new Date();
		} else if(StringUtils.equals(searchType, "1YEARS"))  {
			from = CalendarUtils.addDate(new Date(), 0, -12);
			to = new Date();
		} else if(StringUtils.equals(searchType, "PERIOD"))  {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotEmpty(fromDate)) {
				from = dateFormat.parse(fromDate);
			}
			if(StringUtils.isNotEmpty(toDate)){
				to = dateFormat.parse(toDate);
			}
		}
		
		
		List<DomesticAccountBook> domesticAccountBooks = domesticAccountBookRepository.findByBusinessDayBetweenOrderByBusinessDayDesc(from, to);
		
		
		QDomesticAccountBook qDomesticAccountBook = QDomesticAccountBook.domesticAccountBook;
		
		
		
		
		
		
		//List<DomesticAccountBook> domesticAccountBooks = domesticAccountBookRepository.findAll(sort);
		
		// TODO 총금액 계산 DB에서 할가? 서버에서 할까...
		// 유형별 두가지 : 수입 / 지출 
		BigDecimal income  = domesticAccountBookService.calculatorIncome();
		BigDecimal expense = domesticAccountBookService.calculatorExpense();
		BigDecimal totalSum = income.subtract(expense) ;
		
		logger.debug("domesticAccountBooks Info = " , domesticAccountBooks );
		modelMap.put("domesticAccountBooks", domesticAccountBooks );
		modelMap.put("from", from);
		modelMap.put("to", to);
		modelMap.put("income", income);
		modelMap.put("expense", expense);
		modelMap.put("totalSum", totalSum);
		modelMap.put("searchType", searchType);
		
		
		return "/accountbook/domesticAccountBook_list.tiles";
	}
	
	/**
	 * 폼화면 이동
	 * @param dabMngNo
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/domestic_account_book/form")
	public String  domesticAccountBookForm(@RequestParam(required=false) String dabMngNo
			,ModelMap modelMap) {
		
		if(StringUtils.isNotEmpty( dabMngNo)) {
			//불러오기
			DomesticAccountBook domesticAccountBook = domesticAccountBookRepository.findOne(dabMngNo);
			modelMap.put("domesticAccountBook", domesticAccountBook);
		}
		
		
		// 코드 정보 불러 오기 
		// TODO 가계부 코드 불러오기
		List<CmtbCode>  cmtbCodes = (List<CmtbCode>) cmtbCodeRepository.findAll(QCmtbCode.cmtbCode.upperCode.eq(""));
		modelMap.put("cmtbCodes", cmtbCodes);
		
		return "/accountbook/domesticAccountBook_form.tiles";
	}
	
	
	/**
	 * 저장 및 수정
	 * @param domesticAccontModel
	 * @param bindingResult
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/domestic_account_book/insert")
	public String  domesticAccountBookInsert(@Valid DomesticAccountBookModel domesticAccontModel,BindingResult bindingResult , ModelMap modelMap) {
			
		if(bindingResult.hasErrors()){
			return "/accountbook/domesticAccountBook_form.tiles";
		}
		
		
		if(StringUtils.isEmpty( domesticAccontModel.getDabMngNo())) {
			//신규 저장
			domesticAccontModel.setDabMngNo(UUIDUtils.createUUID());
		}
		
		DomesticAccountBook dto = domesticAccountBookConvert.ConvertToDTO(domesticAccontModel, new DomesticAccountBook());
		domesticAccountBookRepository.save(dto);
		
		return "redirect:/domestic_account_book/list";
	}
	
	
	/**
	 * 삭제
	 * @param dabMngNo
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/domestic_account_book/delete")
	public String  domesticAccountBookDelete(@RequestParam(required=false) String dabMngNo
			,ModelMap modelMap) {
		
		domesticAccountBookRepository.delete(dabMngNo);
		
		return "redirect:/domestic_account_book/list";
	}
	
	
	
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "DOMESTIC_ACCOUNT_BOOK");
	}
}
