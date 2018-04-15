package wang.crick.business.haro.core.module.layoutinfo.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.LayoutInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LayoutInfoRowMapper implements RowMapper<LayoutInfo> {
    public LayoutInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        LayoutInfo data = new LayoutInfo();
        data.setId(rs.getString("id"));
        data.setHotline(rs.getString("hotline"));
        data.setAddress(rs.getString("address"));
        data.setZipCode(rs.getString("zip_code"));
        data.setTelephone(rs.getString("telephone"));
        data.setCopyright(rs.getString("copyright"));
        data.setIcpNo(rs.getString("icp_no"));
        data.setPublishTime(rs.getDate("publish_time"));
        data.setPublishUser(rs.getString("publish_user"));
        return data;
    }
}
