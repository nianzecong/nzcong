package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class VoiceMessage extends Message{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String mediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体 
	private String format;// 语音格式：amr 
	private String recognition;// 语音识别结果，UTF8编码 
	
	public VoiceMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.mediaId = root.elementText("MediaId");
		this.format = root.elementText("Format");
		this.recognition = root.elementText("Recognition");
	}

	public String getMediaId() {
		return mediaId;
	}


	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public String getRecognition() {
		return recognition;
	}


	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}


	@Override
	public Document encode() {
		
		return null;
	}

	
}
