package cn.nzcong.config.service.impl;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nzcong.config.dao.ConfigDao;
import cn.nzcong.config.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired
	ConfigDao configDao;
	
	@Override
	public String getParameter(String key) {
		return configDao.getParameter(key);
	}

	@Override
	public void setParameter(String key, String value) {
		configDao.setParameter(key, value);
	}

	@Override
	public Map<String, String> getParameterMap() {
		return configDao.getParameterMap();
	}

}
