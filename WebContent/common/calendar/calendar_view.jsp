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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>달력</title>
<style type="text/css">
.today {color:navy; font-weight:bold}
.days {font-weight:bold; font-size: 9pt;font-family: "굴림,돋음,arial,helvetica"}
</style>
<link rel="stylesheet" type="text/css" href="/css/calendar.css"/>
<link rel="stylesheet" type="text/css" href="E:\site\mySource\SpringFreamWork\dongbuFarm\WebContent\css\calendar.css"/>

<script language="javascript">

// 배열을 초기화한다.
var months = new Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var days = new Array("일", "월", "화", "수", "목", "금", "토");


//오늘날짜 객체 생성
today = new getToday();


//오늘 날짜 생성 오브젝트
function getToday(){
	this.now = new Date();
	this.year = this.now.getFullYear();
	this.month = this.now.getMonth();
	this.day = this.now.getDate();
}

//달에 포함된 일수 계산
function getDays(month, year){
	// 윤년 계산
	if (1 == month){
		return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	}else{
		return daysInMonth[month];
	}
}
var Calendar_view = function(obj){
	if(obj === undefined){
		//selection box creation
		new Make_selection_week();
	}
	
	//요일 생성
	document.getElementById("week_div").innerHTML = this.make_dayofweek();	
	
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

	
	//[달] 의 계산된 일수
	var intDaysInMonth = getDays(newCal.getMonth(), newCal.getYear());
	
	
	//HTML 생성자
	this.cal_src = "";
	
	
	//calendar 객체 생성
	_calendar = this;
	
	
	for(var i = 0 ;i< startDay+intDaysInMonth ; i++ ){
		//7일씩 잘라내기
		if(!(i%7)){
			this.cal_src += "<ul></ul>";
		}
		if(i>=startDay){
			if((i%7) == 0){//
					this.cal_src += "<li style='color:red' onclick='getDate()' onmouseover='_calendar.mouse_over(this)' onmouseout='_calendar.mouse_out(this)' ><span style='cursor:pointer;'>"+((i-startDay)+1)+"</span></li>\n";
        	}else if((i%7) == 6){
        		this.cal_src += "<li style='color:blue' onclick='getDate()' onmouseover='_calendar.mouse_over(this)' onmouseout='_calendar.mouse_out(this)'><span style='cursor:pointer;'>"+((i-startDay)+1)+"</span></li>\n";
        	}else{
        		this.cal_src +="<li onclick='getDate()' onmouseover='_calendar.mouse_over(this)' onmouseout='_calendar.mouse_out(this)'><span style='cursor:pointer;'>"+((i-startDay)+1)+"</span></li>\n";
        	}
		}else{
			this.cal_src +="<li></li>";
		}
	}
	
	var tag = this.cal_src;
	document.getElementById("dayList").innerHTML = tag;
	
	

	//내부 method() 호출_Example
	//this.make_week();
};

Calendar_view.prototype = {
		
	make_dayofweek : function(){
		
		this.dayofweek_src = "";
		this.dayofweek_src +="<li style='color:#ab4444;'>" + days[0] + "</li>";//일
		
		for (var intLoop = 1; intLoop < days.length-1; intLoop++){
			
			// 요일을 출력한다.
			this.dayofweek_src +="<li><font size=2>" + days[intLoop] + "</font></li>";
			
		}
		
		this.dayofweek_src +="<li>" + days[days.length-1] + "</li>";//토
		
		return this.dayofweek_src; 
	},
	
	mouse_over : function(st){
		st.style.backgroundColor = "#daf1f5";
	},
	
	mouse_out : function(st){
		st.style.backgroundColor = "";
	},
	
	make_week : function(){
		alert("test1");
	},
	
	add_week : function(){
		alert("test2");
	},
	
	make_calendar : function(){
		alert("test3");	
	}
};



