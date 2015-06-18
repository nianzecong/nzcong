package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

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

	public static void main(String[] args) {
		MessageServiceImpl s = new MessageServiceImpl();
		s.processTextMsg(null);
	}
	
}
