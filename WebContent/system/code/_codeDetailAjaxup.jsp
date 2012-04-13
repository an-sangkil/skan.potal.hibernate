<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!--
	1. ViewName : 
	2. fileName :
	3. Function : ajaxDetailAjax Update 코드관리 / 현재 사용하지 않음 
				 
-->
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>
<%@ include file="/common/include/includeJavaScript.jsp" %>



상위코드 : <input type="text" id="upper_code" name="upper_code" value="${code.upper_code }"/> <p/>
코드 : <input type="text" id="code" name="code" value="${code.code }"/> <p/>
코드이름  : <input type="text" id="code_name" name="code_name" value="${code.code_name }"/> <p/>
코드설명  : <input type="text" id="code_comment" name="code_comment" value="${code.code_comment }"/> <p/>
CLS : <input type="text" id="cls" name="cls" value="${code.cls_type }"/> <p/><!-- 폴더 혹은 파일 -->