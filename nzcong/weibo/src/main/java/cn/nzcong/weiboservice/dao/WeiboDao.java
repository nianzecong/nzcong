package cn.nzcong.weiboservice.dao;

import cn.nzcong.weibo.model.Weibo;
import java.util.List;
import java.util.Map;

public abstract interface WeiboDao {
	
	public abstract int addWeibo(Weibo paramWeibo);

	public abstract Weibo getWeibo(String paramString);

	public abstract List<Weibo> getWeiboByDate(Map<String, Object> paramMap);

	public abstract List<String> getDates();
}