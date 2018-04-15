package wang.crick.business.haro.core.base.mvc.entity.pair;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringKeyValuePairRowMapper implements RowMapper<StringKeyValuePair> {

    public StringKeyValuePair mapRow(ResultSet rs, int i) throws SQLException {
        StringKeyValuePair data = new StringKeyValuePair();
        data.setKey(rs.getString("pair_key"));
        data.setValue(rs.getString("pair_value"));
        return data;
    }

}
