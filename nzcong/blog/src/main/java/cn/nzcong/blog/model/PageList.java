package cn.nzcong.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList implements Serializable {

	private static final long serialVersionUID = 4277321546371025573L;
	
	private int total;
	private int totalPage;
	private int currentPage;
	private List datas;
	
	public PageList(){
		total = 0;
		totalPage = 1;
		currentPage = 1;
		datas = new ArrayList();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "PageList = {total:" + total + ", totalPage:" + totalPage + ", currentPage:" + currentPage + ", datas:" + datas + "}";
	}
	
}
