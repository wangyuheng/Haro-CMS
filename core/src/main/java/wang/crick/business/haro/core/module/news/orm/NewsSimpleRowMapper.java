package wang.crick.business.haro.core.module.news.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.News;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsSimpleRowMapper implements RowMapper<News> {

    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News data = new News();
        data.setId(rs.getString("id"));
        data.setTitle(rs.getString("title"));
        data.setCreateTime(rs.getDate("create_time"));
        return data;
    }

}
