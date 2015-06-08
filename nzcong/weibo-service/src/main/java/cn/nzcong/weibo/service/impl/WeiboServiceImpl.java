package cn.nzcong.weibo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.nzcong.utils.DateTimeUtils;
import cn.nzcong.weibo.exception.WeiboAuthException;
import cn.nzcong.weibo.model.User;
import cn.nzcong.weibo.model.Weibo;
import cn.nzcong.weibo.service.WeiboService;
import cn.nzcong.weibo.weibo4j.Oauth;
import cn.nzcong.weibo.weibo4j.Place;
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
	public List<cn.nzcong.weibo.model.Weibo> getTimeLine(String token) throws WeiboAuthException {
		log.debug("getTimeLine - tocken : " + token);
		Timeline tm = new Timeline(token);
		List<cn.nzcong.weibo.model.Weibo> statusList = new ArrayList<cn.nzcong.weibo.model.Weibo>();
		try {
			StatusWapper status = tm.getFriendsTimeline();
			for (Status s : status.getStatuses()) {
				cn.nzcong.weibo.model.Weibo temps = convert(s.getRetweetedStatus() == null ? s : s.getRetweetedStatus());
				statusList.add(temps);
			}
		} catch (WeiboException e) {
			log.error("get Time line error!!" + e);
			throw new WeiboAuthException();
		}
		return statusList;
	}
	
	public List<cn.nzcong.weibo.model.Weibo> getNearByTimeLine(String token, String lat, String lon) throws WeiboAuthException {
		log.debug("getTimeLine - tocken : " + token);
		List<cn.nzcong.weibo.model.Weibo> statusList = new ArrayList<cn.nzcong.weibo.model.Weibo>();
		try {
			StatusWapper status = new Place(token).nearbyTimeLine(lat, lon);
			for (Status s : status.getStatuses()) {
				cn.nzcong.weibo.model.Weibo temps = convert(s.getRetweetedStatus() == null ? s : s.getRetweetedStatus());
				statusList.add(temps);
			}
		} catch (WeiboException e) {
			log.error("get Time line error!!" + e);
			throw new WeiboAuthException();
		}
		return statusList;
	}

	// 微博原文转换
	private cn.nzcong.weibo.model.Weibo convert(Status s) {
		if (s == null)
			return null;
		cn.nzcong.weibo.model.Weibo vo = new cn.nzcong.weibo.model.Weibo();
		vo.setBmiddlePic(s.getBmiddlePic());
		vo.setCommentsCount(s.getCommentsCount());
		vo.setCreatedat(DateTimeUtils.getDateTimeStr(s.getCreatedAt()));
		vo.setWeiboid(s.getId());
		vo.setLatitude(s.getLatitude());
		vo.setLongitude(s.getLongitude());
		vo.setMid(s.getMid());
		vo.setOriginalPic(s.getOriginalPic());
		vo.setRepostsCount(s.getRepostsCount());
		vo.setRetweetedStatus(convert(s.getRetweetedStatus()));
		vo.setText(s.getText());
		vo.setThumbnailPic(s.getThumbnailPic());
		vo.setUserid(s.getUser().getId());
		vo.setUserscreenname(s.getUser().getScreenName());
		return vo;
	}

	private User convert(cn.nzcong.weibo.weibo4j.model.User user) {
		if (user == null)
			return null;
		User vo = new User();
		vo.setDescription(user.getDescription());
		vo.setGender(user.getGender());
		vo.setId(user.getId());
		vo.setImageUrl(user.getProfileImageUrl());
		vo.setLocation(user.getLocation());
		vo.setScreenName(user.getScreenName());
		vo.setUrl(user.getUrl());
		return vo;
	}

	public static void main(String[] args) {
		WeiboServiceImpl client = new WeiboServiceImpl();
		try {
			System.out.println(client.getauthUrl());
			System.out.println(client.getNearByTimeLine("2.00EaJgPD0Jdf5406d7fda56e7ilL6D", "39.9", "116.4"));
		} catch (WeiboAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
