package cn.nzcong.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.blog.dao.BlogDao;
import cn.nzcong.blog.model.Blog;
import cn.nzcong.blog.model.Category;
import cn.nzcong.blog.model.PageList;
import cn.nzcong.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private static Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
	
	@Autowired
	BlogDao blogDao;

	@Override
	public String addBlog(Blog blog) {
		return String.valueOf(blogDao.addBlog(blog));
	}

	@Override
	public int updateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}

	@Override
	public List<Blog> getDrafts() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", 0);
		return blogDao.searchBlogs(params);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return blogDao.searchBlogsCount(param);
	}

	@Override
	public PageList getPagedList(Map<String, Object> param, int currentPage, int pageSize) {
		PageList page = new PageList();
		pageSize = pageSize < 1 ? 10 : pageSize;
		int total = blogDao.searchBlogsCount(param);
		int totalpage = (total + pageSize - 1) / pageSize;
		currentPage = currentPage < 1 ? 1 : totalpage > 0 && currentPage > totalpage ? totalpage : currentPage;
		int offset =  (currentPage - 1) * pageSize; 
		param.put("offset", offset);
		param.put("pageSize", pageSize);
		List datas = blogDao.searchBlogs(param);
		
		page.setCurrentPage(currentPage);
		page.setDatas(datas);
		page.setTotal(total);
		page.setTotalPage(totalpage);
		
		return page;
	}

	@Override
	public Blog getBlog(String id) {
		return blogDao.getBlog(id);
	}

	@Override
	public Blog getById(String id) {
		return blogDao.getBlog(id);
	}

	@Override
	public List<Category> getCategoryList() {
		return blogDao.getCategories();
	}

}
