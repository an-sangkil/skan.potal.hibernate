<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<!--
	* fileName   : boardList.jsp
	* createDate : 2010. 12. 20. 오후 12:21:43
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>
<%@ include file="/common/include/includeJavaScript.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>board List</title>
<script type="text/javascript">

	//게시판 작성 페이지로 이동
	function writeBoardMove(){
		document._board.action = "/common/board/writeBoardMove.common";
		document._board.submit();
	}
	
	function goPage(pageNumber){
		document._board.pagingpage.value = pageNumber;
		document._board.submit();
	}
	
	//게시판 상세보기
	function getDetailBoard(seq){
		document._board.seq.value = seq;
		document._board.viewType.value = "detailView";
		document._board.action = "/common/board/getBoard.common";
		document._board.submit();
		
	}
</script>
</head>
	<body>
	<!-- 1. Header Contents-->
	<div>
		<%@ include file="/mainHeader.jsp" %>
	</div>
		
	<!-- 2. left Contents -->
	<div style="float: left;width: 230px">
		
	</div>	
	
	<!-- 3. main contents -->
	<div class="mainContents">	
		<div style="text-align: right;">
			<button type="button" onclick="writeBoardMove();">글쓰기</button>
		</div>		
		<div class="list_table">
			<table border="0" cellspacing="0" cellpadding="0">
				<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>조회</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${!empty boardList.list}">
						<c:forEach var="items" items="${boardList.list}" varStatus="status">
							<tr onclick="getDetailBoard('${items.seq}')">
								<td>${items.seq}</td>
								<td>
									<c:set var="lev" value="&nbsp;"></c:set>
										<c:choose>
											<c:when test="${items.lev > 0}">
												<c:forEach var="i" begin="0" end="${items.lev-1}">
													<c:set var="lev" value="${lev}&nbsp;&nbsp;&nbsp;"  />
												</c:forEach>
												<c:set var="lev" value="${lev}└ " />				
											</c:when>
										</c:choose>
									${lev} ${items.subject}
								</td>
								<td>${items.createdtime}</td>
								<td>${items.readcnt}</td>
							</tr> 
						</c:forEach>		
					</c:when>
					<c:otherwise>
						<tr>
							<td>데이터가 존재 하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			
			<!-- common paging -->
			<div id="pageArea">
				<script language="javascript">
					document.write(makePageHtml(${boardList.pageInfo.pagingPage}, 10, ${boardList.pageInfo.totalNumberOfEntries}, 10));
				</script>
			</div>
		</div>
		
</div>

		<form name="_board" action="" method="post">
			<input type="hidden" name="pagingpage" id="pagingpage" />
			<input type="hidden" name="seq" id="seq" />
			<input type="hidden" name="viewType" id="viewType" />
			<input type="hidden" name="bbsid" id="bbsid" value='<c:out value="${bbsid}" ></c:out>' />
		</form>
		
	</body>
</html>