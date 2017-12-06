<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jspf"%>


<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--[if lt IE 9]>
    <script src="bower_components/html5shiv/dist/html5shiv.js"></script>
  <![endif]-->

<!--뷰태그:m.네이버에서 복사해서쓰기-->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=yes">

<title>홍주문화회관</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="${CONTEXT_PATH}/assest/mobile/css/bootstrap.min.css" />

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="${CONTEXT_PATH}/assest/mobile/css/bootstrap-theme.min.css" />

<!--cdn font awesome //font icon-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

<!--구글 폰트-->
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel="stylesheet" href="${CONTEXT_PATH}/assest/mobile/css/style.css" />

</head>
<body>

	<header class="header_wrap">
		<tiles:insertAttribute name="header" />
	</header>
	<nav class="nav_cover">
		<tiles:insertAttribute name="sidebar" />
	</nav>
	<div class="content_wrap">
		<tiles:insertAttribute name="body" />
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
	
	
	<!--스크립트 :구글->code.jquery.com - minified다운-->
	<script src="${CONTEXT_PATH}/assest/mobile/js/jquery-2.2.4.min.js" type="text/javascript"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${CONTEXT_PATH}/assest/mobile/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${CONTEXT_PATH}/assest/mobile/js/bootstrapValidator.js" type="text/javascript"></script>
	<script type="text/javascript">
		//왼쪽메뉴 클릭시 펼쳐지기
		$(function() {
			$("dd:not(:first)").css("display", "none");
			$("dt:first").addClass("selected");
			$("dl dt").click(function() {
				if ($("+dd", this).css("display") == "none") {
					$("dd").slideUp("none");
					$("+dd", this).slideDown("none");
					$("dt").removeClass("selected");
					$(this).addClass("selected");
				}
			}).mouseover(function() {
				$(this).addClass("over");
			}).mouseout(function() {
				$(this).removeClass("over");
			});
		}); //function
	
		
		// 함수 선언
		function navToggle() {
			// 오픈 클릭, click() 메서드안에 익명함수를 사용
			$('.nav_cover .open').click(function(e) {
				//기본 동장 취소, form action, a href 등등
				e.preventDefault();

				//클래스 추가
				$('.nav_list').addClass('on');
			});

			//닫기, click() 메서드안에 이름이 있는 함수 사용
			$('.nav_cover a.close').click(listClose);
			$('.nav_list li').click(listClose);

			function listClose(e) {
				//e.preventDefault();
				//닫기
				$('.nav_list').removeClass('on');
			}
		}
		// 함수 실행
		navToggle();

	 	//익스플로러 클릭시 생기는 점선제거
		function bluring() {
			if (event.srcElement.tagName == "A" || event.srcElement.tagName == "IMG") document.body.focus();
		}
	 	
		document.onfocusin = bluring;
	</script>
	
	
</body>
</html>