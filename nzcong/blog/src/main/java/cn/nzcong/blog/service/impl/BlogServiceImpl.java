package cn.nzcong.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.blog.dao.BlogDao;
import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.model.Catagory;
import cn.nzcong.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogDao blogDao;
	
	@Override
	public String addBlog(Blog blog) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Blog> getDrafts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Blog> getPagedList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog getBlog(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Catagory> getCatagorys() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
