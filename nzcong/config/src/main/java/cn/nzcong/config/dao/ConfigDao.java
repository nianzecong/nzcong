package cn.nzcong.config.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ConfigDao {

	public abstract String addParameter(@Param("key") String key, @Param("value") String value);
	
	public abstract String getParameter(@Param("key") String key);
	
	public abstract void setParameter(@Param("key") String key, @Param("value") String value);
	
	public abstract Map<String, String > getParameterMap();
	
}
