String.prototype.replaceAll = function(_findValue, _replaceValue) {
	 return this.replace(new RegExp(_findValue,"g"), _replaceValue);
};
function HTMLtoDOM(html){
	document.getElementById("xxxTempDivForTemplete").innerHTML = "<table>" + html + "</table>";
	return document.getElementById("xxxTempDivForTemplete").childNodes[0].childNodes[0].childNodes[0];
}

function imageFile_Check(fname){
	var check1 = "\\.(bmp|gif|jpg|jpeg|png)$";
	return (new RegExp(check1, "i")).test(fname);
}

// 
function slide(Id, interval, to) {	
    var obj = document.getElementById(Id);    
    var H, step = 5;
	var iconObj = document.getElementById("icon" + Id);
    if (obj == null) return;
    if (to == undefined) { // user clicking
        if (obj._slideStart == true) return;
        if (obj._expand == true) {
            to = 0;
            obj.style.overflow = "hidden";
            
            //iconObj.src = ICON_CLOSE;
        } else {
            slide.addId(Id);
            for(var i=0; i < slide.objects.length; i++) {
                if (slide.objects[i].id != Id && slide.objects[i]._expand == true) {
                    slide(slide.objects[i].id);
                }
            }

            obj.style.height = "";
            obj.style.overflow = "";
            obj.style.display = "block";
            to = obj.offsetHeight; // �̰��̰�
            obj.style.overflow = "hidden";
            obj.style.height = "1px";
            
            //iconObj.src = ICON_OPEN;
        }
        obj._slideStart = true;
    }
   
    step             = ((to > 0) ? 1:-1) * step;
    interval         = ((interval==undefined)?1:interval);

    obj.style.height = (H=((H=(isNaN(H=parseInt(obj.style.height))?0:H))+step<0)?0:H+step)+"px";   
  
    if (H <= 0) {
        obj.style.display = "none";
        obj.style.overflow = "hidden";
        obj._expand = false;
        obj._slideStart = false;
    } else if (to > 0 && H >= to) {
        obj.style.display = "block";
        obj.style.overflow = "visible";
        obj.style.height = H + "px";
        obj._expand = true;
        obj._slideStart = false;
    } else {
        setTimeout("slide('"+Id+"' , "+interval+", "+to+");", interval);
    }
}
slide.objects = new Array();
slide.addId = function(Id)
{
    for (var i=0; i < slide.objects.length; i++) {
        if (slide.objects[i].id == Id) return true;
    }
    slide.objects[slide.objects.length] = document.getElementById(Id);
}


/*******************************************************************************
 * tbl : 병합할 대상 table object startRow : 병합 시작 row, title 한 줄일 경우 1 cNum : 병합 실시할
 * 컬럼번호, 0부터 시작 length : 병합할 row의 길이, 보통 1 add : 비교할 기준에 추가할 컬럼번호 A | 1 B | 1 을
 * 서로 구분하고 싶다면, add에 0번째 컬럼을 추가
 ******************************************************************************/
function mergeCell(tbl, startRow, cNum, length, add) {
	var isAdd = false;
	if (tbl == null)
		return;
	if (startRow == null || startRow.length == 0)
		startRow = 1;
	if (cNum == null || cNum.length == 0)
		return;
	if (add == null || add.length == 0) {
		isAdd = false;
	} else {
		isAdd = true;
		add = parseInt(add);
	}
	cNum = parseInt(cNum);
	length = parseInt(length);

	rows = tbl.rows;
	rowNum = rows.length;

	tempVal = '';
	cnt = 0;
	startRow = parseInt(startRow);

	for (i = startRow; i < rowNum; i++) {
		curVal = rows[i].cells[cNum].innerHTML;
		if (isAdd)
			curVal += rows[i].cells[add].innerHTML;
		if (curVal == tempVal) {
			if (cnt == 0) {
				cnt++;
				startRow = i - 1;
			}
			cnt++;
		} else if (cnt > 0) {
			merge(tbl, startRow, cnt, cNum, length);
			startRow = endRow = 0;
			cnt = 0;
		} else {
		}
		tempVal = curVal;
	}

	if (cnt > 0) {
		merge(tbl, startRow, cnt, cNum, length);
	}
}

