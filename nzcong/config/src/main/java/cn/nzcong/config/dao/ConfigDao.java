package cn.nzcong.config.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ConfigDao {

	public abstract String addParameter(String key, String value);
	
	public abstract String getParameter(@Param("key") String key);
	
	public abstract void setParameter(String key, String value);
	
	public abstract Map<String, String > getParameterMap();
	
}
