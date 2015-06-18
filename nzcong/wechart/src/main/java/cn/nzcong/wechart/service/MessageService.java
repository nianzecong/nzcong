package cn.nzcong.wechart.service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;

public interface MessageService {

	public Message processTextMsg(TextMessage msg);
	
}
