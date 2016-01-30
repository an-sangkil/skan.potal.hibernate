/**
 * <pre>
 * Class Name  : CodeController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 1.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 1.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
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

import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;
import com.skan.potal.web.potal.application.model.CmtbCodeId;
import com.skan.potal.web.potal.application.service.CmtbCodeService;
import com.skan.potal.web.potal.common.util.PageUtils;
import com.skan.potal.web.potal.common.util.UUIDUtils;

/**
 * @author ahn
 *
 */
@Controller
public class CodeController {
		
	private final Logger logger = LoggerFactory.getLogger(CodeController.class);
	@Autowired CmtbCodeRepository cmtbCodeRepository;
	@Autowired CmtbCodeService cmtbCodeService;
	@PersistenceContext private EntityManager entityManager;
	
	
	@RequestMapping("code/code_list")
	private String codeList(
			@RequestParam(required=false , defaultValue="0") Integer page,
			@RequestParam(required=false , defaultValue="10") Integer size,
			@RequestParam(required=false , defaultValue="DESC") Direction direction, 
			@RequestParam(required=false , defaultValue="") String searchType,
			@RequestParam(required=false , defaultValue="") String searchName,
			ModelMap modelMap) throws Exception {
		
		Sort sort = new Sort(
				new Order(Direction.DESC , "cmtbCodeId.code")
				,new Order(Direction.DESC , "codeSeq")
				,new org.springframework.data.domain.Sort.Order(Direction.ASC, "codeName") 
				);  
		
		Page<CmtbCode> codePage;
		if(StringUtils.isNotEmpty(searchName)) {
			codePage = cmtbCodeRepository.findByCodeNameContaining(searchName, new PageRequest(page, size, sort));
		}else {
			codePage = cmtbCodeRepository.findAll(new PageRequest(page, size, sort));
		}
		
		CmtbCode cccc = cmtbCodeRepository.findByCodeInfo("00001");
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
		
		return "/code/code_list.tiles";
	}
	
	@RequestMapping(value="code/code_list" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<CmtbCode> codeList(){
		
		List<CmtbCode>  cmtbCodes = cmtbCodeRepository.findAll();
		
		return cmtbCodes;
	}
	
	
	/**
	 * Code 입력 폼
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("code/code_form")
	private String codeForm(
			//@RequestParam(required=false) String codeMgtNo
			 @RequestParam(required=false) String code
			, ModelMap modelMap) {
		
		if(StringUtils.isNotEmpty(code)){
			
			CmtbCodeId cmtbCodeId = new CmtbCodeId();
			//cmtbCodeId.setCodeMgtNo(codeMgtNo);
			cmtbCodeId.setCode(code);
			CmtbCode cmtbCode = cmtbCodeRepository.findOne(cmtbCodeId);
			modelMap.put("cmtbCode", cmtbCode);
			
		}
		
		return "/code/code_form.tiles";
	}
	

	/**
	 * 코드 저장/수정 
	 * @param cmtbCode
	 * @param bindingResult
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("code/code_insert")
	private String saveCode(@Valid CmtbCode cmtbCode ,@ModelAttribute CmtbCodeId cmtbCodeId ,BindingResult bindingResult, HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		if(StringUtils.isEmpty(cmtbCodeId.getCode())){
			// 신규 저장
			// 상위 코드 값이 있으면 max값 확인
//			cmtbCodeId.setCodeMgtNo(UUIDUtils.createUUID());
			cmtbCode.setUpperCode(StringUtils.defaultIfEmpty(cmtbCode.getUpperCode(), null));
			cmtbCode.setCodeSeq(cmtbCodeService.maxCodeSeq(cmtbCode.getUpperCode()));
			cmtbCodeId.setCode(cmtbCodeService.lpadMaxCodeValue());
		}
		
		cmtbCode.setCmtbCodeId(cmtbCodeId);
		cmtbCodeRepository.save(cmtbCode);
		 
		return "redirect:/code/code_list";
	}
	
	
	@ModelAttribute
	public void commonAttribute(ModelMap modelMap) {
		modelMap.put("MENU_CODE", "CONFIGRATION");
	}
}
