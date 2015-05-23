package cn.nzcong.weibo.exception;

public class WeiboAuthException extends Exception{

	private static final long serialVersionUID = 258415189422639546L;

	public WeiboAuthException(){
		
	}
	
	public WeiboAuthException(String message){
		super(message);
	}
	
	public WeiboAuthException(Throwable e){
		super(e);
	}

	public WeiboAuthException(String message, Throwable e){
		super(message, e);
	}
	
}
