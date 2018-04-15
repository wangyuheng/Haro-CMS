package wang.crick.business.haro.core.base.dao;

import java.util.ArrayList;
import java.util.List;

public class PageContext {

    private final int startIndex;
    private final int endIndex;
    private final String orginalSql;
    private final List<Object> orginalParameters;
    private String sql;

    public PageContext(String orginalSql, int startIndex, int endIndex, List<Object> orginalParameters) {
        this.orginalSql = orginalSql;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        if (orginalParameters != null) {
            this.orginalParameters = new ArrayList<>();
            this.orginalParameters.addAll(orginalParameters);
        } else {
            this.orginalParameters = new ArrayList<>(2);
        }
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public String getOrginalSql() {
        return this.orginalSql;
    }

    public List<Object> getOrginalParameters() {
        return this.orginalParameters;
    }
}
