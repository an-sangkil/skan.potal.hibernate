<!--
	* fileName   : file_name
	* createDate : 2011. 3. 24. 오후 3:03:24
	* CreateUser : skan
	* Document   : todo
	*
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript">
	//댓글 등록
	function _writeComment(){
		
		//필수할목 체크 아이템 설정
		var obj = ['comment_content'];
		
		//필수항목체크
		if(validationCheck(obj)){
			
			//파라미터 
			var pars = {};
			pars = {
					'content' : $F('comment_content'),
					'parent_seq' : $F('seq'),
					'bbsid' : $F('bbsid')
			};
			
			//Ajax call
			var url = "/common/comment/writeComment.common?";
			var method = 'POST';
			var asynchronouse = true;
			var callbackMethod = _writeResponse;
			
			new PAjax.Request(url, method, pars, asynchronouse, callbackMethod);
		}
	}
	
	//댓글등록 CallBack response 함수		
	function _writeResponse(originalResponse){
		try {
			var resultObj = eval('('+originalResponse.responseText+')');
			if(resultObj.result == "SUCCESS"){
				
				// TODO : 생각해볼점
				// 1. 만약  A라는 사람과 B라는 사람이 동시에 접속해서 댓글을 등록한경우는? 어떻게 처리?
				
				//등록된 내용을 Add on 한다.
				var comment = resultObj.comment;
				var targetElementId = new Array();
				targetElementId.push(comment.bbsid);
				targetElementId.push(comment.parent_seq);
				targetElementId.push(comment.seq);
				targetElementId = targetElementId.join('_');
			
				var divObj = $('_comment_list_div');
				var comment_div    = new Element('div',{id:targetElementId , name:''}).update(comment.content);
				var delete_span	   = new Element('span',{id:targetElementId , name:'' , style : 'cursor : pointer;'}).observe("click",function(){
					_deleteComment(comment.bbsid,comment.parent_seq,comment.seq);}).update('삭제');
				
				comment_div.appendChild(delete_span);
				divObj.appendChild(comment_div);
				$('comment_content').value='';
			}	
		} catch (e) {
			alert(e);
		}
	}
	
	
	//댓글삭제
	function _deleteComment(boardid , parent_seq, seq ){
		
		var pars ={};
		
		pars = {
				'bbsid' : boardid, 
				'parent_seq' : parent_seq,
				'seq' : seq
		};
	
		
		var url = '/common/comment/deleteComment.common?';
		var method = 'POST';
		var asynchronouse = true;
		var callbackMethod = _deleteResponse;
		
		
		if(confirm("정말 삭제 하시겠습니까?")){
			new PAjax.Request(url, method, pars, asynchronouse, callbackMethod);	
		}
	}
	
	
	//삭제댓글 Callback 메소드
	function _deleteResponse(response, pars){
		try {
			var resultObj = eval('('+response.responseText+')');
			if(resultObj.result == "SUCCESS"){
				var comment = resultObj.comment;
				
				var targetElementId = new Array();
				targetElementId.push(comment.bbsid);
				targetElementId.push(comment.parent_seq);
				targetElementId.push(comment.seq);
				targetElementId = targetElementId.join('_');
				
				//TODO : 해당 DIV 삭제 혹은 reflash
				$(targetElementId).remove();
			}
		} catch (e) {
			alert(e.message);
		}
	}

</script>
<div>
	<div id="_comment_div">
		<textarea rows="" cols="" id="comment_content"></textarea><button type="button" onclick="_writeComment()">댓글등록</button>
	</div>
	<div id="_comment_list_div">
		<c:choose>
			<c:when test="${ !empty commentList}">
				<c:forEach var="items" items="${commentList}" varStatus="status">
					<div id="${items.bbsid}_${items.parent_seq}_${items.seq}">${items.content}
						<span onclick="_deleteComment('${items.bbsid}','${items.parent_seq}','${items.seq}')" style="cursor : pointer;">삭제</span>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>댓글이 없습니다.</c:otherwise>	
		</c:choose>
	</div>
</div>
