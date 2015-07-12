package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.exception.RobotException;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.service.BaseMessageService;
import cn.nzcong.wechart.utils.RobotUtil;

@Service("textMessageRobotHandler")
public class TextMessageRobotServiceImpl extends BaseMessageService{
	
	private static Logger log = LoggerFactory.getLogger(TextMessageRobotServiceImpl.class);
	
	protected TextMessageRobotServiceImpl(){
		super();
	}

	@Override
	public Message handle(Message _msg) {
		log.debug("TextMessageRobotHandler handle...");
		TextMessage msg = (TextMessage) _msg;
		TextMessage respMsg = new TextMessage();
		try {
			String content = RobotUtil.chart(msg.getContent());
			respMsg.setContent(replaceEnter(content));
		} catch (RobotException e) {
			log.error("processMsg - TEXT - chart error", e);
			respMsg.setContent("矮油，聪哥(机器人)好像生病了，过一阵再来找我吧");
		}
		respMsg.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
		respMsg.setFromUser(msg.getToUser());
		respMsg.setMsgType(msg.getMsgType());
		respMsg.setToUser(msg.getFromUser());
		log.debug("TextMessageRobotHandler resp:" + respMsg);
		return respMsg;
	}
	
	
	
}
