package cn.nzcong.wechart.exception;

public class MessageException extends Exception{

	private static final long serialVersionUID = 8638606241140030206L;

	public MessageException(String message){
		super(message);
	}
	
	public MessageException(String message, Throwable e){
		super(message, e);
	}
	
}
