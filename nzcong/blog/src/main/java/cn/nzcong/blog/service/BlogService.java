package cn.nzcong.blog.service;

import java.util.List;

import cn.nzcong.blog.model.Blog;

public interface BlogService {

	public abstract int addBlog(Blog blog);
	
	public abstract int updateBlog(Blog blog);
	
	public abstract List<Blog> getDrafts();
	
	public abstract int getCount();
	
	public abstract List<Blog> getPagedList(int currentPage, int pageSize);
	
}
