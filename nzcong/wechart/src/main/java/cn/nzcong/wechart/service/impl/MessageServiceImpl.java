package cn.nzcong.wechart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;
import cn.nzcong.wechart.service.BaseMessageHandler;
import cn.nzcong.wechart.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	//private static Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	/****************************
	 * 注入服务
	 */
	@Autowired
	private RobotService robotService;
	
	
	/****************************
	 * 消息处理器node
	 */
	@Autowired
	private BaseMessageHandler textMessageRobotHandler;
	@Autowired
	private BaseMessageHandler textMessageTemplateHandler;
	@Autowired
	private VoiceMessageRobotHandler voiceMessageRobotHandler;
	@Autowired
	private EventMessageSubscribeHandler eventMessageSubscribeHandler;
	
	/****************************
	 * 各种消息处理器 & 责任链装配
	 */
	private BaseMessageHandler textMessageHandler;
	private BaseMessageHandler voiceMessageHandler;
	private BaseMessageHandler eventMessageHandler;
	
	private BaseMessageHandler getTextMessageHandler(){
		if(textMessageHandler == null){
			textMessageTemplateHandler.nextnode(textMessageRobotHandler);
			textMessageHandler = textMessageTemplateHandler;
		}
		return textMessageHandler;
	}
	
	private BaseMessageHandler getVoiceMessageHandler(){
		if(voiceMessageHandler == null){
			voiceMessageHandler = voiceMessageRobotHandler;
		}
		return voiceMessageHandler;
	}
	
	private BaseMessageHandler getEventMessageHandler(){
		if(eventMessageHandler == null){
			eventMessageSubscribeHandler.setNextnode(null);
			eventMessageHandler = eventMessageSubscribeHandler;
		}
		return eventMessageHandler;
	}
	
	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.TextMessage)
	 */
	@Override
	public Message processMsg(TextMessage msg) {
		return this.getTextMessageHandler().handle(msg);
	}

	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.VoiceMessage)
	 */
	@Override
	public Message processMsg(VoiceMessage msg) {
		return getVoiceMessageHandler().handle(msg);
	}

	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.EventMessage)
	 */
	@Override
	public Message processMsg(EventMessage msg) {
		return getEventMessageHandler().handle(msg);
	}
	
	private String replaceEnter(String content){
		return content.replaceAll("<br>", "\n");
	}
	
	public static void main(String[] args) {
		MessageServiceImpl s= new MessageServiceImpl();
		System.out.println(s.replaceEnter("111<br>111"));
		
	}
}
