package cn.nzcong.wechart.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/common")
public class CommonController {

	private static Logger log = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value = "/config")
	public @ResponseBody
	String timelinetest(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("************* " + JSONObject.toJSONStringWithDateFormat(request.getParameterMap(), "yyyy-MM-dd"));
		System.out.println("************* " + request.getParameter("echostr"));
		return request.getParameter("echostr");
	}
	

}