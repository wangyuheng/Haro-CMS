package wang.crick.business.haro.core.module.partner.partnerprojectimage;

import wang.crick.business.haro.core.domain.PartnerProjectImage;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.dto.PartnerProjectImageDto;

import java.util.List;

public interface PartnerProjectImageService {

    List<PartnerProjectImage> findPaged(PartnerProjectImageDto dto);

    PartnerProjectImage findUnique(String id);

    boolean create(PartnerProjectImage partnerProject);

    boolean delete(String id);

}
