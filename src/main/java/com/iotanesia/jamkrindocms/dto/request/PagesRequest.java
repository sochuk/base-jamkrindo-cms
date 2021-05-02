package com.iotanesia.jamkrindocms.dto.request;

public class PagesRequest {
	
	private Integer page = 1;
	private Integer pageSize = 10;
	private String type;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
