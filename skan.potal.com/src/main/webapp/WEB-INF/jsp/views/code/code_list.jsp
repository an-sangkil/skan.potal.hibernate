<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>

<style>
</style>
<script>
var pagingAction = (function (){
	return {
		submit : function (pageNo) {
			document.code_form.page.value = pageNo;  
			document.code_form.submit();		
		},
		pre : function (beginNo) {
			document.code_form.page.value = Number(beginNo-2);  
			document.code_form.submit();
		},
		next : function (endNo) {
			document.code_form.page.value = Number(endNo); 
			document.code_form.submit();
		}
		
	}
})(); 

var addressAction = (function () {
	return {
		moveForm : function () {
			document.code_form.action = "${pageContext.request.contextPath}/code/code_form";
			document.code_form.submit();
		},
		detailView : function (code) {
			document.code_form.action = "${pageContext.request.contextPath}/code/code_form";
			document.code_form.code.value= code;
			document.code_form.submit();
		}
	}
})();

</script>


<form name="code_form" method="post" action="${pageContext.request.contextPath}/code/code_list">
	<input id="page" 		name="page" 		type="hidden" value=""> 
	<input id="code" 		name="code" 		type="hidden" value="">
	
	<div class="jumbotron">
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						<select class="form-control">
								<option value="">코드명</option>
						</select>
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchName" name="searchName" placeholder="검색어" value="${searchName}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	
		<div class="row">
			<div class="col-md-4 col-md-offset-8" align="right">
				<!-- Contextual button for informational alert messages -->
				<button type="submit" class="btn btn-info" onclick="">검색 <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			</div>
		</div>
	</div>
</form>

<div class="row">
	<div class="col-md-4 col-md-offset-8" align="right">
		<!-- Contextual button for informational alert messages -->
		<button type="button" class="btn btn-info" onclick="addressAction.moveForm()">
			코드추가 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
</div>

<div>
	<table class="table table-striped">
		<caption>코드정보</caption>
		<thead>
			<tr>
				<th>코드번호</th>
				<th>순번</th>
				<th>코드명</th>
				<th>상위코드(명)</th>
				<th>설명</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${codePage.content}" varStatus="status">
				<tr onclick="addressAction.detailView('${item.cmtbCodeId.code}')">
					<th scope="row">${item.cmtbCodeId.code}</th>
					<td>${item.codeSeq}</td>
					<td>
						${item.codeName}
					</td>
					<td>
						${item.upperCode }
						<c:if test="${!empty item.cmtbUpperCode }">
						(${item.cmtbUpperCode.codeName})
						</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${fn:length(item.codeComment) > 18}">
							<c:out value="${fn:substring(item.codeComment,0,15)}" />...     
							</c:when>
							<c:otherwise>
								${item.codeComment}
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	<div align="center">
		<c:choose>
			<c:when test="${!empty codePage.content}">
				<ul class="pagination">
					<c:if test="${begin ne 1}">
						<li><a href="#" onclick="pagingAction.pre('${begin}')">이전</a></li>
					</c:if>
					<c:forEach begin="${begin}" end="${end}" var="pagingNo">
						<li class="<c:if test="${current eq pagingNo}">active</c:if>"><a href="#" onclick="pagingAction.submit('${pagingNo-1}')">${pagingNo}</a></li>
					</c:forEach>
					<c:if test="${codePage.totalPages > end }">
						<li><a href="#" onclick="pagingAction.next('${end}')">다음</a></li>
					</c:if>

				</ul>
			</c:when>
		</c:choose>
	</div>
