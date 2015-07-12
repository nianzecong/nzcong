package cn.nzcong.wechart.service;

import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VoiceMessage;

public interface MessageService {

	public BaseMessage processMsg(TextMessage msg) throws MessageException ;

	public BaseMessage processMsg(VoiceMessage msg) throws MessageException ;

	public BaseMessage processMsg(EventMessage msg) throws MessageException ;
	
}
