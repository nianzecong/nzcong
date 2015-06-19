<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
	
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title></title>
	<link rel="stylesheet" href="${ctx}/css/base.css" />
	
	<!-- 百度统计 -->
	<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?3b7d081c99264884a834ea13588aa2c9";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
	</script>

</head>
<body>
	<div class="header">
		<div class="container ft20">
			<div class="menu">
				<img src="${ctx}/images/bighead.png" class="fl bighead" /> <a
					href="http://weibo.com/nianzecong" target="blank"
					class="fl button-weibo button"> <span></span>
				</a> <a href="http://github.com/nianzecong" target="blank"
					class="fl button-git button"> <span></span>
				</a> <a href="javascript:void(0)" class="fr button-menu button"><span></span></a>
			</div>
			<div class="navi fr">
				<a href="http://nzcong.cn" target="blank" class="mgt20"> <span>HOME</span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="weibolist"></div>
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${ctx}/js/index.js"></script>
	<script type="text/javascript">
		var flag = "${flag}";
		var date = "${date}";
	</script>
</body>

</html>