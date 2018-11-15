package com.bzb.talentmarket.bean;

/**
 * @author bzb
 * @Description:
 * @date 2018/11/15 14:34
 */
public class PageBean {
    //当前页
    private Integer page = 1;
    //每页数量
    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getStart() {
        if (page < 1) {
            page = 1;
        }
        return page*limit-limit;
    }

}
