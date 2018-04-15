package wang.crick.business.haro.core.module.partner.partnerproject.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.PartnerProject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartnerProjectRowMapper implements RowMapper<PartnerProject> {

    public PartnerProject mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartnerProject data = new PartnerProject();
        data.setId(rs.getString("id"));
        data.setName(rs.getString("name"));
        data.setCoverPhotoName(rs.getString("cover_photo_name"));
        data.setCoverPhotoType(rs.getString("cover_photo_type"));
        return data;
    }

}
