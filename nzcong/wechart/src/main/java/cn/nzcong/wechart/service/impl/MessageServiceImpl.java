package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Override
	public Message processMsg(TextMessage msg) {
		TextMessage respMsg = new TextMessage();
		respMsg.setContent(msg.getContent());
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType(msg.getMsgType());
		respMsg.setToUser(msg.getFromUser());
		return respMsg;
	}

	@Override
	public Message processMsg(VoiceMessage msg) {
		TextMessage respMsg = new TextMessage();
		respMsg.setContent(StringUtils.isBlank(msg.getRecognition()) ? "你说什么？我没听清" : msg.getRecognition());
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
	
	
}
