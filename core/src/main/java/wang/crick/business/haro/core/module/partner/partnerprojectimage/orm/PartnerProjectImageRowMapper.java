package wang.crick.business.haro.core.module.partner.partnerprojectimage.orm;

import org.springframework.jdbc.core.RowMapper;
import wang.crick.business.haro.core.domain.PartnerProjectImage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartnerProjectImageRowMapper implements RowMapper<PartnerProjectImage> {

    public PartnerProjectImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartnerProjectImage data = new PartnerProjectImage();
        data.setId(rs.getString("id"));
        data.setPhotoName(rs.getString("photo_name"));
        data.setPhotoType(rs.getString("photo_type"));
        return data;
    }

}
