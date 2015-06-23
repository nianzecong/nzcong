package cn.nzcong.blog.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.nzcong.blog.util.AppConfig;

public class InitListener extends HttpServlet implements ServletContextListener {

	private static final long serialVersionUID = -5305055162919415411L;
	private static final Logger logger = LoggerFactory.getLogger(InitListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AppConfig.configure(sce.getServletContext().getRealPath("/") + "/WEB-INF/appconfig.xml");
		logger.debug("Application Start.....");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("Application End.....");
	}

}
