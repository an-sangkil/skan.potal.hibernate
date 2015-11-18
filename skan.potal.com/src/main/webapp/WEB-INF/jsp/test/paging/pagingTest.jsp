<!DOCTYPE html>

<%@page import="com.skan.potal.hibernate.application.model.User"%>
<%@page import="org.springframework.data.domain.Page"%>
<%
	
	Page<User> userPaging  = (Page<User>)request.getAttribute("userPaging");

%>
<html>
	<head>
		
	
	</head>
	<body>
		<table>
			<%
				for(User user : userPaging) {
			%>
			
				<tr>
					<td><%= user.getId() %> </td>
					<td><%= user.getName() %> </td>
					<td></td>
				</tr>
					
					
			<%		
				}
			
			
			%>
		
		</table>
	
	
	</body>
	<script type="text/javascript">
	</script>
</html>

