package com.skan.potal.web.controller;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.skan.potal.common.code.MenuCode;

public abstract class AbstractCommonController {
	
	/**
	 * 공통 Entity Manager DI
	 */
	@Autowired	protected EntityManager entityManager;
	
	public JPAQuery<?> getJPAQueryInstance(){
		JPAQuery<?> jpaQuery = new JPAQuery<>(entityManager);
		return jpaQuery;
	}
	
	public JPADeleteClause getJPADeleteClauseInstance(EntityPath<?> z){
		JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager, z);
		return jpaDeleteClause;
	}
	
	public JPAUpdateClause getJPAUpdateClauseInstance(EntityPath<?> z){
		JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(entityManager, z);
		return jpaUpdateClause;
	}
	
	
	/**
	 * 사이드 메뉴 관리 공통 파라미터....
	 * @param sideMenuCode
	 * @param modelMap
	 */
	@ModelAttribute
	public void menuControl(@RequestParam(required=false) MenuCode sideMenuCode , HttpServletRequest request,  ModelMap modelMap) {
		
		Map<String,? > flashMap  = RequestContextUtils.getInputFlashMap(request);
		if(flashMap != null ) {
			System.out.println(flashMap.get("sideMenuCode"));
			modelMap.put("SIDE_MENU_CODE", flashMap.get("sideMenuCode"));
		} else {
			modelMap.put("SIDE_MENU_CODE", sideMenuCode);
		}
		
	}
}
