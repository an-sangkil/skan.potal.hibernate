<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<!--
	* fileName   : main.jsp
	* createDate : 2011. 2. 20. 오후 2:03:43
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
		function _boardSave(){
			
			//필수항목 체크
			var validationItem = ["_subject"];
			var validation = validationCheck(validationItem);
			
			
			//필수항목 체크 true 
			if(validation){
				var editor_data = CKEDITOR.instances._content.getData();
				document._board.content.value = editor_data;
				document._board.subject.value = document.getElementById("_subject").value;
				document._board.action = "/common/board/updateBoard.common";
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
			게시판 이름은[ <c:out value="${bbsid }"></c:out> ]입니다.
			<div class="insert_table">
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
							<input class="input_longType" type="text" name="_subject" id="_subject" value="<c:if test="${saveMode eq 'replyMode'}">[re]</c:if>${boardInfo.subject}" />
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea id="_content" name="_content">${boardInfo.content}</textarea>
							<script type="text/javascript">
								CKEDITOR.replace( '_content',{});
							</script>
						</td>
					</tr>
				</table>
				<div style="width:800px; text-align: right;">
					<button type="button" onclick="_boardSave()">저장</button>
					<button type="button" onclick="_boardList()">취소</button>
				</div>
			</div>
		
		
		
			<form name="_board" action="" method="post" enctype="multipart/form-data">
				<div id="fileDiv">
					<div>
						<c:choose>
							<c:when test="${ !empty uploadFileList}">
								<c:forEach var="items" items="${uploadFileList}" varStatus="status">
									<div onclick="new AttachFileUpload().attachFileDel(this);" id="${items.file_id}" style="cursor: pointer;">${items.original_file_name}</div>
								</c:forEach>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</div>
					<div>
						<input type="file" name="attach_File" id="attach_File" class="input_file"></input>
						<div class="Btn03" onclick="new AttachFileUpload().attacheFileReset();"><a>
							<span class="Btn01Start"></span>
							<span class="Btn01Center">삭제</span>
							<span class="Btn01End"></span>
						</a></div>
						<div class="Btn03" onclick="new AttachFileUpload().attachAddRow();"><a>
							<span class="Btn01Start"></span>
							<span class="Btn01Center">추가</span>
							<span class="Btn01End"></span>
						</a></div>
					</div>
				</div>
				
				<input type="hidden" name="bbsid" id="bbsid" value="${bbsid }">
				<input type="hidden" name="seq"   id="seq" value="${boardInfo.seq}">
				<input type="hidden" name="ref"   id="ref" value="${boardInfo.ref}">
				<input type="hidden" name="step"  id="step" value="${boardInfo.step}">
				<input type="hidden" name="lev"   id="lev" value="${boardInfo.lev}">
				<input type="hidden" name="filegroupid"   id="filegroupid" value="<c:if test="${saveMode ne 'replyMode'}">${boardInfo.filegroupid}</c:if>">
				<input type="hidden" name="saveMode" 	  id="saveMode" value="${saveMode}">
				<input type="hidden" name="content" 	  id="content" value="">
				<input type="hidden" name="subject" 	  id="subject" value="">
				<input type="hidden" name="deletefilelist"  id="deletefilelist" value="">
			</form>
		</div>
	</body>
</html>