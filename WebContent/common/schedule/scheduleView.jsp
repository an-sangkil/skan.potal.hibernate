<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.dongbu.farm.common.utils.Utils"%>
<%@page import="com.dongbu.farm.common.schedule.model.Schedule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.dongbu.farm.common.spbeancall.SpringBeanCallWeb"%>
<%@page import="com.dongbu.farm.common.schedule.manager.IScheduleManager"%>

<!--
	* fileName   : schedule.jsp
	* createDate : 2011.03. 24. 오후 2:03:43
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"%>
<%@ include file="/common/include/includeJavaScript.jsp"%>
<%
	java.util.Calendar cal = java.util.Calendar.getInstance();

	String strYear = (String)request.getAttribute("std_year");
	String strMonth = (String)request.getAttribute("std_month");

	System.out.println("화면 strYear:" + strYear);
	System.out.println("화면 strMonth:" + strMonth);

	int year = cal.get(java.util.Calendar.YEAR);
	int month = cal.get(java.util.Calendar.MONTH);
	int date = cal.get(java.util.Calendar.DATE);

	if (strYear != null) {
		year = Integer.parseInt(strYear);
		
		//server에서 달수에 +1 한값을 사용하기  때문에 화면에서 달력을 재계산 하기 위해 "-1" 해준다.
		month = Integer.parseInt(strMonth)-1;

		if (month == 12) {
			year++;
			month = 0;
		} else if (month < 0) {
			year--;
			month = 11;
		}
	}
	
	System.out.println("화면 가공 strMonth:" + month);


	cal.set(year, month, 1);
	int startDay = cal.getMinimum(java.util.Calendar.DATE);

	int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

	int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
	int newLine = 0;
	
	
	IScheduleManager scheduleManagerImpl =(IScheduleManager)SpringBeanCallWeb.getBean(request, "scheduleManagerImpl");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>schedule</title>
