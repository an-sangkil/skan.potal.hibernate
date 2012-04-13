<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!--
	* fileName   : mainHeader.jsp
	* createDate : 2010. 12. 1. 오후 4:53:59
	* CreateUser : ahn
	* Document   : todo
	*
-->
<script type="text/javascript">
	function moveTestBoard(targetBoard){
		var params = {};
		params["bbsid"] = targetBoard;
		location.href = "/common/board/getBoardList.common?"+$H(params).toQueryString();
	}
	
	function calendar_TEST(){
		location.href = "/common/schedule/getSchduleList.common";
	}
	
	function codeManagementTest(){
		location.href = "/system/code/codeManagement.jsp";
	}
	
	function addressGroupManagementTest(){
		location.href = "#";
	}
	
	function addressManagementTest(){
		location.href = "/contents/addressMag/addressManagement.jsp";
	}
	
	
</script>
<div class="header">
	<div class="main_menu" onclick="moveTestBoard('testBoard')" style="cursor: pointer;">
		Test Board
	</div>
	<div class="main_menu" onclick="calendar_TEST()" style="cursor: pointer;">
		Calendar Test   
	</div>
	<div class="main_menu" onclick="codeManagementTest()" style="cursor: pointer;">
		Code Management Test   
	</div>
	<div class="main_menu" onclick="addressGroupManagementTest()" style="cursor: pointer;">
		..
	</div>
	<div class="main_menu" onclick="addressManagementTest()" style="cursor: pointer;">
		address Management Test   
	</div>
</div>