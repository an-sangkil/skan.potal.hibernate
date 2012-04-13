<!--
	* fileName   : file_name
	* createDate : 2010. 8. 9. 오후 4:55:12
	* CreateUser : ahn
	* Document   : todo
	*
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession httpsession = null;
	httpsession = request.getSession();
	//세션삭제
	httpsession.invalidate();  
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	location.href="/login.jsp";
</script>
</head>
<body>

</body>
</html>