/*******************************************************************************
 * mergeCell에서 사용하는 함수
 ******************************************************************************/
function merge(tbl, startRow, cnt, cellNum, length) {
	rows = tbl.rows;
	row = rows[startRow];

	for (i = startRow + 1; i < startRow + cnt; i++) {
		for (j = 0; j < length; j++) {
			rows[i].deleteCell(cellNum);
		}
	}
	for (j = 0; j < length; j++) {
		row.cells[cellNum + j].rowSpan = cnt;
	}
}

var FloaterUtil = function(psId, psTop) {
	var id = psId;
	var voFloater = null;
	var interId = null;
	var diffY = 0;
	var lastScrollY = 1;
	var startY = 0;
	
	this.init = function() {
		voFloater = document.getElementById(id);
		startY = psTop;
		this.interval();
	};

	this.interval = function() {
		interId = setInterval(this.floating, 1);
	};
	
	this.floating = function() {
		if (document.body.scrollTop === 0) {
			diffY = document.documentElement.scrollTop;
		} else {
			diffY = document.body.scrollTop;
		}
		if (diffY !== lastScrollY) {
            var percent = .1 * (diffY - lastScrollY);
            if (percent > 0) {
				percent = Math.ceil(percent);
			} else {
				percent = Math.floor(percent);		                
			}
            startY += percent;
            voFloater.style.top = startY + "px";	
			lastScrollY += percent;
	    }
	};
};

/**
 * 이메일 정규식 검사
 * @param email_address  : ex ) mycup@nate.com
 * @returns {Boolean}
 */
function isValidEmail_check(email_address){  
	// 이메일 주소를 판별하기 위한 정규식  
    var format = /^[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\.[0-9a-zA-Z-]+)*$/;  
      
    // 인자 email_address를 정규식 format 으로 검색  
    if (email_address.search(format) != -1) {  
        // 정규식과 일치하는 문자가 있으면 true  
        return true;  
    } else {  
        // 없으면 false 
    	alert("올바른 E-mail 형식이 아닙니다.");
        return false;  
    }  
}  



/**
 * AjaxUtil 
 * @param url
 * @param methodType   : get 혹은 post
 * @param asynchronous : 동기(true) 비동기(false)
 * @param params
 * @param callbackmethod
 * @param responseType : XML / TEXT / JSON 
 * @returns
 * 
 * @example 
 * 			new AjaxUtil(url, methodType, asynchronous, params ,callbackmethod , responseType);
 */
var AjaxUtil = function(url, methodType, asynchronous, params ,callbackmethod , responseType){

	var actionUrl = url;
	var xmlHttp = new XMLHttpRequest();
	
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 0){
			//UNINITIALIZED :: open()이 아직 호출되지 않은 상태
		}else if(xmlHttp.readyState == 1){
			//LOADING :: send()가 호출되지 않은 상태 (load중)
		}else if(xmlHttp.readyState == 2){
			//LOADED::send() 호출 완료하여 header 와 status 사용이 가능한 상태이며, load완료
			
		}else if(xmlHttp.readyState == 3){
			//INTERACTIVE :: 일부 data 를 받을 수 있는 상태로 처리중
			
		}else if(xmlHttp.readyState == "4"){
			//COMPLETE :: 모든 데이터를 받을 수 있는 상태로 완료
			if(xmlHttp.status == "200"){
				this.handleResponse(xmlHttp, callbackmethod, responseType);
			}else if(xmlHttp.status == "404"){
				
			}else if(xmlHttp.status == "500"){
				
			}
		}
	};
	
	if(methodType.toUpperCase() == "GET"){
		
		xmlHttp.open("get" , actionUrl +"?" + params , asynchronous);
		xmlHttp.send(null);
		
	}else if(methodType.toUpperCase() == "POST"){
		
		xmlHttp.open("POST", actionUrl, asynchronous);
	
		//Post 방식에서 다음 헤더 지정
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.setRequestHeader("Content-length", params.length);
		xmlHttp.setRequestHeader("Connection", "close");
		xmlHttp.send(params);  //parameter 를 전송
		
	}
};


