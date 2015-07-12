package cn.nzcong.wechart.message;

import java.io.Serializable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.nzcong.wechart.utils.VerifyUtils;

public abstract class BaseMessage implements Serializable {

	private static final long serialVersionUID = 838560790455515832L;

	protected String msgId;
	protected String toUser;
	protected String fromUser;
	protected int createTime;
	protected String msgType;

	public BaseMessage() {

	}

	public BaseMessage(Document document){
		try {
			Element rootElt = document.getRootElement();
			
			String _toUser = rootElt.elementText("ToUserName");
			String _fromUser =  rootElt.elementText("FromUserName");
			String _createTime =  rootElt.elementText("CreateTime");
			String _msgType =  rootElt.elementText("MsgType");
			String _msgId =  rootElt.elementText("MsgId");
			
			VerifyUtils.verifyBlank(_toUser, _fromUser, _createTime, _msgType);
			
			this.toUser = _toUser;
			this.fromUser = _fromUser;
			this.createTime = Integer.parseInt(_createTime);
			this.msgType = _msgType;
			this.msgId = _msgId;
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected Document getCommonEncode(){
		Document resp = DocumentHelper.createDocument();
		Element ToUserName = DocumentHelper.createElement("ToUserName");
		Element FromUserName = DocumentHelper.createElement("FromUserName");
		Element CreateTime = DocumentHelper.createElement("CreateTime");
		Element MsgType = DocumentHelper.createElement("MsgType");
		Element MsgId = DocumentHelper.createElement("MsgId");

		ToUserName.setText(this.toUser);
		FromUserName.setText(this.fromUser);
		CreateTime.setText(String.valueOf(this.createTime));
		MsgType.setText(this.getMsgType());
		MsgId.setText(this.getMsgId());
		
		Element root = DocumentHelper.createElement("xml");
		root.add(ToUserName);
		root.add(FromUserName);
		root.add(CreateTime);
		root.add(MsgType);
		root.add(MsgId);

		resp.setRootElement(root);
		
		return resp;
	}

	
	public abstract Document encode();
	
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

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (msgId != null)
			builder.append("msgId:").append(msgId).append(", ");
		if (toUser != null)
			builder.append("toUser:").append(toUser).append(", ");
		if (fromUser != null)
			builder.append("fromUser:").append(fromUser).append(", ");
		builder.append("createTime:").append(createTime).append(", ");
		if (msgType != null)
			builder.append("msgType:").append(msgType);
		builder.append("}");
		return builder.toString();
	}

}
