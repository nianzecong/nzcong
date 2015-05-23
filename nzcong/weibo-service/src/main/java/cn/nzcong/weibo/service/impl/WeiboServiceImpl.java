package cn.nzcong.weibo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.service.WeiboService;
import cn.nzcong.weibo.weibo4j.Oauth;
import cn.nzcong.weibo.weibo4j.Timeline;
import cn.nzcong.weibo.weibo4j.model.Status;
import cn.nzcong.weibo.weibo4j.model.StatusWapper;
import cn.nzcong.weibo.weibo4j.model.WeiboException;

@Service
public class WeiboServiceImpl implements WeiboService {
	private static Logger log = LoggerFactory.getLogger(WeiboServiceImpl.class);

	@Override
	public String getauthUrl() {
		try {
			return new Oauth().authorize("code");
		} catch (WeiboException e) {
			log.debug("get Oauth url error!" + e.getMessage());
			return null;
		}
	}

	@Override
	public String getTockenByCode(String code) throws WeiboAuthException {
		try {
			return new Oauth().getAccessTokenByCode(code).getAccessToken();
		} catch (WeiboException e) {
			throw new WeiboAuthException(e);
		}
	}
	
	@Override
	public List<cn.nzcong.weibo.model.Status> getTimeLine(String tocken) throws WeiboAuthException{
		log.debug("getTimeLine - tocken : " + tocken);
		Timeline tm = new Timeline(tocken);
		List<cn.nzcong.weibo.model.Status> statusList = new ArrayList<cn.nzcong.weibo.model.Status>();
		try {
			StatusWapper status = tm.getFriendsTimeline();
			for (Status s : status.getStatuses()) {
				cn.nzcong.weibo.model.Status temps = convert(s.getRetweetedStatus() == null ? s : s.getRetweetedStatus());
				statusList.add(temps);
			}
		} catch (WeiboException e) {
			log.error("get Time line error!!" + e);
			throw new WeiboAuthException();
		}
		return statusList;
	}

	// 微博原文转换
	private cn.nzcong.weibo.model.Status convert(Status s) {
		if (s == null)
			return null;
		cn.nzcong.weibo.model.Status vo = new cn.nzcong.weibo.model.Status();
		vo.setBmiddlePic(s.getBmiddlePic());
		vo.setCommentsCount(s.getCommentsCount());
		vo.setCreatedAt(s.getCreatedAt());
		vo.setId(s.getId());
		vo.setLatitude(s.getLatitude());
		vo.setLongitude(s.getLongitude());
		vo.setMid(s.getMid());
		vo.setOriginalPic(s.getOriginalPic());
		vo.setRepostsCount(s.getRepostsCount());
		vo.setRetweetedStatus(convert(s.getRetweetedStatus()));
		vo.setText(s.getText());
		vo.setThumbnailPic(s.getThumbnailPic());
		return vo;
	}


}
