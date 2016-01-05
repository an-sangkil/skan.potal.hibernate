<%@page import="com.skan.potal.web.potal.common.util.PageUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<style>
.table-curved {
	border-collapse: separate;
}

.table-curved {
	border: solid #ccc 1px;
	border-radius: 6px;
	border-left: 0px;
}

.table-curved td, .table-curved th {
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
}

.table-curved th {
	border-top: none;
}

.table-curved th:first-child {
	border-radius: 6px 0 0 0;
}

.table-curved th:last-child {
	border-radius: 0 6px 0 0;
}

.table-curved th:only-child {
	border-radius: 6px 6px 0 0;
}

.table-curved tr:last-child td:first-child {
	border-radius: 0 0 0 6px;
}

.table-curved tr:last-child td:last-child {
	border-radius: 0 0 6px 0;
}
</style>
<script>
var pagingAction = (function (){
	return {
		submit : function (pageNo) {
			document.address_form.page.value = pageNo;  
			document.address_form.submit();		
		},
		pre : function (beginNo) {
			document.address_form.page.value = Number(beginNo-2);  
			document.address_form.submit();
		},
		next : function (endNo) {
			document.address_form.page.value = Number(endNo); 
			document.address_form.submit();
		}
		
	}
})(); 

</script>

<form name="address_form" method="get" action="${pageContext.request.contextPath}/address/address_list">
	<input id="page" name="page" type="hidden" value="">
</form>
<table class="table table-curved">
	<caption>주소정보.</caption>
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>email</th>
			<th>Username</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${hmMngAddressPage.content}" varStatus="status">
			<tr>
				<th scope="row">${item.hmMgNum}</th>
				<td>${item.name}</td>
				<td>@mdo</td>
				<td>@mdo</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:choose>
	<c:when test="${!empty hmMngAddressPage.content}">
		<ul class="pagination">
			<c:if test="${begin ne 1}">
			<li><a href="#" onclick="pagingAction.pre('${begin}')">이전</a></li>
			</c:if>
			<c:forEach begin="${begin}" end="${end}" var="pagingNo">
				<li class="<c:if test="${current eq pagingNo}">active</c:if>"><a href="#" onclick="pagingAction.submit('${pagingNo-1}')">${pagingNo}</a></li>
			</c:forEach>
			<c:if test="${hmMngAddressPage.totalPages > end }">			
			<li><a href="#" onclick="pagingAction.next('${end}')">다음</a></li>
			</c:if>
			
		</ul>
	</c:when>
</c:choose>
