<!--
	* fileName   : addressManagement.jsp
	* createDate : 2011. 4. 5. 오후 6:01:42
	* CreateUser : skan
	* Document   : 주소록 관리 페이지임
	*
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<%@ include file="/common/include/includeCss.jsp"%>
<%@ include file="/common/include/includeJavaScript.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dongbu ::</title>
	<script type="text/javascript">
		
		//기념일 sell 추가
		function addSellAnniversary(){
			var tdAry = new addAppendChild('_anniversary_table' , 3 );
			var anniversary_type = "<code:codelist codeGroup='00038' isSelectBox='Y' attribute='id=\'anver_type_code\' name=\'anver_type_code\'' selectedInfo=''></code:codelist>";
			//new Element('input',{id:'anver_type_code' ,name:'anver_type_code' , alt:'기념일 종류'})
			tdAry[0].innerHTML = anniversary_type;
			tdAry[1].appendChild(new Element('input',{id:'anver_content' , name:'anver_content' , alt:'기념일 내용'}));
			tdAry[1].appendChild(new Element('input',{id:'anver_seq'     , name:'anver_seq'     , alt:'번호' ,   type:'hidden' , value : 'empty'}));
			tdAry[2].appendChild(new Element('input',{id:'anver_date'    , name:'anver_date'    , alt:'기념일 날짜'}).observe('click',function(){new Calendar(this);}));
			tdAry[2].appendChild(new Element('button',{type:'button'}).observe('click',function(){removeSellAnniversary(this);}).update("삭제"));
		}
		
		//기념일 sell 삭제
		function removeSellAnniversary(obj){
			new RemonveNode(obj);
		}
		
		//저장 버튼 클릭
		function saveAddress(){
			
			//필수상목 체크 아이템
			var obj = ['user_name' , 'phone'];
			
			var validation = validationCheck(obj);
			
			if(validation){
				
				if(document.getElementById("email").value != ""){
					
					//이메일 유효성 체크
					var emailBoolean = isValidEmail_check($('email').value);
					validation = emailBoolean;
					
				}
			}
			
			if(validation){
				if($F('ads_mgt_no') == ''){
					_writeAddress();
				}else{
					_modifyAddress();
				}
			}
		}
		
		//연락처 저장
		function _writeAddress(){
			//분리시 사용
			//$F('cell_phone_start');
			//$F('cell_phone_end');
			//$F('cell_phone_middle');
			//$('cell_phone').value = "";
			
			document._address_form.action = "/content/address/writeAddress.dongbu";
			document._address_form.submit();
		}
		
		//연락처 수정
		function _modifyAddress(){
			document._address_form.action = "/content/address/modifyAddress.dongbu";
			document._address_form.submit();
		}
		
		
		//연락처 상세보기
		function detailAddress(ads_mgt_no , user_name){
			var pars = {
					ads_mgt_no : ads_mgt_no,
					user_name : ''
			};
			var successTarget  = '_right_address_contents';//_addressDetail_div 
			var url 		   = '/content/address/getAddressAjaxUp.dongbu'; 
			var method		   = 'GET';
			 
			var evalScript = false;
			new PAjax.Updater(successTarget , url, method, pars , evalScript);
			
			
			if($('_newSaveButton')){
				$('_newSaveButton').show();
			}
		}
		
		
		//새로운 연락처 등록 버튼 클릭
		function _newSaveAddress(){
			
			$('_newSaveButton').hide();
			
			_cancleAddress();
			
			var trAry = $('_anniversary_table_tbody').getElementsByTagName('tr');
			
			if(trAry.length != 0 ){
				var tbody = trAry[0].parentNode;
				
				//상위 하나의 tr만 남기고 모두 삭제 한다.
				for(var i = trAry.length-1 ; 0 < i  ; i--){
					trAry[i].removeNode(true);
				}
			}
		}
		
		function _searchAddress(){
			//document._addressSearch_form.user_name.value = $F('_user_name');
			//document._addressSearch_form.action = "/content/address/getAddressList.dongbu";
			//document._addressSearch_form.submit();
			
			var successTarget  = '_left_address_contents'; 
			var url 		   = '/content/address/getAddressList.dongbu'; 
			var method		   = 'GET';
			var pars		   = {
				user_name : encodeURIComponent($F('_user_name'))			 
			};
			var evalScript = true;
			
			
			new PAjax.Updater(successTarget , url, method, pars , evalScript);
			
			
		}
		
		
		function _cancleAddress(){
			//일반정보 초기화
			$('ads_mgt_no').value = "";
			$('group_code').value = "";
			$('user_name').value = "";
			$('position_code').value = $('position_code')[0].value;
			$('company_name').value = "";
			$('phone').value = "";
			$('cell_phone').value = "";
			$('postcode').value = "";
			$('district_address').value = "";
			$('detail_address').value = "";
			$('email').value = "";
			$('memo').value = "";
			
			//기념일 정보 초기화
			$('anver_type_code').value = $('anver_type_code')[0].value;
			$('anver_content').value = "";
			$('anver_date').value = "";
			$('anver_seq').value = "";
		}
		
		
		//탭이동
		function addressTabMovement(url , params){
			
			var successTarget  = '_left_address_contents'; 
			var url 		   = url; 
			var method		   = 'GET';
			var pars		   = params;				 
			var evalScript = true;
			
			new PAjax.Updater(successTarget , url, method, pars , evalScript);
			
			if(url == '/contents/addressMag/_tabAddressTree_Ajaxup.jsp'){
				extTreeDOM_CREATE();
			}
			
		}
		
		/*******************************/
		function extTreeDOM_CREATE(){
			//Ext.onReady(function(){
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
									dataUrl:'/system/code/addressGroupManagementTreeNodes/getAddressGroupCodeList.system?',///system/code/getCodeList.system?
									baseAttrs  : {"callbackNameElement" : "<%= request.getParameter("")%>",
												  "callbackCodeElement" : "<%= request.getParameter("")%>"},
									baseParams : {baseType:'tree'},
									paramsAsHash : true
								})
					});
					// set the root node
					var root = new Tree.AsyncTreeNode({
						text: '그룹관리',		 
						allowDrag : false,     
						allowDrop : false,    
						id : '00000',//00000_upper           
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

								//사람.							
								detailAddress(node.id);
							
							}else if(!node.isLeaf()){//부서

								
								//파라미터 
								var pars = {};
								pars = {
										'code' : node.id
								};
								
								_addressGroupDetail (pars);
							}
						}
					}
				//});
			}
			
			//부서 내용 상세보기
			function _addressGroupDetail (pars){
				//Ajax call
				var successTarget = "_right_address_contents";
				var url = "/content/address/getAddressGroupDetailAJU.dongbu?";
				var method = "get";
				var evalScript = false;
				
				new PAjax.Updater(successTarget , url, method, pars , evalScript);
			}
		
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
				$('group_code').value = obj.code;
				$('group_code_name').value = obj.codeName;
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
					var url = "/content/address/writeAddressGroupAJR.dongbu?";
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
					var url = "/content/address/modifyAddressGroupAJR.dongbu?";
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
		
		//Event.observe(document, 'dom:loaded', function(){addressTabMovement('/content/address/getAddressList.dongbu');});
		document.observe('dom:loaded', function(){addressTabMovement('/content/address/getAddressList.dongbu');});
	</script>
