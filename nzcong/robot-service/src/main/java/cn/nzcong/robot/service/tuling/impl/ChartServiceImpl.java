package cn.nzcong.robot.service.tuling.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import cn.nzcong.robot.exception.InnerException;
import cn.nzcong.robot.exception.RobotException;
import cn.nzcong.robot.service.RobotService;

import com.alibaba.fastjson.JSONObject;

public class ChartServiceImpl implements RobotService {

	public String chart(String content) throws RobotException {
		String result = "";
		try {
			JSONObject resultJson = JSONObject.parseObject(sendGet(content));
			result = resultJson.getString("text");
		} catch (Exception e) {
			throw new InnerException("get TULING resultl error", e);
		}
		return result;
	}

	
	public String sendGet(String content) throws Exception{
		String APIKEY = "97930e2bd64cd0a68feda45eb20badb3";
		String INFO = URLEncoder.encode(content, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 取得输入流，并使用Reader读取
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			throw e;
		} finally{
			if(reader != null){
				reader.close();
			}
			// 断开连接
			if(connection != null){
				connection.disconnect();
			}
		}
		return URLDecoder.decode(sb.toString(), "utf-8");
	}
	
	public static void main(String[] args) throws Exception {
		ChartServiceImpl s = new ChartServiceImpl();
		System.out.println(s.sendGet("讲个笑话"));
		
		try{
			System.out.println("try");
			throw new Exception();
		} catch (Exception e){
			System.out.println("catch");
			throw e;
		} finally{
			System.out.println("finally");
		}
	}
	
}
