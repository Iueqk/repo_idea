package com.pojo;

public class PromotionAdVo {

    private Integer currentPage = 1;


    private Integer pageSize = 10;

    @Override
    public String toString() {
        return "PromotionAdVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
