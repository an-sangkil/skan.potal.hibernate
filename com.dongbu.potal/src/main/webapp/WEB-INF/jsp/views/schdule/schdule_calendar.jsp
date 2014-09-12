<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<script src="${pageContext.request.contextPath}/assets/metro/js/calendar/lib/jquery-ui.custom.min.js"></script> <!-- jQuery UI -->
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/lib/moment.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/fullcalendar.js"></script>
<script	src="${pageContext.request.contextPath}/assets/metro/js/calendar/lang-all.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/js/calendar/fullcalendar.css">


<script>
$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			dayClick: function() {
				console.log('a day has been clicked!');
		        // change the day's background color just for fun
		        //$(this).css('background-color', 'red');
			},
			eventClick: function(calEvent, jsEvent, view) {
				
				console.log('calEvent = ' , calEvent);
		        //alert('Event: ' + calEvent.title);
		        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
		        //alert('View: ' + view.name);
		        // change the border color just for fun
		        $(this).css('border-color', 'red');

		    },
		    eventMouseover : function () {
		    	console.log('asdasdasd');
		    },
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			//defaultDate: '2014-06-12',
			lang : 'ko',
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				var title = prompt('이벤트 제목:' , '오늘날짜~~');
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
				$('#calendar').fullCalendar('unselect');
			},
			editable: true,
			events: [
				{
					title: 'All Day Event',
					start: '2014-06-01'
				},
				{
					title: 'Long Event',
					start: '2014-06-07',
					end: '2014-06-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2014-06-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2014-06-16T16:00:00'
				},
				{
					title: 'Meeting',
					start: '2014-06-12T10:30:00',
					end: '2014-06-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2014-06-12T12:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2014-06-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2014-06-28'
				}
			]
		});
		
		
		
	});
	
	

</script>

<!-- Page heading -->
<div class="page-head">
	<h2 class="pull-left">
		<i class="icon-file-alt"></i> Calendar
	</h2>

	<!-- Breadcrumb -->
	<div class="bread-crumb pull-right">
		<a href="index.html"><i class="icon-home"></i> Home</a>
		<!-- Divider -->
		<span class="divider">/</span> <a href="#" class="bread-current">Dashboard</a>
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
						<div class="pull-left">Calendar</div>
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

