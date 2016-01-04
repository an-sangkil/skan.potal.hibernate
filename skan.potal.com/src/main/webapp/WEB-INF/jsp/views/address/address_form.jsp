<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>
<script>
	var addressAction = (function () {

		var actionUrl = "";
		
		if('${cmtbaddress.cmtbaddressPK.schMgtNo}' != '') {
			actionUrl = "${pageContext.request.contextPath}/schdule/schdule_modify";
		} else {
			actionUrl = "${pageContext.request.contextPath}/schdule/schdule_insert";
		}
		
		return {
			submit : function () {
				document.address_form.action=actionUrl;
				document.address_form.submit();
			}
		}
		
	})();
	
	$(function() {
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
		
		$("#stdDate").datepicker(dateObject);
		$("#endDate").datepicker(dateObject);
		
	});
	
	
</script>

<form name="address_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/schdule/schdule_insert" method="post">
<input type="hidden" id="schMgtNo" name="schMgtNo" value="${cmtbaddress.cmtbaddressPK.schMgtNo}">
<input type="hidden" id="schSeq" name="schSeq" value="${cmtbaddress.cmtbaddressPK.schSeq}">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">시작날짜</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="stdDate" name="stdDate" placeholder="오늘 날짜" value="${cmtbaddress.stdDate}">
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.stdDate" /></label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">종료날짜</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="endDate" name="endDate" placeholder="오늘 날짜" value="${cmtbSchedule.endDate}">
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.endDate" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">날씨</label>
		<div class="col-sm-10">
			<select class="form-control" id="todayWeatherCode" name="todayWeatherCode">
				<c:forEach var="cmtbCode" items="${cmtbCodes}" varStatus="status">
					<option value="${cmtbCode.code}" <c:if test="${cmtbSchedule.todayWeatherCode eq cmtbCode.code}">selected</c:if>>${cmtbCode.codeName}</option>
				</c:forEach>
			</select> 
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.todayWeatherCode" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">최저온도</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="minTemperature" name="minTemperature" placeholder="17" value="${cmtbSchedule.minTemperature}" />
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.minTemperature" /></label>
			</div>
		</div>

		<label for="lastname" class="col-sm-2 control-label">최고온도</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="maxTemperature" name="maxTemperature" placeholder="17" value="${cmtbSchedule.maxTemperature}" /> 
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.maxTemperature" /></label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">제목</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="schSubject" name="schSubject" placeholder="제목" value="${cmtbSchedule.schSubject}" />
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.schSubject" /></label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">내용</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="3" id="schContent" name="schContent" placeholder="내용을 입력하세요">${cmtbSchedule.schContent}</textarea>
			<div>
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbSchedule.schContent" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" class="btn btn-default" onclick="scheduleAction.submit();">저장</button>
			<button type="button" class="btn btn-danger" onclick="history.back(-1)">취소</button>
		</div>
	</div>

</form>