</head>
<body>
	
	<!-- 
	
		버그사항  : 1. 그룹에서 검색후 해당 그룹을 선택하면 TB_ADDRESS_GRP 을 검색하지 않고 CMTB_CODE 테이블을 검색해 엉뚱한 값이 저장된다.
				  2. CODE NAME Function 두가지 경우로 만들어야 한다. 
				    2.1 하나는 공통 코드 펑션
				    2.2 두번째는 어디레스 구릅 펑선으로 해야함.  
	 -->
	 
	 
	 
	<!-- 1. Header Contents-->
	<div>
		<%@ include file="/mainHeader.jsp" %>
	</div>
		
	<!-- 2. left Contents -->
	<div style="float: left;width: 230px">
		
	</div>
	
	<!-- 3. main Contents -->
	<div class="mainContents">
		
		<!-- TAB -->
		<div id="address_tab_div" style="width: 800px">
			<div style="float: left;width: 100px;height: 30px;border:1px solid #c7c7c7;cursor: pointer;" onclick="addressTabMovement('/content/address/getAddressList.dongbu')">AddressList</div>
			<div style="width: 100px;height: 30px;border:1px solid #c7c7c7;cursor: pointer;" onclick="addressTabMovement('/contents/addressMag/_tabAddressTree_Ajaxup.jsp')">AddressTree</div>
			<script type="text/javascript">
			</script>
		</div>
		
		
		<div>	
			<!-- 좌측내용 -->
			<div id="_left_address_contents"></div>
			
			<!-- 공간확보 -->
			<div style="width: 20px;float: left"></div>
			
			<!-- 우측내용 -->
			<div id="_right_address_contents">
				<div class="insert_table" style="float: left;width: 480px">
					<form name="_address_form" method="post" action="" >
						<div id="_addressDetail_div">
							<table width="100%">
								<tbody>
									<tr>
										<th>이름</th>
										<td> 
											<input id="user_name" name="user_name" alt="이름" value=""/>
											<input type="hidden" id="ads_mgt_no" name="ads_mgt_no" value="" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<th>그룹</th>
										<td>
											<input id="group_code" name="group_code" alt="그룹코드" value=""/>
											<input id="group_code_name" name="group_code_name" alt="그룹코드이름" value=""/>
											<button type="button" onclick="searchAddressGroup_popup('00000','upperCodeCallback');">검색</button>
										</td>
									</tr>
									<tr>
										<th>직책코드</th>
										<td>
											<code:codelist codeGroup="00029" isSelectBox="Y" attribute="id='position_code' name='position_code'" selectedInfo=""></code:codelist>
										</td>
									</tr>
									<tr>
										<th>회사명</th>
										<td><input id="company_name" name="company_name" value=""/></td>
									</tr>
									<tr>
										<th>집전화</th>
										<td><input id="phone" name="phone" value=""/></td>
									</tr>
									<tr>
										<th>휴대전화</th>
										<td>
											<!-- 분리시 사용
											<input type="text" id="cell_phone_start"  name="cell_phone_start"  value="" />
											<input type="text" id="cell_phone_middle" name="cell_phone_middle" value="" />
											<input type="text" id="cell_phone_end"    name="cell_phone_end"    value="" /> -->
											<input type="text" id="cell_phone" 	  name="cell_phone" 	   value="" />	
										</td>
									</tr>
									<tr>
										<th>우편번호</th>
										<td><input id="postcode" name="postcode" value=""/></td>
									</tr>
									<tr>
										<th>지역주소</th>
										<td> <input id="district_address" name="district_address" value=""/></td>
									</tr>
									<tr>
										<th>상세주소</th>
										<td><input id="detail_address" name="detail_address" value=""/></td>
									</tr>
									<tr>
										<th>이메일</th>
										<td> <input id="email" name="email" value="" alt="이메일"/></td>
									</tr>
									<tr>
										<th>메모</th>
										<td><textarea rows="" cols="" id="memo" name="memo" alt="메모"></textarea></td>
									</tr>
								</tbody>
							</table>
							
							<button type="button" onclick="addSellAnniversary();">추가</button>
							
							
							<table id="_anniversary_table" width="100%">
								<thead>
									<tr>
										<th>기념일 종류</th>
										<th>기념일 내용</th>
										<th>기념일 날짜</th>
									</tr>
								</thead>
								<tbody id="_anniversary_table_tbody">
									<tr>
										<td>
											<code:codelist codeGroup="00038" isSelectBox="Y" attribute="id='anver_type_code' name='anver_type_code'" selectedInfo=""></code:codelist><!-- 기념일 종류-->
										</td>
										<td><input id="anver_content"   name="anver_content"   alt="기념일 내용" value=""/></td>
										<td><input id="anver_date"      name="anver_date"      alt="기념일 날짜" onclick="new Calendar(this);" value="" readonly="readonly"/>
											<button type="button" onclick="removeSellAnniversary(this);">삭제</button>			
										</td>
									</tr>
								</tbody>
							</table>
							
							<div>
								<button type="button" onclick="_newSaveAddress();" id="_newSaveButton" style="display: none;">새연락처추가</button>
								<button type="button" onclick="saveAddress();"     id="_saveButton">저장</button>
							</div>
							
							
						</div>
					</form>
				</div>		
			
			</div>
		</div>
	
	</div>
	
	<form name="_addressSearch_form" method="get">
		<input type="hidden" id="user_name" name="user_name" alt="검색용" value=""/>
	</form>
</body>
</html>