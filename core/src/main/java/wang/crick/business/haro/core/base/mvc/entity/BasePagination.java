package wang.crick.business.haro.core.base.mvc.entity;

import java.io.Serializable;

public abstract class BasePagination implements Serializable {

    private int pageSize;
    private int pageNo;
    private int pageTotal;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCurrentRows() {
        return getPageNo() * getPageSize();
    }
}
