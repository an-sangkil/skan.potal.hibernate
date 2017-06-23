<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>
	<tiles:insertAttribute name="title" />
</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/navbar/navbar.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="${pageContext.request.contextPath}/assets/jquery/ui/jquery-ui.css" rel="stylesheet"></link>
<link href="${pageContext.request.contextPath}/assets/jquery/ui/jquery-ui.structure.css" rel="stylesheet"></link>
<link href="${pageContext.request.contextPath}/assets/jquery/ui/jquery-ui.theme.css" rel="stylesheet"></link>

<script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.11.3.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/assets/jquery/ui/jquery-ui.js"></script>

<!-- Favicon -->
<link rel="shortcut icon" href="img/favicon/favicon.png">
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-default">
			<tiles:insertAttribute name="header" />
		</nav>

		<div>
			<tiles:insertAttribute name="body" />
		</div>

		<!-- Footer starts -->
		<tiles:insertAttribute name="footer" />
		<!-- Footer ends -->
	</div>


</body>
</html>