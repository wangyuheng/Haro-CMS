package wang.crick.business.haro.core.base.dao.orm;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MapResultSetExtractor<K, V> implements ResultSetExtractor<Map<K, V>> {
    public Map<K, V> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<K, V> result = new HashMap<K, V>();
        while (rs.next()) {
            result.put((K) rs.getObject(1), (V) rs.getObject(2));
        }
        return result;
    }
}