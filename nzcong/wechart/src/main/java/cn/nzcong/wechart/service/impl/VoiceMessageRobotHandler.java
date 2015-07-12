package cn.nzcong.wechart.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.BaseMessageHandler;

@Service("voiceMessageRobotHandler")
public class VoiceMessageRobotHandler extends BaseMessageHandler{
	
	private static Logger log = LoggerFactory.getLogger(VoiceMessageRobotHandler.class);
	
	@Autowired
	private RobotService robotService; 

	@Override
	public BaseMessage handle(BaseMessage _msg) {
		log.debug("handle...");
		TextMessage respMsg = new TextMessage();
		VoiceMessage msg = (VoiceMessage) _msg;
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
		log.debug("handle - resp:" + respMsg);
		return respMsg;
	}
	
	
	
}
