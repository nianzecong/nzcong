var domain = "http://" + window.location.host;

//TODO
function getPagedList(currentPage){
	var u = domain + "/blog/list/get";
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
			var list = data && data.datas ? data.datas : [];
			log("blog list :" + list.length);
			var listStr = "";
			for(var i = 0 ; list && i < list.length ; i++){
				log("append blog : " + (i + 1) + "/" + list.length );
				var blog = list[list.length - i - 1];
				listStr += "<div class=\"blogItem\"><div class=\"blogtitle\">";
				listStr += blog.type == "2" ? "<a class=\"flag red\">top</a>" : "";
				listStr += "<a href=\"javascript:viewBlog(\"" + blog.id + "\");\"><h1>" + blog.title + "</h1></a><span class=\"time\">" + blog.addTime + "</span></div>";
				listStr += "<div class=\"blogContent\">" + blog.text + "</div>";
				listStr += "<div class=\"blogFunction\">";
				listStr += "<a class=\"flag fr\">top</a>";
				listStr += "<a class=\"flag fr\" onclick=\"javascript:edit('" + blog.id + "')\">edit</a>";
				listStr += "<a class=\"flag fr\">hide</a>";
				listStr += "<div class=\"cl\"></div></div>";
				listStr += "</div>";
			}
			$("div.container div.bloglist").append(listStr);
		}
	});
}

function edit(id){
	window.location.href="/blog/editor?blogId=" + id + "&pwd=" + a;
}

function log(content){
	console ? console.log(content) : "";
}

$(function() {
//	initCategories();
	getPagedList(1);
	if(a == ""){
		$("div.publicBlogFunction").hide();
		$("div.blogFunction").hide();
	}
});
