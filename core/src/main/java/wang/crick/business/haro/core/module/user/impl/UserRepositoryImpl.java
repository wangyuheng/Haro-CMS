package wang.crick.business.haro.core.module.user.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.domain.User;
import wang.crick.business.haro.core.module.user.UserRepository;
import wang.crick.business.haro.core.module.user.orm.UserRowMapper;

@Repository
public class UserRepositoryImpl extends BaseDao implements UserRepository {
    public User findUnique() {
        return queryOne("select id, username from ha_r_user limit 1", new UserRowMapper());
    }

    public User findUnique(String username, String password) {
        return queryOne("select id, username from ha_r_user where username=? and password=?", new UserRowMapper(), username, password);
    }

    public boolean modifyPassword(String password, String id) {
        return update("update ha_r_user set password=? where id=?", password, id);
    }
}
