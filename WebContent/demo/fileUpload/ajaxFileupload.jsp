<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/include/includeJavaScript.jsp" %>
<script type="text/javascript">

	/*이미지 업로드*/
	document.observe("dom:loaded", function() {
		var button1=$('fileButton1'), fileId1=$('beforImg'), img1=$('img1');
		var button2=$('fileButton2'), fileId2=$('afterImg'), img2=$('img2');
		//이미지 1번
		new AjaxUpload(button1,{
				action: '/common/repository/imgUpload.common', 
				name: 'userfile1',
				onSubmit : 
					function(file, ext){
						button1.src="/images/ajax-loader.gif";
						this.disable();
				},
				onComplete: 
					function(file, response){
						this.enable();
						var res = eval('(' + response + ')');
						fileId1.value = res.fileId;
						alert(res.fileId);
						button1.src="/images/ico_search_03.gif";
						img1.src = "/servlet/ImgView?fileId="+res.fileId;
				}
		});
		//이미지 2번
		new AjaxUpload(button2,{
			action: '/common/repository/imgUpload.common', 
			name: 'userfile2',
			onSubmit : 
				function(file, ext){
					button2.src="/images/ajax-loader.gif";
					this.disable();
			},
			onComplete: 
				function(file, response){
					this.enable();
					var res = eval('(' + response + ')');
					fileId2.value = res.fileId;
					button2.src="/images/ico_search_03.gif";
					img2.src = "/servlet/ImgView?fileId="+res.fileId;
			}
		});		
	});
	
</script>
</head>
<body>
	<form>
		<div>
			HELLO WORLD
			<table>
				<tr>
					<td>
						<input type="hidden" id="beforImg" />
						<img id="img1" src="/servlet/ImgView?fileId=660667ef-1415-4fc4-9768-a48daee97ee6" width="180" height="180" style="CURSOR: pointer;" />
						<img id="fileButton1" align="middle" src="/images/ico_search_03.gif" style="CURSOR: pointer;" />		
					</td>
					<td>
						<input type="hidden" id="afterImg"/>
						<img  id="img2" src="/servlet/ImgView?fileId=9cb059e4-92f7-40c5-bd6f-a86c792fefd3" width="180" height="180" style="CURSOR: pointer;" />
				        <img  id="fileButton2" align="middle" src="/images/ico_search_03.gif" style="CURSOR: pointer;" />
					</td>
				</tr>
			</table>
		</div>	
			<div>
				
	        </div>
	</form>
</body>
</html>