AjaxUtil.prototype = {
		handleResponse : function (response, callbackmethod){
			var resJSON = eval('(' + response + ')'); //JSON
			var resXML	= response.responseXML;		  //XML
			var resText = response.responseText;	  //Text	
			
			callbackmethod(response);
		},
		
		uninitialized : function (){//readyState 0
			
		},
		
		open : function (){//readyState 1
			
		},
		
		sent : function (){//readyState 2
			
		},
		
		receiving : function (){//readyState 3
			
		},
		
		loaded : function(){//readyState 4
			
		}
};

/**
 * Prototype Ajax.Request
 * @param url
 * @param method
 * @param pars
 * @param asynchronouse
 * @param callbackMethod
 * @returns
 */
var PAjax = function (){}; 
PAjax.Request = function (url, method, pars, asynchronous, callbackMethod){
	try {
		new Ajax.Request(url,{
			method: method, 
			parameters: pars, 
			asynchronous : asynchronous,
			encoding : 'UTF-8',
			onLoading : function(response){
				
			},
			onSuccess  : function (response , xjson){
				callbackMethod(response , xjson);
			},
			onFailure : function (response , xjson){
				alert('동작중 오류가 발생하였습니다. 재시도 하십시요.');
			}
		});
	} catch (e) {
		alert(e.message);
	}
};
/**
 * Prototype Ajax.Updater
 * @param successTarget : 타켓
 * @param url			: 호출 유알엘
 * @param method		: 'get' 혹은 'post'
 * @param pars			: 전달할 파라미터 인자값
 * @param evalScript	: 데이터상의 <script>내용을 어떻게 처리 할것인지 'true', 'false'
 */
PAjax.Updater = function(successTarget , url, method, pars , evalScript, asynchronous){
	
	var asyn = isNull(asynchronous);
	
	if(asyn == '' ){
		asyn = false;
	}
	try {
		new Ajax.Updater({success:successTarget},url,
				{method : method,
				parameters : pars,
				encoding : 'UTF-8',
				evalScript :evalScript,
				onLoding:function(){},
				asynchronous : asyn,
				//onSuccess:function(){},
				
				//onComplete:function(){alert('성공');},
				
				onFailure:function(){
						alert('호출에 실패 하였습니다.');
					}
				
				});
	} catch (e) {
		alert(e.message);
	}
};


function isNull(obj){
	if(obj == 'undefirned' || obj == null || obj || ''){
	
		return '';
	}
	
	return obj;
}



/**
 * 파일업로더 공통 JS
 * @returns
 */
var attachCount = 0;
var AttachFileUpload = function(){
	
};

AttachFileUpload.prototype = {
	//파일을 추가할수 있는 Node 생성
	attachAddRow : function() {
		var fileDiv   = $("fileDiv");
		var childDiv  = new Element("div");
		var inputFile = new Element("input",{"type":"file", "name":"attach_File"+attachCount , "id":"attach_File"+attachCount});
		var divDelButton = new Element("div",{}).observe("click",function(){new AttachFileUpload().attachDelRow(this);});
		var comAtag1 = new Element("a",{});
	
		var span1 = new Element("span",{"class":"Btn01Start"});
		var span2 = new Element("span",{"class":"Btn01Center"}).update("삭제");
		var span4 = new Element("span",{"class":"Btn01End"});
	
		inputFile.setAttribute("class","input_file");
		divDelButton.setAttribute("class","Btn03");
	
		span1.setAttribute("class","Btn01Start");
		span2.setAttribute("class","Btn01Center");
		span4.setAttribute("class","Btn01End");
		
		comAtag1.appendChild(span1);
		comAtag1.appendChild(span2);
		comAtag1.appendChild(span4);
	
		divDelButton.appendChild(comAtag1);
		childDiv.appendChild(inputFile);
		childDiv.appendChild(divDelButton);
		fileDiv.appendChild(childDiv);
		attachCount++;
	} ,
	
	//파일 추가 DIV 삭제
	attachDelRow : function(obj){
		var fileObj = obj;
		$(fileObj).up().remove();
	} ,
	
	//첫번째 파일업로드 내용 리셋
	attacheFileReset : function(){
		document.getElementById("attach_File").select();
		document.selection.clear();
	} ,
	
	//View 파일 삭제 : 실제 파일을 삭제하지는 않는다. 현재 선택된 Node 만 삭제한다.
	attachFileDel : function(obj){
		var fileObj = obj;
		if(confirm("정말 삭제하시겠습니까?")){
			
			$(fileObj).remove();
			
			var file_id = obj.id;
			
			deleteFileList(file_id);
		}
	}
	
};



