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
			<div class="publicBlogFunction" style="display:none">
				<span class="button"><a href="${ctx}/editor">新博</a></span>
				<span class="button"><a href="${ctx}/draft">草稿</a></span>
				<span class="button"><a href="${ctx}/hidden">隐藏</a></span>
			</div>
			<div class="bloglist">
				
			</div>
		</div>
		
		<!----------------------------------
		  bottom start 
		 -->
		<div class="bottom">
			<div class="container">
				<a href="http://nzcong.cn/" target="_self">nzcong.cn</a>
				<a href="javascript:void(0)" target="_self">© 2015</a>
				<br/>
				<a href="http://www.miitbeian.gov.cn/" target="blank">鲁ICP备15014475号</a>
				<br/>
				<span>本网站只供个人学习讨论之用</span>
				<br/>
				<span>如果侵犯了您的权益请联系</span>
				<br/>
				<a href="javascript:void(0);" target="_self">nianzecong在126.com</a>
				<br/>
				
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.js"></script>
		<script type="text/javascript" src="${ctx}/js/marked.js"></script>
		<script type="text/javascript" src="${ctx}/js/bloglist.js"></script>
		<script type="text/javascript">
			var login = "${login}";
			var ctx = "${ctx }";
			var status = "${status}";
			$(function() {
				var isIE = !-[1,];
				if(isIE){
					//$("body div.container.main").html("对不起，因为作者比较傲娇，所以该页面不支持IE访问，请使用chrome或者firefox。</br></br></br></br></br></br>");
				}
			});
		</script>
	</body>

</html>