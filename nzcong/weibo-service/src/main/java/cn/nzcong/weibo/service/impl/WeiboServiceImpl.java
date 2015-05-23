package cn.nzcong.weibo.service.impl;

import org.springframework.stereotype.Service;

import cn.nzcong.weibo.service.WeiboService;

@Service
public class WeiboServiceImpl implements WeiboService {

	@Override
	public String test(){
		return "success";
	}
	
}
