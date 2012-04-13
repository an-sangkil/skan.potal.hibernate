<!--
/**
 * File name: CM_Calender_Z01.jsp
 * Function	:
 * Comment	: CALENDER
 * History	: 06/18/2002, Jang, Eun mi v1.0 first
 * @version	: 2.0
 * @author	: Copyright (c) 현대정보기술. All Rights Reserved.
 * @modifier: Copyright (c) 맨인소프트. All Rights Reserved.
 */
-->
<%@	page contentType="text/html;charset=UTF-8"	session="false"	%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%
	/**
	 * 현재 날짜 또는 입력 날짜를 참조하는 Calendar 객체를 만든다.
	 */
	GregorianCalendar calendar = new GregorianCalendar();

	String date = request.getParameter("date");
	if ( date != null ) {
		Date reqDate = java.text.DateFormat.getDateInstance().parse(date);
		calendar.setTime( reqDate );
	}

	String sYear = request.getParameter("year");
	String sMonth = request.getParameter("month");
	String sDay = request.getParameter("day");
	String events = request.getParameter("events")==null?"":request.getParameter("events");

	int year = calendar.get(Calendar.YEAR);
	if ( sYear != null )
		year = Integer.parseInt(sYear);

	int month = calendar.get(Calendar.MONTH) + 1;
	if ( sMonth != null )
		month = Integer.parseInt(sMonth);

	int day = calendar.get(Calendar.DAY_OF_MONTH);
	if ( sDay != null )
		day = Integer.parseInt(sDay);

//	calendar.set( year, month-1, day );
//	String c = year+"-"+month+"-"+day;
//	System.out.println(c);
%>
<%  %>
<HTML>
<head> <SCRIPT FOR="cbButton" EVENT="onmousedown()"> this.className = 'iconDown';</SCRIPT> <SCRIPT FOR="cbButton" EVENT="onmouseover()"> this.className = 'iconOver'; </SCRIPT> <SCRIPT FOR="cbButton" EVENT="onmouseout()"> this.className = 'iconOut'; </SCRIPT>
<TITLE>달력</TITLE>
<STYLE TYPE="text/css">
.today {color:navy; font-weight:bold}
.days {font-weight:bold; font-size: 9pt;font-family: "굴림,돋음,arial,helvetica"}
</STYLE>
<link rel="stylesheet" type="text/css" href="/css/calendar.css"/>

<SCRIPT LANGUAGE="JavaScript">

// 배열을 초기화한다.
var months = new Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var days = new Array("일", "월", "화", "수", "목", "금", "토");

function getDays(month, year){
	// 윤년 계산
	if (1 == month){
		return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	}else{
		return daysInMonth[month];
	}
}

// 오늘 확인
function getToday(){
	this.now = new Date(<%=year%>,<%=month-1%>, <%=day%> );
	this.year = this.now.getYear();
	this.month = this.now.getMonth();
	this.day = this.now.getDate();
}

// 달력을 오늘 날짜로 시작한다.
today = new getToday();

/*--------------------------------------------------------------------------------------------------
 newCalendar()
-------------------------------------------------------------------------------------------------*/
function newCalendar(){
	today = new getToday();
	var parseYear = parseInt(document.all.year1.value);
	document.all.spanYear1.innerHTML =  parseYear;
	var newCal = new Date(parseYear, document.all.month.selectedIndex, 1);
	var day = -1;
	var startDay = newCal.getDay();
	var list_top = 0;

	if ((today.year == newCal.getYear()) && (today.month == newCal.getMonth())){
		day = today.day;
	}

	// 테이블의 datList라는 이름의 tBody 요소를 지정한다.
	var tableCal = document.getElementById("dayList");
	var intDaysInMonth = getDays(newCal.getMonth(), newCal.getYear());
	for (var intWeek = 0; intWeek < tableCal.childNodes.length; intWeek++)
		for (var intDay = 0; intDay < tableCal.childNodes[intWeek].childNodes.length; intDay++)
		{
			var cell = tableCal.childNodes[intWeek].childNodes[intDay];
			// 계산 날짜들을 시작한다.
			if ((intDay == startDay) && (0 == list_top)){
				list_top = 1;
			}
			// 오늘을 강조하여 표시한다.
			cell.className = (day == list_top) ? "today" : "";
			// 날짜의 숫자를 셀에 출력시킨다.
			if ((list_top > 0) && (list_top <= intDaysInMonth)){
				cell.innerText = list_top++;
			}else{
				cell.innerText = "";
			}
	}
}
/*--------------------------------------------------------------------------------------------------
changeCalendar(flag)
-------------------------------------------------------------------------------------------------*/
function changeCalendar(flag)
{
	var newYear, newMonth
	newYear = document.all.year1
	newMonth = document.all.month
	if (flag == 1){
		newYear.value--;
	}else if(flag == 2) {
		newYear.value++;
	}else if(flag == 3) {
		if (newMonth.selectedIndex == 0){
			newYear.value--;
			newMonth.selectedIndex = 11;
		}else {
			newMonth.selectedIndex--;
		}
	}else {
		if (newMonth.selectedIndex == 11){
				newYear.value++;
				newMonth.selectedIndex = 0;
		}else {
			newMonth.selectedIndex++;
		}
	}
	newCalendar();
}

