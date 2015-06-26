package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nzcong.blog.service.BlogService;
import cn.nzcong.config.service.ConfigService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class BlogTest {

	@Autowired
	ConfigService configService;
	@Autowired
	BlogService blogService;
	
	
	@Test
	public void getParameter(){
		String token = configService.getParameter("weibo.admin.token");
		System.out.println("****** " + token);
		configService.setParameter("weibo.admin.token", token);
	}
	
}
