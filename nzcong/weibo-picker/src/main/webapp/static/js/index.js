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
			$("div.weibolist").html("");
			if (!data || !data.weiboList || data.weiboList < 1) {
				alert("获取微博错误");
				return;
			}
			var list = data.weiboList;
			for ( var i = 0; i < list.length; i++) {
				var listStr = "";
				var item = list[i];
				var user = item.user;
				var text = item.text;
				// 替换链接
				var m = (text + " ").match(/http:\/\/.+?(?=[\s，。,$\u4e00-\u9fa5])/g);
				for (var j = 0 ; m && j < m.length ; j++ ){
				     var link = m[j];
				     text = text.replace(link , "<a class=\"link\" href=\"" + link + "\" title=\"" + link + "\" target=\"blank\">LINK</a>" );
				}
				// 替换话题
				var mhuati = (text + " ").match(/#(\S+?)#/g);
				for(var j = 0 ; mhuati && j < mhuati.length ; j++){
					var huati = mhuati[j];
					text = text.replace(huati, "<a class=\"huati\" href=\"http://huati.weibo.com/k/" + huati.replace(/#/g, "") + "\" title=\"" + huati + "\" target=\"blank\">" + huati + "</a>");
				}
				// 替换艾特
				var mat = (text + " ").match(/@(\S{2,20})(?=[\s，。,:：()（）])/g);
				for(var j = 0 ; mat && j < mat.length ; j++){
					var at = mat[j];
					text = text.replace(at, "<a class=\"at\" href=\"http://weibo.com/n/" + at.replace("@","") + "\" title=\"" + at.replace("@","") + "\" target=\"blank\">" + at + "</a>");
				}
				listStr += "<div class=\"weiboItem\">";
				// 被删除的微博
				if(user){
					listStr += "<div class=\"weiboUser\"><a href=\"http://weibo.com/" + user.id + "\">@" + user.screenName + "</a></div>";
				} else {
					listStr += "<div class=\"weiboUser\"><a href=\"javascript:void(0)\">无名</a></div>";
				}
				listStr += "<div class=\"weiboContent\">";
				listStr += "<div>" + text + "</div>";
				listStr += "</div><div class=\"weiboImages\">";
				if(item.thumbnailPic)
					listStr += "<img src=\"" + item.thumbnailPic + "\"/>";
				listStr += "</div>";
				listStr += "<div class=\"weiboFunction\">";
				listStr += "<span><a class=\"link\" href=\"javascript:void(0)\">转" + item.repostsCount + "</a>";
				listStr += "<a class=\"link\" href=\"javascript:void(0)\">评" + item.commentsCount + "</a></span>";
				listStr += "</div></div>";
				$("div.weibolist").html($("div.weibolist").html() + listStr);
			}
		}
	});
}
