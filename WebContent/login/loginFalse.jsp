<!--
	* fileName   : fileName
	* createDate : 2010. 7. 20. 오전 11:47:24
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login false</title>
	<%@ include file="/common/include/includeCss.jsp"  %>
	<%@ include file="/common/include/includeJavaScript.jsp"  %>
	<script type="text/javascript">
	//login
	function getLogin(){
		var params = {};
		params["user_id"]   = $F('user_id');
		params["password"] = $F('password');
		location.href = "/login/getLogin.login?"+$H(params).toQueryString();
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
<body style="padding: 10px;">
	<center>
		<!-- CSS3 적용, 아직 EP는 적용안된다고 함/크롬, 파폭(O) -->
		<div class="div_outsider_round" style="width: 200px;height: 70px;">
			<table>
				<tr>
					<td>
						<span>
							<input type="text" 	   id="user_id"   name="user_id"   value="">
							<input type="password" id="password"  name="password"  value="">
						</span>
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
											<em unselectable="on" class=""><button type="button" id="ext-gen11" class="x-btn-text" onclick="getLogin();">login</button></em>
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
	</center>
	<!-- 
	 * FIELDSET 테두리 HTML4.0V 
	<fieldset style="width:400px; height:100px; text-align:left; margin:0; padding:10px;">
		<legend style="margin: 0; padding: 0 ;"  align="center">로그인</legend>
	</fieldset>
	 *  일반 DIV 테두리
	<div style="width: 400px; height: 100px; text-align: left; margin: 0; padding: 4px ; border:1px solid silver;"></div>
 	-->
	
</body>
</html>