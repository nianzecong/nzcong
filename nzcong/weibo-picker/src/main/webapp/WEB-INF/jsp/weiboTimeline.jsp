<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<div class="header">
		<div class="container ft20">
			<div class="menu">
				<img src="images/bighead.png" class="fl bighead" /> <a
					href="http://weibo.com/nianzecong" target="blank"
					class="fl button-weibo button"> <span></span></a> <a
					href="http://github.com/nianzecong" target="blank"
					class="fl button-git button"> <span></span></a> <a
					href="javascript:void(0)" class="fr button-menu button"><span></span></a>
			</div>
			<div class="navi fr">
				<a href="http://nzcong.cn" target="blank" class="mgt20"> <span>HOME</span></a>
			</div>
		</div>
	</div>
	<div class="container">
		<span class="title f30"><br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	</div>
	<div class="bottom">
		<div class="container">
			<a href="http://nzcong.cn/" target="_self">http://nzcong.cn/</a><br />
			<a href="javascript:void(0);" target="_self">nianzecong在126.com</a><br />
			<a href="http://www.miitbeian.gov.cn/" target="blank">鲁ICP备15014475号</a><br />
			<span>本网站只供个人学习讨论之用</span><br />
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type : "post",
				url : "getTimeLine",
				param : {},
				async : true,
				success : function(data) {
					alert(data);
				}
			});
		});
	</script>
</body>
</html>
