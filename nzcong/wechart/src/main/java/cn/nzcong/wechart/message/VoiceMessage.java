package cn.nzcong.wechart.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class VoiceMessage extends BaseMessage{
	
	private static final long serialVersionUID = -1617262303859861785L;
	
	private String mediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体 
	private String format;// 语音格式：amr 
	private String recognition;// 语音识别结果，UTF8编码 
	
	public VoiceMessage(){
		super("voice");
	}
	
	public VoiceMessage(Document document){
		super(document);
		Element root = document.getRootElement();
		this.mediaId = root.elementText("MediaId");
		this.format = root.elementText("Format");
		this.recognition = root.elementText("Recognition");
	}

	@Override
	public Document encode() {
		Document resp = super.getCommonEncode();
		Element MediaId = DocumentHelper.createElement("MediaId");
		Element Recognition = DocumentHelper.createElement("Recognition");
		Element Format = DocumentHelper.createElement("Format");
		MediaId.setText(this.mediaId);
		Recognition.setText(this.recognition);
		Format.setText(this.format);
		
		Element root = resp.getRootElement();
		root.add(MediaId);
		root.add(Recognition);
		root.add(Format);
		return resp;
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
	public String toString() {
		return "{mediaId:" + mediaId + ", format:" + format + ", recognition:" + recognition + ", msgId:" + msgId + ", toUser:" + toUser + ", fromUser:" + fromUser + ", createTime:" + createTime + ", msgType:" + msgType + "}";
	}


	
}
