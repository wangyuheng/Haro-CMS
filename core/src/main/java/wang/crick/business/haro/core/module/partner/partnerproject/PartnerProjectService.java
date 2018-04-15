package wang.crick.business.haro.core.module.partner.partnerproject;

import wang.crick.business.haro.core.domain.PartnerProject;
import wang.crick.business.haro.core.module.partner.dto.PartnerDto;

import java.util.List;

public interface PartnerProjectService {

    List<PartnerProject> findPaged(PartnerDto dto);

    PartnerProject findUnique(String id);

    boolean create(PartnerProject partnerProject);

    boolean delete(String id);

    boolean update(PartnerProject partnerProject);

    List<PartnerProject> findIndex();
}
