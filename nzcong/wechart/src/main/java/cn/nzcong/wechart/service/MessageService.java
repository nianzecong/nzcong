package cn.nzcong.wechart.service;

import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;

public interface MessageService {

	public Message processMsg(TextMessage msg);

	public Message processMsg(VoiceMessage msg);

	public Message processMsg(EventMessage msg);
	
}
