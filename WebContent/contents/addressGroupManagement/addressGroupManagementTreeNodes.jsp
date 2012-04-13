<%@page import="com.dongbu.farm.content.address.model.Address"%>
<%@page import="com.dongbu.farm.content.address.model.AddressGroup"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%/**
	* fileName   : addressGroupTreeNodes.jsp
	* createDate : 2011. 4. 5. 오후 6:01:42
	* CreateUser : skan
	* Document   : 주소록 그룹관리 / 사용자 유저도 같이 뿌려줌
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
		List<Address> addressList = (List<Address>)request.getAttribute("addressList");
		int categoryCount = 0;
		for(AddressGroup codes : addressGroupList){
%>
			{"text":"<%= codes.getCode_name() %>", 
			 "id"  :"<%= codes.getCode()%>",
			 "leaf":false} <%=  addressList.size() != 0 ? ",":"" %>
<%
			categoryCount++;
			//if( categoryCount < addressGroupList.size()){
			//}
		}
		
		int addressIndex = 0;
		for(Address address : addressList){
%>
			{"text":"<%= address.getUser_name()      %>", 
			 "id"  :"<%= address.getAds_mgt_no() %>",
			 "leaf":true} <%=  addressIndex < addressList.size()-1 ? ",":"" %>
			
<%
			addressIndex++;
		}
%>
	]
