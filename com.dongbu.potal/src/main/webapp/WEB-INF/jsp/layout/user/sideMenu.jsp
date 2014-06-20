<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<div class="sidebar-dropdown">
	<a href="#">Navigation</a>
</div>

<!--- Sidebar navigation -->
<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
<ul id="nav">
	<!-- Main menu with font awesome icon -->
	<li><a href="index.html" class="open"><i class="icon-home"></i>
			Dashboard</a> <!-- Sub menu markup 
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
              <li><a href="#">Submenu #3</a></li>
            </ul>--></li>
	<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
			Widgets <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
		<ul>
			<li><a href="widgets1.html">Widgets #1</a></li>
			<li><a href="widgets2.html">Widgets #2</a></li>
			<li><a href="widgets3.html">Widgets #3</a></li>
		</ul></li>
	<li><a href="calendar.html"><i class="icon-calendar"></i>
			Calendar</a></li>
</ul>