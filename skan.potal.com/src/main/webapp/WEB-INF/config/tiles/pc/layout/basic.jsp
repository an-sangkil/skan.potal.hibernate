<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>

<!DOCTYPE >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv="Cache-Control" content="no-cache"/> 
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/> 

<title><tiles:getAsString name="title" /></title>
<link rel="shortcut icon" href="${CONTEXT_PATH}/assest/custom/images/knk.png.ico" />

<!--[if lt IE 9]>
		<script src="bower_components/html5shiv/dist/html5shiv.js"></script>
	<![endif]-->
<link rel="stylesheet" href="${CONTEXT_PATH}/assest/pc/js/calendar/calendar.css"  />
<!--팝업-->
<link rel="stylesheet" href="${CONTEXT_PATH}/assest/pc/css/rms_style.css" />
<!--구글 폰트-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato" >
<!--cdn font awesome //font icon-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

<script type="text/javascript" src="${CONTEXT_PATH}/assest/pc/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${CONTEXT_PATH}/assest/pc/js/calendar/calendar.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<script type="text/javascript" src="${CONTEXT_PATH}/assest/pc/js/jquery.spinner.js"></script>
</head>
<body>
	<dl id="skipNavi">
		<dt class="skip">바로가기 목록</dt>
		<dd>
			<a href="#header_wrap" class="skip">상단 바로가기</a>
		</dd>
		<dd>
			<a href="#nav_wrap" class="skip">네비게이션 바로가기</a>
		</dd>
		<dd>
			<a href="#content_wrap" class="skip">content 바로가기</a>
		</dd>
	</dl>
	<header class="header_wrap" id="header_wrap">
		<tiles:insertAttribute name="header" />
	</header>

	<div class="content_wrap" id="content_wrap">
		<aside class="aside_wrap">
			<tiles:insertAttribute name="sidebar" />
		</aside>
		<section class="section_wrap">
			<tiles:insertAttribute name="body" />
		</section>
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>

</body>
<script type="text/javascript" src="${CONTEXT_PATH}/assest/pc/js/jquery.reveal.js"></script>
</html>
