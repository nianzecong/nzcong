$(function() {
	var isIE = !-[ 1, ];
	if (isIE) {
		$("body").html("对不起，因为作者比较傲娇，所以该页面不支持IE访问，请使用chrome或者firefox。");
	}

	$("div.menu a.button-menu").bind("click", function() {
		$(this).toggleClass("current");
	});

	initTimeLine();
});

function initTimeLine() {
	$.ajax({
		type : "post",
		url : "getTimeLine",
		param : {},
		async : true,
		success : function(data) {
			if (!data || !data.weiboList || data.weiboList < 1) {
				alert("获取微博错误");
				return;
			}
			var list = data.weiboList;
			var listStr = "";
			for ( var i = 0; i < list.length; i++) {
				var item = list[i];
				var user = item.user;
				listStr += "<div class=\"weiboItem\">";
				listStr += "<div class=\"weiboUser\"><a href=\"http://weibo.com/" + user.id + "\">@" + user.screenName + "</a></div>";
				listStr += "<div class=\"weiboContent\">";
				listStr += "<div>" + item.text + "</div>";
				listStr += "</div><div class=\"weiboImages\">";
				if(item.thumbnailPic)
					listStr += "<img src=\"" + item.thumbnailPic + "\"/>";
				listStr += "</div>";
				listStr += "<div class=\"weiboFunction\">";
				listStr += "<span>转发</span>";
				listStr += "</div></div>";
			}
			$("div.weibolist").html(listStr);
		}
	});
}
