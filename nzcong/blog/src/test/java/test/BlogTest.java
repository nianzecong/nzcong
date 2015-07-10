package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.service.BlogService;
import cn.nzcong.blog.util.UuidUtils;
import cn.nzcong.config.service.ConfigService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class BlogTest {/*

	private static final String blogId = UuidUtils.getUUID();
	
	@Autowired
	ConfigService configService;
	@Autowired
	BlogService blogService;
	
	private void print(String content){
		System.out.println("****************************\n");
		System.out.println(content);
		System.out.println("\n****************************");
	}
	
	
	@Test
	public void getParameter(){
		String token = configService.getParameter("weibo.admin.token");
		print(token);
		configService.setParameter("weibo.admin.token", token);
	}
	
	@Test
	public void addBlog(){
		Blog blog = new Blog();
		blog.setCatagoryId("4037B16D-F66E-4CE5-A15B-BD5C4D9A87FB");
		blog.setId(blogId);
		blog.setText("#test");
		blog.setTitle("这是一个测试博客");
		blog.setType(0);// 草稿
		print("addBlog\n" + blogService.addBlog(blog));
	}
	
	@Test
	public void searchBlogList(){
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> types = new ArrayList<String>();
		types.add("2");
		params.put("types", types);
		print("searchBlogList\n" + blogService.getPagedList(params, 1, 2).toString());
	}
	
	@Test
	public void getBlogByid(){
		print("getBlogByid\n" + blogService.getBlog("0C348FD5-371F-49C8-90AA-D1D572B53512").toString());
	}
	@Test
	public void updateBlog(){
		print("updateBlog\n" + blogService.updateBlog(blogService.getBlog("0C348FD5-371F-49C8-90AA-D1D572B53512")));
	}
	
	@Test
	public void getCategories(){
		print("getCategories\n" + blogService.getCategoryList());
	}
	
	public static void main(String[] args) {
		System.out.println(UuidUtils.getUUID());
	}
*/}