/**
 * 화면에서 삭제된 파일의 file_id 값을 deleteFileAr에 저장. 
 * @param fileId
 * @returns
 */
var deleteFileAr = new Array();
var deleteFileList = function(fileId){
	deleteFileAr.push(fileId);
	document.getElementById("deletefilelist").value = deleteFileAr;
};


/**
 * 파일다운로드
 * 사용방법 :
 * 		   <div onclick="fileDownload();" id="${items.fileId}" style="cursor: pointer;"> 
 *         
 * @return
 */
function fileDownload(){
	var fileId  = event.srcElement.id;

	if(fileId != ""){
		if($$("#_targetDownload").length == 0)
			document.body.insertAdjacentHTML('beforeEnd', "<iframe id=\"_targetDownload\" src=\"\" style=\"display:none\"></iframe>");
    	$("_targetDownload").src = "/servlet/ReposFileDownload?fileId=" + fileId;
	}
}

/**
 * 팝업 윈도우 가운데 정렬
 * @param _width 
 * @param _height 
 * @param openUrl
 * @param winName
 * @return
 */
function openWinCenter(_width , _height , openUrl , winName){
    var winl = (screen.width-_width)/2;
    var wint = (screen.height- _height )/2;
    
    var style 	   = new Array();
    style.push("height=" +_height);style.push("width="  +_width);
    style.push("top=" + wint);      style.push("left=" + winl);
    style.push("toolbar=no");      style.push("directories=no");
	style.push("status=no");       style.push("menubar=no");
	style.push("scrollbars=yes");  style.push("resizable=no");
	style = style.join();

	var winObj = window.open(openUrl,winName,style);
    return winObj;
}

/**
 * 팝업 윈도우 가운데 정렬(팝업 옵션 설정가능)
 * @param _width 
 * @param _height 
 * @param openUrl
 * @param winName
 * @return
 */
function openWinCenterOption(_width , _height , openUrl , winName,toolbar,directories,status,menubar,scrollbars,resizable){
    var winl = (screen.width-_width)/2;
    var wint = (screen.height- _height )/2;
    
    var style 	   = new Array();
    style.push("height=" +_height);style.push("width="  +_width);
    style.push("top=" + wint);      style.push("left=" + winl);
    style.push("toolbar=" + toolbar);      style.push("directories=" + directories);
	style.push("status=" + status);       style.push("menubar=" + menubar);
	style.push("scrollbars=" + scrollbars);  style.push("resizable=" + resizable);
	style = style.join();
	
	var winObj = window.open(openUrl,winName,style);
    return winObj;
}


/**
 * mouse over 시 색상 변경
 * @param state
 * @return
 */
function onmouse(state){

	if (state=="over"){
		window.event.srcElement.parentElement.style.backgroundColor="#ffffe0";
    	window.event.srcElement.style.cursor  = "pointer";
	} else if(state == "down"){
		window.event.srcElement.parentElement.style.backgroundColor="#90EE90";
	} else {
		window.event.srcElement.parentElement.style.backgroundColor="#ffffFF";
	}
	
}

/**
 * 지정된 테이블의 td Change
 * addInsertBefore
 * @param tableId or table TbodyId
 * @param tdCount
 * @return
 */
var addInsertBefore = function(tableId,tdCount){
		var tableObj = $(tableId);
		var tr = new Element("tr",{});
		var td = [tdCount];
		for(var i=0 ; i < tdCount ; i++ ){
			new Effect.Highlight(td[i] = new Element("td",{}),{startcolor :'#ffff99',endcolor:'#ffffff'});//.update(i);
		}
		for(var i=0 ; i < tdCount ; i++ ){
			tr.appendChild(td[i]);
		}
		tableObj.insertBefore(tr,tableObj.firstChild);
	return td;
};

