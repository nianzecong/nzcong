package cn.nzcong.wechart.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.ImageMessage;
import cn.nzcong.wechart.message.LinkMessage;
import cn.nzcong.wechart.message.LocationMessage;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.ShortvideoMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VideoMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.BaseMessageHandler;
import cn.nzcong.wechart.service.MessageService;
import cn.nzcong.wechart.utils.SecurityUtils;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/*")
public class CommonController {

	private static Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	MessageService messageServcie;
	
	@RequestMapping(value = "/gateway",method=RequestMethod.GET )
	public @ResponseBody
	String config(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.debug("config start - " + JSONObject.toJSONStringWithDateFormat(request.getParameterMap(), "yyyy-MM-dd"));
		String timestamp = request.getParameter("timestamp");
		String echostr = request.getParameter("echostr");
		String nonce = request.getParameter("nonce");
		String signature = request.getParameter("signature");
		try {
			String sign = SecurityUtils.getSHA1sign("nzcong", timestamp, nonce, "");
			if(!StringUtils.isBlank(sign) && sign.equals(signature)){
				log.debug("config success");
				return echostr;
			} else {
				log.error("config - error - " + sign);
			}
		} catch (Exception e) {
			log.error("config - error - " + e, e);
		}
		return "error";
	}
	
	@RequestMapping(value = "/gateway",method=RequestMethod.POST )
	public @ResponseBody
	String recieveMessage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String messageXml = "";
		String respStr = "";
		try {
		    // 从request中取得输入流  
		    InputStream inputStream = request.getInputStream();  
		    // 读取输入流  
		    SAXReader reader = new SAXReader();  
		    Document document = reader.read(inputStream);
		    log.debug("recieve message : " + document.asXML());
		    // 解码为对应的message对象
		    BaseMessage msg = BaseMessageHandler.decode(document);
			log.debug(msg.getClass().getName() + " : " + JSONObject.toJSONString(msg));
			BaseMessage resp = null;
			if(msg instanceof TextMessage){
				resp = messageServcie.processMsg((TextMessage)msg);
			} else if(msg instanceof ImageMessage){
				
			} else if(msg instanceof LinkMessage){
				
			} else if(msg instanceof LocationMessage){
				
			} else if(msg instanceof ShortvideoMessage){
				
			} else if(msg instanceof VideoMessage){
				
			} else if(msg instanceof VoiceMessage){
				resp = messageServcie.processMsg((VoiceMessage)msg);
			} else if(msg instanceof EventMessage){
				resp = messageServcie.processMsg((EventMessage)msg);
			}
			respStr = resp == null ? respStr : resp.encode().asXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("recieve message end : " + respStr);
		return respStr;
	}
	
}