//날짜 클릭 
function getDate(){
	var date_str, d;
	date_str = document.all.year1.value + "-" ;
	date_str = date_str + document.all.month.options[document.all.month.selectedIndex].text + "-" ;
	if ("LI" == event.srcElement.tagName) {
		// 날짜가 확실한지 검사한다.
		d = event.srcElement.innerText ;
		if ("" != d){
			if (d.length == 1)
				d = "0" + d;
			date_str = date_str + d;
			opener.<%=request.getParameter("callback")%>('<%=request.getParameter("objIdorName")%>' , date_str , '<%= request.getParameter("calendarIdx")%>');
			self.close();
		}
	}
}

</SCRIPT>
</HEAD>

<body onload="newCalendar()">
<div class="calendar" >
	<div class="header" style="width: 190px">
		<div class="year">
			<a href="#menu01"><img src="../image/common/arrow_left.jpg" width="12px" height="13px" alt="이전해로가기" onclick="changeCalendar(1)"/></a>
			<span class="calendarText_11B_brown">
				<span id="spanYear1"></span>
				<input type="hidden" name="year1" align="top" readonly="readonly" />
				<script language="javascript">
					// 연도를 문서에 화면에 출력한다. / 현재 년도를 선택한다.
					document.all.year1.value = today.year;
					document.all.spanYear1.innerHTML =  today.year;
				</SCRIPT>
			</span>
			<a href="#menu01"><img src="../image/common/arrow_right.jpg" width="12px" height="13px" alt="다음해로가기" onClick="changeCalendar(2)"/></a>
		</div>
		<div class="month">
			<a href="#menu01"><img src="../image/common/arrow_left.jpg" width="12px" height="13px" alt="이전달로가기" onclick="changeCalendar(3)"/></a>
			<span class="calendarText_12B_brown">
				<select ID="month" onchange="newCalendar()" style="height:12;width:40" >
				<script type="text/javascript">
					// 달을 화면에 출력한다. / 현재 달을 선택한다.
					for (var intLoop = 0; intLoop < months.length; intLoop++){
						document.write("<OPTION " + (today.month == intLoop ? "Selected" : "") + ">" + months[intLoop]);
					}
				</script>
				</select>
			</span>
			<a href="#menu01"><img src="../image/common/arrow_right.jpg" width="12px" height="13px" alt="다음달로가기" onclick="changeCalendar(4)"/></a>
		</div>
	</div>
	
	
	<div class="week" style="width: 190px">
	<!-- 
		각각의 요일을 위한 칼럼을 만든다. 
		일 월 화 수 목 금 토 
	-->
		<script language="javascript">
			document.write("<li style='color:#ab4444;'>" + days[0] + "</li>");//일
			for (var intLoop = 1; intLoop < days.length-1; intLoop++){
				// 요일을 출력한다.
				document.write("<li><font size=2>" + days[intLoop] + "</font></li>");
			}
			document.write("<li >" + days[days.length-1] + "</li>");//토
		</script>
	</div>
	<div id="dayList" class="day" onclick="getDate()" style="width: 190px">
		<script language="javascript" type="text/javascript">
			function over(st){
				st.style.backgroundColor = "#daf1f5";
			}
			function out(st){
				st.style.backgroundColor = "";
			}
			
			for (var intWeeks = 0; intWeeks < 6; intWeeks++)
			{
				document.write("<ul>");
				for (var intDays = 0; intDays < days.length; intDays++) {
					if (intDays == 0) {
						document.write("<li style='color:red;cursor:hand' onMouseOver=over(this) onMouseOut=out(this)></li>");
					}
					else if (intDays == days.length-1) {
						document.write("<li style='color:blue;cursor:hand' onMouseOver=over(this) onMouseOut=out(this)></li>");
					}
					else {
						document.write("<li style=cursor:hand onMouseOver=over(this) onMouseOut=out(this)></li>");
					}
				}
				document.write("</ul>");
			}
			</SCRIPT>
	</div>
</div>
</BODY>
</HTML>

