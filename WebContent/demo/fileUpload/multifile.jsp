<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/include/includeJavaScript.jsp" %>
<style type="text/css">
 #upload{  
     margin:30px 200px; padding:15px;  
     font-weight:bold; font-size:1.3em;  
     font-family:Arial, Helvetica, sans-serif;  
     text-align:center;  
     background:#f2f2f2;  
     color:#3366cc;  
     border:1px solid #ccc;  
     width:150px;  
     cursor:pointer !important;  
     -moz-border-radius:5px; -webkit-border-radius:5px;  
}  
</style>
<script type="text/javascript">

	
	
</script>
</head>
<body>
	AJAX MULTIFILE UPLOAD
	<div id="upload" >Upload File</div><span id="status" ></span>  
	<!--List Files-->  
 	<ul id="files" ></ul> 		
</body>
</html>