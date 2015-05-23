package cn.nzcong.weiboservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/*")
public class TestController {
	
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	// 匹配url为/test/login，请求方法为post，从页面获取参数userName,password返回页面test.jsp
	@RequestMapping(value = "/login")
	public String validUser(String loginName, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("login {loginName:" + loginName + ",password:" + password + "}");
		return "test";
	}

}