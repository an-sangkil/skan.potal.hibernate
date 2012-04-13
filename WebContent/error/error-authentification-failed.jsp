<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/include/taglib.inc"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="content-style-type" content="text/css"/>

<title>dongbu::system</title>
</head>
<body>
	<script>
		//alert('Authentification failed!!\n\nYou must login first.');
		alert("올바르지 못한 방법으로 접근 하였습니다. \n\n 로그인 페이지로 이동합니다.");
		location.href = "/";
	</script>
</body>
</html>