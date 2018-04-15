package wang.crick.business.haro.core.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.util.StringUtils;
import wang.crick.business.haro.core.base.dao.orm.MapResultSetExtractor;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;
import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 封装数据库查询方法
 */
public abstract class BaseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IDialect dialect;

    protected <T extends BaseDomain> void validationAndGenerate(T entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(HelperGuid.getRandomStr());
        }
    }

    protected boolean insert(String sql, Object... args) {
        return 1 == jdbcTemplate.update(sql, args);
    }

    protected boolean batchInsert(String sql, List<Object[]> batchArgs) {
        boolean flag = false;
        int[] results = jdbcTemplate.batchUpdate(sql, batchArgs);
        if (results.length > 0) {
            for (int i : results) {
                if (i > 0) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 根据select 查询记录是否存在， 标志位为1
     * 例如：select 1 from user
     */
    protected boolean isExisted(String sql, Object... args) {
        List<Integer> results = null;
        try {
            results = jdbcTemplate.query(sql, args, new RowMapperResultSetExtractor<Integer>(new SingleColumnRowMapper<Integer>(Integer.class), 1));
        } catch (Exception e) {

        }
        boolean flag = false;
        if (null != results && results.size() > 0) {
            Integer result = results.iterator().next();
            flag = null != result && 1 == result;
        }
        return flag;
    }

    protected <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
        List<T> results = null;
        try {
            results = jdbcTemplate.query(sql, rowMapper, args);
        } catch (Exception e) {

        }
        return results;
    }

    protected <T> List<T> queryPaged(String sql, BasePageDto page, RowMapper<T> rowMapper, Object... args) {
        int totalCount = queryOneInt(this.dialect.getRowCountSql(sql), args);
        page.setTotalCount(totalCount);
        if (page.getPageNo() > page.getTotalPages()) {
            page.setPageNo(1);
        }
        List<Object> params = null;
        if (args != null) {
            params = Arrays.asList(args);
        }
        PageContext context = new PageContext(sql, page.getStartIndex(), page.getEndIndex(), params);
        this.dialect.getTopSql(context);
        return query(context.getSql(), rowMapper, context.getOrginalParameters().toArray());
    }


    protected <T> T queryOne(String sql, RowMapper<T> rowMapper, Object... args) {
        T result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, rowMapper, args);
        } catch (Exception e) {

        }
        return result;
    }

    protected Map<String, Object> queryMap(String sql, Object... args) {
        Map<String, Object> result = null;
        try {
            result = jdbcTemplate.query(sql, new MapResultSetExtractor<String, Object>(), args);
        } catch (Exception e) {

        }
        return result;
    }

    protected Map<String, Object> queryOneMap(String sql, Object... args) {
        Map<String, Object> result = null;
        try {
            result = jdbcTemplate.queryForMap(sql, args);
        } catch (Exception e) {

        }
        return result;
    }

    protected String queryOneString(String sql, Object... args) {
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {

        }
        return result;
    }

    protected int queryOneInt(String sql, Object... args) {
        Integer result = 0;
        try {
            result = jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {

        }
        return result;
    }

    protected int countTenant(String tableName, String tenantId) {
        int result = 0;
        try {
            result = queryOneInt("select count(1) from " + tableName + " where tenant_id=?", tenantId);
        } catch (Exception e) {

        }
        return result;
    }

    protected boolean update(String sql, Object... args) {
        return 1 == jdbcTemplate.update(sql, args);
    }

    protected boolean delete(String sql, Object... args) {
        return 1 == jdbcTemplate.update(sql, args);
    }

    protected int clear(String tableName) {
        return jdbcTemplate.update("truncate table " + tableName);
    }
}
