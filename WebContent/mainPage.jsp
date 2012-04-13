<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<!--
	* fileName   : main.jsp
	* createDate : 2010. 7. 20. 오후 2:03:43
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>
<%@ include file="/common/include/includeJavaScript.jsp"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Main Page</title>
<script type="text/javascript">
	//login
	function getLogin(){
		var params = {};
		params["user_id"]   = $F('user_id');
		params["password"]  = $F('password');
	
		document._login.action = "/login/getLogin.login";
		document._login.submit();
		//location.href = "/login/getLogin.login?"+$H(params).toQueryString();
	}
	
	function showResult(){
		Ext.MessageBox.show({
			title: '로그인 오류',
			msg: '등록되지 않은 아이디 입니다.',
			buttons: Ext.MessageBox.OK,					
			fn: showResult,
			icon: Ext.MessageBox.WARNING
		});
	}
</script>
</head>
	<body>
		<%@ include file="/mainHeader.jsp" %>
		
		<!-- main contents -->
		<form name="_login" action="" method="post">
			<c:choose>
				<c:when test="${!empty sessionScope.member}"> 
					<div>
						<div>
							<span>${sessionScope.member.user_name}님 안녕하세요.</span>
						</div>
						<span onclick="location.href='/login/sessionRemove.jsp';" style="cursor: pointer;">로그아웃</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="default_div_round">
						<table>
							<tr>
								<td>
									<div>
										<input type="text"     id="user_id"   name="user_id"   value="mycup">
										<input type="password" id="password"  name="password"  value="1234">
									</div>
								</td>
								<td>
									<div>
										<table cellspacing="0" class="x-btn x-btn-noicon" id="ext-comp-1004" style="width: auto;">
											<tbody class="x-btn-medium x-btn-icon-medium-left">
												<tr>
													<td class="x-btn-tl"><i>&nbsp;</i></td>
													<td class="x-btn-tc"></td>
													<td class="x-btn-tr"><i>&nbsp;</i></td>
												</tr>
												<tr>
													<td class="x-btn-ml"><i>&nbsp;</i></td>
													<td class="x-btn-mc">
														<em unselectable="on" class=""><button type="button" id="ext-gen11" class="x-btn-text" onclick="getLogin();">login</button></em></td>
													<td class="x-btn-mr"><i>&nbsp;</i></td>
												</tr>
												<tr>
													<td class="x-btn-bl"><i>&nbsp;</i></td>
													<td class="x-btn-bc"></td>
													<td class="x-btn-br"><i>&nbsp;</i></td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
			<div>
				
			</div>
		</form>
	</body>
</html>