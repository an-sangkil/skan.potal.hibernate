<%@page import="com.skan.potal.web.potal.common.util.PageUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<style>
</style>
<script>
var pagingAction = (function (){
	return {
		submit : function (pageNo) {
			document.cattle_form.page.value = pageNo;  
			document.cattle_form.submit();		
		},
		pre : function (beginNo) {
			document.cattle_form.page.value = Number(beginNo-2);  
			document.cattle_form.submit();
		},
		next : function (endNo) {
			document.cattle_form.page.value = Number(endNo); 
			document.cattle_form.submit();
		}
		
	}
})(); 

var cattleAction = (function () {
	return {
		moveForm : function () {
			document.cattle_form.action = "${pageContext.request.contextPath}/address/cattle_form";
			document.cattle_form.submit();
		},
		detailView : function (hmMgtNo) {
			document.cattle_form.action = "${pageContext.request.contextPath}/address/cattle_form";
			document.cattle_form.hmMgtNo.value= hmMgtNo;
			document.cattle_form.submit();
		}
	}
})();

</script>


<form name="cattle_form" method="post" action="${pageContext.request.contextPath}/cattle/cattle_list">
	<input id="page" name="page" type="hidden" value=""> 
	<input id="entityDiscernNo" name="entityDiscernNo" type="hidden" value="">
	
	<div class="jumbotron">
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						개체번호
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchEntityDiscernNo" name="searchEntityDiscernNo" placeholder="검색어" value="${searchName}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						성별
					</label>
					<div class="col-sm-4">
						<select class="form-control" id="searchGender" name="searchGender">
							<option value="황소">황소</option>						
							<option value="암소">암소</option>
						</select>
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
			</div><div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						기간(분만예정일)
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="expectedDateConfinementFrom" name="expectedDateConfinementFrom" placeholder="시작일" value="${expectedDateConfinementFrom}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="expectedDateConfinementTo" name="expectedDateConfinementTo" placeholder="종료일" value="${expectedDateConfinementTo}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						기간(출생일)
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchBirthDayFrom" name="searchBirthDayFrom" placeholder="시작일" value="${searchBirthDayFrom}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchBirthDayTo" name="searchBirthDayTo" placeholder="종료일" value="${searchBirthDayTo}">
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
				<button type="button" class="btn btn-warning" onclick="document.cattle_form.reset();">Reset <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></button>
				<button type="submit" class="btn btn-info" onclick="">검색 <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			</div>
		</div>
	</div>
</form>

<div class="row">
	<div class="col-md-4 col-md-offset-8" align="right">
		<!-- Contextual button for informational alert messages -->
		<button type="button" class="btn btn-info" onclick="addressAction.moveForm()">
			개체추가 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
</div>

<div>
	<table class="table table-striped">
		<caption>개체관리.</caption>
		<thead>
			<tr>
				<th>번호</th>
				<th>성별</th>
				<th>개월수</th>
				<th>분만예정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${hmCattlePage.content}" varStatus="status">
				<tr>
					<td>${item.entityDiscernNo}</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
</div>
	<div align="center">
		<c:choose>
			<c:when test="${!empty hmCattlePage.content}">
				<ul class="pagination">
					<c:if test="${begin ne 1}">
						<li><a href="#" onclick="pagingAction.pre('${begin}')">이전</a></li>
					</c:if>
					<c:forEach begin="${begin}" end="${end}" var="pagingNo">
						<li class="<c:if test="${current eq pagingNo}">active</c:if>"><a href="#" onclick="pagingAction.submit('${pagingNo-1}')">${pagingNo}</a></li>
					</c:forEach>
					<c:if test="${hmCattlePage.totalPages > end }">
						<li><a href="#" onclick="pagingAction.next('${end}')">다음</a></li>
					</c:if>

				</ul>
			</c:when>
		</c:choose>
	</div>