/**
 * 지정된 테이블에 노드 추가 (td add).
 * appendChild
 * @param tableId 테이블 오브첵트 아이디값
 * @param tdCount 추가될 TD 갯수
 * @return 현재 생성된 td를 return 한다. 이값으로 호출한곳에서 나머지 값 설정
 */
var addAppendChild = function(tableId , tdCount){
	var tr = new Element("tr",{});
	var td = [tdCount];
	for(var i=0 ; i < tdCount ; i++ ){
		new Effect.Highlight(td[i] = new Element("td",{})/*.update(i)*/,{startcolor :'#ffff99',endcolor:'#ffffff'});
	}
	for(var i=0 ; i < tdCount ; i++ ){
		tr.appendChild(td[i]);
	}
	
	//tbody 테크를 찾아 노드를 추가한다.
	$(tableId).getElementsByTagName("tbody")[0].appendChild(tr);
	
	return td;
};

/**
 * 선택된 체크박스 노드(cell) 삭제  
 * @param tableId
 * @param checkBoxName
 * @return
 */
var RemoveNodeChoose = function(tableId, checkBoxName){
	var checkBoxObj = $$('input[name="'+checkBoxName+'"]');
	
	for(var i=0 ; i < checkBoxObj.length ; i++ ){
		if(checkBoxObj[i].checked){
			var td 	  = checkBoxObj[i].parentNode;
			var tr 	  = td.parentNode;
			var table = tr.parentNode;
			table.removeChild(tr);
			//$(tableId).removeChild(tr);
		}
	}
};

/**
 * 클릭된 노드 삭제
 * @param element
 * @returns
 */
var RemonveNode = function(element){
	var removeElement = element.parentNode.parentNode;
	//new Effect.Highlight( removeElement ,{startcolor :'#ffff99',endcolor:'#ffffff'});
	
	var lengthChekObj = removeElement.parentNode;
	
	//첫번째 행은 삭제 하지 않는다. 
	//첫번째 행도 삭제 할 경우 조건문(if) 삭제
	if(lengthChekObj.getElementsByTagName('tr').length > 1){
		removeElement.removeNode(true);
	}
	
};


/**
 * @function            : 달력 
 * @param objIdorName   : element Name name or Id name
 * @param callbackFname : call back function name
 * @return
 */
function calenderOpenpopup(objIdorName,callbackFname){
	var params = new Array();
	params.push("element=test");
	params.push("callback="+callbackFname);
	params.push("objIdorName="+objIdorName);
	params = params.join("&");
	
	var url = "/util/CM_Calender.jsp?"+params;
	var style = "height=200,width=200,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no";
	openWinCenterOption("190" , "200" , url , "calenderOpenpopup","no","no","no","no","no","no");
}
/**
 * calenderCallback
 * @param objIdorName
 * @param dateStr
 * @return
 */
function calenderOpenCallback(objIdorName , dateStr){
	$(objIdorName).value=dateStr;
}


/**
 * dynamic calendar  
 * @param obj			 : 선택된 element
 * @param tableId		 : 테이블 아이디 값 혹은 tbody id값
 * @param objIdorName    : return된 값을 넣고자 하는 element id 혹은 name(현재는 id 값으로만 가능)
 * @param callbackFname  : callbackFunctionName 으로 콜백 기능을 새로 구현하지 않는한 "dynamicCalendarCallback" 으로 기술 한다.
 * @return
 */
function dynamicCalendar(obj , tableId , objIdorName, callbackFname){
	var calendarIdx = 0;
	var date_ymd = $$('input[name="'+objIdorName+'"]');
	var choiceNode = $(tableId).getElementsByTagName("img");
	
	for(var i=0 ; i< choiceNode.length ;  i++){
		if(obj == choiceNode[i]){
			break;
		}
		calendarIdx++;
	}
	var params = new Array();
	params.push("element=test");
	params.push("callback="+callbackFname);
	params.push("objIdorName="+objIdorName);
	params.push("calendarIdx="+calendarIdx);
	
	params = params.join("&");
	
	var url = "/util/CM_Calender.jsp?"+params;
	//var style = "height=200,width=200,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no";
	//window.open(url,"calenderOpenpopup",style);
	openWinCenterOption("190" , "200" , url , "calenderOpenpopup","no","no","no","no","no","no");
}
/**
 * dynamicCalendarCallBack
 * @param objIdorName
 * @param dateStr
 * @param calendarIdx
 * @return
 */
