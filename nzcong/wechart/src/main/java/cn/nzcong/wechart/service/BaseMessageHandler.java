package cn.nzcong.wechart.service;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;

import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.EventMessage;
import cn.nzcong.wechart.message.ImageMessage;
import cn.nzcong.wechart.message.LinkMessage;
import cn.nzcong.wechart.message.LocationMessage;
import cn.nzcong.wechart.message.NewsMessage;
import cn.nzcong.wechart.message.ShortvideoMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.message.VideoMessage;
import cn.nzcong.wechart.message.VoiceMessage;

public abstract class BaseMessageHandler{
	
	private BaseMessageHandler nextnode;
	
	protected BaseMessageHandler(){
		
	}
	
	public BaseMessageHandler getNextnode() {
		return nextnode;
	}

	public BaseMessageHandler setNextnode(BaseMessageHandler nextnode) {
		this.nextnode = nextnode;
		return this.nextnode;
	}
	
	public BaseMessageHandler nextnode(BaseMessageHandler nextnode) {
		return setNextnode(nextnode);
	}
	
	public abstract BaseMessage handle(BaseMessage msg)throws MessageException ;

	public BaseMessage nextHandler(BaseMessage msg) throws MessageException {
		return nextnode == null ? null : nextnode.handle(msg);
	}
	
	@SuppressWarnings("rawtypes")
	public static final Map<String, Class> msgTypeMap = new HashMap<String, Class>(){
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
			put("news", NewsMessage.class);
		}
	};
	
	public static BaseMessage decode(Document document) throws MessageException{
		String messageType = document.getRootElement().elementText("MsgType");
		BaseMessage message = null;
		Constructor constructor;
		try {
			constructor = BaseMessageHandler.msgTypeMap.get(messageType).getDeclaredConstructor(new Class[] { Document.class});
			constructor.setAccessible(true);
			message = (BaseMessage) constructor.newInstance(new Object[] { document });
		} catch (Exception e) {
			throw new MessageException("Message decode error", e);
		}
		return message;
	}
	
	
	public String replaceEnter(String content){
		return content.replaceAll("<br>", "\n");
	}
	
}
