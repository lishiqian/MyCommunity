package com.lsq.community.common;

import com.github.pagehelper.PageInfo;

public class PageData {
    private int pageCurrentNum;
    private int pageTotalNum;
    private Object data;

    public PageData(){}

    public PageData(PageInfo pageInfo,Object data){
        this.init(pageInfo);
        this.data = data;
    }

    public int getPageCurrentNum() {
        return pageCurrentNum;
    }

    public void setPageCurrentNum(int pageCurrentNum) {
        this.pageCurrentNum = pageCurrentNum;
    }

    public int getPageTotalNum() {
        return pageTotalNum;
    }

    public void setPageTotalNum(int pageTotalNum) {
        this.pageTotalNum = pageTotalNum;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void init(PageInfo pageInfo){
        this.pageCurrentNum = pageInfo.getPageNum();
        this.pageTotalNum = pageInfo.getPages();
    }
}
