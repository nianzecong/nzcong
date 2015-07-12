package cn.nzcong.wechart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.service.BaseMessageHandler;

@Service("textMessageTemplateHandler")
public class TextMessageTemplateHandler extends BaseMessageHandler{

	private static Logger log = LoggerFactory.getLogger(TextMessageTemplateHandler.class);

	@Override
	public Message handle(Message _msg) {
		log.debug("handle...");
		// TODO Auto-generated method stub
		return super.nextHandler(_msg);
	}
	
	
	
}
