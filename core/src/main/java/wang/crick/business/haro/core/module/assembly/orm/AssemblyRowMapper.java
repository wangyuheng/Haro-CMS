package wang.crick.business.haro.core.module.assembly.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.assembly.Assembly;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssemblyRowMapper implements RowMapper<Assembly> {

    public Assembly mapRow(ResultSet rs, int rowNum) throws SQLException {
        Assembly data = new Assembly();
        data.setId(rs.getString("id"));
        data.setContent(rs.getString("content"));
        data.setType(rs.getInt("type"));
        data.setCategory(rs.getInt("category"));
        data.setPublishTime(rs.getDate("publish_time"));
        data.setPublishUser(rs.getString("publish_user"));
        return data;
    }
}
