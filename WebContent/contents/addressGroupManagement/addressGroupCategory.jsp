<%@page import="com.dongbu.farm.common.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!--
	* fileName   : addressGroupManagement.jsp
	* createDate : 2011.04. 08. 오후 2:03:43
	* CreateUser : ahn
	* Document   : 카테고리 팝업 - 팝업화면으로 다른 구릅으로 변경 하고 자 할경우 사용한다.
	
				 
-->
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"  %>
<%@ include file="/common/include/includeJavaScript.jsp" %>
<%@page import="java.util.HashMap"%>

<html>
	<head>
		<title>Reorder TreePanel</title>
			
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
					rootVisible: false,
					containerScroll: true, 
					loader: new Tree.TreeLoader({	// ajax를 위한 url을 지정합니다.
								dataUrl:'/system/code/addressGroupTreeNodes/getAddressGroupCodeList.system?',///system/code/getCodeList.system?
								baseAttrs  : {"callbackNameElement" : "<%= request.getParameter("")%>",
											  "callbackCodeElement" : "<%= request.getParameter("")%>"},
								baseParams : {baseType:'tree'},
								paramsAsHash : true
							})
				});
				// set the root node
				var root = new Tree.AsyncTreeNode({
					text: 'js-tree',		 
					allowDrag : false,     
					allowDrop : false,    
					id : '<%= Utils.strValue(request.getParameter("childCode")).equals("") ? "00000" : request.getParameter("childCode") %>',           
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
							'cls' : node.cls 
						};
						*/						

						//파라미터 
						var pars = {};
						pars = {
								'code' : node.id
						};
						
						//var successTarget = "categoryDetailView_div";
						//var url = "/system/code/getCodeDetailAJU.system?";
						var url = "/system/code/getCodeDetailAJR.system?";
						var method = "GET";
						var evalScript = false;
						
						
						//Ajax call
						var method = 'GET';
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
						$('codeName').value = node.code_name ;
					}
				}catch(e){
					alert(e.message);
				}	
			}
			
			
			function openerPageObjreturn(){
				var obj={"codeName" : $F("codeName") ,
						 "code" : $F("code")};
				opener.<%= request.getParameter("callbackFunction") == null ? "callbackMethod파라미터가필요합니다" : request.getParameter("callbackFunction") %>(obj);
				window.close();
			}	
			</script>
			
			
	</head>
<body>
<div id="bizCategorydiv" style="overflow:auto; height:300px;width:250px;border:1px solid #c3daf9;"></div> 
<!-- 상세내용 -->
<div>
	<div id="categoryDetailView_div">
	</div>
	<div>
		<input  type="text" name="codeName" id="codeName" readonly="readonly" style="float:left; width:195px; margin-left:2px;"/>
		<input type="hidden" name="code"    id="code"    value="" readonly="readonly"/>
		
		<button type="button" onclick="openerPageObjreturn()">확인</button>
	</div>
</div>
</body>
</html>