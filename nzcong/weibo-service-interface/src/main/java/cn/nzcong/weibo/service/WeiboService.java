package cn.nzcong.weibo.service;

import java.util.List;

import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.model.Weibo;

public interface WeiboService {

	public abstract String getauthUrl();
	
	public abstract String getTockenByCode(String code) throws WeiboAuthException ;
	
	public abstract List<Weibo> getTimeLine(String tocken) throws WeiboAuthException;
	
}
