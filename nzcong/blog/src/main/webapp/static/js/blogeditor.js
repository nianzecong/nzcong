var domain = "http://" + window.location.host;

function initCategories() {
	log("initCategories...");
	$("#blogCatagory").html("<option value=\"\">分类</option>");
	var u = domain + "/blog/getcatagorylist";
	$.ajax({
		type : "post",
		url : u,
		param : {},
		// async : true,
		success : function(data) {
			var list = [];
			try {
				list = eval(data);
			} catch (e) {
			}
			if (list && list.length > 1) {
				for ( var i = 0; i < list.length; i++) {
					var category = list[i];
					$("#blogCatagory").append(
							"<option value=\"" + category.cid + "\">"
									+ category.cname + "</option>");
				}
			}
		}
	});
}

function save(type) {
	var u = domain + "/blog/save";
	type = parseInt(type);
	type = type == type ? type : 0;
	var params = {
		id : currentBlog,
		title : $("#blogTitle").val(),
		catagory : $("#blogCatagory").val(),
		text : $("#markdownInput").val(),
		type : type
	};
	$.ajax({
		type : "post",
		url : u,
		data : params,
		async : true,
		success : function(data) {
			if(data && data != ""){
				currentBlog = data;
				log("currentBlog : " + currentBlog);
				$("#info").html("已于" + new Date().format("hh:mm") + "保存");
			} else {
				$("#info").html("<span style=\"color:red\">保存失败</span>");
			}
		}
	});
}

function startSaveMonitor(){
	setTimeout(function(){
		if($("#blogTitle").val() == "" || $("#markdownInput").val() == ""){
			return;
		}
		save($("#blogType").val());
		log("存草稿 - " + new Date().format("hh:mm") + "- type: " + $("#blogType").val());
		startSaveMonitor();
	}, 60000);
}

function log(content){
	console ? console.log(content) : "";
}

$(function() {
	$("#markdownInput").bind("keyup", function() {
		$("#markdownview").html(marked($(this).val()));
	});
	initCategories();
	log("startSaveMonitor...");
	startSaveMonitor();

	$("#markdownview").html(marked($("#markdownInput").html()));
});
