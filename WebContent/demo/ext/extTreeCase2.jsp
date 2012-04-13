<!--
	* fileName   : file_name
	* createDate : 2010. 8. 17. 오전 11:51:26
	* CreateUser : ahn
	* Document   : todo
	*
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Reorder TreePanel</title>
			<%@ include file="/common/include/includeCss.jsp"  %>
			<%@ include file="/common/include/includeJavaScript.jsp"  %>
			<script type="text/javascript">
			
			Ext.onReady(function(){
				var Tree = Ext.tree;
				var myTree = new Tree.TreePanel({
							//loader: new Tree.TreeLoader(),
							useArrows: true,
							autoScroll: false,
							containerScroll: true,
							animate:true,
							enableDD:false,  //true to enable drag and drop
							rootVisible:true,
							border: false,
							draggable: false
							
				});
				
	
				var Rroot = new Tree.AsyncTreeNode({
				//root: {
							nodeType: 'async',
				            draggable: false,
		                    text: '분류',
		                    id:'classification',
		                    //allowDrag:false,
		                    //allowDrop:true,
		                    children: [
										{text: '영어', id:'cls-1',  children: [
										          {text: '영문법',   id:'eng-1', qtip:'영어문법강좌입니다.',   leaf:true, allowDelete:true},
										          {text: '영어 회화', id:'eng-2', qtip:'영어회화 초보용입니다.', leaf:true, allowDelete:true},
										          {text: '영작문',   id:'eng-3', qtip:'영작문 중급입니다.',    leaf:true, allowDelete:true}
										]},
										
										{text: '수학', id:'cls-2', children: [
										          {text: '미분', id:'math-1',      leaf:true, allowDelete:true},
										          {text: '적분', id:'math-2',      leaf:true, allowDelete:true},
										          {text: '집합', id:'math-3',      leaf:true, allowDelete:true},
										          {text: '극한값 계산', id:'math-4', leaf:true, allowDelete:true}
										
										]},
										
										{text: '국어', id:'cls-3', children: [
										          {text: '시', id:'kor-1', children: [
										                     {text: '19세기', id:'poem-1', leaf:true, allowDelete:true},
										                     {text: '시조',   id:'poem-2', leaf:true, allowDelete:true},
										                     {text: '근대시', id:'poem-3', leaf:true, allowDelete:true}
										
										          ]},
										          {text: '문법', id:'kor-2', leaf:true, allowDelete:true}
										]},
										
										{text: '지구와현경', id:'earth-1', leaf:true, allowDelete:true, url : 'http://wwww.naver.com'},
										{text: '법학개론',   id:'law-1',   leaf:true, allowDelete:true}
				                    ]
				});
				
				myTree.setRootNode(Rroot);
				myTree.render('treediv');
				myTree.expandAll();
				Rroot.expand();
	          
				// 이벤트 시작-------------------------------------------------------------------------------------------------------
				myTree.on('click', doClick);
				
				// click 펑션-----------------------------------------------
				function doClick(node){
				          if(myTree.getSelectionModel().isSelected(node)){
				                     if (node.isLeaf()){//isLeaf 하위가 없는 경우 true
				                               alert('id: ' + node.id + ',  leaf: true, url:' + node.attributes.url);
				                               var actionURL = "";
				                               new Ajax.Request(actionURL,{
					                               method     : 'post',
					                               parameters : {},
					                               onLoading  : function(){},
					                               onSuccess  : function(response){
						                               var resultObj = eval('('+response.responseText+')');
						                               
					                               },
					                               onFailure  : function(){}
				                               });
				                     } else {
				                               alert('id:  ' + node.id + ',  leaf: false, url:' + node.url);
				                     }
				          }
				};
			});
			
			</script>
	</head>
<body>
	<div id="wrapper"><div id="treediv" style="overflow:auto; height:300px;width:250px;border:1px solid #c3daf9;"></div></div>
</body>
</html>


