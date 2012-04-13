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
		
		//게시글 등록 
		function _boardWrite(){
			
			//필수항목 체크
			var validationItem = ["_subject"];
			var validation = validationCheck(validationItem);
			
			if(validation){
				var editor_data = CKEDITOR.instances._content.getData();
				document._board.content.value = editor_data;
				document._board.subject.value = document.getElementById("_subject").value;
				document._board.action = "/common/board/writeBoard.common";
				document._board.submit();
			} 
			
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
		
		<div class="insert_table">
			게시판 이름은[ <c:out value="${bbsid }"></c:out> ]입니다.
			<table>
				<tr>
					<th>작성자</th>
					<td>	</td>
					<th>작성자아이디</th>
					<td>	</td>
				</tr>
				<tr>
					<th>
						제목
					</th>
					<td colspan="3">
						<input class="input_longType" type="text" name="_subject" id="_subject" value="" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea id="_content" name="_content"></textarea>
						<script type="text/javascript">
						CKEDITOR.replace( '_content',{});
						</script>
					</td>
				</tr>
			</table>
			<div style="width:800px; text-align: right;">
				<button type="button" onclick="_boardWrite()">저장</button>
				<button type="button" onclick="_boardList()">취소</button>
			</div>
		</div>
		
		<form name="_board" action="" method="post" enctype="multipart/form-data">		
			<div id="fileDiv">
				<div>
					<input type="file" name="attach_File" id="attach_File" class="input_file" />
					
					<button type="button" onclick="fileAttachReset()">삭제</button>
					<button type="button" onclick="new AttachFileUpload().attachAddRow()">추가</button>
					
				</div>
			</div>
			<input type="hidden" name="bbsid" id="bbsid" value="${bbsid }">
			<input type="hidden" name="content" id="content" value="">
			<input type="hidden" name="subject" id="subject" value="">
		</form>
	</div>
	</body>
</html>