</head>
<script type="text/javascript">
	
	//상세보기
	function _detailSchedule(sch_mgt_no, sch_seq){
		
		//파라미터 
		var pars = {};
		pars = {
				sch_mgt_no: sch_mgt_no,
				sch_seq : sch_seq
		};
		
		//Ajax call
		var url = "/common/schedule/getSchdule.common";
		var method = 'GET';
		var asynchronouse = true;
		var callbackMethod = _detailResponse;
		
		new PAjax.Request(url, method, pars, asynchronouse, _detailResponse);
	}

	//상세보기 콜백 메소드
	function _detailResponse(response){
		try {
			var resultObj = eval('('+response.responseText+')');
			if(resultObj.result == "SUCCESS"){
				var schedule = resultObj.schedule;
				$('sch_mgt_no').value=schedule.sch_mgt_no;
				$('sch_seq').value=schedule.sch_seq;
				$('sch_subject').value=schedule.sch_subject;
				$('sch_content').value=schedule.sch_content;
				$('std_date').value=schedule.std_date;
				               
				$('_min_degreesPoint').value = schedule.min_degreespoint_code;
				$('min_temperature').value = schedule.min_temperature;
				$('_max_degreesPoint').value = schedule.max_degreespoint_code;
				$('max_temperature').value = schedule.max_temperature;
				$('today_weather_code').value = schedule.today_weather_code;
				
				
				
				/*var _saveButton = $('_saveButton');
				
				var _modifyButton =  new Element("button").observe("click",function(){_modifyResponse();}).update("수정");
				_saveButton.replaceNode(_modifyButton);*/
			}
		} catch (e) {
			alert(e.message);
		}
			
	}
	
	//저장 분기
	function _saveSchedule(){
		
		//새로저장
		if($('sch_mgt_no').value=="" &&	$('sch_seq').value == ""){
			_writeSchedule();
		}else{
			//내용수정
			_modifySchedule();
		}
	}
	
	
	//일정관리 새로 등록
	function _writeSchedule(){
		
		//필수상목 체크 아이템
		var obj = ['sch_subject' , 'sch_content' , 'std_date'];
		
		//파라미터 
		var pars = {};
		pars = {
				sch_subject : $F('sch_subject'),
				sch_content : $F('sch_content'),
				std_date : $F('std_date'),
				min_degreespoint_code : $F('_min_degreesPoint'),
				min_temperature : $F('min_temperature'),
				max_degreespoint_code : $F('_max_degreesPoint'),
				max_temperature : $F('max_temperature'),
				today_weather_code : $F('today_weather_code')
		};
		
		
		//필수항목체크
		if(validationCheck(obj)){
			//Ajax call
			var url = "/common/schedule/writeSchedule.common?";
			var method = 'POST';
			var asynchronouse = true;
			var callbackMethod = _writeResponse;

			new PAjax.Request(url, method, pars, asynchronouse, _writeResponse);
		}
	}
	//일정관리 등록 콜백 메소드
	function _writeResponse(response){
		//location.reload();
		try {
			var resultObj = eval('('+response.responseText+')');
			if(resultObj.result == "SUCCESS"){
				var schedule = resultObj.schedule;
				var std_dateAry = schedule.std_date.split("-");
				var params = {};
				params ={
						sch_mgt_no : schedule.sch_mgt_no,
						sch_seq : schedule.sch_seq,
						sch_subject : schedule.sch_subject,
						sch_content : schedule.sch_content,
						std_date : schedule.std_date,
						
						//서버쪽에서 달계산을 하기때문에 리얼먼스에서 -1을 한값을 보낸다
						std_year  : std_dateAry[0],
						std_month : eval(std_dateAry[1]-1)
				};
				
				location.href = "/common/schedule/getSchduleList.common?" + $H(params).toQueryString();		
			}
		} catch ( e){
			alert(e.message);
		}
	}
	
	
	
	
	
	//일정관리 수정
	function _modifySchedule(){
		
		//필수상목 체크 아이템
		var obj = ['sch_mgt_no' , 'sch_seq' , 'sch_subject' , 'sch_content' , 'std_date'];
		
		//파라미터 
		var pars = {};
		pars = {
				sch_mgt_no : $F('sch_mgt_no'),
				sch_seq : $F('sch_seq'),
				sch_subject : $F('sch_subject'),
				sch_content : $F('sch_content'),
				std_date : $F('std_date'),
				min_degreespoint_code : $F('_min_degreesPoint'),
				min_temperature : $F('min_temperature'),
				max_degreespoint_code : $F('_max_degreesPoint'),
				max_temperature : $F('max_temperature'),
				today_weather_code : $F('today_weather_code')
		};
		
		//필수항목체크
		if(validationCheck(obj)){
			//Ajax call
			var url = "/common/schedule/modifySchedule.common?";
			var method = 'POST';
			var asynchronouse = true;
			var callbackMethod = _modifyResponse;

			new PAjax.Request(url, method, pars, asynchronouse, _modifyResponse);
		}
	}
	
	function _modifyResponse(response){
		//location.reload();
		try {
			var resultObj = eval('('+response.responseText+')');
			if(resultObj.result == "SUCCESS"){
				var schedule = resultObj.schedule;
				var std_dateAry = schedule.std_date.split("-");
				var params = {};
				params ={
						sch_mgt_no : schedule.sch_mgt_no,
						sch_seq : schedule.sch_seq,
						sch_subject : schedule.sch_subject,
						sch_content : schedule.sch_content,
						std_date : schedule.std_date,
						
						//서버쪽에서 달계산을 하기때문에 리얼먼스에서 -1을 한값을 보낸다
						std_year  : std_dateAry[0],
						std_month : eval(std_dateAry[1]-1)
				};
				
				location.href = "/common/schedule/getSchduleList.common?" + $H(params).toQueryString();		
			}
		} catch ( e){
			alert(e.message);
		}
	}
	
	//스케줄 삭제
	function _deleteSchedule(){
		//필수상목 체크 아이템
		var obj = ['sch_mgt_no' , 'sch_seq' , 'std_date'];
		
		//파라미터 
		var pars = {};
		pars = {
				sch_mgt_no : $F('sch_mgt_no'),
				sch_seq : $F('sch_seq'),
				std_date : $F('std_date')
		};
		
		//필수항목체크
		if(validationCheck(obj)){
			if(confirm("정말 삭제하시겠습니까?")){
				//Ajax call
				var url = "/common/schedule/deleteSchedule.common?";
				var method = 'POST';
				var asynchronouse = true;
				var callbackMethod = _deleteResponse;
	
				new PAjax.Request(url, method, pars, asynchronouse, _deleteResponse);
			}
		}
	}
	function _deleteResponse(){
		location.reload();
	}
	
	//값 초기화
	function _cancleSchedule(){
		
		$('sch_mgt_no').value="" ; 
		$('sch_seq').value = "";
		$('sch_subject').value = "";
		$('sch_content').value = "";
		$('std_date').value = "";
		//$('_min_degreesPoint').value = $('_min_degreesPoint')[0].value;
		$('min_temperature').value = "";
		//$('_max_degreesPoint').value = $('_max_degreesPoint')[0].value;
		$('max_temperature').value = "";
		//$('today_weather_code').value = $('today_weather_code')[0].value;
	}
	
	var Schedule = function(){};
	Schedule.MouseOver = function(obj){
		obj.style.backgroundColor = "#daf1f5";
	};
	Schedule.MouseOut = function(obj){
		obj.style.backgroundColor = "";
	};
	/*
		맑음
		맑은후흐림
		맑은후비
		맑은후눈
		흐림
		흐린후맑음
		흐린후비
		흐린후눈
		비
		비온후흐림
		비온후갬
		비온후눈
		눈
		눈온후흐림
		눈온후갬
		눈온후비
		진눈깨비
		안개낀후맑음
	*/
