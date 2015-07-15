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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value = "/")
	public String index(){
		return "redirect:/list";
	}
	@RequestMapping(value = "/editor")
	public String editor(String blogId, HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes) {
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		}
		//model.put("blog", blogService.getBlog(blogId));
		return "blogeditor";
	}

	@RequestMapping(value = "/editor/{blogId}")
	public String editorById(@PathVariable("blogId") String blogId, HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute( "blog", blogService.getBlog(blogId));
		 return "redirect:/editor" ;
	}
	
	@RequestMapping(value = "/list")
	public String list(String status, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(!StringUtils.isEmpty(pwd) && pwd.equals(this.getParameter(PWD_KEY))){
			model.put("login", "success");
			model.put("status", status);
		}
		return "bloglist";
	}
	
	@RequestMapping(value = "/view/{blogId}")
	public String view(@PathVariable("blogId") String blogId, HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes) {
		Blog blog = blogService.getBlog(blogId);
		if(blog == null){
			model.put("message", "你瞅啥？？根本就没有这篇博客");
			return "message";
		}
		model.put("blog", blogService.getBlog(blogId));
		return "blogview";
	}
	
	@RequestMapping(value = "/print/{blogId}")
	public String print(@PathVariable("blogId") String blogId, HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes) {
		Blog blog = blogService.getBlog(blogId);
		if(blog == null){
			model.put("message", "根本就没有这篇博客");
			return "message";
		}
		model.put("blog", blogService.getBlog(blogId));
		return "blogview-print";
	}
	
	@RequestMapping(value = "/list/{pwd}")
	public String listAdmin(@PathVariable("pwd") String pwd, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		} else {
			request.getSession().setAttribute(PWD_KEY, pwd);
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/draft")
	public String draftList(HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes) {
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		} else {
			request.getSession().setAttribute(PWD_KEY, pwd);
			redirectAttributes.addAttribute("status", "draft");
		}
		return "redirect:/list";
	}
	@RequestMapping(value = "/hidden")
	public String hiddenList(HttpServletRequest request, HttpServletResponse response, ModelMap model, final RedirectAttributes redirectAttributes) {
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			model.put("message", "你TM在逗我？");
			return "message";
		} else {
			request.getSession().setAttribute(PWD_KEY, pwd);
			redirectAttributes.addAttribute("status", "hidden");
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody
	String updateTop(String blogId, int type, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			return "0";
		}
		Blog blog = blogService.getBlog(blogId);
		blog.setType(type);
		int result = blogService.updateBlog(blog);
		return String.valueOf(result);
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
	JSONObject getBlogList(Map<String, Object> params, int currentPage, int pageSize, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("getBlogList start - " + params + "{currentPage: " + currentPage + ", pageSize: " + pageSize + "}");
		List<String> types = new ArrayList<String>();
		types.add("1");// 已发布
		types.add("2");// 置顶
		params.put("types", types);
		log.debug("getBlogList End");
		return JSONObject.parseObject(JSONObject.toJSONString(blogService.getPagedList(params, currentPage, pageSize)));
	}
	
	@RequestMapping(value = "/draft/get")
	public @ResponseBody
	JSONObject getDraftList(Map<String, Object> params, int currentPage, int pageSize, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("getPagedList start - " + params + "{currentPage: " + currentPage + ", pageSize: " + pageSize + "}");
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			return null;
		}
		List<String> types = new ArrayList<String>();
		types.add("0");// 草稿
		params.put("types", types);
		log.debug("getPagedList End");
		return JSONObject.parseObject(JSONObject.toJSONString(blogService.getPagedList(params, currentPage, pageSize)));
	}
	
	@RequestMapping(value = "/hidden/get")
	public @ResponseBody
	JSONObject getHiddenList(Map<String, Object> params, int currentPage, int pageSize, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("getHiddenList start - " + params + "{currentPage: " + currentPage + ", pageSize: " + pageSize + "}");
		String pwd = (String)request.getSession().getAttribute(PWD_KEY);
		if(StringUtils.isEmpty(pwd) || !pwd.equals(this.getParameter(PWD_KEY))){
			return null;
		}
		List<String> types = new ArrayList<String>();
		types.add("3");// 隐藏
		params.put("types", types);
		log.debug("getHiddenList end");
		return JSONObject.parseObject(JSONObject.toJSONString(blogService.getPagedList(params, currentPage, pageSize)));
	}

	
	
}