<%@page import="com.skan.potal.web.potal.common.util.PageUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<style>
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

var addressAction = (function () {
	return {
		moveForm : function () {
			document.address_form.action = "${pageContext.request.contextPath}/address/address_form";
			document.address_form.submit();
		},
		detailView : function (hmMgtNo) {
			document.address_form.action = "${pageContext.request.contextPath}/address/address_form";
			document.address_form.hmMgtNo.value= hmMgtNo;
			document.address_form.submit();
		}
	}
})();

</script>


<form name="address_form" method="post" action="${pageContext.request.contextPath}/address/address_list">
	<input id="page" name="page" type="hidden" value=""> <input id="hmMgtNo" name="hmMgtNo" type="hidden" value="">
	
	<div class="jumbotron">
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						<select class="form-control">
								<option value="">이름</option>
								<option value="">전화번호</option>
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
			주소추가 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
</div>

<div>
	<table class="table table-striped">
		<caption>주소정보.</caption>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${hmMngAddressPage.content}" varStatus="status">
				<tr>
					<th scope="row">${item.hmMgNum}</th>
					<td>${item.name}</td>
					<td><c:forEach var="hmAddressPhone" items="${item.hmAddressPhoneSet}" varStatus="status">
							<c:if test="${status.first}">${hmAddressPhone.phoneNumber}</c:if>
						</c:forEach></td>
					<td><c:forEach var="hmEmailInfo" items="${item.hmEmailInfoSet}" varStatus="status">
							<c:if test="${status.first}">${hmEmailInfo.hmEmail}</c:if>
						</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	<div align="center">
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
	</div>
