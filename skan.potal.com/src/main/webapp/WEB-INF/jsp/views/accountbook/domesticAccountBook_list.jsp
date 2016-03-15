<%@page import="com.skan.potal.web.potal.common.util.PageUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<style>
<!--
.header-fixed {
    width: 100% 
}

.header-fixed > thead,
.header-fixed > tbody,
.header-fixed > thead > tr,
.header-fixed > tbody > tr,
.header-fixed > thead > tr > th,
.header-fixed > tbody > tr > td {
    display: block;
}

.header-fixed > tbody > tr:after,
.header-fixed > thead > tr:after {
    content: ' ';
    display: block;
    visibility: hidden;
    clear: both;
}

.header-fixed > tbody {
    overflow-y: auto;
    height: 380px;
}

.header-fixed > tbody > tr > td,
.header-fixed > thead > tr > th {
    width: 20%;
    float: left;
}
-->
</style>
<form name="domesticAccountBook_form" method="post" action="${pageContext.request.contextPath}/domestic_account_book/list">
	<input id="page" 		name="page" 		type="hidden" value=""> 
	
	<div class="jumbotron">
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						기간
					</label>
					<div class="col-sm-5"> 
						<input type="radio" name="searchType" value="BASIC"   <c:if test="${searchType eq 'BASIC' }">checked</c:if> <c:if test="${empty searchType}">checked</c:if>    onclick="DomesticAccountBookAction.search()">기본 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="1WEEK"   <c:if test="${searchType eq '1WEEK' }">checked</c:if>    onclick="DomesticAccountBookAction.search()">1주일 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="1MONTHS" <c:if test="${searchType eq '1MONTHS' }">checked</c:if>  onclick="DomesticAccountBookAction.search()">1달 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="3MONTHS" <c:if test="${searchType eq '3MONTHS' }">checked</c:if>  onclick="DomesticAccountBookAction.search()">3달 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="6MONTHS" <c:if test="${searchType eq '6MONTHS' }">checked</c:if>  onclick="DomesticAccountBookAction.search()">6달 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="1YEARS"  <c:if test="${searchType eq '1YEARS' }">checked</c:if>   onclick="DomesticAccountBookAction.search()">1년 &nbsp;&nbsp;
						<input type="radio" name="searchType" value="PERIOD"  <c:if test="${searchType eq 'PERIOD' }">checked</c:if>   onclick="DomesticAccountBookAction.period()">기간지정 &nbsp;&nbsp;
						<div class="has-error">
							기본 : 올해 1월 1일 부터 오늘까지
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						유형
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchName" name="searchName" placeholder="검색어" value="${searchName}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
					<label for="firstname" class="col-sm-2 control-label"> 
						검색어
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="searchName" name="searchName" placeholder="검색어" value="${searchName}">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label"> 
						기간(거래일)
					</label>
					<div class="col-sm-4">
					
					
						<input type="text" class="form-control" id="from" name="fromDate" placeholder="시작일" value="<fmt:formatDate value="${from}" type="date" pattern="yyyy-MM-dd"/>" readonly="readonly">
						<div class="has-error">
							<label class="control-label" for="inputSuccess1"></label>
						</div>
					</div>
					<label for="firstname" class="col-sm-2" style="text-align: center;"> 
						~
					</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="to" name="toDate" placeholder="종료일" value="<fmt:formatDate value="${to}" type="date" pattern="yyyy-MM-dd"/>" readonly="readonly">
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
		<button type="button" class="btn btn-info" onclick="DomesticAccountBookAction.moveForm()">
			신규등록 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
</div>


<div class="col-lg-12"> 
	<table class="table table-striped header-fixed">
		<caption>수입지출 정보</caption>
		<thead>
			<tr>
				<th>유형</th>
				<th>세부유형</th>
				<th>거래일</th>
				<th>금액</th>
				<th>내역</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty domesticAccountBooks}">
					<c:forEach var="item" items="${domesticAccountBooks}" varStatus="status">
						<tr>
							<td>${item.typePersonalCode.codeName}</td>
							<td></td>
							<td>${item.businessDay}</td>
							<td>${item.amount}</td>
							<td>${item.breakdown}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="5">데이터가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<div class="row">
		<div class="col-md-4 col-md-offset-8">
			<table class="table table-striped">
				<tbody>
					<tr>
						<td >수입 : ${income}</td>
						<td >지출 : ${expense}</td>
						<td align="center"> 합계 : ${totalSum} </td>
					</tr>
				</tbody>
			</table>
		
		</div>
	</div>

	
</div>
	<div align="center">
		
	</div>
	
	
	
<script>

var DomesticAccountBookAction = (function () {
	var dateObject = {
			changeMonth: true, 
			changeYear: true,
			nextText: '다음 달',
			prevText: '이전 달',
			showButtonPanel: true, 
			currentText: '오늘 날짜', 
			closeText: '닫기', 
			dateFormat: "yy-mm-dd",
			dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
			monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		}
	
	//  기본 옶션 정보가 필요한 경우 셋팅
	var defaultSearchOption = {
	};
	
	console.log('aa');
	
	if('${searchType}' == 'PERIOD') {
		$("#from").datepicker(dateObject);
		$("#to").datepicker(dateObject);
		document.domesticAccountBook_form.from.removeAttribute('readonly');
		document.domesticAccountBook_form.to.removeAttribute('readonly');
	}
	
	
	return {
		moveForm : function () {
			document.domesticAccountBook_form.action = "${pageContext.request.contextPath}/domestic_account_book/form";
			document.domesticAccountBook_form.submit();
		},
		detailView : function (code) {
			document.domesticAccountBook_form.action = "${pageContext.request.contextPath}/domestic_account_book/form";
			document.domesticAccountBook_form.code.value= code;
			document.domesticAccountBook_form.submit();
		},
		search : function () {
			document.domesticAccountBook_form.action = "${pageContext.request.contextPath}/domestic_account_book/list";
			document.domesticAccountBook_form.submit();
		},
		period : function () {
			
			$("#from").datepicker(dateObject);
			$("#to").datepicker(dateObject);
			
			document.domesticAccountBook_form.from.removeAttribute('readonly');
			document.domesticAccountBook_form.to.removeAttribute('readonly');
		}
	}
})();
</script>
