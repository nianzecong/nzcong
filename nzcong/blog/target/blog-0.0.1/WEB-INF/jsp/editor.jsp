<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>markdown编辑器</title>
		<link rel="stylesheet" href="css/base.css" />
	</head>

	<body>
		<div class="header">
			<div class="container ft20">
				<div class="menu">
					<img src="images/bighead.png" class="fl bighead" />
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
				<input class="blogTitle" placeholder="这里输入标题" id="blogTitle" value="" class="fl">
				<select class="blogCatagory" id="blogCatagory" class="fr">
					<option value="1">分类</option>
				</select>
				<div class="cl"></div>
				<textarea id="markdownInput" placeholder="这里输入markdown语法的内容" rows="30" cols="" style="width:100%"></textarea>
				<input type="button" class="button ok" value="提交" onclick=""/>
				<input type="button" class="button cancel" value="删除" onclick=""/>
				<span class="info fr" id="info">已与16:10保存</span>
			</div>
			<div class="markdownReview" id="markdownview" class="fr"></div>
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
		<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
		<script type="text/javascript" src="js/marked.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#markdownInput").bind("keyup", function() {
					$("#markdownview").html(marked($(this).val()));
				});
			});
		</script>
	</body>

</html>