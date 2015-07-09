package cn.nzcong.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.service.BlogService;
import cn.nzcong.blog.util.DateTimeUtils;
import cn.nzcong.blog.util.UuidUtils;
import cn.nzcong.config.service.ConfigService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/*")
public class BlogController {

	private static Logger log = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	BlogService blogService;
	@Autowired
	ConfigService configService;
	
	private final String PWD_KEY = "blog.admin.password";
	private String getParameter(String key){
		return configService.getParameter(key);
	}
	
	@RequestMapping(value = "/editor")
	public String timeline(String blogId, String pwd, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		}
		model.put("blog", blogService.getBlog(blogId));
		//TODO 跳转后编辑
		return "editor";
	}
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "bloglist";
	}
	
	@RequestMapping(value = "/list/{pwd}")
	public String list(@PathVariable("pwd") String pwd, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		}
		model.put("a", pwd);
		return "bloglist";
	}

	@RequestMapping(value = "/getcatagorylist")
	public @ResponseBody
	String getCatagoryList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return JSONArray.toJSONString(blogService.getCategoryList());
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	String save(String id, String title, String catagory, String text, Integer type, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Blog blog = blogService.getBlog(id);
		type = type == null ? 0 : type;
		if (blog == null) {
			id = UuidUtils.getUUID();
			blog = new Blog();
			blog.setId(id);
			blog.setCatagoryId(catagory);
			blog.setText(text);
			blog.setTitle(title);
			blog.setType(type == 0 ? blog.getType() : type);// 0草稿，1已发布，2置顶，3隐藏
			blogService.addBlog(blog);
		} else {
			blog.setCatagoryId(catagory);
			blog.setText(text);
			blog.setTitle(title);
			blog.setUpdateTime(type == 0 ? blog.getUpdateTime() : DateTimeUtils.getNowDateTimeStr());
			blog.setType(type);// 草稿
			blogService.updateBlog(blog);
		}
		return id;
	}
	
	@RequestMapping(value = "/list/get")
	public @ResponseBody
	JSONObject getCatagoryList(Map<String, Object> params, int currentPage, int pageSize, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<String> types = new ArrayList<String>();
		types.add("1");// 已发布
		types.add("2");// 置顶
		params.put("types", types);
		return JSONObject.parseObject(JSONObject.toJSONString(blogService.getPagedList(params, currentPage, pageSize)));
	}

	
	
}