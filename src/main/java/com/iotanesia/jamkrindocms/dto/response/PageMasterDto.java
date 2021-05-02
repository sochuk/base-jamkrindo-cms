package com.iotanesia.jamkrindocms.dto.response;

public class PageMasterDto {

    private Object list;
    private int currentPage;
    private int pageSize;
    private int totalItem;

    public void setList(Object list) {
        this.list = list;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public Object getList() {
        return list;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getTotalItem() {
        return totalItem;
    }
}
