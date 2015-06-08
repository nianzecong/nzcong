package cn.nzcong.weiboservice.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.nzcong.weibo.model.Weibo;

@Service
public interface WeiboDao {

	public abstract int addWeibo(Weibo weibo);
	
	public abstract Weibo getWeibo(String id);
	
	public abstract List<Weibo> getWeiboByDate(String date);
	
}
