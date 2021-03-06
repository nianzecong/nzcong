<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>博客查看</title>
		<link rel="stylesheet" href="${ctx}/css/base.css" />
		<link rel="stylesheet" href="${ctx}/js/highlight/css/zenburn.css">
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
			<%-- <div class="publicBlogFunction">
				<span class="button"><a href="${ctx}/editor/${blog.id}">改</a></span>
			</div> --%>
			<div class="blogItem">
				<div class="blogtitle"><a href="javascript:"><h1>${blog.title }</h1></a><br/><span class="time">${blog.addTime }</span></div>
				<br/>
				<div class="blogContent" id="blog">${blog.text }</div>
			</div>
		</div>
		<br/>
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
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.js"></script>
		<script type="text/javascript" src="${ctx}/js/marked.js"></script>
		<script type="text/javascript" src="${ctx}/js/DateFormat.js"></script>
		<script type="text/javascript" src="${ctx}/js/highlight/highlight.pack.js"></script>
		<script type="text/javascript">
			var login = "${login}";
			var ctx = "${ctx }";
			$(function() {
				$("#blog").html(marked($("#blog").html()));
				$('pre code').each(function(i, block) {
					hljs.highlightBlock(block);
				});
				var isIE = !-[1,];
				if(isIE){
					//$("body div.container.main").html("对不起，因为作者比较傲娇，所以该页面不支持IE访问，请使用chrome或者firefox。</br></br></br></br></br></br>");
				}
			});
		</script>
	</body>

</html>