<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>

<div class="sidebar-dropdown">
	<a href="#">Navigation</a>
</div>

<!--- Sidebar navigation -->
<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
<ul id="nav">
	<!-- Main menu with font awesome icon -->
	<li>
		<a href="#"><i class="icon-home"></i>Dashboard</a> 
	</li>
	<li class="has_sub">
		<a href="#">
			<i class="icon-list-alt"></i>
			일정관리 
			<span class="pull-right">
				<i class="icon-chevron-right"></i>
			</span>
		</a>
		<ul>
			<li><a href="">Widgets #1</a></li>
			<li><a href="">Widgets #2</a></li>
			<li><a href="">Widgets #3</a></li>
		</ul>
	</li>
	<li>
		<a href="#"><i class="icon-calendar"></i>한우개체관리</a>
	</li>
	<li>
		<a href="#"><i class="icon-calendar"></i>주소록</a>
	</li>
	<li>
		<a href="#"><i class="icon-calendar"></i>가계부</a>
	</li>
	<li>
		<a href="#"><i class="icon-calendar"></i>코드관리</a>
	</li>
</ul>