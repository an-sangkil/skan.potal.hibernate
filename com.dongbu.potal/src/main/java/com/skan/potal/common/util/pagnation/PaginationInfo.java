package com.skan.potal.common.util.pagnation;

import org.springframework.beans.factory.annotation.Value;


/**
 * <pre>
 * Class Name  : PagnationInfo.java
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
 * Copyright (C) 2014 by SKAN All right reserved.
 */
public class PaginationInfo {
	
	@Value(value="${potal.db.type?:postgresql}")
	public static String DB_TYPE; 
	 
	private int pageItemCount ;          // 한페이지에 보여줄 아이템 갯수
    private int pageSize;                // 한페이지에 보여지는 페이지 건수
    private int currentPageNo;           // 선택한 페이지 번호
    
    private int firstDataNo;             // 첫번째 데이터 번호값
    private int lastDataNo;              // 마지막 데이터 번호값
       
    private int firstPageNoOnPageList;   // 화면에 보여지는 페이지 리스트의 처음 페이지 번호

    private int firstRecordIndex;        // 페이지 검색 시작점 (limit offset 사용시 / 현재 페이지의 offSet 계산에 활용)

	/**
	 * @return the pageItemCount
	 */
	public int getPageItemCount() {
		return pageItemCount;
	}

	/**
	 * @param pageItemCount the pageItemCount to set
	 */
	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * @param currentPageNo the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * @return the firstDataNo
	 */
	public int getFirstDataNo() {
		return firstDataNo;
	}

	/**
	 * @param firstDataNo the firstDataNo to set
	 */
	public void setFirstDataNo(int firstDataNo) {
		this.firstDataNo = firstDataNo;
	}

	/**
	 * @return the lastDataNo
	 */
	public int getLastDataNo() {
		return lastDataNo;
	}

	/**
	 * @param lastDataNo the lastDataNo to set
	 */
	public void setLastDataNo(int lastDataNo) {
		this.lastDataNo = lastDataNo;
	}

	/**
	 * @return the firstPageNoOnPageList
	 */
	public int getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}

	/**
	 * @param firstPageNoOnPageList the firstPageNoOnPageList to set
	 */
	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}

	
	/**
	 * 페이지 시작점 NUM
	 * @return
	 */
	public int getFirstRecordIndex() {
		
		if ("cubrid".equals(DB_TYPE )) {
			firstRecordIndex = (getCurrentPageNo() - 1) * getPageItemCount() + 1;
		} else if ("mysql".equals(DB_TYPE)) {
			firstRecordIndex = (getCurrentPageNo() - 1) * getPageItemCount();
		} else if ("postgre".equals(DB_TYPE)) {
			firstRecordIndex = (getCurrentPageNo() - 1) * getPageItemCount();
		} else if ("oracle".equals(DB_TYPE)) {
			firstRecordIndex = (getCurrentPageNo() - 1) * getPageItemCount() + 1;;
		}
		
		return firstRecordIndex;
	}
	
    
}
