package wang.crick.business.haro.core.module.assembly.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.domain.assembly.Assembly;
import wang.crick.business.haro.core.module.assembly.AssemblyRepository;
import wang.crick.business.haro.core.module.assembly.orm.AssemblyRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AssemblyRepositoryImpl extends BaseDao implements AssemblyRepository {

    public Assembly findUniqueByType(int type) {
        return queryOne("select id, type, category, content, publish_time, publish_user from ha_r_assembly where type=?", new AssemblyRowMapper(), type);
    }

    public List<Assembly> findByCategory(int category) {
        return query("select id, type, category, content, publish_time, publish_user from ha_r_assembly where category=?", new AssemblyRowMapper(), category);
    }

    public boolean update(Assembly entity) {
        return update("update ha_r_assembly set content=?, publish_time=?, publish_user=? where id=?", entity.getContent(), entity.getPublishTime(), entity.getPublishUser(), entity.getId());
    }

    public boolean batchInsert(List<Assembly> entityList) {
        List<Object[]> params = new ArrayList<Object[]>();
        for (Assembly entity : entityList) {
            params.add(new Object[]{
                    entity.getId(), entity.getType(), entity.getCategory(), entity.getContent(), entity.getPublishTime(), entity.getPublishUser()
            });
        }
        return batchInsert("insert into ha_r_assembly(id, type, category, content, publish_time, publish_user)values(?,?,?,?,?, ?)", params);
    }

}
