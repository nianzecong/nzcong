package cn.nzcong.wechart.service;

import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;

public interface MessageService {

	public Message processTextMsg(TextMessage msg);

	public Message processVoiceMsg(VoiceMessage msg);
	
}
