<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>

<%@page import="com.dongbu.farm.system.category.manager.ICategoryManager"%>
<%@page import="com.dongbu.farm.system.code.model.Code"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%
	Enumeration ss = request.getParameterNames();
	String getS = request.getParameter("node");			
	String getk = request.getParameter("baseTree");
	String language = request.getParameter("language");
	String value;
	String codeName = "";//선택된 language값에 따라 한글,영문을 바꿔 넣기 위한 변수
	/* Parameter로 넘어오는 내용 출력
	*/
	while(ss.hasMoreElements()){
	String key = (String)ss.nextElement();
	value = request.getParameter(key);
		System.out.println(key+" : "+value+" ");
	}
%>
			[
<%
		List<Code> codeList = (List<Code>)request.getAttribute("CodeList");
		int categoryCount = 0;
		for(Code codes : codeList){
			
%>
				{"text":"<%= codes.getCode_name() %>", 
				 "id"  :"<%= codes.getCode()%>",
				 "leaf":false} <%=  categoryCount < codeList.size()-1 ? ",":"" %>
<%
			categoryCount++;
			if( categoryCount < codeList.size()){
			}
		}
%>
			]
