package cn.nzcong.weibo.model;

import java.io.Serializable;

public class Weibo implements Serializable {

	private static final long serialVersionUID = -8795691786466526420L;

	private String createdat; // status创建时间
	private String weiboid; // status id
	private String mid; // 微博MID
	private String text; // 微博内容
	private String thumbnailPic; // 微博内容中的图片的缩略地址
	private String bmiddlePic; // 中型图片
	private String originalPic; // 原始图片
	private Weibo retweetedStatus = null; // 转发的博文，内容为status，如果不是转发，则没有此字段
	private double latitude = -1; // 纬度
	private double longitude = -1; // 经度
	private int repostsCount; // 转发数
	private int commentsCount; // 评论数
	private User user;

	public String getReweiboId(){
		return retweetedStatus == null ? null : retweetedStatus.getWeiboid();
	}
	
	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getWeiboid() {
		return weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getBmiddlePic() {
		return bmiddlePic;
	}

	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}

	public String getOriginalPic() {
		return originalPic;
	}

	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}

	public Weibo getRetweetedStatus() {
		return retweetedStatus;
	}

	public void setRetweetedStatus(Weibo retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Weibo = {createdat:" + createdat + ", weiboid:" + weiboid + ", mid:" + mid + ", text:" + text + ", thumbnailPic:" + thumbnailPic + ", bmiddlePic:" + bmiddlePic + ", originalPic:" + originalPic + ", retweetedStatus:" + retweetedStatus + ", latitude:" + latitude + ", longitude:" + longitude + ", repostsCount:" + repostsCount + ", commentsCount:" + commentsCount + ", user:" + user + "}";
	}


}
