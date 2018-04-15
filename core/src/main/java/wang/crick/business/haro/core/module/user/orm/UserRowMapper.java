package wang.crick.business.haro.core.module.user.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User data = new User();
        data.setId(rs.getString("id"));
        data.setUsername(rs.getString("username"));
        return data;
    }
}