function dynamicCalendarCallback(objIdorName , dateStr, calendarIdx){
	document.getElementsByName(objIdorName)[calendarIdx].value = dateStr;
}


/**
 * 필수항목 체크 fn
 * @param obj : element 의 id 값을 적어준다.
 * @sample : var obj = ['creator','modifier','sc_mng_no'];
 * 			 다음 function 을 사용할 경우 각 element 에는 꼭 alt 를 달아주어야 한다.
 * @return
 */ 
function validationCheck(obj){
	var checkStatus = true;
	for ( var i = 0; i < obj.length; i++) {
		if($(obj[i]).value == "" ){
			alert("필수 항목 <"+$(obj[i]).alt+"> 을 입력해주세요.");
			checkStatus = false;
			break;
		}
	}
	return checkStatus;
}


/**
 * 공통 코드 카테고리 팝업
 * @param childCode			  : 최상위 Code값이며 항상 뒤에 "_child를 붙여 준다.)
 * 		                   	    ex  "00017_upper"
 * @param callbackFunction    : 콜백 받아 사용할 function 명.
 * @param callbackNameElement : baseParameter ElementName 값으로써 callback Fn 사용하면 값 넣지 않아도됨
 * @param callbackCodeElement : baseParameter ElementCode 값으로써 callback Fn 사용하면 값 넣지 않아도됨
 * @param language			  : 언어팩 종류 KOR , ENG, CHN / Default KOR 적용됨
 * @return
 */	
function searchCodeCategoryPopup(childCode,callbackFunction,callbackNameElement,callbackCodeElement){
	var params = {
			"childCode" : childCode,
			"callbackFunction" : callbackFunction,
			"callbackNameElement" : callbackNameElement,
			"callbackCodeElement" : callbackCodeElement
			};
	var url = "/system/code/codeCategory.jsp?"+$H(params).toQueryString();
	openWinCenterOption("290" , "390" , url , "searchBizCategoryPopup","no","no","no","no","no","no");
}

/**
 * 연락처 그룹 카테고리 팝업
 * @param childCode			  : 최상위 Code값이며 항상 뒤에 "_child를 붙여 준다.)
 * 		                   	    ex  "00017_upper"
 * @param callbackFunction    : 콜백 받아 사용할 function 명.
 * @param callbackNameElement : baseParameter ElementName 값으로써 callback Fn 사용하면 값 넣지 않아도됨
 * @param callbackCodeElement : baseParameter ElementCode 값으로써 callback Fn 사용하면 값 넣지 않아도됨
 * @param language			  : 언어팩 종류 KOR , ENG, CHN / Default KOR 적용됨
 * @return
 */	
function searchAddressGroup_popup(childCode,callbackFunction,callbackNameElement,callbackCodeElement){
	var params = {
			"childCode" : childCode,
			"callbackFunction" : callbackFunction,
			"callbackNameElement" : callbackNameElement,
			"callbackCodeElement" : callbackCodeElement
			};
	var url = "/contents/addressGroupManagement/addressGroupCategory.jsp?"+$H(params).toQueryString();
	openWinCenterOption("290" , "390" , url , "searchBizCategoryPopup","no","no","no","no","no","no");
}

/**
 * 체크된 행 이동 
 * @param from		이동하려는 tbody id
 * @param to		이동될 tbody ㅑㅇ
 * @return
 */
function fncMove( from, to ){
    var fm = document.getElementById(from);
    var to = document.getElementById(to);

   // 멀티로 옮길때 위에서부터 옮기면 아래 값이 옮겨 지면서 순서가 바뀌어서
   // 아래서부터 역으로 올라오도록 변경 하시면 됩니다.
    for( var i=(fm.childNodes.length-1); i>=0; i-- ){
        var node1 = fm.childNodes[i];
        if(node1.nodeName=='TR'){
            for(var j=0; j<node1.childNodes.length; j++){
                var node2 = node1.childNodes[j];
                if(node2.nodeName=='TD'){
                    for(var k=0; k<node2.childNodes.length; k++){
                        var node3 = node2.childNodes[k];
                        if(node3.nodeName=='INPUT'){
                            if(node3.checked){
                                node3.checked = false;
                                to.appendChild(node1);
                            }
                        }
                    }
                }
            }
        }
    }
}


