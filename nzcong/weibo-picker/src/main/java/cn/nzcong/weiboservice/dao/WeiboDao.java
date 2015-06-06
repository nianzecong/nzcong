package cn.nzcong.weiboservice.dao;

import java.util.List;

import cn.nzcong.weibo.model.Weibo;

public interface WeiboDao {

	public abstract int addWeibo(Weibo weibo);
	
	public abstract Weibo getWeibo(String id);
	
	public abstract List<Weibo> getWeiboByDate(String date);
	
}
