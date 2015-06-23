package cn.nzcong.blog.dao;

import java.util.Map;

import cn.nzcong.blog.model.Blog;

public interface BlogDao {

	public abstract int addBlog(Blog blog);
	
	public abstract int updateBlog(Blog blog);
	
	public abstract int searchBlogsCount(Map params);
	
	public abstract int searchBlogs(Map params);
	
}
