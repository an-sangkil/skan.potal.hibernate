package com.skan.potal.common.util.pagnation.renderer;

import java.text.MessageFormat;

import com.skan.potal.common.util.pagnation.PaginationInfo;
import com.skan.potal.common.util.pagnation.PagingList;

/**
 * <pre>
 * Class Name  : PagingRenderer.java
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
public class PagingRenderer {
public String previousPageLabel = "<li><a href=\"#\" class=\"prev\" onclick=\"{0}({1},{2}); return false;\">이전</a></li>";
	
	public String currentPageLabel = "<li class=\"active\"><a href=\"javascript:;\">{0}</a></li>"; 
	public String otherPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
	
	public String nextPageLabel = "<li><a href=\"#\" class=\"next\" onclick=\"{0}({1},{2}); return false;\">다음</a></li>";
	
	public String renderPagination(PaginationInfo paginationInfo,PagingList pagingList , String jsFunction) {
		
		int currentPageNo = paginationInfo.getCurrentPageNo(); 									// 선택한 페이지 번호
		int selectListSize   =  pagingList.getSelectList().size();								// 검색된  페이지의  전체 아이템 갯수
		
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();					// 현재 페이지의 시작점  EX:) 1/11/21
		int pageViewingNo = (( selectListSize -1 ) / 10 ) + 1;									// 화면에 보여줄 페이지 계산(1~10) 몇개     ((전체 사이즈 -1 / 기준 페이지 사이즈 ))+1
		

		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"pagination\">\n");
		
		if(firstPageNoOnPageList > 10) {
			sb.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - paginationInfo.getPageSize()) , "\'PAGING_BEFOR\'" })); // 이전  (첫번째 페이지 번호 - 페이지 사이즈)
		}
		
			//검색된 사이즈 양 만큼 ㄱㄱ
			for (int i = 0 ; i < 10 && i < pageViewingNo ; i++) {
				if (i+(firstPageNoOnPageList) == currentPageNo) {
					sb.append(MessageFormat.format(currentPageLabel, new Object[] { Integer.toString((i) + firstPageNoOnPageList) }));
				}
				else {
					sb.append(MessageFormat.format(otherPageLabel, new Object[] { jsFunction, Integer.toString((i) + firstPageNoOnPageList), Integer.toString((i) + firstPageNoOnPageList) }));
				}
			}

			
		if(selectListSize > 100){
			sb.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList+paginationInfo.getPageSize()) , "\'PAGING_AFTER\'" })); // 다음 (처음 페이지 번호 + 페이지 사이즈)
		}
		
		sb.append("</ul>\n");
		
		return sb.toString();
	}
}