/**
 * Button DOM creation 
 * @param textValue : 버튼 이름
 * @return
 */
function createDOMButton(textValue){
	var div = new Element("div",{"class":"Btn03"});
	var aTag = new Element("a");
	var span0 = new Element("span",{"class":"Btn01Start"});
	var span1 = new Element("span",{"class":"Btn01Center"}).update(textValue);
	var span2 = new Element("span",{"class":"Btn01End"});

	div.setAttribute("class","Btn03");
	span0.setAttribute("class","Btn01Start");
	span1.setAttribute("class","Btn01Center")
	span2.setAttribute("class","Btn01End");

	aTag.appendChild(span0);
	aTag.appendChild(span1);
	aTag.appendChild(span2);
	div.appendChild(aTag);
	
	return div; 
}

/**
 * 검색 Element 초기화
 * @return
 */
function searchElementInitialization(){
	var inputElement = document.getElementsByTagName("input");
	for ( var i = 0; i < inputElement.length; i++) {
		inputElement.item(i).value = "";
	}
	
	var selectElement = document.getElementsByTagName("select");
	for ( var i = 0; i < selectElement.length; i++) {
		var selectBox = selectElement[i];
		selectBox[0].selected = "seledted";
	}
	
	/*inputElement.each(function(element){
		element.value="";
	});*/
}

/**
*
* 첨부문서 ACL 요청
*/

function requestACL(biz_mgt_no, biz_code, empno){
	
	var params = "biz_mgt_no="+ biz_mgt_no + "&biz_code=" + biz_code+ "&empno=" + empno;
	//alert(params);

	var ajax_url = "/ecms/contOrgDoc/requestACL.law";
	var ajax = new Ajax.Request(
		ajax_url,
		{
			method:"get",
			parameters: params,
			asynchronous:false,
			onComplete:function(result){
				try{
					var o = eval('(' + result.responseText + ')');
					if(o.result == 'ok'){
						alert('열람신청하였습니다.');
					}else{
						alert(o.resultMsg);
					}
				}catch(e){alert(e.message)}
				
				

			},
			onFailure:function(result){
				alert('실패 하였습니다!!! \n' + result.responseText);
			}
		}
	);

}



//엑셀다운로드 관련
function excelDownStart(excelTitle,tableId,imageHide){

	var tableObj = document.getElementById(tableId);
	IframeExcelDown.excelDownLoad(excelTitle,tableObj.outerHTML,imageHide);

}

//인쇄페이지 - 계약검토의견서, 계약원본등록서, 법률자문의견서
function popPrintablePage(url, mgt_no){
	
	try{
		
		var win = window.open("", "winPrintable", "width=900,height=700,menubar=yes,status=no,toolbar=no,channelmode=no,scrollbars=yes,left=200,top=100");
		
		var f = document.createElement("FORM");
		f.setAttribute("name", "frmPopPrintablePage");
		f.setAttribute("action", url);
		f.setAttribute("method", "post");
		f.setAttribute("target", "winPrintable");
		
		//alert(url + ":" + mgt_no);
		
		var e = document.createElement("input");
		e.setAttribute("type", "hidden");
		e.setAttribute("name", "biz_mgt_no");
		e.setAttribute("value", mgt_no);
		
		f.appendChild(e);
		document.appendChild(f);
		f.submit();	
	}catch(e){
		alert(e.message);
	}
	
}

/**
 * 숫자만 사용 가능
 * @example < input type="text" name="TEL" style="ime-mode:Disabled;" onKeyPress="isNumber();" /> 
 */
function isNumber(){
var keyCode = event.keyCode;
	if (keyCode < 48 || keyCode > 57){
		alert("문자는 사용할 수 없습니다."+"["+keyCode+"]");
		event.returnValue=false;
	}
}
 



