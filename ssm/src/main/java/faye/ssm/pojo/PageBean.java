package faye.ssm.pojo;

import java.util.List;

public class PageBean<T> {

	private Integer pageNow;

	private Integer pageSize;

	private Integer pageCount;

	private Integer datacount;

	private Integer index;

	private List<T> list;

	public Integer getIndex() {
		index = (pageNow - 1) * pageSize;
		return index;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		pageCount = (int) Math.ceil(datacount / (double) pageSize);
		return pageCount;
	}

	public Integer getDatacount() {
		return datacount;
	}

	public void setDatacount(Integer datacount) {
		this.datacount = datacount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
