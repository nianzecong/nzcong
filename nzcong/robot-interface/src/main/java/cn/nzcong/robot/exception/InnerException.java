package cn.nzcong.robot.exception;

public class InnerException extends RobotException{
	private static final long serialVersionUID = 7392122565545892831L;

	public InnerException(String message) {
		super(message);
	}

	public InnerException(String messgae, Throwable e) {
		super(messgae, e);
	}
}
