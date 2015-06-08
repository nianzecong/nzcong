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

import cn.nzcong.utils.AppConfig;
import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.model.Weibo;
import cn.nzcong.weibo.service.WeiboService;
import cn.nzcong.weiboservice.dao.WeiboDao;

@Controller
@RequestMapping(value = "/*")
public class WeiboController {

	private static Logger log = LoggerFactory.getLogger(WeiboController.class);

	private String getToken(){
		return AppConfig.getParameter("weibo.token");
	}

	@Autowired
	private WeiboService weiboService;
	@Autowired
	private WeiboDao weiboDao;

	@RequestMapping(value = "/timeline")
	public String timeline(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "weiboTimeline";
	}
	
	@RequestMapping(value = "/reload")
	public @ResponseBody
	String reload(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return String.valueOf(AppConfig.reload());
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
			String token = weiboService.getTockenByCode(code);
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
		List<Weibo> weiboList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			weiboList = weiboService.getTimeLine(getToken());
			for(Weibo weibo : weiboList){
				if(weiboDao.getWeibo(weibo.getWeiboid()) == null){
					weiboDao.addWeibo(weibo);
				}
			}
		} catch (WeiboAuthException e) {
			log.error("getTimeLine - error : " + e, e);
		}
		map.put("weiboList", weiboList);
		return map;
	}

}