package cn.nzcong.robot.service;

import cn.nzcong.robot.exception.RobotException;

public interface RobotService {

	public abstract String chart(String content) throws RobotException;
	
}
