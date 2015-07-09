<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>博客列表</title>
		<link rel="stylesheet" href="${ctx}/css/base.css" />
	</head>

	<body>
		<!----------------------------------
		 header start 
		 -->
		<div class="header">
			<div class="container ft20">
				<div class="menu">
					<img src="${ctx}/images/bighead.png" class="fl bighead" />
					<a href="http://weibo.com/nianzecong" target="blank" class="fl button-weibo button">
						<span></span>
					</a>
					<a href="http://github.com/nianzecong" target="blank" class="fl button-git button">
						<span></span>
					</a>
					<a href="javascript:void(0)" class="fr button-menu button"><span></span></a>
				</div>
				<div class="navi fr">
					<a href="http://nzcong.cn" target="blank" class="mgt20">
						<span>HOME</span>
					</a>
				</div>
			</div>
		</div>
		
		<!----------------------------------
		 blog list start 
		 -->
		<div class="container main">
			message: ${message }
			<br/><br/><br/><br/><br/>
		</div>
		
		<!----------------------------------
		  bottom start 
		 -->
		<div class="bottom">
			<div class="container">
				<a href="http://nzcong.cn/" target="_self">http://nzcong.cn/</a>
				<br/>
				<a href="javascript:void(0);" target="_self">nianzecong在126.com</a>
				<br/>
				<a href="http://www.miitbeian.gov.cn/" target="blank">鲁ICP备15014475号</a>
				<br/>
				<span>本网站只供个人学习讨论之用</span>
				<br/>
			</div>
		</div>
		<script type="text/javascript">
		
		</script>
	</body>

</html>