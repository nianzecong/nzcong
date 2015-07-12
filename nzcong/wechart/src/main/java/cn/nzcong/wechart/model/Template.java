package cn.nzcong.wechart.model;

import java.io.Serializable;

public class Template implements Serializable {

	private static final long serialVersionUID = -3423678908378304413L;
	
	private String templateId;
	private String messageType;
	private String messageContent;
	private String keyWords;
	private String title;
	private String description;
	private String pic;
	private String addTime;
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "{templateId:" + templateId + ", messageType:" + messageType + ", messageContent:" + messageContent + ", keyWords:" + keyWords + ", title:" + title + ", description:" + description + ", pic:" + pic + ", addTime:" + addTime + "}";
	}
	
	
	
}
