package cn.nzcong.blog.model;

import java.io.Serializable;

public class Blog implements Serializable{

	private static final long serialVersionUID = 1798389238282131234L;
	
	private String id;
	private String title;
	private String text;
	private String catagoryId;
	
	private int type;
	private int top;
	
	private String updateTime;
	private String addTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (id != null)
			builder.append("id:").append(id).append(", ");
		if (title != null)
			builder.append("title:").append(title).append(", ");
		if (text != null)
			builder.append("text:").append(text).append(", ");
		if (catagoryId != null)
			builder.append("catagoryId:").append(catagoryId).append(", ");
		builder.append("type:").append(type).append(", top:").append(top).append(", ");
		if (updateTime != null)
			builder.append("updateTime:").append(updateTime).append(", ");
		if (addTime != null)
			builder.append("addTime:").append(addTime);
		builder.append("}");
		return builder.toString();
	}
	
}
