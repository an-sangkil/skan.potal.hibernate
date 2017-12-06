<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>

<h1 class="skip">네비 메뉴</h1>
<a href="" class="open"><i class="fa fa-bars"></i><span class="skip">열기</span></a>

<div class="nav_list">
	<div class="home_on">
		<a class="close" href=""><i class="fa fa-times" aria-hidden="true"></i></a>
		<a href="${CONTEXT_PATH}/"><img src="${CONTEXT_PATH}/assest/mobile/img/logo.png" alt="홍주문화회관 로고" title="홍주문화회관" /></a>
		<sec:authorize access="isAnonymous()"> 
			<h4>예매를 위해 로그인을 해주세요.</h4>
			<a href="${CONTEXT_PATH}/login" id="login_btns">로그인</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			안녕하세요.
			<a href="${CONTEXT_PATH}/logout" id="login_btns">로그아웃</a>
		</sec:authorize>

	</div>
	<!--home_on-->

	<ul>
		<li><a href="${CONTEXT_PATH}/"> <i class="fa fa-home" aria-hidden="true"></i>HOME <i class="nav_next fa fa-angle-right" aria-hidden="true"></i>
		</a></li>
		<li><a href="${CONTEXT_PATH}/concert/concertList"> <i class="fa fa-ticket" aria-hidden="true"></i>공연 <i class="nav_next fa fa-angle-right" aria-hidden="true"></i>
		</a></li>
		<li><a href="${CONTEXT_PATH}/intro"> <i class="fa fa-share-alt" aria-hidden="true"></i>소개 <i class="nav_next fa fa-angle-right" aria-hidden="true"></i>
		</a></li>
		<li><a href="${CONTEXT_PATH}/info"> <i class="fa fa-list" aria-hidden="true"></i>이용안내 <i class="nav_next fa fa-angle-right" aria-hidden="true"></i>
		</a></li>
	</ul>

</div>
<!--nav_list-->