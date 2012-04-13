<!--
	* fileName   : file_name
	* createDate : 2010. 8. 9. 오후 4:11:16
	* CreateUser : ahn
	* Document   : todo
	*
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%
	//HttpSession session = request.getSession(false);
	
    try{
    	if(session != null && session.isNew()){
    		/*
    		 *정상적인 LoginPage를 통해서 온것이 아니면 session을 삭제 한다.
    		 */
    		session.invalidate();
		%>
			<script type="text/javascript">
				alert("정상로그인 하세요");
				location.href = "/login.jsp"; 			
			</script>
		<%
			return ;
		}

    	if(session != null){
    		if(session.getAttribute("user_id") != null && !session.getAttribute("user_id").equals("")){ 
    			Object user_idObj 				= session.getAttribute("user_id");
    			Object user_nameObj 			= session.getAttribute("user_name");
    			Object e_mailObj 				= session.getAttribute("e_mail");
    			Object dept_codeObj 			= session.getAttribute("dept_code");
    			Object dept_nameObj 			= session.getAttribute("dept_name");
    			Object addressObj 				= session.getAttribute("address");
    			Object phone_numberObj 			= session.getAttribute("phone_number");
    			Object mobile_phone_numberObj	= session.getAttribute("mobile_phone_number");
    			Object ssn_numberObj 		    = session.getAttribute("ssn_number");
    			Object zip_codeObj 			    = session.getAttribute("zip_code");
    			Object genderObj 			    = session.getAttribute("gender");
    			
    			String user_id 				= (String)user_idObj;
    			String user_name 			= (String)user_nameObj;
    			String e_mail 				= (String)e_mailObj;
    			String dept_code 			= (String)dept_codeObj;
    			String dept_name 			= (String)dept_nameObj;
    			String address 				= (String)addressObj;
    			String phone_number 		= (String)phone_numberObj;
    			String mobile_phone_number  = (String)mobile_phone_numberObj;
    			String ssn_number 		    = (String)ssn_numberObj;
    			String zip_code 		    = (String)zip_codeObj;
    			String gender 			    = (String)genderObj;
		%>
			<c:set var="user_id" 				value="<%= user_id %>"></c:set>
			<c:set var="user_name" 				value="<%= user_name %>"></c:set>
			<c:set var="e_mail" 				value="<%= e_mail %>"></c:set>
			<c:set var="dept_code" 				value="<%= dept_code %>"></c:set>
			<c:set var="dept_name" 				value="<%= dept_name %>"></c:set>
			<c:set var="address" 				value="<%= address %>"></c:set>
			<c:set var="phone_number" 			value="<%= phone_number %>"></c:set>
			<c:set var="mobile_phone_number" 	value="<%= mobile_phone_number %>"></c:set>
			<c:set var="ssn_number" 			value="<%= ssn_number %>"></c:set>
			<c:set var="zip_code" 				value="<%= zip_code %>"></c:set>
			<c:set var="gender" 				value="<%= gender %>"></c:set>
			
			<script type="text/javascript">
				alert("ok"); 
			</script>
		<%
    		}else{
   			%>
    			<script type="text/javascript">
    				alert("false");
    				location.href = "/login.jsp"; 
    			</script>
    		<%
    			return ;
    		}
		}else{
			%>
			<script type="text/javascript">
				alert("session is null");
				location.href = "/login.jsp"; 
			</script>
			<%
			return ;
		}
		%>
    	
<%  	
    }catch(NullPointerException e){
    	e.printStackTrace();
%>
	<script type="text/javascript">
		location.href = "/login.jsp";
	</script>	
<%
    }
%>
    
    

