package cn.nzcong.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	private static String appConfigpath;
	private static Map<String, String> propertyCache;
	private static String confText = "";

	public static String getParameter(String name) {
		if (propertyCache != null && propertyCache.containsKey(name)) {
			return (String) propertyCache.get(name);
		}
		return null;
	}

	public static Map<String, String> getPropertyCache() {
		return propertyCache;
	}

	public static boolean reload() {
		return configure(appConfigpath);
	}

	public static boolean configure(String filename) {
		boolean result = true;
		if (filename != null && filename.trim().length() > 0) {
			appConfigpath = filename;
			if (propertyCache != null) {
				propertyCache.clear();
			} else {
				propertyCache = new HashMap<String, String>();
			}
			try {
				SAXReader saxReader = new SAXReader();
				org.dom4j.Document doc = saxReader.read(new File(filename));
				confText = doc.asXML();
				Iterator<Element> elements = doc.getRootElement().elementIterator();
				while (elements.hasNext()) {
					Element subelement = (Element) elements.next();
					String subelementName = subelement.getName();
					if ("param".equals(subelementName)) {
						Attribute key = subelement.attribute("key");
						propertyCache.put(key.getValue(), subelement.getText());
					}
				}
			} catch (Exception e) {
				logger.error("configure error:", e);
				result = false;
			}
		} else {
			logger.error("configure error: appConfigpath is null");
			result = false;
		}
		return result;
	}

	public static boolean modify(String paramName, String paramValue) {
		boolean result = false;
		if (!StringUtils.isEmpty(paramName) && !StringUtils.isEmpty(paramValue)) {
			try {
				org.dom4j.Document doc = DocumentHelper.parseText(confText);
				List<Element> list = doc.selectNodes("/config/param");
				Iterator<Element> iter = list.iterator();
				while (iter.hasNext()) {
					Element element = (Element) iter.next();
					String key = element.attributeValue("key");
					if (paramName.equals(key)) {
						element.setText(paramValue);
						result = true;
					}
				}
				// 未找到节点，新建
				if (!result) {
					Element root = doc.getRootElement();
					Element param = root.addElement("param");
					param.addAttribute("key", paramName);
					param.setText(paramValue);
					result = true;
				}
				if (result) {
					confText = doc.asXML();
					saveConf(confText);
				}
			} catch (Exception e) {
				logger.error("modify error:", e);
			}
		}
		return result;
	}

	public static void saveConf(String confText) {
		try {
			FileWriter fw = new FileWriter(appConfigpath);
			confText = confText.replaceAll("&amp;", "&");
			confText = confText.replaceAll("&", "&amp;");
			fw.write(confText);
			fw.close();
		} catch (Exception e) {
			logger.error("Error save conf File:", e);
		}

	}
}
