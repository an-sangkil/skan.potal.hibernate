<%@page import="com.dongbu.farm.common.utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
	* fileName   : codeManagement.jsp
	* createDate : 2011.03. 24. 오후 2:03:43
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeJavaScript.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>schedule</title>
			<link rel="stylesheet" type="text/css" href="/css/common.css" />
			<link rel="stylesheet" type="text/css" href="/css/tree.css" />
			<script type="text/javascript">
			Ext.onReady(function(){
				// shorthand
				var Tree = Ext.tree;
				var tree = new Tree.TreePanel({
					//el:'bizCategorydiv',
					useArrows:true,
					autoScroll:true,
					animate:true,
					enableDD:false,
					rootVisible: true,
					containerScroll: true, 
					loader: new Tree.TreeLoader({	// ajax를 위한 url을 지정합니다.
								dataUrl:'/system/code/getCodeList.system?',///system/code/getCodeList.system?
								baseAttrs  : {"callbackNameElement" : "<%= request.getParameter("")%>",
											  "callbackCodeElement" : "<%= request.getParameter("")%>"},
								baseParams : {baseType:'tree'},
								paramsAsHash : true
							})
				});
				// set the root node
				var root = new Tree.AsyncTreeNode({
					text: '공통코드',		 
					allowDrag : false,     
					allowDrop : false,    
					id : '<%= Utils.strValue(request.getParameter("childCode")).equals("") ? "00000" : request.getParameter("childCode") %>',//00000_upper           
					Params : {"params1":""}
				});
				tree.setRootNode(root);
				// render the tree
				tree.render('bizCategorydiv');
				root.expand();
				tree.on("click", choiceInformation);
				function choiceInformation(node){
					if(tree.getSelectionModel().isSelected(node)){//선택한 Node와 내가 가지고 있는 Node가 같은건지 ,
						if(node.isLeaf()){//isLeaf 하위가 아이템이 있는경우 false[폴더표시] 없는 경우 true[파일이미지 표시]  
							 //alert('text:'+node.text +',id: ' + node.id);
						}
						/*
						var Code = {
							'code' : node.code ,
							'upper_code' : node.upper_code ,  
							'code_comment': node.code_comment ,
							'code_name'  : node.code_name ,
							'code_seq'  : node.code_seq ,
							'cls_type' : node.cls 
						};
						*/						

						//파라미터 
						var pars = {};
						pars = {
								'code' : node.id
						};
						
						//Ajax call
						//var successTarget = "categoryDetailView_div";
						//var url = "/system/code/getCodeDetailAJU.system?";
						//var evalScript = false;
						var url = "/system/code/getCodeDetailAJR.system?";
						var method = "GET";
						var asynchronouse = true;
						var callbackMethod = _detailResponse;
						
						new PAjax.Request(url, method, pars, asynchronouse, _detailResponse);
						//new PAjax.Updater(successTarget , url, method, pars , evalScript);
						
					}
				}
			});
			
			function _detailResponse(response){
				try {
					var resultObj = eval('('+response.responseText+')');
					if(resultObj.result == "SUCCESS"){
						var node = resultObj.code;
						
						$('code').value = node.code ;
						$('upper_code').value = node.upper_code ;  
						$('upper_code_name').value = node.upper_code_name ;
						$('code_comment').value = node.code_comment; 
						$('code_name').value = node.code_name ;
						$('code_seq').value =node.code_seq ;
						$('cls_type').value =node.cls_type;
					}
				}catch(e){
					alert(e.message);
				}	
			}
			
			function upperCodeCallback(obj){
				$('upper_code').value = obj.code;
				$('upper_code_name').value = obj.codeName;
			}
			
			//코드추가 버튼 이벤트
			function addCodeButton(){
				//현재 선택된 항목을 상위 코드로 셋팅해준다.
				$('upper_code').value = $F('code');
				$('upper_code_name').value = $F('code_name');
				
				//불필요한 항목 리셋
				elementClear();
			}
			
			function elementClear(){
				$('code').value = '';
				//$('upper_code').value = '';
				//$('upper_code_name').value = '';
				$('code_comment').value = ''; 
				$('code_name').value = '';
				$('code_seq').value = '';
				$('cls_type').value = '';
			}
			
			function _cancle(){
				$('code').value = '';
				$('upper_code').value = '';
				$('upper_code_name').value = '';
				$('code_comment').value = ''; 
				$('code_name').value = '';
				$('code_seq').value = '';
				$('cls_type').value = '';
			}
			
			function saveCode(){
				//필수상목 체크 아이템
				var obj = ['upper_code' , 'upper_code_name'];

				//
				if(validationCheck(obj)){
					if($F('code') == ''){
						_writeCode();
					}else{
						_modifyCode();
					}
				}
			}
			
			function _writeCode(){
				//필수상목 체크 아이템
				var obj = ['code_name' , 'code_comment'];

				//
				if(validationCheck(obj)){
					
					var pars = {};
					pars = {
							'upper_code' : $F('upper_code') ,  
							'code_comment': $F('code_comment') ,
							'code_name'  : $F('code_name') ,
							'cls_type' : $F('cls_type') 	
					};
					var url = "/system/code/writeCodeAJR.system?";
					var method = "POST";
					var asynchronouse = true;
					var callbackMethod = _writeResponse;
					
					new PAjax.Request(url, method, pars, asynchronouse, _writeResponse);
				}
			}
			function _writeResponse(response){
				try {
					var resultObj = eval('('+response.responseText+')');
					if(resultObj.result == "SUCCESS"){
						location.reload();
					}
				}catch(e){
					alert(e.message);
				}
			}
			
			function _modifyCode(){
				//필수상목 체크 아이템
				var obj = ['code_name' , 'code_comment'];

				//
				if(validationCheck(obj)){
					
					var pars = {};
					pars = {
							'upper_code' : $F('upper_code') ,
							'code' : $F('code') ,
							'code_comment': $F('code_comment') ,
							'code_name'  : $F('code_name') ,
							'code_seq' : $F('code_seq') ,
							'cls_type' : $F('cls_type') 	
					};
					var url = "/system/code/modifyCodeAJR.system?";
					var method = "POST";
					var asynchronouse = true;
					var callbackMethod = _modifyResponse;
					
					new PAjax.Request(url, method, pars, asynchronouse, _modifyResponse);
				}
			}
			
			function _modifyResponse(response){
				try {
					var resultObj = eval('('+response.responseText+')');
					if(resultObj.result == "SUCCESS"){
						location.reload();
					}
				}catch(e){
					alert(e.message);
				}	
			}
			</script>
	</head>
