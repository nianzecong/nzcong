package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class LinkMessage extends BaseMessage{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String title;
	private String description;
	private String url;
	
	
	public LinkMessage(){
		
	}
	
	public LinkMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.title = root.elementText("Title");
		this.description = root.elementText("Description");
		this.url = root.elementText("Url");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "{title:" + title + ", description:" + description + ", url:" + url + "}";
	}

	@Override
	public Document encode() {
		Document resp = super.getCommonEncode();
		Element titleEvent = DocumentHelper.createElement("Title");
		titleEvent.setText(this.title);
		Element descriptionEvent = DocumentHelper.createElement("Description");
		descriptionEvent.setText(this.description);
		Element urlEvent = DocumentHelper.createElement("Url");
		urlEvent.setText(this.url);
		
		Element root = resp.getRootElement();
		root.add(titleEvent);
		root.add(descriptionEvent);
		root.add(urlEvent);
		return resp;
	}
	
}
