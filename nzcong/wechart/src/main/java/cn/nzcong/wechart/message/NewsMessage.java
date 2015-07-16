package cn.nzcong.wechart.message;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class NewsMessage extends BaseMessage{

	private static final long serialVersionUID = -6142566566588307419L;
	
	private int articleCount;
	private List<Article> articles;
	
	public NewsMessage(){
		super("news");
	}
	
	public NewsMessage(Document document){
		super(document);
		//TODO 没有上行图文消息用不到解码
	}
	
	public Article newArticle(){
		return new Article();
	}
	
	public class Article{
		protected String title;
		protected String description;
		protected String picUrl;
		protected String url;
	
		public Article(){
			
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String toString() {
			return "{title:" + title + ", description:" + description + ", picUrl:" + picUrl + ", url:" + url + "}";
		}
	}
	
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "{articleCount:" + articleCount + ", articles:" + articles + "}";
	}

	@Override
	public Document encode() {
		Document document = super.getCommonEncode();
		
		Element articleCountElt = DocumentHelper.createElement("ArticleCount");
		Element articlesElt = DocumentHelper.createElement("Articles");

		articleCountElt.setText(String.valueOf(this.articles.size()));
		for(Article art : this.articles){
			Element itemElt = DocumentHelper.createElement("item");
			
			Element titleElt = DocumentHelper.createElement("Title");
			Element descriptionElt = DocumentHelper.createElement("Description");
			Element picUrlElt = DocumentHelper.createElement("PicUrl");
			Element urlElt = DocumentHelper.createElement("Url");
			
			titleElt.setText(art.title);
			descriptionElt.setText(art.description);
			picUrlElt.setText(art.picUrl);
			urlElt.setText(art.url);
			
			itemElt.add(titleElt);
			itemElt.add(descriptionElt);
			itemElt.add(picUrlElt);
			itemElt.add(urlElt);
			articlesElt.add(itemElt);
		}
		Element root = document.getRootElement();
		root.add(articleCountElt);
		root.add(articlesElt);
		return document;
	}

}
