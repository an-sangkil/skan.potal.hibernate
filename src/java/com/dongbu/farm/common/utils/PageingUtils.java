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
	 * 조회할 페이지와 Page당 보여줄 Row수로 끝Row번호를 얻는다.
	 * @param targetPageNo	  : 요청 페이지
	 * @param rowCountPerPage : 화면에 보여줄 Row
	 * @return
	 */
	public static int toRow(int targetPageNo, int rowCountPerPage) {
		return targetPageNo * rowCountPerPage;
	}
	
	/**
	 * Oracle
	 * 조회할 페이지와 Page당 보여줄 Row수로 시작Row번호를 얻는다.
	 * @param targetPageNo    : 요청 페이지
	 * @param rowCountPerPage : 화면에 보여줄 Row
	 * @return
	 */
	public static int fromRow(int targetPageNo, int rowCountPerPage) {
		return ((targetPageNo - 1) * rowCountPerPage + 1);
	}
	
	/**
	 * Oracle
	 * 화면에 표시될(밑에) 계산된 전체 페이지 전체 갯수
	 * @param totalCount : 총 Row 갯수
	 * @param maxRow     : 페이지당 게시물 수
	 * @return
	 */
	public static  int totalPage(int totalCount,int maxRow){
		return (totalCount + maxRow -1 ) / maxRow;
	}
	
	/**
	 * Oracle
	 * 마지막 Page 계산법 -- 전체 페이지 계산이라고 봐도 무관
	 * @param totalCount : 총 Row 갯수
	 * @param maxRow     : 페이지당 게시물수
	 * @return
	 */
	public static int lastEndPage(int totalCount,int maxRow){
		return totalCount/maxRow+(totalCount%maxRow==0?0:1);
	}
	
	/**
	 * Oracle
	 * 처음 페이지 계산법 
	 * @param totalCount 총 Row 갯수
	 * @return
	 */
	public static int firstStartPage(int totalCount){
		return (totalCount*0)+1;
	}
	
    /**
     * 화면에 보이는 Start Page 번호  
     * 1 = ( 4 * (( 4 - 1) / 4 )) + 1  			-- 활용법
     * @param currpageNum
     * @param maxTotalPage_Size
     * @return
     */
    public static int startPage(int maxTotalPage, int currpageNum){
    	
    	return (maxTotalPage*((currpageNum-1)/maxTotalPage))+1;	
    }

    /**
     * 화면에 보이는 End Page 번호 
     * 4 = 1 + ( 4 - 1)							-- 활용법
     * 이렇게 하면 [1]~[4]까지는 나올겁니다.
     * @param startPage
     * @param maxTotalPage_Size
     * @return
     */
    public static int endPage(int startPage,int maxTotalPage_Size){
    	
    	return startPage+(maxTotalPage_Size-1);
    }
	
	/**
	 * 요청 페이지 계산법 
	 * @param currpageNum : 요청 페이지 번호
	 * @param maxRow	  : 페이지당 게시물 수
	 */
	public static int pageForm(int currpageNum,int maxRow){
		return ((currpageNum-1)*maxRow);
	}
	
}
