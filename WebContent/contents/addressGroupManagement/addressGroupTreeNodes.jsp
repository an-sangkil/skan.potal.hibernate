<%@page import="com.dongbu.farm.content.address.model.Address"%>
<%@page import="com.dongbu.farm.content.address.model.AddressGroup"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%/**
	* fileName   : addressGroupTreeNodes.jsp
	* createDate : 2011. 4. 5. 오후 6:01:42
	* CreateUser : skan
	* Document   : 주소록 그룹관리 / 그룹만 JSON방식으로 화면에 write 한다
	*/
%>
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
		List<AddressGroup> addressGroupList   = (List<AddressGroup>)request.getAttribute("addressGroupList");
		int categoryCount = 0;
		for(AddressGroup codes : addressGroupList){
%>
			{"text":"<%= codes.getCode_name() %>", 
			 "id"  :"<%= codes.getCode()%>",
			 "leaf":false} <%= categoryCount < addressGroupList.size()-1 ? ",":"" %>
<%
			categoryCount++;
			if( categoryCount < addressGroupList.size()){
			}
		}
%>
	]
