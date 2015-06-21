package cn.nzcong.robot.exception;

public class RobotException extends Exception {
	private static final long serialVersionUID = 5536871319948895807L;

	public RobotException(String message) {
		super(message);
	}

	public RobotException(String messgae, Throwable e) {
		super(messgae, e);
	}

}
