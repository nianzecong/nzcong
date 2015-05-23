<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>weibo login</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script>
	var authUrl = "${OauthUrl}";
	var message = "${message}";
	$(function() {
		if(message != ""){
			alert(message);
		} else if (authUrl == "") {
			$("body").html("获取鉴权地址失败");
		} else {
			window.location.href = authUrl;
		}
		$.ajax({
			type:"post",
			url:"http://localhost:8080/weibo-picker/getTimeLine",
			param : {},
			async:true,
			success: function(data){
				alert(data);
			}
		});
	});
</script>
</head>
<body>
	正在跳转
</body>
</html>