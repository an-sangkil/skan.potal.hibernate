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
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.potal.web.potal.accountbook.dto.DomesticAccountBook;
import com.skan.potal.web.potal.accountbook.repository.DomesticAccountBookRepository;
import com.skan.potal.web.potal.accountbook.service.DomesticAccountBookService;
import com.skan.potal.web.potal.common.util.UUIDUtils;

/**
 * 수입 지출
 * @author skan
 *
 */
@Controller
public class DomesticAccountBookController {
	
	private final Logger logger = LoggerFactory.getLogger(DomesticAccountBookController.class);
	
	@Autowired private DomesticAccountBookRepository domesticAccountBookRepository;
	@Autowired private DomesticAccountBookService domesticAccountBookService;
	
	/**
	 * 조회
	 * @param fromDate
	 * @param toDate
	 * @param searchType
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/domestic_account_book/list")
	public String  domesticAccountBookList(
			@RequestParam(defaultValue="", required=false) String fromDate ,
			@RequestParam(defaultValue="", required=false) String toDate,
			@RequestParam(defaultValue="", required=false) String searchType,
			ModelMap modelMap) {
		
		Sort sort = new Sort(
				new org.springframework.data.domain.Sort.Order(Direction.DESC, "businessDay") 
				//,new Order(Direction.DESC , "hmMgNum")
				);  
		List<DomesticAccountBook> domesticAccountBooks = domesticAccountBookRepository.findAll(sort);
		
		// TODO 총금액 계산 DB에서 할가? 서버에서 할까...
		// 유형별 두가지 : 수입 / 지출 
		BigDecimal income  = domesticAccountBookService.calculatorIncome();
		BigDecimal expense = domesticAccountBookService.calculatorExpense();
		BigDecimal totalSum = income.subtract(expense) ;
		
		logger.debug("domesticAccountBooks Info = " , domesticAccountBooks );
		modelMap.put("domesticAccountBooks ", domesticAccountBooks );
		modelMap.put("income", income);
		modelMap.put("expense", expense);
		modelMap.put("totalSum", totalSum);
		
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
		
		if(StringUtils.isEmpty( dabMngNo)) {
			//불러오기
			domesticAccountBookRepository.findOne(dabMngNo);
		}
		
		return "/accountbook/domesticAccountBook_form.tiles";
	}
	
	
	/**
	 * 저장 및 수정
	 * @param domesticAccontBook
	 * @param bindingResult
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/domestic_account_book/insert")
	public String  domesticAccountBookInsert(@Valid DomesticAccountBook domesticAccontBook,BindingResult bindingResult , ModelMap modelMap) {
			
		if(bindingResult.hasErrors()){
			return "/accountbook/domesticAccountBook_form.tiles";
		}
		
		
		if(StringUtils.isEmpty( domesticAccontBook.getDabMngNo())) {
			//신규 저장
			domesticAccontBook.setDabMngNo(UUIDUtils.createUUID());
		}
		
		domesticAccountBookRepository.save(domesticAccontBook);
		
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
