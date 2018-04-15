package wang.crick.business.haro.core.module.partner.partnerprojectimage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.crick.business.haro.core.domain.PartnerProjectImage;
import wang.crick.business.haro.core.helper.HelperFile;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.PartnerProjectImageRepository;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.PartnerProjectImageService;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.dto.PartnerProjectImageDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartnerProjectImageServiceImpl implements PartnerProjectImageService {

    @Autowired
    private PartnerProjectImageRepository partnerProjectImageRepository;

    public List<PartnerProjectImage> findPaged(PartnerProjectImageDto dto) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("partner_project_id", dto.getPartnerProjectId());
        return partnerProjectImageRepository.findPaged(dto, params);
    }

    public PartnerProjectImage findUnique(String id) {
        return partnerProjectImageRepository.findUnique(id);
    }

    public boolean create(PartnerProjectImage partnerProjectImage) {
        HelperFile.moveTempFileToFormal(partnerProjectImage.getFileName());
        return partnerProjectImageRepository.create(partnerProjectImage);
    }

    public boolean delete(String id) {
        //TODO 删除对应图片
        return partnerProjectImageRepository.delete(id);
    }

}
