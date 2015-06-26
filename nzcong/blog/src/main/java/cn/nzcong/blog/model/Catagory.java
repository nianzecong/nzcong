package cn.nzcong.blog.model;

import java.io.Serializable;

public class Catagory implements Serializable {

	private static final long serialVersionUID = 2087722443451871284L;
	
	private String cid;
	private String cname;
	private int blogCount;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (cid != null)
			builder.append("cid:").append(cid).append(", ");
		if (cname != null)
			builder.append("cname:").append(cname).append(", ");
		builder.append("blogCount:").append(blogCount).append("}");
		return builder.toString();
	}
}
