package cn.nzcong.blog.model;

import java.io.Serializable;

public class Blog implements Serializable{

	private static final long serialVersionUID = 1798389238282131234L;
	
	private String id;// 文章ID
	private String title;// 文章标题
	private String text;// 文章内容markdown
	private String catagoryId;// 文章分类
	
	private int type;// 0草稿，1已发布，2置顶，3隐藏
	
	private String updateTime;// 修改时间
	private String addTime;// 添加时间
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
	/**
	 * 0草稿，1已发布，2置顶，3隐藏
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
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
		builder.append("type:").append(type).append(", ");
		if (updateTime != null)
			builder.append("updateTime:").append(updateTime).append(", ");
		if (addTime != null)
			builder.append("addTime:").append(addTime);
		builder.append("}");
		return builder.toString();
	}
	
}
