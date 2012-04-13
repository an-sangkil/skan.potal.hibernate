<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>
<%@ include file="/common/include/includeJavaScript.jsp"  %>
<html>
	<head>
		<title>Login Page</title>
		<script type="text/javascript" src="/js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="/js/ext/ext-all.js"></script>
		
		<script type="text/javascript"> 
			//login
			function getLogin(){
				var params = {};
				params["userId"]   = $F('userId');
				params["password"] = $F('password');
				location.href = "/login/getLogin.login?"+$H(params).toQueryString();
			}
		</script>
	</head>
	<body>
		 <c:choose>
		 	<c:when test="${empty member}">
		    	<script>	
					Ext.MessageBox.alert("회원 가입 하세요", "가입되지 않은 아이디 입니다.", function (){
						location.href="/login/loginFalse.jsp";
						});
				</script>  
		    </c:when>
		    <c:when test="${!empty member}">
		    	<c:choose>
		    		<c:when test="${(member.user_id eq user_id) && (member.password eq password) }">
						<script>
							var params = {
									"user_id"   : '${member.user_id}',
									"password"  : '${member.password}'
							};
							location.href = "/login/setSessiongSetting.login?"+$H(params).toQueryString();
						</script>		    		
		    		</c:when>
		    		<c:when test="${(member.user_id != user_id) && (member.password eq password) }">
						<script>
							Ext.MessageBox.alert("아이디를 확인 하세요.", "검색된 아이디가 없습니다.", function (){location.href="/login/loginFalse.jsp";});
						</script>		    		
		    		</c:when>
		    		<c:otherwise>
		    			<script>
							Ext.MessageBox.alert("Error", "비밀 번호가 올바르지 않습니다.", function (){location.href="/login/loginFalse.jsp?user_id="+"${member.user_id}";});
						</script>
		    		</c:otherwise>
		    	</c:choose>
		    </c:when>
		    <c:otherwise>
		       <!-- TODO : empty  -->
		    </c:otherwise>
		</c:choose>
	</body>
</html>