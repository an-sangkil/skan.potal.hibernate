package com.dongbu.potal.web.potal.common.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.dongbu.potal.common.util.pagnation.PaginationInfo;
import com.dongbu.potal.common.util.pagnation.PagingList;

/**
 * <pre>
 * Class Name  : AbstractCommonDao.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 8. 5.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 8. 5.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Repository
public abstract class AbstractCommonDao extends SqlSessionDaoSupport {
	
	private final String NAMESPACE = this.getClass().getPackage().getName();
	
	
	@SuppressWarnings("unchecked")
	public PagingList pagingList(Map<String, Object> dataMap , String selectQueryId) {
		
		
		int pageUnit = 10;
		int pageSize = 10;
		int FIRST_DATA_NO = 0;
		int currentPageNo = 0;
		
		try {
			currentPageNo = Integer.parseInt(dataMap.get("pageIndex") + "");
		} catch (Exception e) {
			currentPageNo = 1;
		}
		
		try {
			FIRST_DATA_NO = Integer.parseInt(dataMap.get("FIRST_DATA_NO") + "");
		} catch (Exception e) {
			FIRST_DATA_NO = 1;
		}
		

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setPageItemCount(pageUnit);
		paginationInfo.setPageSize(pageSize);
		paginationInfo.setCurrentPageNo(currentPageNo);
		paginationInfo.setFirstDataNo(FIRST_DATA_NO);
		
		dataMap.put( "itemRowNumber" , paginationInfo.getFirstRecordIndex());
		dataMap.put( "pageItemCount" , 101);
		dataMap.put( "FIRST_DATA_NO" , paginationInfo.getFirstDataNo());
		
		List<Object> list =  super.getSqlSession().selectList(NAMESPACE + selectQueryId, dataMap);
		
		if( StringUtils.defaultString((String)dataMap.get("pagingType"), "")  .equals("PAGING_BEFOR")) {
			Collections.reverse( list );
		}
		
		if(list.size() > 0) {
			
			Map<String, Object> aa= (Map<String, Object>)list.get(0);
			try {
				String firstDataNo = String.valueOf(aa.get("no"));
				paginationInfo.setFirstDataNo(Integer.parseInt(firstDataNo));
			} catch (Exception e) {
				
			}
		}
		
		if(list.size() > 100) {
			Map<String, Object> aa= (Map<String, Object>)list.get(list.size() -1);
			try {
				String lastDataNo = String.valueOf(aa.get("no"));
				paginationInfo.setLastDataNo(Integer.parseInt(lastDataNo));
			} catch (Exception e) {
				
			}
		}

		PagingList pagingList = new PagingList();
		pagingList.setPaginationInfo(paginationInfo);
		
		List<Object> pageDataList = new ArrayList<>();
		
		int offSet = ( currentPageNo - (paginationInfo.getFirstPageNoOnPageList()) ) * pageSize;
		for( int i = 0; i < 10 && ( offSet + i) < list.size() ; i++ ) {
			pageDataList.add( list.get( offSet + i ) );
		}
		
		pagingList.setSelectList(list);
		pagingList.setPageDataList(pageDataList);
		
		return pagingList; 
	}
	
	public abstract int insert(Object obj , Map<String,Object> dataMap ) throws Exception;
	public abstract int update(Object obj , Map<String,Object> dataMap ) throws Exception;
	public abstract int delete(Object obj , Map<String,Object> dataMap ) throws Exception;
	
	public abstract Class<?> select(Object obj , Map<String,Object> dataMap ) throws Exception;
	public abstract List<?> selectList(Object obj, Class<?> classes,Map<String, Object> dataMap) throws Exception;
	
}
