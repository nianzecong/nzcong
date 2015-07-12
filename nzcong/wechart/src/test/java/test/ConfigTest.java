package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nzcong.wechart.dao.MessageTemplateDao;
import cn.nzcong.wechart.model.Template;
import cn.nzcong.wechart.utils.UuidUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class ConfigTest {

	@Autowired
	MessageTemplateDao messageTemplateDao;
	
	private void print(String content){
		System.out.println("****************************\n");
		System.out.println(content);
		System.out.println("\n****************************");
	}
	
	
	@Test
	public void add(){
		Template t = new Template();
		t.setTemplateId(UuidUtils.getUUID());
		t.setKeyWords("ceshi");
		t.setMessageContent("http://nzcong.cn/blog/view/239386EB-1B49-4A6A-A139-EE91911896A9");
		t.setMessageType("text");
		t.setTitle("title");
		t.setDescription("des");
		t.setPic("pic");
		//print("add\n" + messageTemplateDao.add(t));
	}
	
	@Test
	public void getList(){
		print("getList\n" + messageTemplateDao.getList());
	}
	
}
