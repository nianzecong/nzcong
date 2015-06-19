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
	var u = "getTimeLine" + (flag && flag != "" ? "/" + flag : "");
	$.ajax({
		type : "post",
		url : u,
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
				     // 提取域名，js不支持(?<=exp)，先做处理：替换掉http:// 拼接“.”，并替换掉“..”；再匹配"/"前面的 .xxx.xxx，下一行切去开头的“.”
				     var mlink = ("." + m[j].replace("http://", "").replace("..", ".")).match(/(\.\w+?\.\w+?)(?=\/.*)/g);
				     var domain = mlink && mlink[0] ? mlink[0].substr(1) : "LINK";// 识别不到的显示“LINK” = =
				     text = text.replace(link , "<a class=\"link\" href=\"" + link + "\" title=\"" + link + "\" target=\"blank\">" + domain + "</a>" );
				}
				// 替换话题
				var mhuati = (text + " ").match(/#(\S+?)#/g);
				for(var j = 0 ; mhuati && j < mhuati.length ; j++){
					var huati = mhuati[j];
					text = text.replace(huati, "<a class=\"huati\" href=\"http://huati.weibo.com/k/" + huati.replace(/#/g, "") + "\" title=\"" + huati + "\" target=\"blank\">" + huati + "</a>");
				}
				// 替换艾特
				var mat = (text + " ").match(/@([_0-9a-zA-Z\u4e00-\u9fa5]{2,10})(?=[^0-9a-zA-Z\u4e00-\u9fa5])/g);
				for(var j = 0 ; mat && j < mat.length ; j++){
					var at = mat[j];
					text = text.replace(at, "<a class=\"at\" href=\"http://weibo.com/n/" + at.replace("@","") + "\" title=\"" + at.replace("@","") + "\" target=\"blank\">" + at + "</a>");
				}
				listStr += "<div class=\"weiboItem\" id=\"" + item.weiboid + "\" style=\"display:none\">";
				listStr += "<div class=\"weiboUser\"><a href=\"http://weibo.com/" + item.userid + "\">@" + item.userscreenname + "</a></div>";
				listStr += "<div class=\"weiboContent\">";
				listStr += "<div>" + text + "</div>";
				listStr += "</div><div class=\"weiboImages\">";
				listStr += "<div class=\"view\">";
				listStr += "<a class=\"link\" style=\"float:right\" href=\"javascript:void(0)\" target=\"blank\">点击查看原图</a><br/>";
				listStr += "<img src=\"\"/></div>";
				var pics = item.thumbnailPic ? item.thumbnailPic.split(";;") : [];
				for(var k = 0 ; k < pics.length ; k++){
					listStr += "<span><img src=\"" + pics[k] + "\"/></span>";
				}
				listStr += "</div>";
				listStr += "<div class=\"weiboFunction\">";
				listStr += "<span><a class=\"link\" href=\"javascript:void(0)\">转" + item.repostsCount + "</a>";
				listStr += "<a class=\"link\" href=\"javascript:void(0)\">评" + item.commentsCount + "</a></span>";
				listStr += "</div></div>";
				$("div.weibolist").html($("div.weibolist").html() + listStr);
				$("#" + item.weiboid).show();
				$("#" + item.weiboid + " .weiboImages").find("span img").each(function(){
					var $this = $(this);
					if(($this[0].width * 2) < $this[0].height){
						$this.attr("src", item.bmiddlePic);
					}
//					if($(this)[0].width > $(this)[0].height){
//						$(this).height($(this)[0].height);
//					} else {
//						$(this).width($(this)[0].width);
//					}
					$this.parent().height($this.width());
				});
			}// for weibo List
			
			$("div.weiboItem .weiboImages span").click(function(){
				var $this = $(this);
				// 处理view class
				$this.parent().find("span").removeClass("view");
				$this.addClass("view");
				// 预览图地址获取
				var $img = $this.find("img");
				var bmiddle = $img.attr("src").replace("/thumbnail/", "/bmiddle/");
				var original = $img.attr("src").replace("/thumbnail/", "/large/").replace("/bmiddle/", "/large/");
				// 预览图地址设置、【查看原图】按钮地址
				var $divView = $this.parent().find("div.view");
				$divView.find("img").attr("src", bmiddle);
				$divView.find("a.link").attr("href", original);
				//alert($divView.find("a.link").attr("href"));
				$divView.show();
			});
			$("div.weiboItem .weiboImages div.view").click(function(){
				var $this = $(this);
				$this.parent().find("span").removeClass("view");
				$this.hide();
			});
		}
	});
}
