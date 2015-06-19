package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class EventMessage extends Message{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String content;
	
	public EventMessage(){
		
	}
	
	public EventMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.content = root.elementText("Content");
	}

	public Document encode(){
		Document resp = DocumentHelper.createDocument();
		Element ToUserName = DocumentHelper.createElement("ToUserName");
		Element FromUserName = DocumentHelper.createElement("FromUserName");
		Element CreateTime = DocumentHelper.createElement("CreateTime");
		Element MsgType = DocumentHelper.createElement("MsgType");
		Element Content = DocumentHelper.createElement("Content");

		ToUserName.setText(this.toUser);
		FromUserName.setText(this.fromUser);
		CreateTime.setText(String.valueOf(this.createTime));
		MsgType.setText(this.getMsgType());
		Content.setText(this.content);
		
		Element root = DocumentHelper.createElement("xml");
		root.add(ToUserName);
		root.add(FromUserName);
		root.add(CreateTime);
		root.add(MsgType);
		root.add(Content);

		resp.setRootElement(root);
		
		return resp;
	}
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (content != null)
			builder.append("content:").append(content).append(", ");
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
