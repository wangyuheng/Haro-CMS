package wang.crick.business.haro.core.module.partner.partnerprojectimage.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.PartnerProjectImage;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.PartnerProjectImageRepository;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.orm.PartnerProjectImageRowMapper;

import java.util.List;
import java.util.Map;

@Repository
public class PartnerProjectImageRepositoryImpl extends BaseDao implements PartnerProjectImageRepository {

    public List<PartnerProjectImage> findPaged(BasePageDto dto, Map<String, Object> params) {
        return queryPaged("select id, name, photo_name, photo_type from ha_r_partner_project_image where partner_project_id=? ", dto, new PartnerProjectImageRowMapper(), params.get("partner_project_id"));
    }

    public PartnerProjectImage findUnique(String id) {
        return queryOne("select id, name, photo_name, photo_type from ha_r_partner_project_image where id=? ", new PartnerProjectImageRowMapper(), id);
    }

    public boolean create(PartnerProjectImage partnerProjectImage) {
        validationAndGenerate(partnerProjectImage);
        return insert("insert into ha_r_partner_project_image(id, partner_project_id, photo_name, photo_type, create_time, " +
                        "create_user)values(?,?,?,?,?, " +
                        "?)", partnerProjectImage.getId(), partnerProjectImage.getPartnerProjectId(), partnerProjectImage.getPhotoName(), partnerProjectImage.getPhotoType(), partnerProjectImage.getCreateTime(),
                partnerProjectImage.getCreateUser());
    }

    public boolean delete(String id) {
        return delete("delete from ha_r_partner_project_image where id=? ", id);
    }

}
