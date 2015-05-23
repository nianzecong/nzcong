package cn.nzcong.weiboservice.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.model.Status;
import cn.nzcong.weibo.service.WeiboService;

@Controller
@RequestMapping(value = "/*")
public class WeiboController {

	private static Logger log = LoggerFactory.getLogger(WeiboController.class);

	private String tocken = "";

	@Autowired
	private WeiboService weiboService;

	@RequestMapping(value = "/timeline")
	public String timeline(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "weiboTimeline";
	}
	
	@RequestMapping(value = "/login")
	public String validUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("OauthUrl", weiboService.getauthUrl());
		return "weiboLogin";
	}

	@RequestMapping(value = "/updateCode")
	public String updateCode(String code, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("updateCode - " + code);
		try {
			this.tocken = weiboService.getTockenByCode(code);
		} catch (WeiboAuthException e) {
			//TODO redirect
		}
		model.put("message", StringUtils.isEmpty(code) ? "code upodate failed" : "code update success");
		return "weiboLogin";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTimeLine")
	public @ResponseBody
	Map getTimeLine(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Status> weiboList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			weiboList = weiboService.getTimeLine(tocken);
		} catch (WeiboAuthException e) {
			//TODO redirect
		}
		map.put("weiboList", weiboList);
		return map;
	}

}