package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.exception.RobotException;
import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.service.BaseMessageHandler;

@Service("textMessageRobotHandler")
public class TextMessageRobotHandler extends BaseMessageHandler{
	
	private static Logger log = LoggerFactory.getLogger(TextMessageRobotHandler.class);
	
	@Autowired
	private RobotService robotService; 
	
	@Override
	public BaseMessage handle(BaseMessage _msg) {
		log.debug("handle...");
		TextMessage msg = (TextMessage) _msg;
		TextMessage respMsg = new TextMessage();
		try {
			String content = robotService.chart(msg.getContent());
			respMsg.setContent(replaceEnter(content));
		} catch (RobotException e) {
			log.error("processMsg - TEXT - chart error", e);
			respMsg.setContent("矮油，聪哥(机器人)好像生病了，过一阵再来找我吧");
		}
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType(msg.getMsgType());
		respMsg.setToUser(msg.getFromUser());
		log.debug("handle - resp:" + respMsg);
		return respMsg;
	}
	
	
	
}
