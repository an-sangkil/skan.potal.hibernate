<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<script>
	var addressAuction = (function (){
		
		return {
			submit : function () {
				document.code_form.action = "${pageContext.request.contextPath}/code/code_insert";
				document.code_form.submit();
			},
			upperCode : function () {
				$('#upperCode').val($('#upperCodeData').val());
				$("#myModal").modal('hide');
			}
		}
	})();

	function callback() {
		setTimeout(function() {
	        $( "#message:visible" ).removeAttr( "style" ).fadeOut();
	      }, 5000 );
	}
	
	$(function() {
		if('${message}' == 'SUCCESS'){
			$('#message').fadeIn().delay(1200).fadeOut();
			//$('#message').show('blind', {
		    //   direction : 'up'
		    //}, 500, callback); 
		} else if('${message}' == 'FAIL') {
			$('#message').show();
		}
		
	});
	
	
</script>

<div class="alert alert-success" role="alert" id="message" style="display: none;">
	<c:if test=""></c:if>
	<c:choose>
		<c:when test="${message eq 'SUCCESS'}">성공하였습니다.</c:when>
		<c:otherwise>실패하였습니다.</c:otherwise>
	</c:choose>

</div>

<form name="code_form" id="code_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/code/code_insert" method="post">
<%-- <input type="hidden" id="codeMgtNo" name="codeMgtNo" value="${cmtbCode.cmtbCodeId.codeMgtNo}"> --%>
<input type="hidden" id="code" 		name="code" 	 value="${cmtbCode.cmtbCodeId.code}">
<input type="hidden" id="codeSeq" 	name="codeSeq" 	 value="${cmtbCode.codeSeq}">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">상위코드</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="upperCode" name="upperCode" placeholder="상위코드" value="${cmtbCode.upperCode}" readonly="readonly">
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbCode.upperCode" /></label>
			</div>
		</div>
		<div class="col-sm-2">
		<button type="button" class="btn btn-info" id="myBtn">선택하세요</button> 
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">코드명</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="codeName" name="codeName" placeholder="코드명" value="${cmtbCode.codeName}" >
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbCode.codeName" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">코드설명</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="" cols="" id="codeComment" name="codeComment">${cmtbCode.codeComment}</textarea>
			
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="cmtbCode.codeComment" /></label>
			</div>
		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default" onclick="">저장</button>
			<button type="button" class="btn btn-danger" onclick="javascript:document.code_form.reset();">취소</button>
		</div>
	</div>

</form>


<div class="modal fade" id="myModal" role="dialog"
	aria-labelledby="gridSystemModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel">상위코드 입력</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<label for="recipient-name" class="control-label">상위코드:</label>
							<select class="form-control" id="upperCodeData">
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" onclick="addressAuction.upperCode()">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script>
	$(document).ready(function() {
		$("#myBtn").click(function() {
			$("#myModal").modal();
			$.ajax({
				url : '${pageContext.request.contextPath}/code/code_list',
				dataType : 'json',
				accepts : {
					xml : 'text/xml',
					text : 'text/plain'
				}
			}).done(function(data) {
				$('#upperCodeData').find('option').remove();
				$(data).each(function (index, obj){
					$('#upperCodeData').append('<option value='+obj.cmtbCodeId.code+'>' + obj.codeName +'</option>')
				})
			})
		});
	});
</script>
