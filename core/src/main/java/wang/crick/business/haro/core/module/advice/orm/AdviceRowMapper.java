package wang.crick.business.haro.core.module.advice.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.Advice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdviceRowMapper implements RowMapper<Advice> {
    public Advice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Advice data = new Advice();
        data.setId(rs.getString("id"));
        data.setState(rs.getInt("state"));
        data.setTitle(rs.getString("title"));
        data.setEmail(rs.getString("email"));
        data.setContent(rs.getString("content"));
        data.setName(rs.getString("name"));
        data.setCreateTime(rs.getTimestamp("create_time"));
        return data;
    }
}
