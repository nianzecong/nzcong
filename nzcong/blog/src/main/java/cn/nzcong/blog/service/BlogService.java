package cn.nzcong.blog.service;

import java.util.List;
import java.util.Map;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.model.Category;
import cn.nzcong.blog.model.PageList;

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
	
	public abstract int getCount(Map<String, Object> param);
	
	public abstract Blog getById(String id);
	
	public abstract PageList getPagedList(Map<String, Object> param, int currentPage, int pageSize);
	
	public abstract List<Category> getCategoryList();
}
