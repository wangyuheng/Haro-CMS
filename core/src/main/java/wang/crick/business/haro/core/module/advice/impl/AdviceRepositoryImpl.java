package wang.crick.business.haro.core.module.advice.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.Advice;
import wang.crick.business.haro.core.module.advice.AdviceRepository;
import wang.crick.business.haro.core.module.advice.orm.AdviceRowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AdviceRepositoryImpl extends BaseDao implements AdviceRepository {
    public Advice findUnique(String id) {
        return queryOne("select id, state, title, name, email, content, create_time " +
                "from ha_wr_advice where id=?", new AdviceRowMapper(), id);
    }

    public List<Advice> findPaged(BasePageDto dto, Map<String, Object> params) {
        String sql = "select id, state, title, name, email, content, create_time " +
                "from ha_wr_advice ";
        List<Object> conditionParams = new ArrayList<Object>();
        if (null != params && params.size() > 0) {
            StringBuilder conditionSql = new StringBuilder();
            if (null != params.get("title")) {
                conditionParams.add(params.get("title"));
                conditionSql.append(" and title like ?");
            }
            if (null != params.get("state")) {
                conditionParams.add(params.get("state"));
                conditionSql.append(" and state = ?");
            }
            sql = sql + conditionSql.toString().replaceFirst("and", "where");
            sql = sql + " order by state, create_time desc ";
        }
        return queryPaged(sql, dto, new AdviceRowMapper(), conditionParams.toArray());
    }

    public boolean create(Advice entity) {
        validationAndGenerate(entity);
        return insert("insert into ha_wr_advice (id, state, title, name, email, content, create_time)values(?,?,?,?,?, ?,?)",
                entity.getId(), entity.getState(), entity.getTitle(), entity.getName(), entity.getEmail(), entity.getContent(), entity.getCreateTime()
        );
    }

    public boolean delete(String id) {
        return delete("delete from ha_wr_advice where id=?", id);
    }

    public boolean deleteMulti(String[] ids) {
        StringBuilder sql = new StringBuilder();

        sql.append("delete from ha_wr_advice where id in (");

        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql.append("?)");
            } else {
                sql.append("?,");
            }
        }

        return delete(sql.toString(), ids);
    }

    public boolean updateState(int state, String id) {
        return update("update ha_wr_advice set state=? where id=? ", state, id);
    }
}
