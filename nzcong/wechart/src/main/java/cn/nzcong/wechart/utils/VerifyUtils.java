package cn.nzcong.wechart.utils;

import org.apache.commons.lang3.StringUtils;

import cn.nzcong.wechart.exception.MessageException;

public class VerifyUtils {

	private VerifyUtils(){
		
	}
	
	/**
	 * 验证参数是否为空
	 * 
	 * @param params
	 * @throws MessageException<br>有参数为空
	 */
	public static void verifyBlank(String... params) throws MessageException {
		for (String param : params) {
			if (StringUtils.isBlank(param)) {
				throw new MessageException("必要的参数为空" + params);
			}
		}
	}
	
}
