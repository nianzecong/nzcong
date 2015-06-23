<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>weibo login</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script>
	var authUrl = "${OauthUrl}";
	var message = "${message}";
	$(function() {
		if (message != "") {
			alert(message);
		} else if (authUrl == "") {
			$("body").html("获取鉴权地址失败");
		} else {
			window.location.href = authUrl;
		}
	});
</script>
</head>
<body>正在跳转
</body>
</html>