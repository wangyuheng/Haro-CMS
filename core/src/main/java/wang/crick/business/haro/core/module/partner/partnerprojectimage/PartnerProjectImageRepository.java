package wang.crick.business.haro.core.module.partner.partnerprojectimage;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.PartnerProjectImage;

import java.util.List;
import java.util.Map;

public interface PartnerProjectImageRepository {

    List<PartnerProjectImage> findPaged(BasePageDto dto, Map<String, Object> params);

    PartnerProjectImage findUnique(String id);

    boolean create(PartnerProjectImage partnerProject);

    boolean delete(String id);

}
