<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>博客列表</title>
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
			<div class="publicBlogFunction">
				<span class="button">新</span>
			</div>
			<div class="bloglist">
				<div class="blogItem">
					<div class="blogtitle"><span class="flag red">top</span><a href="javascript:"><h1>博客标题</h1></a><span class="time">2015-05-05 00:00</span></div>
					<div class="blogContent">
						【超强技术帖：遇到不会读的字，怎么用拼音打出来？[偷乐]】方法很简单，就是先打个“u”然后打各个部首的读音，就能在拼音输入法中打出来哦！比如，骉，可以输入umamama，输入法就会自动出现“骉”字，并附上读音。快转给需要的人吧！
					</div>
					<div class="blogFunction">
					</div>
				</div>
				<div class="blogItem">
					<div class="blogtitle"><a href="javascript:"><h1>博客标题</h1></a><span class="time">2015-05-05 00:00</span></div>
					<div class="blogContent">
						【超强技术帖：遇到不会读的字，怎么用拼音打出来？[偷乐]】方法很简单，就是先打个“u”然后打各个部首的读音，就能在拼音输入法中打出来哦！比如，骉，可以输入umamama，输入法就会自动出现“骉”字，并附上读音。快转给需要的人吧！
					</div>
					<div class="blogFunction">
					</div>
				</div>
			</div>
		</div>
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
		<script type="text/javascript" src="js/bloglist.js"></script>
		<script type="text/javascript">
			$(function() {
				var isIE = !-[1,];
				if(isIE){
					$("body").html("对不起，因为作者比较傲娇，所以该页面不支持IE访问，请使用chrome或者firefox。");
				}
			});
		</script>
	</body>

</html>