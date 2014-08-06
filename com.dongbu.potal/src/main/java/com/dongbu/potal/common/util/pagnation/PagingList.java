package com.dongbu.potal.common.util.pagnation;

import java.util.List;

/**
 * <pre>
 * Class Name  : PagingList.java
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
public class PagingList {
	
	private PaginationInfo paginationInfo;
	private List<?> selectList;			// 한 페이지에 불러올 전체 리스트 default 100
	private List<?> pageDataList;		// 한 페이지여 보여줄 리스트 default 10
	
	/**
	 * @return the paginationInfo
	 */
	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}
	/**
	 * @param paginationInfo the paginationInfo to set
	 */
	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
	/**
	 * @return the selectList
	 */
	public List<?> getSelectList() {
		return selectList;
	}
	/**
	 * @param selectList the selectList to set
	 */
	public void setSelectList(List<?> selectList) {
		this.selectList = selectList;
	}
	/**
	 * @return the pageDataList
	 */
	public List<?> getPageDataList() {
		return pageDataList;
	}
	/**
	 * @param pageDataList the pageDataList to set
	 */
	public void setPageDataList(List<?> pageDataList) {
		this.pageDataList = pageDataList;
	}
	
}
