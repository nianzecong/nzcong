package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TextMessage extends Message{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String content;
	
	public TextMessage(){
		
	}
	
	public TextMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.content = root.elementText("Content");
	}

	public Document encode(){
		Document resp = super.getCommonEncode();
		Element Event = DocumentHelper.createElement("Content");
		Event.setText(this.content);
		
		Element root = resp.getRootElement();
		root.add(Event);
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
