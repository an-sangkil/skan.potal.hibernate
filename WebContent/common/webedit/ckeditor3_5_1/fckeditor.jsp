<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="net.fckeditor.*" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Sample CKEditor Site</title>
	<script type="text/javascript" src="ckeditor.js"></script>
	<script type="text/javascript">
		function _write(){
			alert(document.getElementById("editor1").value);
		}
	</script>
</head>
<body>
	<form method="post">
		<p>
			My Editor:<br />
			<textarea id="editor1" name="editor1"><p>Initial value.</p></textarea>
			<script type="text/javascript">
			
			var editor = CKEDITOR.replace( 'editor1',
				    {
				        toolbar : 'Full',
				       // language :'kr'
				        uiColor : '#9AB8F3'
				       //filebrowserBrowseUrl : '/browser/browse.php',
				       //filebrowserUploadUrl : '/uploader/upload.php'
				    });
			</script>
		</p>
		<p>
			<input type="button" onclick="_write()" value="TEST"/>
		</p>
	</form>
</body>
</html>