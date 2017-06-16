/**
 * <pre>
 * Class Name  : PageUtils.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 5.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 5.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.tms.mobile.common.utils;

/**
 * @author ahn
 *
 */
public class PageUtils {

	private int pagingSize = 10;
	private int begin;
	private int end;

	public int pagingBegin(int currentNo) {

		this.begin = Math.max(1, (int) Math.floor(((currentNo - 1) / pagingSize)) * pagingSize + 1);

		return this.begin;
	}

	public int pagingEnd( int totalPages) {
		
		this.end = Math.min( this.begin + this.pagingSize, totalPages);
		
		if(totalPages > (begin+pagingSize)) {
			end = end-1;
		}
		
		return this.end;
	}

	public int getPagingSize() {
		return pagingSize;
	}

	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
