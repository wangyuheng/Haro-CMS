package wang.crick.business.haro.core.base.dao.dialect;

import org.springframework.stereotype.Component;
import wang.crick.business.haro.core.base.dao.IDialect;
import wang.crick.business.haro.core.base.dao.PageContext;

import java.util.Locale;

@Component
public class MySqlDialect implements IDialect {
    private static final String DIALECT_SQL = "SELECT CURRENT_TIMESTAMP";

    public String getSimpleSQL() {
        return "SELECT CURRENT_TIMESTAMP";
    }

    public String getRowCountSql(String sql) {
        String lowerSql = sql.toLowerCase(Locale.US);
        StringBuilder countSql = new StringBuilder();
        if ((lowerSql.indexOf(" distinct(") > 0) || (lowerSql.indexOf(" distinct ") > 0) ||
                (lowerSql.indexOf(" group by ") > 0)) {
            countSql.append("SELECT COUNT(*) AS TNUM FROM (");
            countSql.append(sql);
            countSql.append(") PTBL");
        } else {
            int fromIndex = lowerSql.indexOf(" from ");
            countSql.append("SELECT COUNT(*) AS TNUM ");
            countSql.append(sql.substring(fromIndex));
        }
        return countSql.toString();
    }

    public void getTopSql(PageContext context) {
        StringBuilder outerSql = new StringBuilder(context.getOrginalSql());
        outerSql.append(" limit ?,? ");
        context.getOrginalParameters().add(context.getStartIndex() - 1);
        context.getOrginalParameters().add(context.getEndIndex() - context.getStartIndex() + 1);
        context.setSql(outerSql.toString());
    }
}
