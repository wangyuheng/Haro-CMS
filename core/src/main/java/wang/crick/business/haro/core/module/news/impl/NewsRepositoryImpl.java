package wang.crick.business.haro.core.module.news.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.News;
import wang.crick.business.haro.core.module.news.NewsRepository;
import wang.crick.business.haro.core.module.news.orm.NewsRowMapper;
import wang.crick.business.haro.core.module.news.orm.NewsSimpleRowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NewsRepositoryImpl extends BaseDao implements NewsRepository {

    public boolean create(News entity) {
        validationAndGenerate(entity);
        return insert("insert into ha_r_news (id, title, author, source, hot_flag, " +
                        "top_flag, new_flag, type, summary, create_time, " +
                        "create_user)values(" +
                        "?, ?, ?, ?, ?, " +
                        "?, ?, ?, ?, ?, " +
                        "?) ",
                entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getSource(), entity.getHotFlag(),
                entity.getTopFlag(), entity.getNewFlag(), entity.getType(), entity.getSummary(), entity.getCreateTime(),
                entity.getCreateUser());
    }

    public boolean createContent(String newsId, String content) {
        return insert("insert into ha_rwm_news_content (news_id, content)values(?,?)", newsId, content);
    }

    public List<News> findPaged(BasePageDto dto, Map<String, Object> params) {
        String sql = "select id, title, author, source, hot_flag, " +
                "top_flag, new_flag, type, summary,create_time, " +
                "create_user " +
                "from ha_r_news  ";
//        String countSql = "select count(*) from ha_r_news ";
        List<Object> conditionParams = new ArrayList();
        if (null != params && params.size() > 0) {

            StringBuilder conditionSql = new StringBuilder();
            if (null != params.get("title")) {
                conditionParams.add(params.get("title"));
                conditionSql.append(" and title like ?");
            }
            if (null != params.get("author")) {
                conditionParams.add(params.get("author"));
                conditionSql.append(" and author like ?");
            }
            if (null != params.get("type")) {
                conditionParams.add(params.get("type"));
                conditionSql.append(" and type = ?");
            }
            sql = sql + conditionSql.toString().replaceFirst("and", "where");
//            countSql = countSql + conditionSql.toString().replaceFirst("and", "where");
        }
//        dto.setTotalCount(queryOneInt(countSql, conditionParams.toArray()));
//        return query(sql, new NewsRowMapper(), conditionParams.toArray());
        return queryPaged(sql, dto, new NewsRowMapper(), conditionParams.toArray());
    }

    public List<News> findPagedSimple(BasePageDto page, Map<String, Object> params) {
        String sql = "select id, title, create_time " +
                "from ha_r_news ";
        List<Object> conditionParams = new ArrayList();
        if (null != params && params.size() > 0) {
            StringBuilder conditionSql = new StringBuilder();
            if (null != params.get("type")) {
                conditionParams.add(params.get("type"));
                conditionSql.append(" and type = ?");
            }
            if (null != params.get("title")) {
                conditionParams.add(params.get("title"));
                conditionSql.append(" and title like ?");
            }
            sql = sql + conditionSql.toString().replaceFirst("and", "where");
        }
        return queryPaged(sql, page, new NewsSimpleRowMapper(), conditionParams.toArray());
    }

    public News findUnique(String id) {
        String sql = "select id, title, author, source, hot_flag, " +
                "top_flag, new_flag, type, summary,create_time, " +
                "create_user " +
                "from ha_r_news where id=? ";
        return queryOne(sql, new NewsRowMapper(), id);
    }

    public String getContent(String newsId) {
        return queryOneString("select content from ha_rwm_news_content where news_id=?", newsId);
    }

    public List<News> findIndex(int type) {
        String sql = "select id, title, create_time " +
                "from ha_r_news " +
                "where type=? " +
                "limit 5 ";
        return query(sql, new NewsSimpleRowMapper(), type);
    }

    public boolean delete(String id) {
        return delete("delete from ha_r_news where id=?", id);
    }

}
