package com.dongbu.farm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ������ ó���� ���� ���� Ŭ����
 */
public class PageInfo {

	private int pagingPage;// ���� ������
	private int totalNumberOfPages;// ��ü ������ ��
	private int totalNumberOfEntries;// ��ü ������ ��
	private int pagingNumberPer = 10;// ������ �� �� ����

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