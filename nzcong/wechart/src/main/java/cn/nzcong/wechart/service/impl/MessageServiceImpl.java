package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.BaseMessageService;
import cn.nzcong.wechart.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	private static Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	private BaseMessageService textMessageHandler;
	
	@Autowired
	private RobotService robotService;
	@Autowired
	private BaseMessageService textMessageRobotHandler;
	@Autowired
	private BaseMessageService textMessageTemplateHandler;
	
	public BaseMessageService getTextMessageHandler(){
		textMessageHandler = textMessageTemplateHandler;
		textMessageHandler.setNextnode(textMessageRobotHandler);
		return textMessageHandler;
	}
	
	@Override
	public Message processMsg(TextMessage msg) {
		return this.getTextMessageHandler().handle(msg);
	}

	@Override
	public Message processMsg(VoiceMessage msg) {
		TextMessage respMsg = new TextMessage();
		try {
			respMsg.setContent(StringUtils.isBlank(msg.getRecognition()) ? "你说什么？我没听清" : replaceEnter(robotService.chart(msg.getRecognition())));
		} catch (Exception e) {
			log.error("processMsg - TEXT - chart error", e);
			respMsg.setContent("矮油，聪哥(机器人)好像生病了，过一阵再来找我吧");
		}
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType("text");
		respMsg.setToUser(msg.getFromUser());
		return respMsg;
	}

	@Override
	public Message processMsg(EventMessage msg) {
		String content = "你好啊";
		
		if(msg.getEventData() == null){
		} else if(msg.getEventData() instanceof EventMessage.SubscribeMsg){
			content = "欢迎关注";
		} else if(msg.getEventData() instanceof EventMessage.UnSubscribeMsg){
			return null;
		}
		TextMessage respMsg = new TextMessage();
		respMsg.setContent(content);
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType("text");
		respMsg.setToUser(msg.getFromUser());
		return respMsg;
	}
	
	private String replaceEnter(String content){
		return content.replaceAll("<br>", "\n");
	}
	
	public static void main(String[] args) {
		MessageServiceImpl s= new MessageServiceImpl();
		System.out.println(s.replaceEnter("111<br>111"));
		
	}
}
