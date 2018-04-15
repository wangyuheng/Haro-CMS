package wang.crick.business.haro.core.base.mvc.entity;

import java.io.Serializable;

public class BasePageDto implements Serializable {

    private static final long serialVersionUID = -8937268303802723671L;
    private int totalCount = 0;
    private int pageNo = 1;
    private int pageSize = 10;
    private int turning = 0;
    private String orderBy = null;
    private String groupBy = null;
    private boolean lazy = false;

    public final int getTotalCount() {
        return this.totalCount;
    }

    public final void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public final int getPageNo() {
        return this.pageNo;
    }

    public final void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTurning() {
        return this.turning;
    }

    public void setTurning(int turning) {
        this.turning = turning;
    }

    public final String getOrderBy() {
        return this.orderBy;
    }

    public final void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public final String getGroupBy() {
        return this.groupBy;
    }

    public final void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public boolean isLazy() {
        return this.lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public int getTotalPages() {
        if (this.totalCount <= 0) {
            return 1;
        }
        int totalPages = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize > 0) {
            totalPages++;
        }
        return totalPages;
    }

    public final int getStartIndex() {
        return (this.pageNo - 1) * this.pageSize + 1;
    }

    public final int getEndIndex() {
        return this.pageNo * this.pageSize;
    }

    public boolean isTurningPage() {
        return this.turning == 1;
    }
}
