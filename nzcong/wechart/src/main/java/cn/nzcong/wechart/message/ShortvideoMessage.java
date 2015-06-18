package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class ShortvideoMessage extends Message{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String content;
	
	public ShortvideoMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.content = root.elementText("Content");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Document encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
