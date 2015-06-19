package cn.nzcong.weiboservice.dao;

import java.util.List;
import java.util.Map;

import cn.nzcong.weibo.model.Weibo;

public interface WeiboDao {

	public abstract int addWeibo(Weibo weibo);
	
	public abstract Weibo getWeibo(String id);
	
	public abstract List<Weibo> getWeiboByDate(Map<String, Object> params);
	
	/**
	 * 查找前10个日期
	 * @return
	 */
	public abstract List<String> getDates();
	
}
