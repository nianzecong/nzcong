package cn.nzcong.wechart.dao;

import java.util.List;

import cn.nzcong.wechart.model.Template;

public interface MessageTemplateDao {

	public abstract int add(Template t);
	
	public abstract int update(Template t);
	
	public abstract List<Template> getList();
	
}
