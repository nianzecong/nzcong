<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>markdown编辑器</title>
		<link rel="stylesheet" href="${ctx}/css/base.css" />
		<link rel="stylesheet" href="${ctx}/js/highlight/css/zenburn.css">
	</head>

	<body>
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
		
		<div class="container">
			<div class="fl blogeditor">
				<input type="text" class="blogTitle" placeholder="这里输入标题" id="blogTitle" value="${blog.title }" class="fl">
				<input type="hidden" id="blogType" value="${blog.type}"/>
				<input type="hidden" id="catagoryId" value="${blog.catagoryId}"/>
				<select class="blogCatagory" id="blogCatagory" class="fr">
					<option value="">分类</option>
				</select>
				<div class="cl"></div>
				<textarea id="markdownInput" placeholder="这里输入markdown语法的内容" rows="30" cols="" style="width:100%">${blog.text }</textarea>
				<input type="button" class="button ok" value="保存" onclick="javascript:save($('#blogType').val() == '' ? 1 : $('#blogType').val())"/>
				<input type="button" class="button cancel" value="删除" onclick="javascript:save(3)"/>
				<input type="button" class="button ok" value="提交" onclick="javascript:save(1)" style="width:70px"/>
				<span class="info fr" id="info"></span>
			</div>
			<div class="markdownview">
				<div class="markdownReview" id="markdownview" class="fr"></div>
			</div>
		</div>
		<div class="cl"></div>
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
		
		<script type="text/javascript" src="${ctx}/js/blogeditor.js"></script>
		<script type="text/javascript">
			var currentBlog = "${blog.id}";
		</script>
	</body>

</html>