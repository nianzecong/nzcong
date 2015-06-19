package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	public static void main(String[] args) {
		MessageServiceImpl s = new MessageServiceImpl();
		s.processTextMsg(null);
	}
	
	@Override
	public Message processTextMsg(TextMessage msg) {
		TextMessage respMsg = new TextMessage();
		respMsg.setContent(msg.getContent());
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType(msg.getMsgType());
		respMsg.setToUser(msg.getFromUser());
		return respMsg;
	}

	@Override
	public Message processVoiceMsg(VoiceMessage msg) {
		TextMessage respMsg = new TextMessage();
		respMsg.setContent(StringUtils.isBlank(msg.getRecognition()) ? "你说什么？我没听清" : msg.getRecognition());
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType("text");
		respMsg.setToUser(msg.getFromUser());
		return respMsg;
	}
	
}
