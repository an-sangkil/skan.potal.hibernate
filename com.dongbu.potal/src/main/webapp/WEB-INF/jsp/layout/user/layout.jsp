<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<!-- Title and other stuffs -->
	<title>POTAL</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">
	
	<!-- Stylesheets -->
	<link href="${pageContext.request.contextPath}/assets/metro/style/bootstrap.css" rel="stylesheet">
	<!-- Font awesome icon -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/font-awesome.css"> 
	<!-- jQuery UI -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/jquery-ui.css"> 
	<!-- Calendar -->
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/fullcalendar.css"> --%>
	<!-- prettyPhoto -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/prettyPhoto.css">  
	<!-- Star rating -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/rateit.css">
	<!-- Date picker -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/bootstrap-datetimepicker.min.css">
	<!-- CLEditor -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/jquery.cleditor.css"> 
	<!-- Bootstrap toggle -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/metro/style/bootstrap-switch.css">
	<!-- Main stylesheet -->
	<link href="${pageContext.request.contextPath}/assets/metro/style/style.css" rel="stylesheet">
	<!-- Widgets stylesheet -->
	<link href="${pageContext.request.contextPath}/assets/metro/style/widgets.css" rel="stylesheet">   
	
	<!-- HTML5 Support for IE -->
	<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/assets/metro/js/html5shim.js"></script>
	<![endif]-->

  
  
  
  
  
  
	<!-- JS -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.js"></script> <!-- jQuery -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/bootstrap.js"></script> <!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->
	
	<!-- jQuery Flot -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/excanvas.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.flot.stack.js"></script>
	
	<!-- jQuery Notification - Noty -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.noty.js"></script> <!-- jQuery Notify -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/themes/default.js"></script> <!-- jQuery Notify -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/layouts/bottom.js"></script> <!-- jQuery Notify -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/layouts/topRight.js"></script> <!-- jQuery Notify -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/layouts/top.js"></script> <!-- jQuery Notify -->
	<!-- jQuery Notification ends -->
	
	<script src="${pageContext.request.contextPath}/assets/metro/js/sparklines.js"></script> <!-- Sparklines -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/filter.js"></script> <!-- Filter for support page -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/custom.js"></script> <!-- Custom codes -->
	<script src="${pageContext.request.contextPath}/assets/metro/js/charts.js"></script> <!-- Charts & Graphs -->
  
  
  
  
  

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">

</head>
<body>
	<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
		<div class="conjtainer">
	      <!-- Menu button for smallar screens -->
	      <div class="navbar-header">
			  <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
				<span>Menu</span>
			  </button>
			  <!-- Site name for smallar screens -->
			  <a href="index.html" class="navbar-brand hidden-lg">MacBeth</a>
			</div>
	      
	      
	
	      <!-- Navigation starts -->
	      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">         
	        <!-- Search form -->
	        <form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
			</form>
	        <!-- Links -->
	        <ul class="nav navbar-nav pull-right">
	          <li class="dropdown pull-right">            
	            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	              <i class="icon-user"></i> Admin <b class="caret"></b>              
	            </a>
	            
	            <!-- Dropdown menu -->
	            <ul class="dropdown-menu">
	              <li><a href="#"><i class="icon-user"></i> Profile</a></li>
	              <li><a href="#"><i class="icon-cogs"></i> Settings</a></li>
	              <li><a href="login.html"><i class="icon-off"></i> Logout</a></li>
	            </ul>
	          </li>
	          
	        </ul>
	      </nav>
	
	    </div>
	</div>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
 	
	<div class="content">
  		<!-- Sidebar -->
	    <div class="sidebar">
		 	<tiles:insertAttribute name="sideMenu" />
		</div>
		
		<!-- Main bar -->
  		<div class="mainbar">
		    <tiles:insertAttribute name="body" />
  		</div>
		
	</div>
	
	<!-- Mainbar ends -->
    <div class="clearfix"></div>
	
	<!-- Footer starts -->
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer> 	
	<!-- Footer ends -->
	
	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 	
	
</body>
</html>