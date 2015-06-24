package cn.nzcong.weiboservice.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.nzcong.config.service.ConfigService;
import cn.nzcong.weibo.model.Weibo;
import cn.nzcong.weibo.service.WeiboService;
import cn.nzcong.weiboservice.dao.WeiboDao;
import cn.nzcong.weiboservice.service.TimerService;

@Component
@Service
public class TimerServiceImpl implements TimerService{
	
	private static Logger log = LoggerFactory.getLogger(TimerServiceImpl.class);
	private static final String TOKEN_KEY = "weibo.admin.token";
	
	@Autowired
	private WeiboDao weiboDao;
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private ConfigService configService;
	
	public String getToken(){
		return configService.getParameter(TOKEN_KEY);
	}
	
	@Scheduled(cron="0 0/4 * * * ? ")   //每4min执行一次 
	@Override
	public void checkTimeLine() {
		try {
			// 随机跳过
			int random = new Random().nextInt(100);
			log.debug("checkTimeLine - random : " + random);
			if(random > 20){ // 4/5的几率跳过
				return;
			}
			// 刷新时间线
			List<Weibo> weiboList = weiboService.getTimeLine(getToken());
			log.debug("checkTimeLine : " + weiboList.size() +  " ... ");
			for(Weibo weibo : weiboList){
				if(weibo.getRepostsCount() > 500 && weiboDao.getWeibo(weibo.getWeiboid()) == null){
					weiboDao.addWeibo(weibo);
					log.debug("checkTimeLine - insert - " + weibo.getWeiboid());
				}
			}
		} catch (Exception e) {
			log.error("checkTimeLine - error : " + e, e);
		}
	}

	public static void main(String[] args) {
//		for(int i = 0 ; i < 1000 ; i++){
//			System.out.print(String.valueOf(new Random().nextInt(100) < 20 ? "true" : "") + "\t");
//			if(i%20 == 19)
//				System.out.println();
//		}
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		TimerService service = context.getBean(TimerService.class);
//		System.out.println(service.getToken());
		service.checkTimeLine();
	}
	
}
