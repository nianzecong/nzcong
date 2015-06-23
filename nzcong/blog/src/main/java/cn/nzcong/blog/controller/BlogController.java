package cn.nzcong.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/*")
public class BlogController {

	private static Logger log = LoggerFactory.getLogger(BlogController.class);

	@RequestMapping(value = "/editor")
	public String timeline(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "editor";
	}

	@RequestMapping(value = "/saveasdraft")
	public @ResponseBody
	String saveAsDraft(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		return "";
	}
	
}