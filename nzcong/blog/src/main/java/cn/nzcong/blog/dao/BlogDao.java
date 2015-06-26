package cn.nzcong.blog.dao;

import java.util.Map;

import cn.nzcong.blog.model.Blog;

public interface BlogDao {

	/**
	 * 添加博客，返回ID
	 * @param blog
	 * @return
	 */
	public abstract String addBlog(Blog blog);
	
	public abstract int updateBlog(Blog blog);
	
	public abstract int searchBlogsCount(Map params);
	
	public abstract int searchBlogs(Map params);
	
}
