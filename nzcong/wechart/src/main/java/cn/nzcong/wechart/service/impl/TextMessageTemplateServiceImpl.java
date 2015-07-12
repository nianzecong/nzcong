package cn.nzcong.wechart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.service.BaseMessageService;

@Service("textMessageTemplateHandler")
public class TextMessageTemplateServiceImpl extends BaseMessageService{

	private static Logger log = LoggerFactory.getLogger(TextMessageTemplateServiceImpl.class);

	
	protected TextMessageTemplateServiceImpl(){
		super();
	}

	@Override
	public Message handle(Message _msg) {
		log.debug("TextMessageTemplateHandler handle...");
		// TODO Auto-generated method stub
		return super.getNextnode().handle(_msg);
	}
	
	
	
}
