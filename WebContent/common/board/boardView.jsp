<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<!--
	* fileName   : main.jsp
	* createDate : 2010. 7. 20. 오후 2:03:43
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
<title>Main Page</title>

	<script type="text/javascript" src="/common/webedit/ckeditor3_5_1/ckeditor.js"></script>
	<script type="text/javascript">
		
		//게시글 수정 화면으로 이동 
		function _boardModifyMove(){
			document._board.viewType.value = "modifyMode";
			document._board.action = "/common/board/getBoard.common";
			document._board.submit();
		}
		
		function _boardReplyMove(){
			document._board.viewType.value = "replyMode";
			document._board.action = "/common/board/getBoard.common";
			document._board.submit();
		}
		
		//게시판 목록으로 이동
		function _boardList(){
			document._board.action = "/common/board/getBoardList.common";
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
		게시판 이름은[ <c:out value="${bbsid }"></c:out> ]입니다.
		<div class="insert_table">
			<table>
				<tr>
					<th>
						작성자
					</th>
					<td>
					</td>
					<th>
						작성자아이디
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						제목
					</th>
					<td colspan="3">
						${boardInfo.subject}			
					</td>
				</tr>
				<tr>
					<th>
						내용
					</th>
					<td colspan="3">
						<pre style="width:540px">${boardInfo.content }</pre>			
					</td>
				</tr>
			</table>	
			
			<!-- 첨부파일 -->
			<div>
				<c:choose>
					<c:when test="${ !empty uploadFileList}">
						<c:forEach var="items" items="${uploadFileList}" varStatus="status">
							<div onclick="fileDownload();" id="${items.file_id}" style="cursor: pointer;">${items.original_file_name}</div>
						</c:forEach>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</div>

			<div>
				<button type="button" onclick="_boardReplyMove()">답글</button>
				<button type="button" onclick="_boardModifyMove()">수정</button>
				<button type="button" onclick="_boardList()">목록</button>
			</div>
		</div>
		
		
		<!-- 댓글 -->
		<%@include file="/common/comment/comment.jsp" %>
		
		
		<!-- facebook.com plug-in -->
		<div>
		<!-- 
			<script src="http://connect.facebook.net/en_US/all.js#xfbml=1"></script>
	      	<fb:like></fb:like> -->
			      <div id="fb-root"></div>
		      <script src="http://connect.facebook.net/en_US/all.js">
		      </script>
		      <script>
		         //var aa = json_decode("https://graph.facebook.com/me/friends?access_token=2227470867|2.KmVncckALqJAhK61Z_3KCw__.3600.1300957200-100002109219873|k_EdR4xd745F3IlOIlW9fraWA4w");
				
		         </script>
      	</div>
	</div>	
		<form name="_board" action="" method="post">
			<input type="hidden" name="seq" id="seq" value="${boardInfo.seq }">
			<input type="hidden" name="bbsid" id="bbsid" value="${bbsid}">
			<input type="hidden" name="viewType" id="viewType" value="">
			<input type="hidden" name="content" id="content" value="">
			<input type="hidden" name="subject" id="subject" value="">
		</form>
	</body>
</html>