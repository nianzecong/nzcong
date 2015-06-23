package cn.nzcong.config.service;

import java.util.Map;

public interface ConfigService {

	public abstract String getParameter(String key);
	
	public abstract void setParameter(String key, String value);
	
	public abstract Map<String, String > getParameterMap();
	
}
