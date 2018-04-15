package wang.crick.business.haro.core.base.dao;

import java.sql.SQLException;


public interface IDialect {
    String getSimpleSQL() throws SQLException;

    String getRowCountSql(String paramString);

    void getTopSql(PageContext paramPageContext);
}
