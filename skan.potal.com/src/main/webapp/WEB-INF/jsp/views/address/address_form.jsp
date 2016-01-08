<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLib.jspf"%>

<script>
	var addressAuction = (function (){
		
		return {
			submit : function () {
				document.address_form.action = "${pageContext.request.contextPath}/address/address_insert";
				document.address_form.submit();
			}			
		}
	})();

	function callback() {
		setTimeout(function() {
	        $( "#message:visible" ).removeAttr( "style" ).fadeOut();
	      }, 5000 );
	}
	
	$(function() {
		$('#message').fadeIn().delay(1200).fadeOut();
		//$('#message').show('blind', {
	    //   direction : 'up'
	    //}, 500, callback); 
	});
	
</script>
<div class="alert alert-success" role="alert" id="message" style="display: none;"> </div>

<form name="address_form" id="address_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/address/address_insert" method="post">
<input type="hidden" id="hmMgNum" name="hmMgNum" value="${hmMngAddress.hmMhNum}">

	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">이름</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="name" name="name" placeholder="이름" value="${hmMngAddress.name}">
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="hmMngAddress.name" /></label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">전화번호</label>
		<div class="col-sm-10">
			<input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="전화번호" value="${hmMngAddress.hmAddressPhoneSet}">
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="hmAddressPhone.phoneNumber" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">이메일</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="hmEmail" name="hmEmail" placeholder="example@exam.com" value="${hmMngAddress.hmEmailInfoSet}"> 
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="hmEmailInfo.hmEmail" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">주소</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="hmAddInfo" name="hmAddInfo" placeholder="주소" value="${hmMngAddress.hmAddressInfoSet}" />
			<div class="has-error">
				<label class="control-label" for="inputSuccess1"><form:errors path="hmAddressInfo.hmAddInfo" /></label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" class="btn btn-default" onclick="addressAuction.submit();">저장</button>
			<button type="button" class="btn btn-danger" onclick="javascript:document.address_form.reset();">취소</button>
		</div>
	</div>

</form>