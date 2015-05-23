package cn.nzcong.weiboservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.nzcong.weibo.service.WeiboService;

@Controller
@RequestMapping(value = "/*")
public class WeiboController {
	
	private static Logger log = LoggerFactory.getLogger(WeiboController.class);
	
	@Autowired
	private WeiboService weiboService;
	
	// 匹配url为/test/login，请求方法为post，从页面获取参数userName,password返回页面test.jsp
	@RequestMapping(value = "/login")
	public String validUser(String loginName, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("login {loginName:" + loginName + ",password:" + password + "}");
		log.debug(weiboService.test());
		return "test";
	}

}