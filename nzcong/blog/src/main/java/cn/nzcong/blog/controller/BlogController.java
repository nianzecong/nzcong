package cn.nzcong.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.service.BlogService;
import cn.nzcong.blog.util.UuidUtils;

@Controller
@RequestMapping(value = "/*")
public class BlogController {

	private static Logger log = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	BlogService blogService;

	@RequestMapping(value = "/editor")
	public String timeline(String blogId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("blog", blogService.getBlog(blogId));
		return "editor";
	}

	@RequestMapping(value = "/getcatagorylist")
	public @ResponseBody
	String getCatagoryList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return null;
	}

	@RequestMapping(value = "/savedraft")
	public @ResponseBody
	String saveAsDraft(String id, String title, String catagory, String text, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Blog blog = blogService.getBlog(id);
		if (blog == null) {
			id = UuidUtils.getUUID();
			blog = new Blog();
			blog.setId(id);
			blog.setCatagoryId(catagory);
			blog.setText(text);
			blog.setTitle(title);
			blog.setType(0);// 草稿
			blogService.addBlog(blog);
		} else {
			blog.setCatagoryId(catagory);
			blog.setText(text);
			blog.setTitle(title);
			blog.setType(0);// 草稿
			blogService.updateBlog(blog);
		}
		return id;
	}

}