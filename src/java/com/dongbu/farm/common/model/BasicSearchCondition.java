/*
 * $Id: BasicSearchCondition.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
 * created by    : ���ȣ
 * creation-date : 2008. 09. 09
 * =========================================================
 * Copyright (c) 2008 Maninsoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * �˻����� ����Ӽ��� ������ Ŭ����
 * 
 * @author ���ȣ
 * @version $Id: BasicSearchCondition.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
 */
public class BasicSearchCondition {

	/**
	 * �� �������� ������ Row ����
	 */
	protected int rowsCountPerPage;
	/**
	 * ��ȸ�� ������ ��ȣ
	 */
	protected int pageNumToView;
	/**
	 * ���������� ��� ����Ʈ
	 */
	protected List<SortInfo> sortInfos;

	/**
	 * ��������
	 * 
	 * @author ���ȣ
	 * @version $Id: BasicSearchCondition.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
	 */
	public class SortInfo {
		private String sortColumn;
		private boolean isAscending;

		/**
		 * @param sortColumn
		 * @param isAscending
		 */
		public SortInfo(String sortColumn, boolean isAscending) {
			super();
			this.sortColumn = sortColumn;
			this.isAscending = isAscending;
		}

		public String getSortColumn() {
			return sortColumn;
		}

		public void setSortColumn(String sortColumn) {
			this.sortColumn = sortColumn;
		}

		public boolean isAscending() {
			return isAscending;
		}
		
		public String getSortOrder() {
			return isAscending ? "ASC" : "DESC";
		}

		public void setAscending(boolean isAscending) {
			this.isAscending = isAscending;
		}
	}

	public BasicSearchCondition() {
		super();
		this.sortInfos = new ArrayList<SortInfo>();
	}

	/**
	 * @param sortColumnName
	 * @param sortOrder
	 * @param rowsCountPerPage
	 * @param pageNumToView
	 */
	public BasicSearchCondition(int rowsCountPerPage, int pageNumToView) {
		super();
		this.rowsCountPerPage = rowsCountPerPage;
		this.pageNumToView = pageNumToView;
		this.sortInfos = new ArrayList<SortInfo>();
	}

	public int getRowFrom() {
		return ((pageNumToView - 1) * rowsCountPerPage + 1);
	}

	public int getRowTo() {
		return rowsCountPerPage * pageNumToView;
	}

	public int getRowsCountPerPage() {
		return rowsCountPerPage;
	}

	public void setRowsCountPerPage(int rowsCountPerPage) {
		this.rowsCountPerPage = rowsCountPerPage;
	}

	public int getPageNumToView() {
		return pageNumToView;
	}

	public void setPageNumToView(int pageNumToView) {
		this.pageNumToView = pageNumToView;
	}

	public List<SortInfo> getSortInfos() {
		return sortInfos;
	}

	/**
	 * ���Ŀ� ���� ������ �߰��Ѵ�. ȣ������� ���Ŀ켱�����̴�.
	 * 
	 * @param sortColumn
	 * @param isAscending
	 */
	public void addSortInfo(String sortColumn, boolean isAscending) {
		Assert.hasLength(sortColumn);
		this.sortInfos.add(new SortInfo(sortColumn, isAscending));
	}

}
