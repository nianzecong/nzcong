package cn.nzcong.wechart.message;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.nzcong.wechart.exception.MessageException;

public class EventMessage extends Message {

	private static final long serialVersionUID = -1617262303859861785L;

	private String event;
	private CommonMsg eventData;

	public EventMessage() {

	}

	public EventMessage(Document document) throws MessageException {
		super(document);
		Element root = document.getRootElement();
		this.event = root.elementText("Event");
		this.eventData = CommonMsg.decode(event, document);
	}

	public Document encode() {
		Document resp = super.getCommonEncode();
		Element Event = DocumentHelper.createElement("Event");
		Event.setText(this.event);

		Element root = resp.getRootElement();
		root.add(Event);

		return resp;
	}

	/**
	 * getters & setters
	 */
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public CommonMsg getEventData() {
		return eventData;
	}

	public void setEventData(CommonMsg eventData) {
		this.eventData = eventData;
	}

	@Override
	public String toString() {
		return "{event:" + event + ", eventData:" + eventData + ", msgId:" + msgId + ", toUser:" + toUser + ", fromUser:" + fromUser + ", createTime:" + createTime + ", msgType:" + msgType + "}";
	}

	/**
	 * 订阅消息数据
	 */
	public static class SubscribeMsg extends CommonMsg {
		public String event;

		public SubscribeMsg(Document document) {
			super(document);
			this.event = document.getRootElement().elementText("Event");
		}
	}
	/**
	 * 订阅消息数据
	 */
	public static class UnSubscribeMsg extends CommonMsg {
		public String event;
		
		public UnSubscribeMsg(Document document) {
			super(document);
			this.event = document.getRootElement().elementText("Event");
		}
	}

	/**
	 * 普通消息及处理器
	 */
	private abstract static class CommonMsg {
		public static final Map<String, Class> eventMap = new HashMap<String, Class>() {
			private static final long serialVersionUID = 3487538497008632559L;
			{
				put("subscribe", SubscribeMsg.class);
				put("unsubscribe", UnSubscribeMsg.class);
			}
		};
 
		public CommonMsg(Document document) {
		}

		public static CommonMsg decode(String eventType, Document parentDocument) throws MessageException {
			CommonMsg msg;
			Constructor constructor;
			try {
				Class clazz = eventMap.get(eventType);
				if(clazz == null){
					return null;
				}
				constructor = clazz.getDeclaredConstructor(new Class[] { Document.class });
				constructor.setAccessible(true);
				msg = ((CommonMsg) constructor.newInstance(new Object[] { parentDocument }));
			} catch (Exception e) {
				throw new MessageException("Message decode error", e);
			}
			return msg;
		}
	}

}
