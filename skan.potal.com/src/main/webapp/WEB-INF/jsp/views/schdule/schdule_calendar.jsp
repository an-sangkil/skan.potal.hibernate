<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<script src="${pageContext.request.contextPath}/assets/metro/js/calendar/lib/jquery-ui.custom.min.js"></script> <!-- jQuery UI -->
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/lib/moment.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/fullcalendar.js"></script>
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/lang-all.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/js/calendar/fullcalendar.css">
<style type="text/css">
.holiday
{
    border:1px solid #69196c;
    background: #764ead url(holiday.png) 50% top repeat-x; 
    color: white;   
}
.fc-sat .fc-day-number {
  background-color: #FFFFFF;
  color: blue;
}
.fc-sun .fc-day-number {
  background-color: white;
  color: red;
}
</style>

<script>
$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			dayClick: function() {
				console.log('a day has been clicked!');
		        // change the day's background color just for fun
		        $(this).css('background-color', 'blue');
			},
			eventClick: function(calEvent, jsEvent, view) {
				document.schedule_.schMgtNo.value = calEvent.id;
				document.schedule_.schSeq.value = calEvent.schSeq;
				document.schedule_.action = '${pageContext.request.contextPath}/schdule/schdule_form';
				document.schedule_.submit();

				console.log('calEvent = ' , calEvent);
		        //alert('Event: ' + calEvent.title);
		        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
		        //alert('View: ' + view.name);
		        // change the border color just for fun
		        $(this).css('border-color', 'blue');
		    },
		    eventMouseover : function () {
		    	console.log('mouse Over');
		    },
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: '${toDay}',
			lang : 'ko',
			weekends : true,
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				
				document.schedule_.startDate.value = start;
				document.schedule_.endDate.value = end;
				document.schedule_.action = '${pageContext.request.contextPath}/schdule/schdule_form';
				document.schedule_.submit();
				/*var title = prompt('이벤트 제목:' , '오늘날짜~~');
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					
					console.log(start);
					console.log(start._d.getFullYear());					
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');*/
			},
			editable: true,
			dayRender: function (date, cell) {
				
				// 또다른 공휴일 설정
				
				var holidays = $.fullCalendar.moment('2016-01-01');
			    if ($.inArray(date, holidays) >= 0) {
			    	console.log(date);
			        // if you aren't using ui theme, just remove this line
			        $(cell).removeClass('ui-widget-content');                            
			        $(cell).addClass('holiday');

			    }
			    //if (moment().diff(date,'days') > 0){
		        //    cell.css("background-color","silver");
		        //}
			},
			events: [
						<c:forEach items="${cmtbSchedules}" var="item" varStatus="status">
							{
								id     : '${item.cmtbSchedulePK.schMgtNo}',
								schSeq : '${item.cmtbSchedulePK.schSeq}',
								title  : '${item.schSubject}',
								start  : '${item.stdDate}',
								end    : '${item.endDate}'
							},
						</c:forEach>
			]
		});
		//$(".fc-sat").css('backgroundColor','#c4e1ff');//이거 토요일 의TD
		//$(".fc-sun").css('backgroundColor','#c4e1ff');//이것은 일요일에 있을 TD
	});
</script>

<form name="schedule_" method="post">
	<input id="schMgtNo" name="schMgtNo" 	type="hidden" />
	<input id="schSeq" 	 name="schSeq" 		type="hidden" />
	<input id="startDate" 	name="startDate" 	type="hidden" />
	<input id="endDate" 	name="endDate" 		type="hidden" />
</form>


<!-- Page heading -->
<div class="page-head">
	<h2 class="pull-left">
		<i class="icon-file-alt"></i> 일정관리
	</h2>
	<!-- Breadcrumb -->
	<div class="bread-crumb pull-right">
<!-- 		<a href="#"><i class="icon-home"></i> Home</a> -->
		<!-- Divider -->
<!-- 		<span class="divider">/</span> <a href="#" class="bread-current">Dashboard</a> -->
	</div>
	<div class="clearfix"></div>
</div>
<!-- Page heading ends -->




<!-- Matter -->

<div class="matter">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- Widget -->
				<div class="widget">
					<!-- Widget title -->
					<div class="widget-head">
<!-- 						<div class="pull-left">스케쥴 정보</div> -->
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="icon-remove"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content">
						<!-- Widget content -->
						<div class="padd">
							<!-- Below line produces calendar. I am using FullCalendar plugin. -->
							<div id="calendar"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Matter ends -->

