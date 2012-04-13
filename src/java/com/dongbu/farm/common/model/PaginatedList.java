package com.dongbu.farm.common.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 페이지 처리를 위한 공통 클래스
 */
public class PaginatedList {

	private PageInfo pageInfo;
	private List<Object> list;

	public PaginatedList() {
		super();
	}

	public PaginatedList(PageInfo pageInfo) {
		super();
		this.pageInfo = pageInfo;
	}

	public PaginatedList(List<Object> list) {
		super();
		this.list = list;
	}

	public PaginatedList(PageInfo pageInfo, List<Object> list) {
		super();
		this.pageInfo = pageInfo;
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}