package wang.crick.business.haro.core.base.mvc.entity.pair;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StringKeyValuePairMapRowMapper implements ResultSetExtractor<Map<String, String>> {
    public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, String> result = new HashMap<String, String>();
        while (rs.next()) {
            result.put(rs.getString("pair_key"), rs.getString("pair_value"));
        }
        return result;
    }
}
