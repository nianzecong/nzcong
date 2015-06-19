package cn.nzcong.wechart.message.handler;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;

import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.ImageMessage;
import cn.nzcong.wechart.message.LinkMessage;
import cn.nzcong.wechart.message.LocationMessage;
import cn.nzcong.wechart.message.Message;
import cn.nzcong.wechart.message.ShortvideoMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VideoMessage;
import cn.nzcong.wechart.message.VoiceMessage;

public class MessageHandler {
	
	@SuppressWarnings("rawtypes")
	private static final Map<String, Class> msgTypeMap = new HashMap<String, Class>(){
		private static final long serialVersionUID = -3280081752827029930L;
		{
			put("image", ImageMessage.class);
			put("link", LinkMessage.class);
			put("location", LocationMessage.class);
			put("shortvideo", ShortvideoMessage.class);
			put("text", TextMessage.class);
			put("video", VideoMessage.class);
			put("voice", VoiceMessage.class);
			put("event", EventMessage.class);
		}
	};
	
	public static Message decode(Document document) throws MessageException{
		String messageType = document.getRootElement().elementText("MsgType");
		Message message = null;
		Constructor constructor;
		try {
			constructor = MessageHandler.msgTypeMap.get(messageType).getDeclaredConstructor(new Class[] { Document.class});
			constructor.setAccessible(true);
			message = ((Message) constructor.newInstance(new Object[] { document }));
		} catch (Exception e) {
			throw new MessageException("Message decode error", e);
		}
		return message;
	}
	
}
