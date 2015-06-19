package cn.nzcong.weiboservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nzcong.utils.AppConfig;
import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.model.Weibo;
import cn.nzcong.weibo.service.WeiboService;
import cn.nzcong.weibo.utils.DateTimeUtils;
import cn.nzcong.weiboservice.dao.WeiboDao;
import cn.nzcong.weiboservice.service.TimerService;

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
	@Autowired
	private TimerService timerService;

	@RequestMapping(value = "/test")
	public void timelinetest(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		timerService.checkTimeLine();
	}
	
	@RequestMapping(value = "/timeline")
	public String timeline(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "weiboTimeline";
	}
	
	@RequestMapping(value = "/hot")
	public String hot(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("flag", "hot");
		return "weiboTimeline";
	}
	
	@RequestMapping(value = "/reload")
	public @ResponseBody
	String reload(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return String.valueOf(AppConfig.reload());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTimeLine")
	public @ResponseBody
	Map getTimeLine(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Weibo> weiboList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			weiboList = weiboService.getTimeLine(getToken());
		} catch (WeiboAuthException e) {
			log.error("getTimeLine - error : " + e, e);
		}
		map.put("weiboList", weiboList);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTimeLine/{flag}")
	public @ResponseBody
	Map getTimeLine(@PathVariable("flag") String flag, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Weibo> weiboList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if("hot".equals(flag)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("date", DateTimeUtils.getNowDateTimeStr(DateTimeUtils.DATE_PATTERN));
				weiboList = weiboDao.getWeiboByDate(params);
			}
			//weiboList = weiboService.getTimeLine(getToken());
		} catch (Exception e) {
			log.error("getTimeLine - error : " + e, e);
		}
		map.put("weiboList", weiboList);
		return map;
	}
	

}