package wang.crick.business.haro.core.module.news.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.News;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<News> {

    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News data = new News();
        data.setId(rs.getString("id"));
        data.setTitle(rs.getString("title"));
        data.setAuthor(rs.getString("author"));
        data.setSource(rs.getString("source"));
        data.setHotFlag(rs.getInt("hot_flag"));
        data.setTopFlag(rs.getInt("top_flag"));
        data.setNewFlag(rs.getInt("new_flag"));
        data.setType(rs.getInt("type"));
        data.setSummary(rs.getString("summary"));
        data.setCreateTime(rs.getTimestamp("create_time"));
        data.setCreateUser(rs.getString("create_user"));
        return data;
    }

}
