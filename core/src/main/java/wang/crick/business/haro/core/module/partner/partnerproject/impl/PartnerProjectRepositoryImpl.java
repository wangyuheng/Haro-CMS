package wang.crick.business.haro.core.module.partner.partnerproject.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.PartnerProject;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectRepository;
import wang.crick.business.haro.core.module.partner.partnerproject.orm.PartnerProjectRowMapper;

import java.util.List;

@Repository
public class PartnerProjectRepositoryImpl extends BaseDao implements PartnerProjectRepository {

    public List<PartnerProject> findPaged(BasePageDto dto) {
        return queryPaged("select id, name, cover_photo_name, cover_photo_type from ha_r_partner_project ", dto, new PartnerProjectRowMapper());
    }

    public PartnerProject findUnique(String id) {
        return queryOne("select id, name, cover_photo_name, cover_photo_type from ha_r_partner_project where id=? ", new PartnerProjectRowMapper(), id);
    }

    public boolean create(PartnerProject partnerProject) {
        validationAndGenerate(partnerProject);
        return insert("insert into ha_r_partner_project(id, name, cover_photo_name, cover_photo_type, create_time, " +
                        "create_user)values(?,?,?,?,?, " +
                        "?)", partnerProject.getId(), partnerProject.getName(), partnerProject.getCoverPhotoName(), partnerProject.getCoverPhotoType(), partnerProject.getCreateTime(),
                partnerProject.getCreateUser());
    }

    public boolean delete(String id) {
        return delete("delete from ha_r_partner_project where id=? ", id);
    }

    public boolean update(PartnerProject partnerProject) {
        return update("update ha_r_partner_project set name=? where id=? ", partnerProject.getName(), partnerProject.getId());
    }


    public List<PartnerProject> findIndex() {
        return query("select id, name, cover_photo_name, cover_photo_type from ha_r_partner_project limit 12 ", new PartnerProjectRowMapper());
    }

}
