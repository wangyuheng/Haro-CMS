package wang.crick.business.haro.core.base.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;
import wang.crick.business.haro.core.base.mvc.entity.IEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class BaseMapperRepository {

    public static final String SQLNAME_SEPARATOR = ".";
    private final Logger logger = LogManager.getLogger(this.getClass());


    //third way I use spring bean factory to manage sqlsession so i do not close it.
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    //for simple crud
    private SqlSession sqlSession;

    @Autowired
    private void setSqlSession() {
        this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
    }

    //for batch insert and update
    private SqlSession batchSqlSession;

    @Autowired
    private void setBatchSqlSession() {
        this.batchSqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

    private String getDefaultSqlNamespace() {
        String namespace = this.getClass().getCanonicalName();
        return namespace;
    }

    private String getSqlName(String sqlId) {
        return this.getDefaultSqlNamespace() + SQLNAME_SEPARATOR + sqlId;
    }

    protected final <T extends BaseDomain> boolean insert(String sqlId, T entity) {
        Assert.notNull(entity);
        try {
            if (StringUtils.isEmpty(entity.getId())) {
                entity.setId(HelperGuid.getRandomStr());
            }
            sqlSession.insert(getSqlName(sqlId), entity);
            return true;
        } catch (Exception e) {
            logger.debug(e);
            return false;
        }
    }

    protected <T extends IEntity> T selectOne(String sqlId, String id) {
        try {
            return sqlSession.selectOne(getSqlName(sqlId), id);
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }
    }

    protected <T extends IEntity> T selectOne(String sqlId, Map<String, Object> parameterMap) {
        try {
            return sqlSession.selectOne(getSqlName(sqlId), parameterMap);
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }
    }

    protected String selectOneString(String sqlId, Map<String, Object> parameterMap) {
        try {
            return sqlSession.selectOne(getSqlName(sqlId), parameterMap);
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }
    }

    protected int selectOneInt(String sqlId, Map<String, Object> parameterMap) {
        try {
            Integer result = sqlSession.selectOne(getSqlName(sqlId), parameterMap);
            return null == result || result == 0 ? 0 : result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e);
            return 0;
        }
    }

    protected <T extends IEntity> List<T> selectList(String sqlId, Map<String, Object> parameterMap) {
        try {
            return sqlSession.selectList(getSqlName(sqlId), parameterMap);
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }
    }
//    protected <T extends IEntity> List<T> selectPageList(String sqlId, Map<String, Object> parameterMap, PageDTO page) {
//        try {
//            if (0 == page.getTotalCount()) {
////    			page.setTotalCount(this.selectO);
//            }
//            RowBounds rowBounds = new RowBounds(page.getStartIndex(), page.getPageSize());
//            return sqlSession.selectList(getSqlName(sqlId), parameterMap, rowBounds);
//        } catch (Exception e) {
//            logger.debug(e);
//            return null;
//        }
//    }

    /**
     * it maybe out of memory if the data is too large.
     */
    @Deprecated
    protected <T extends IEntity> List<T> selectAll(String sqlId) {
        try {
            return this.selectList(sqlId, null);
        } catch (Exception e) {
            logger.debug(e);
            return null;
        }
    }

    protected int update(String sqlId, Object id) {
        try {
            return sqlSession.update(getSqlName(sqlId), id);
        } catch (Exception e) {
            logger.debug(e);
            return 0;
        }
    }

    protected int delete(String sqlId, Object id) {
        try {
            return sqlSession.delete(getSqlName(sqlId), id);
        } catch (Exception e) {
            logger.debug(e);
            return 0;
        }
    }

    /**
     * recommend use <foreach> in the mapper.xml
     */
    @Deprecated
    protected boolean batchDelete(String sqlId, Object[] params) {
        try {
            for (Object param : params) {
                batchSqlSession.delete(getSqlName(sqlId), param);
            }
//    		sqlSession.clearCache();
            return true;
        } catch (Exception e) {
            logger.debug(e);
            return false;
        }
    }
}
