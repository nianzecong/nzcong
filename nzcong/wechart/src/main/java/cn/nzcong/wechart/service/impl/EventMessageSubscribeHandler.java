package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.service.BaseMessageHandler;

@Service("eventMessageSubscribeHandler")
public class EventMessageSubscribeHandler extends BaseMessageHandler{
	
	private static Logger log = LoggerFactory.getLogger(EventMessageSubscribeHandler.class);
	
	@Autowired
	private RobotService robotService; 

	@Override
	public BaseMessage handle(BaseMessage _msg) throws MessageException {
		log.debug("handle...");
		TextMessage respMsg = new TextMessage();
		EventMessage msg = (EventMessage) _msg;
		String content = "你好啊";
		
		if(msg.getEventData() != null && msg.getEventData() instanceof EventMessage.SubscribeMsg){
			content = "欢迎关注";
		} else {
			// 不是订阅事件，下一个环节处理
			return super.nextHandler(_msg);
		}
		
		respMsg.setContent(content);
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType("text");
		respMsg.setToUser(msg.getFromUser());
		log.debug("handle - resp:" + respMsg);
		return respMsg;
	}
	
}
