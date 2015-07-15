var domain = "http://" + window.location.host;

//TODO
function getPagedList(currentPage){
	$("div.container div.bloglist").html("加载慢别怪我……卖不起好服务器 T.T");
	var u =  status == "hidden" ? "/blog/hidden/get" : 
				status == "draft" ? "/blog/draft/get" : 
									"/blog/list/get";
	u = domain + u;
	var params = {
			currentPage : currentPage,
			pageSize : 10,
		};
	$.ajax({
		type : "post",
		url : u,
		data : params,
		async : false,
		success : function(data) {
			$("div.container div.bloglist").html("");
			var list = data && data.datas ? data.datas : [];
			log("blog list :" + list.length);
			var listStr = "";
			if(list.length == 0){
				$("div.container div.bloglist").append("<br/><br/><br/><br/>博主还没有发表博客哦<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
			}
			for(var i = 0 ; list && i < list.length ; i++){
				log("append blog : " + (i + 1) + "/" + list.length );
				var blog = list[list.length - i - 1];
				listStr += "<div class=\"blogItem\"><div class=\"blogtitle\">";
				listStr += blog.type == "2" ? "<a class=\"flag red\">top</a>" : "";
				listStr += "<a href=\"" + ctx + "/view/" + blog.id + "\" target=\"blank_" + blog.id + "\"><h1>" + blog.title + "</h1></a><span class=\"time\">" + blog.addTime + "</span></div>";
				// 替换掉<html>标签和空白字符
				var text = marked(blog.text).replace(/<.+?>|\s/g, "");
				text = text.length > 275 ? text.substr(0, 175) + "..." : text;
				log("length: " + text.length);
				listStr += "<div class=\"blogContent\">" + text + "</div>";
				listStr += "<div class=\"blogFunction\">";
				if(blog.type == "2"){
					listStr += "<a class=\"flag fr\" onclick=\"javascript:update('" + blog.id + "', " + 1 + ")\">untop</a>";
				} else {
					listStr += "<a class=\"flag fr red\" onclick=\"javascript:update('" + blog.id + "', " + 2 + ")\">top</a>";
				}
				listStr += "<a class=\"flag fr\" onclick=\"javascript:edit('" + blog.id + "')\">edit</a>";
				listStr += "<a class=\"flag fr\" onclick=\"javascript:update('" + blog.id + "', " + 3 + ")\">hide</a>";
				listStr += "<div class=\"cl\"></div></div>";
				listStr += "</div>";
			}
			$("div.container div.bloglist").append(listStr);
		}
	});
}

function update(id, type){
	var u = domain + "/blog/update";
	var params = {
			blogId : id,
			type : type,
		};
	$.ajax({
		type : "post",
		url : u,
		data : params,
		async : false,
		success : function(data) {
			if(data == "1"){
				alert("修改成功");
			} else {
				alert("修改失败");
			}
		}
	});
}

function edit(id){
	window.location.href="/blog/editor/" + id;
}

function log(content){
	console ? console.log(content) : "";
}

$(function() {
	for(var i = 0 ; i < 10 ; i++){
		log("欢迎调试聪哥的博客程序！！ ^_^ ..........." + Math.random());
	}
	getPagedList(1);
	if(login == ""){
		$("div.publicBlogFunction").remove();
		$("div.blogFunction").html("");
	} else {
		$("div.publicBlogFunction").show();
	}
});
