package wang.crick.business.haro.core.module.partner.partnerproject;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.PartnerProject;

import java.util.List;

public interface PartnerProjectRepository {

    List<PartnerProject> findPaged(BasePageDto dto);

    PartnerProject findUnique(String id);

    boolean create(PartnerProject partnerProject);

    boolean delete(String id);

    boolean update(PartnerProject partnerProject);

    List<PartnerProject> findIndex();
}
