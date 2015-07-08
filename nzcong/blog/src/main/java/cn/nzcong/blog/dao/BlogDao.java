package cn.nzcong.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.model.Category;

public interface BlogDao {

	/**
	 * 添加博客，返回ID
	 * @param blog
	 * @return
	 */
	public abstract int addBlog(@Param("blog") Blog blog);
	
	/**
	 * 修改博客
	 * @param blog
	 * @return
	 */
	public abstract int updateBlog(@Param("blog") Blog blog);
	
	/**
	 * 根据ID获取博客
	 * @param id
	 * @return
	 */
	public abstract Blog getBlog(@Param("id") String id);
	
	/**
	 * 获取总数
	 * @param param
	 * @return
	 */
	public abstract int searchBlogsCount(@Param("param") Map param);
	
	/**
	 * 获取列表
	 * @param param
	 * @return
	 */
	public abstract List<Blog> searchBlogs(@Param("param") Map param);

	/**
	 * 获取所有分类
	 * @return
	 */
	public abstract List<Category> getCategories();
	
}