//날짜 클릭 
function getDate(){
	var date_str, d;
	
	date_str = document.getElementById("year1").value + "-"
	
	date_str = date_str + document.all.month.options[document.all.month.selectedIndex].text + "-" ;
	

	if ("SPAN" == (event.srcElement.tagName).toUpperCase()
			|| "LI" == (event.srcElement.tagName).toUpperCase()) {
		//선택(클릭)된 날짜
		d = event.srcElement.innerText ;
		if ("" != d){
			
			if (d.length == 1){
				//한자릿수 인경우 앞에  "0"을 붙여준다.
				d = "0" + d;
			}
			
			//화면에 출력될 값
			date_str = date_str + d;
			alert(date_str);
		}
	}
}


/*
	년도 변경
	@param flag : 1 = 이전년도  / 2 = 다음년도 / 3 = 이전달 / 4= 다음달 .
*/
function changeCalendar(flag){
	var newYear, newMonth;
	newYear = document.all.year1;
	newMonth = document.all.month;
	

	switch (flag) {
	case 1:	newYear.value--;	break;
	
	case 2:	newYear.value++;	break;
	
	case 3:
		
		if (newMonth.selectedIndex == 0){
			newYear.value--;
			newMonth.selectedIndex = 11;
		}else {
			newMonth.selectedIndex--;
		}
		
		break;
	
	case 4:	
		
		if (newMonth.selectedIndex == 11){
			//년도 변경 및 달을 1월달로
			newYear.value++;
			newMonth.selectedIndex = 0;
		}else {
			newMonth.selectedIndex++;	
		}
		
		break;
	default: break;
	
	}
	
	new Calendar_view(true);
}

//달을 화면에 출력한다. / 현재 달을 선택한다.
var Make_selection_week = function(){
	
	this.selection_src = "";
	this.selection_src += "<select id='month' onchange='new Calendar_view(true)' style='height:12;width:40'>";
	for (var intLoop = 0; intLoop < months.length; intLoop++){
		//document.write("<option " + (today.month == intLoop ? "Selected" : "") + ">" + months[intLoop]);
		this.selection_src += "<option " + (today.month == intLoop ? "Selected" : "") + ">" + months[intLoop];
	}
	this.selection_src += "</select>";
	
	document.getElementById("month_span").innerHTML = this.selection_src; 
};

window.onload = function(){
	document.all.year1.value = today.year;
	document.all.spanYear1.innerHTML =  today.year;
	new Calendar_view();
}
</script>
</head>

<body>
<div class="calendar" >
	<div class="header" style="width: 190px">
		<!-- [년]도 -->
		<div class="year">
			<a href="#menu01"><img src="../image/common/arrow_left.jpg" width="12px" height="13px" alt="이전해로가기" onclick="changeCalendar(1)"/></a>
			<span class="calendarText_11B_brown">
				<!-- // 연도를 문서에 화면에 출력한다. / 현재 년도를 선택한다. -->
				<span id="spanYear1"></span>
				<input type="hidden" name="year1" align="top" readonly="readonly" />
				<script type="text/javascript">
				
				</script>
				
			</span>
			<a href="#menu01"><img src="../image/common/arrow_right.jpg" width="12px" height="13px" alt="다음해로가기" onClick="changeCalendar(2)"/></a>
		</div>

		
		<!-- [달]수 -->
		<div class="month">
			<a href="#menu01"><img src="../image/common/arrow_left.jpg" width="12px" height="13px" alt="이전달로가기" onclick="changeCalendar(3)"/></a>
			<span class="calendarText_12B_brown" id="month_span">
				
			</span>
			<a href="#menu01"><img src="../image/common/arrow_right.jpg" width="12px" height="13px" alt="다음달로가기" onclick="changeCalendar(4)"/></a>
		</div>
	</div>	
	
	
	<div class="week" style="width: 190px" id="week_div">
		<!-- 각각의 요일을 위한 칼럼을 만든다. [일 월 화 수 목 금 토]	-->
	</div>
	<div id="dayList" class="day" style="width: 190px">
	</div>
</div>
</BODY>
</HTML>

