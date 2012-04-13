/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 6.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.utils;

public class PageingUtils {
	
	
	/**
	 * Oracle
	 * ��ȸ�� �������� Page�� ������ Row���� ��Row��ȣ�� ��´�.
	 * @param targetPageNo	  : ��û ������
	 * @param rowCountPerPage : ȭ�鿡 ������ Row
	 * @return
	 */
	public static int toRow(int targetPageNo, int rowCountPerPage) {
		return targetPageNo * rowCountPerPage;
	}
	
	/**
	 * Oracle
	 * ��ȸ�� �������� Page�� ������ Row���� ����Row��ȣ�� ��´�.
	 * @param targetPageNo    : ��û ������
	 * @param rowCountPerPage : ȭ�鿡 ������ Row
	 * @return
	 */
	public static int fromRow(int targetPageNo, int rowCountPerPage) {
		return ((targetPageNo - 1) * rowCountPerPage + 1);
	}
	
	/**
	 * Oracle
	 * ȭ�鿡 ǥ�õ�(�ؿ�) ���� ��ü ������ ��ü ����
	 * @param totalCount : �� Row ����
	 * @param maxRow     : �������� �Խù� ��
	 * @return
	 */
	public static  int totalPage(int totalCount,int maxRow){
		return (totalCount + maxRow -1 ) / maxRow;
	}
	
	/**
	 * Oracle
	 * ������ Page ���� -- ��ü ������ ����̶�� ���� ����
	 * @param totalCount : �� Row ����
	 * @param maxRow     : �������� �Խù���
	 * @return
	 */
	public static int lastEndPage(int totalCount,int maxRow){
		return totalCount/maxRow+(totalCount%maxRow==0?0:1);
	}
	
	/**
	 * Oracle
	 * ó�� ������ ���� 
	 * @param totalCount �� Row ����
	 * @return
	 */
	public static int firstStartPage(int totalCount){
		return (totalCount*0)+1;
	}
	
    /**
     * ȭ�鿡 ���̴� Start Page ��ȣ  
     * 1 = ( 4 * (( 4 - 1) / 4 )) + 1  			-- Ȱ���
     * @param currpageNum
     * @param maxTotalPage_Size
     * @return
     */
    public static int startPage(int maxTotalPage, int currpageNum){
    	
    	return (maxTotalPage*((currpageNum-1)/maxTotalPage))+1;	
    }

    /**
     * ȭ�鿡 ���̴� End Page ��ȣ 
     * 4 = 1 + ( 4 - 1)							-- Ȱ���
     * �̷��� �ϸ� [1]~[4]������ ���ð̴ϴ�.
     * @param startPage
     * @param maxTotalPage_Size
     * @return
     */
    public static int endPage(int startPage,int maxTotalPage_Size){
    	
    	return startPage+(maxTotalPage_Size-1);
    }
	
	/**
	 * ��û ������ ���� 
	 * @param currpageNum : ��û ������ ��ȣ
	 * @param maxRow	  : �������� �Խù� ��
	 */
	public static int pageForm(int currpageNum,int maxRow){
		return ((currpageNum-1)*maxRow);
	}
	
}
