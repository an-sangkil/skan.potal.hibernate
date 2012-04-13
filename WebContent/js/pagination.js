/**
 * 
 * @param nPageNo	   : 현재 페이지 번호			
 * @param nPageSize	   : 페이징 번호들(화면에 보여질 페이지 수 default 10)
 * @param nRecordCount : 총엔티티갯수
 * @param nListSize    : 화면에 보여질 엔티티 갯수 
 * @return
 */
function makePageHtml(nPageNo, nPageSize, nRecordCount, nListSize) {
	
	var nTotalEnd = Math.floor(nRecordCount / nListSize); // 맨 끝 페이지를 구한다.(총글수 / 화면에 보여질 페이지의수)

	// 마지막 페이지의 글 수가 초과하면 페이지수 1 추가
	if ((nRecordCount % nListSize) != 0) {
		++nTotalEnd;
	}

	//현재 페이지가 마지막 페이지보다 클때 전 페이지를 보여준다.
	if (nPageNo > nTotalEnd) {
		nPageNo -= 1;
	}

	var nStartPage = Math.floor(((nPageNo - 1) / nPageSize)) * nPageSize + 1;
	var nEndPageTmp = nStartPage + nPageSize - 1;
	var nEndPage = (nTotalEnd > nEndPageTmp) ? nEndPageTmp : nTotalEnd;

	var nPrevious = (nStartPage == 1) ? 0 : (nStartPage - 1);
	var nNext = (nTotalEnd > nEndPage) ? (nEndPage + 1) : 0;

	pageHtml = "";
	pageHtml += "<table width='100%' border=0 style='border-style: none; border-width: 0; border-color: #000000; margin:10 0 0 0;' > ";
	pageHtml += "<tr> ";
	pageHtml += "	<td style='font-family: Dotum,DotumChe,Arial ; font-size: 11px;' style='position:relative; width:100%; height:40px; padding-bottom:0px; border-right:none; border-bottom:none; border-left:none; text-align:center; repeat-x left top'> ";
	if (nRecordCount > 0) {
					pageHtml +="<!--<div class='page_option'><span class='leftBlank'>"+nPageNo+"&nbsp;/&nbsp;Page (Total "+nTotalEnd+")</span></div>-->";
					pageHtml +="<a href=\"javascript:goPage(1)\"><img alt='처음' src='/image/paging/ico_first.gif' border='0' class='pageMove' align='middle' ></a>";
		if (nRecordCount != 0) {
			if (nPrevious != 0) {
					
				pageHtml += "<a href=\"javascript:goPage('"
						+ nPrevious
						+ "')\"><img alt='이전' src='/image/paging/ico_prev.gif' border='0' class='pageMove' align='middle' ></a>";

			} else {
				pageHtml += "<img alt='이전' src='/image/paging/ico_prev.gif' border='0' class='pageMove' align='middle' >";
			}
		}

		for ( var i = nStartPage; i <= nEndPage; i++) {
			if (i == nPageNo) {
				pageHtml += "&nbsp;&nbsp;|&nbsp;&nbsp;<B style='vertical-align:top'>" + i + "</B>";
			} else {
				pageHtml += "&nbsp;&nbsp;|&nbsp;&nbsp;<a style='vertical-align:top' href=\"javascript:goPage('" + i + "')\" class='page_link'>" + i	+ "</a>";
			}
		}
		pageHtml += "&nbsp;&nbsp;|&nbsp;&nbsp;";

		if (nRecordCount != 0) {
			if (nNext != 0) {
				pageHtml += "<a href=\"javascript:goPage('"
						+ nNext
						+ "')\"><img alt='다음' src='/image/paging/ico_next.gif' border='0' class='pageMove' align='middle' ></a>";
			} else {
				pageHtml += "<img alt='다음' src='/image/paging/ico_next.gif' border='0' class='pageMove' align='middle' >";
			}
		}
		pageHtml += "<a href=\"javascript:goPage("+nTotalEnd+")\"><img alt='끝' src='/image/paging/ico_last.gif' border='0' class='pageMove' align='middle' ></a>";
	}
	pageHtml += "</td>";
	pageHtml += "</tr>";
	pageHtml += "</table>";

	//alert(pageHtml);
	
	return pageHtml;
}

var pagination = new Object();

/*
		function goPage(next_page) {
			document._form.pageNo.value = next_page ;
			document._form.target = "";		
			document._form.action = "/common/sanction/getSanctionList.common";
			document._form.submit();
		}
		<SCRIPT LANGUAGE="JavaScript">
			document.write( makePageHtml( 
					<c:out value="${sanctionList[0].PAGE_NO}" default="1"/>, 
					10, 
					<c:out value="${sanctionList[0].TOTAL_COUNT}" default="0"/> , 
					10)
			) ;
		</SCRIPT>
*/