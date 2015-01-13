<!DOCTYPE html>
<html>
	<head>
		
	
	</head>

	<body>
		<button type="button">G0</button>
		<input type="text" id="userName" value="" />
	</body>
	<script type="text/javascript">
		
	var button  = document.querySelector("button");
		button.addEventListener("click",function (){ 
			var userName = document.querySelector("button").value;
			location.href = "/test2.do?userName="+ userName;
		} , false);
		
	</script>
</html>

