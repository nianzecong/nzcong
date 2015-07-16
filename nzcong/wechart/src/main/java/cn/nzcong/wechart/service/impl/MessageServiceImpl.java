package cn.nzcong.wechart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.robot.service.RobotService;
import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.BaseMessage;
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
	private BaseMessageHandler voiceMessageRobotHandler;
	@Autowired
	private BaseMessageHandler eventMessageSubscribeHandler;
	
	/****************************
	 * 各种消息处理器 & 责任链装配
	 */
	private BaseMessageHandler textMessageHandler;
	private BaseMessageHandler voiceMessageHandler;
	private BaseMessageHandler eventMessageHandler;
	
	// 文字消息处理器
	private BaseMessageHandler getTextMessageHandler(){
		if(textMessageHandler == null){
			// 模板处理 - 机器人处理
			textMessageTemplateHandler.nextnode(textMessageRobotHandler);
			textMessageHandler = textMessageTemplateHandler;
		}
		return textMessageHandler;
	}
	
	// 语音消息处理器
	private BaseMessageHandler getVoiceMessageHandler(){
		if(voiceMessageHandler == null){
			// 机器人处理
			voiceMessageHandler = voiceMessageRobotHandler;
		}
		return voiceMessageHandler;
	}
	
	// 事件消息处理器
	private BaseMessageHandler getEventMessageHandler(){
		if(eventMessageHandler == null){
			// 订阅消息
			eventMessageSubscribeHandler.setNextnode(null);
			eventMessageHandler = eventMessageSubscribeHandler;
		}
		return eventMessageHandler;
	}
	
	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.TextMessage)
	 */
	@Override
	public BaseMessage processMsg(TextMessage msg)  throws MessageException {
		return this.getTextMessageHandler().handle(msg);
	}

	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.VoiceMessage)
	 */
	@Override
	public BaseMessage processMsg(VoiceMessage msg)  throws MessageException {
		return getVoiceMessageHandler().handle(msg);
	}

	/* (non-Javadoc)
	 * @see cn.nzcong.wechart.service.MessageService#processMsg(cn.nzcong.wechart.message.EventMessage)
	 */
	@Override
	public BaseMessage processMsg(EventMessage msg)  throws MessageException {
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
