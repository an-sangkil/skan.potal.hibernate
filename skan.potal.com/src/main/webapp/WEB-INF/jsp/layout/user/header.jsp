<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">관리 시스템</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}/main_page">Home</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">가계부</a></li>
			<li><a href="${pageContext.request.contextPath}/address/address_list">주소록</a></li>
			<li><a href="${pageContext.request.contextPath}/schdule/schdule_list">일정관리</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">시스템관리 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">코드관리</a></li>
					<li><a href="#">권한관리 </a></li>
					<li><a href="#">Something else here</a></li>
					<li role="separator" class="divider"></li>
					<li class="dropdown-header">Nav header</li>
					<li><a href="#">Separated link</a></li>
					<li><a href="#">One more separated link</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a href="#">내정보<span class="sr-only">(current)</span></a></li>
			<li><a href="#">로그아웃</a></li>
		</ul>
	</div>
	<!--/.nav-collapse -->
</div>
<!--/.container-fluid -->