<body>

	<!-- 1. Header Contents-->
<div>
	<%@ include file="/mainHeader.jsp" %>
</div>
	
	<!-- 2. Left Contents -->
<div style="float: left;width: 230px">
	
</div>

	<!-- 3. Main Contents -->
<div class="mainContents">
	
	<div id="bizCategorydiv" style="height:300px;width:250px;float: left;overflow:auto;height:280px; margin-bottom:30px; border:2px solid #c7c7c7; margin:0 auto;"></div>
	<!-- 상세내용 -->
	<div>
		<div style="">
			<div class="insert_table">
				<table style="width: 500px">
					<tr>
						<th>
							상위코드
						</th>
						<td>
							<input type="text" id="upper_code" name="upper_code" value="${code.upper_code }" readonly="readonly"/> 
							<input type="text" id="upper_code_name" name="upper_code_name" readonly="readonly"/>
							<span onclick="searchCodeCategoryPopup('00000','upperCodeCallback');">변경</span>
						</td>
					</tr>
					<tr>
						<th>
							코드
						</th>
						<td>
							<input type="text" id="code" name="code" value="${code.code }" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th>
							코드이름
						</th>
						<td>
							<input type="text" id="code_name" name="code_name" value="${code.code_name }"/> 
						</td>
					</tr>
					<tr>
						<th>
							코드설명
						</th>
						<td>
							<textarea rows="" cols="" id="code_comment" name="code_comment">${code.code_comment }</textarea>
						</td>
					</tr>
					<tr>
						<th>
							분류
						</th>
						<td>
							<select>
								<option value="">폴더</option>
								<option value="">아이템</option>
							</select> 
							<input type="text" id="cls_type" name="cls_type" value="${code.cls_type }"/> <!-- 폴더 혹은 파일 -->
							<input type="hidden" id="code_seq" name="code_seq" value="${code.code_seq}" readonly="readonly"/> <p/><!-- 순서 --> 
						</td>
					</tr>
				</table>
			</div>
			
			<div style="text-align: right;">
				<button type="button" onclick="addCodeButton()">코드추가</button>
				<button type="button" onclick="saveCode()">저장</button>
				<button type="button" onclick="_cancle()">취소</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>