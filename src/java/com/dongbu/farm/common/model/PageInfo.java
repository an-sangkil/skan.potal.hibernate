package com.dongbu.farm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 페이지 처리를 위한 공통 클래스
 */
public class PageInfo {

	private int pagingPage;// 현재 페이지
	private int totalNumberOfPages;// 전체 페이지 수
	private int totalNumberOfEntries;// 전체 데이터 수
	private int pagingNumberPer = 10;// 페이지 당 볼 갯수

	public PageInfo() {
		super();
	}

	public PageInfo(int pagingNumberPer) {
		super();
		this.pagingNumberPer = pagingNumberPer;
	}

	public PageInfo(int pagingPage, int totalNumberOfPages, int totalNumberOfEntries) {
		super();
		this.pagingPage = pagingPage;
		this.totalNumberOfPages = totalNumberOfPages;
		this.totalNumberOfEntries = totalNumberOfEntries;
	}

	public PageInfo(int pagingPage, int totalNumberOfPages, int totalNumberOfEntries, int pagingNumberPer) {
		super();
		this.pagingPage = pagingPage;
		this.totalNumberOfPages = totalNumberOfPages;
		this.totalNumberOfEntries = totalNumberOfEntries;
		this.pagingNumberPer = pagingNumberPer;
	}

	public int getPagingPage() {
		if (pagingPage == 0)
			return 1;
		return pagingPage;
	}

	public void setPagingPage(int pagingPage) {
		this.pagingPage = pagingPage;
	}

	public int getTotalNumberOfPages() {
		this.totalNumberOfPages = (int) Math.ceil((double) totalNumberOfEntries / (double) pagingNumberPer);
		return this.totalNumberOfPages;
	}

	public int getTotalNumberOfEntries() {
		return totalNumberOfEntries; 
	}

	public void setTotalNumberOfEntries(int totalNumberOfEntries) {
		this.totalNumberOfEntries = totalNumberOfEntries;
	}

	public int getPagingNumberPer() {
		return pagingNumberPer;
	}

	public void setPagingNumberPer(int pagingNumberPer) {
		this.pagingNumberPer = pagingNumberPer;
	}

	public int getStartPoint() {
		return ((this.pagingPage - 1) * this.pagingNumberPer) + 1;
	}

	public int getEndPoint() {
		return this.pagingPage * this.pagingNumberPer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}