</script>
<body>

	<!-- 1. Header Contents-->
<div>
	<%@ include file="/mainHeader.jsp" %>
</div>
	
<!-- left Contents -->
<div style="float: left;width: 230px">
	
</div>	
<div class="mainContents">	
	<div class="insert_table">
		<table width="800px" border="0" cellspacing="0" cellpadding="0"> 
			<tr>
				<th>날짜</th>
				<td>
					<input class="input_imgType" type="text" name="std_date" id="std_date" readonly="readonly" onClick="new Calendar(this);" value="${schedule.std_date}" />
					<input type="hidden" name="sch_mgt_no" id="sch_mgt_no" readonly="readonly" value="${schedule.sch_mgt_no }"/>
					<input type="hidden" name="sch_seq" id="sch_seq" readonly="readonly" value="${schedule.sch_seq }"/>
				</td>
				<th>날씨</th>
				<td>
					<code:codelist codeGroup="00022" isSelectBox="true" attribute="name='today_weather_code' id='today_weather_code'"  selectedInfo="${schedule.today_weather_code }"></code:codelist>
				</td>
			</tr>
			<tr>
				<!-- 영상 : degree_above_zero --><!-- 영하 : degree_below_zero -->
				<th>최저온도</th>
				<td>
					<code:codelist codeGroup="00021" isSelectBox="true" attribute="name='_min_degreesPoint' id='_min_degreesPoint'"  selectedInfo="${schedule.min_degreespoint_code}"></code:codelist>
					<input class="input_shortType" type="text" name="min_temperature" id="min_temperature" style="ime-mode:Disabled;" onKeyPress="isNumber();" value="${schedule.min_temperature}"/>
				</td>
				<th>최고온도</th>
				<td>
					<code:codelist codeGroup="00021" isSelectBox="true" attribute="name='_max_degreesPoint' id='_max_degreesPoint'"  selectedInfo="${schedule.max_degreespoint_code}"></code:codelist>
					<input class="input_shortType" type="text" name="max_temperature" id="max_temperature" style="ime-mode:Disabled;" onKeyPress="isNumber();" value="${schedule.max_temperature}" />	
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input class="input_longType" type="text" id="sch_subject" name="sch_subject"  value="${schedule.sch_subject }" />
				</td>
				
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="5" cols="" id="sch_content" style="width: 100%">${schedule.sch_content}</textarea>
				</td>
			</tr>
		</table>
		<div style="text-align: right;width: 800px" >
			<button type="button" onclick="_cancleSchedule()">새로추가</button>
			<button type="button" onclick="_saveSchedule()" id="_saveButton">저장</button>	
			<button type="button" onclick="_deleteSchedule()" id="_deleteButton">삭제</button>
			<!-- <button type="button" onclick="_cancleSchedule()">취소</button> -->
		</div>
	</div>
	<br/>
	<div class="calendar_view">
		<table width="800px" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><%--표시하고자 하는 날짜의 년, 월 정보를 전달--%> 
					<a href="/common/schedule/getSchduleList.common?std_year=<%=year - 1%>&std_month=<%=Utils.numberType(month)%>">◀</a> 
					<strong><%=year%>년</strong> 
					<a href="/common/schedule/getSchduleList.common?std_year=<%=year + 1%>&std_month=<%=Utils.numberType(month)%>">▶</a>
				</td>
				<td>
					<div align="center">
						<a href="/common/schedule/getSchduleList.common?std_year=<%=year%>&std_month=<%=Utils.numberType(month-1)%>">◀</a> 
							<strong><%=month + 1%>월</strong>
						<a href="/common/schedule/getSchduleList.common?std_year=<%=year%>&std_month=<%=Utils.numberType(month + 1)%>">▶</a>
					</div>
				</td>
				<td>
					<div align="right">
						오늘은:<STRONG><%=year + "-" + (month + 1) + "-" + date%></strong>입니다.
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<div class="weekDay">
			<table width="800px" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th>
							<div align="center"><font color="red">일</font></div>
						</th>
						<th>
							<div align="center">월</div>
						</th>
						<th>
							<div align="center">화</div>
						</th>
						<th>
							<div align="center">수</div>
						</th>
						<th>
							<div align="center">목</div>
						</th>
						<th>
							<div align="center">금</div>
						</th>
						<th>
							<div align="center">토</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<%
					
					    Map<String,List<Schedule>> scMap = (Map<String,List<Schedule>>)request.getAttribute("scMap");
						
						for (int index = 1; index < start; index++) {
							out.print("<TD>&nbsp;</TD>");
							newLine++;
						}
				
						for (int index = 1; index <= endDay; index++) {
							
							String color = "";
							
							if(newLine  == 0){
								color = "RED";
							}else if(newLine == 6 ){
								color  = "BLUE";
							}else{
								color  = "BLACK";
							}
								
							
							String dateStr  = "";
							dateStr = String.valueOf(year)+"-"+Utils.numberType(month+1)+"-"+Utils.numberType(index);
							
							out.print("<TD width='100px' height='100px' onmouseover='new Schedule.MouseOver(this)' onmouseout='new Schedule.MouseOut(this)'>");
							out.print("<div class='ellipsis' style='width:100px;height:100px'>");
							out.print("<span style='width:100px;text-align: right;'><font color="+ color + ">" + index + "</font></span><br/>");
							if( scMap.get(dateStr) != null){
								List<Schedule> scheduleList = scMap.get(dateStr);
								for (Schedule schedule : scheduleList) {
									out.print("<div class='ellipsis' style='width:100px;' title='"+ schedule.getSch_subject() +"'");
									out.print("<nobr style='width:100px;text-align: left;'><a href='javascript:_detailSchedule(\""+schedule.getSch_mgt_no()+"\", \""+schedule.getSch_seq()+"\")'>" +schedule.getSch_subject() + "</a></nobr><p/>");
									out.print("</div>");
								}
							}
							out.print("</div>");
							out.print("</TD>");
							
							
							newLine++;
				
							if (newLine == 7) {
								out.print("</TR>");
								if (index <= endDay) {
									out.print("<TR>");
								}
								newLine = 0;
							}
						}
				
						while (newLine > 0 && newLine < 7) {
							out.print("<TD>&nbsp;</TD>");
							newLine++;
						}
					%>
					<tr>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
