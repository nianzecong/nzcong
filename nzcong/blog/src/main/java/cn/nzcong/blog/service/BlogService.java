package cn.nzcong.blog.service;

import java.util.List;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.model.Catagory;

public interface BlogService {

	public abstract Blog getBlog(String id);
	
	/**
	 * 添加博客，返回ID
	 * @param blog
	 * @return
	 */
	public abstract String addBlog(Blog blog);
	
	public abstract int updateBlog(Blog blog);
	
	public abstract List<Blog> getDrafts();
	
	public abstract int getCount();
	
	public abstract List<Blog> getPagedList(int currentPage, int pageSize);
	
	public abstract List<Catagory> getCatagorys();
	
}
