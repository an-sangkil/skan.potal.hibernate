package com.skan.potal.web.potal.application.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skan.potal.web.potal.accountbook.dto.PersonalCode;
import com.skan.potal.web.potal.application.dao.PersonalCodeRepository;
import com.skan.potal.web.potal.application.service.PersonalCodeService;
import com.skan.potal.web.potal.common.util.PageUtils;

/**
* Description : 개인코드 컨트롤러
* 				공통에서 사용하는 코드가 아닌 개인이 사용할수 있는 코드로, 개인이 추가 삭제가 가능하다.
*
*				
* @author ahn
* @since 2016. 10. 3.
* @version 
*
* Copyright (C) 2016 by SKAN.COMPANY All right reserved.
*/
@Controller
public class PersonalCodeController {
		
	private final Logger logger = LoggerFactory.getLogger(PersonalCodeController.class);
	@Autowired PersonalCodeRepository PersonalCodeRepository;
	@Autowired PersonalCodeService PersonalCodeService;
	@PersistenceContext private EntityManager entityManager;
	
	
	@RequestMapping("personal_code/personal_code_list")
	private String codeList(
			@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchType,
			@RequestParam(required=false , defaultValue="") String searchName,
			ModelMap modelMap) throws Exception {
		
		Sort sort = new Sort(
				new Order(Direction.DESC , "PersonalCodeId.code")
				,new Order(Direction.DESC , "codeSeq")
				,new org.springframework.data.domain.Sort.Order(Direction.ASC, "codeName") 
				);  
		
		Page<PersonalCode> codePage;
		if(StringUtils.isNotEmpty(searchName)) {
			codePage = PersonalCodeRepository.findByCodeNameContaining(searchName, new PageRequest(page, size, sort));
		}else {
			codePage = PersonalCodeRepository.findAll(new PageRequest(page, size, sort));
		}
		
		PersonalCode cccc = PersonalCodeRepository.findByCodeInfo("00001");
		logger.debug("dsdfsdfs = {} ",cccc);
		PageUtils pageUtils = new PageUtils();
		int current = codePage.getNumber() + 1;
		//int begin = Math.max(1, current - 5);
		int begin = pageUtils.pagingBegin(current);
	    int end = pageUtils.pagingEnd(codePage.getTotalPages());
	    
		logger.debug("pageInfo = " , codePage);
		
		modelMap.put("current", current);
		modelMap.put("begin", begin);
		modelMap.put("end", end);
		modelMap.put("searchName", searchName);
		modelMap.put("codePage", codePage);
		
		return "/personal_code/personal_code_list.tiles";
	}
	
	@RequestMapping(value="personal_code/code_list" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PersonalCode> codeList(){
		
		List<PersonalCode>  PersonalCodes = PersonalCodeRepository.findAll();
		
		return PersonalCodes;
	}
	
	
	/**
	 * Code 입력 폼
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("personal_code/personal_code_form")
	private String codeForm(
			//@RequestParam(required=false) String codeMgtNo
			 @RequestParam(required=false) String code
			, ModelMap modelMap) {
		
		if(StringUtils.isNotEmpty(code)){
			
			//PersonalCodeId PersonalCodeId = new PersonalCodeId();
			//PersonalCodeId.setCodeMgtNo(codeMgtNo);
			//PersonalCodeId.setCode(code);
			PersonalCode PersonalCode = PersonalCodeRepository.findOne(code);
			modelMap.put("personalCode", PersonalCode);
			
		}
		
		return "/personal_code/personal_code_form.tiles";
	}
	

	/**
	 * 코드 저장/수정 
	 * @param PersonalCode
	 * @param bindingResult
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("personal_code/personal_code_insert")
	private String saveCode(@Valid PersonalCode PersonalCode ,BindingResult bindingResult, HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		if(StringUtils.isEmpty(PersonalCode.getCode())){
			// 신규 저장
			// 상위 코드 값이 있으면 max값 확인
//			PersonalCodeId.setCodeMgtNo(UUIDUtils.createUUID());
			PersonalCode.setUpperCode(StringUtils.defaultIfEmpty(PersonalCode.getUpperCode(), null));
			PersonalCode.setCodeSeq(PersonalCodeService.maxCodeSeq(PersonalCode.getUpperCode()));
			PersonalCode.setCode(PersonalCodeService.lpadMaxCodeValue());
		}
		
		PersonalCodeRepository.save(PersonalCode);
		 
		return "redirect:/personal_code/personal_code_list";
	}
	
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CONFIGRATION");
	}
}
