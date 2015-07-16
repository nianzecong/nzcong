package cn.nzcong.wechart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.wechart.dao.MessageTemplateDao;
import cn.nzcong.wechart.exception.MessageException;
import cn.nzcong.wechart.message.BaseMessage;
import cn.nzcong.wechart.message.LinkMessage;
import cn.nzcong.wechart.message.NewsMessage;
import cn.nzcong.wechart.message.TextMessage;
import cn.nzcong.wechart.model.Template;
import cn.nzcong.wechart.service.BaseMessageHandler;

@Service("textMessageTemplateHandler")
public class TextMessageTemplateHandler extends BaseMessageHandler{

	private static Logger log = LoggerFactory.getLogger(TextMessageTemplateHandler.class);

	@Autowired
	private MessageTemplateDao messageTemplateDao;
	
	@Override
	public BaseMessage handle(BaseMessage _msg) throws MessageException {
		log.debug("handle...");
		TextMessage msg = (TextMessage) _msg;
		BaseMessage resMessage = null; 
		List<Template> list = messageTemplateDao.getList();
		for(Template t : list){
			String[] keys = t.getKeyWords().split(",");
			for(String key : keys){
				if(msg.getContent().indexOf(key) >= 0){
					System.out.println("=== math : " + t);
					if("text".equals(t.getMessageType())){
						// 回复text消息
						resMessage = new TextMessage();
						((TextMessage)resMessage).setContent(t.getMessageContent());
					} else if("news".equals(t.getMessageType())){
						resMessage = new NewsMessage();
						//TODO 暂时不需要回复多条图文消息，等需要时需要与数据库一起处理
						((NewsMessage)resMessage).setArticleCount(1);
						// 实例化一条图文消息
						NewsMessage.Article art = ((NewsMessage)resMessage).newArticle();
						art.setDescription(t.getDescription());
						art.setPicUrl(t.getPic());
						art.setTitle(t.getTitle());
						art.setUrl(t.getMessageContent());
						// 加入消息列表
						List<NewsMessage.Article> arts = new ArrayList<NewsMessage.Article>();
						arts.add(art);
						((NewsMessage)resMessage).setArticles(arts);
					} else {
						log.error("handle - 未知的模板消息类型 - " + t);
						resMessage = new TextMessage();
						((TextMessage)resMessage).setContent("");
					}
					resMessage.setCreateTime(Integer.parseInt(String.valueOf(new Date().getTime() / 1000)));
					resMessage.setFromUser(msg.getToUser());
					resMessage.setMsgId(msg.getMsgId());
					resMessage.setToUser(msg.getFromUser());
					break;
				}
			}
			if(resMessage != null){
				log.debug("handle - resMessage： " + resMessage);
				return resMessage;
			}
		}
		return super.nextHandler(_msg);
	}
	
	
	
}
