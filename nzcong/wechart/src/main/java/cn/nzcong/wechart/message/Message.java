package cn.nzcong.wechart.message;

import java.io.Serializable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.nzcong.wechart.utils.VerifyUtils;

public abstract class Message implements Serializable {

	private static final long serialVersionUID = 838560790455515832L;

	private String toUser;
	private String fromUser;
	private String createTime;
	private String msgType;

	public Message() {

	}

	public Message(String xml){
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootElt = document.getRootElement();
			
			String _toUser = rootElt.elementText("ToUserName");
			String _fromUser =  rootElt.elementText("FromUserName");
			String _createTime =  rootElt.elementText("CreateTime");
			String _msgType =  rootElt.elementText("MsgType");
			
			VerifyUtils.verifyBlank(_toUser, _fromUser, _createTime, _msgType);
			
			this.toUser = _toUser;
			this.fromUser = _fromUser;
			this.createTime = _createTime;
			this.msgType = _msgType;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	/*************************
	 *   getters & setters
	 *************************/
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (toUser != null)
			builder.append("toUser:").append(toUser).append(", ");
		if (fromUser != null)
			builder.append("fromUser:").append(fromUser).append(", ");
		if (createTime != null)
			builder.append("createTime:").append(createTime).append(", ");
		if (msgType != null)
			builder.append("msgType:").append(msgType);
		builder.append("}");
		return builder.toString();
	}

}
