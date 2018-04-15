package wang.crick.business.haro.core.module.partner.partnerproject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.crick.business.haro.core.domain.PartnerProject;
import wang.crick.business.haro.core.helper.HelperFile;
import wang.crick.business.haro.core.module.partner.dto.PartnerDto;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectRepository;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectService;

import java.util.List;

@Service
public class PartnerProjectServiceImpl implements PartnerProjectService {

    @Autowired
    private PartnerProjectRepository partnerProjectRepository;

    public List<PartnerProject> findPaged(PartnerDto dto) {
        return partnerProjectRepository.findPaged(dto);
    }

    public PartnerProject findUnique(String id) {
        return partnerProjectRepository.findUnique(id);
    }

    public boolean create(PartnerProject partnerProject) {
        HelperFile.moveTempFileToFormal(partnerProject.getFileName());
        return partnerProjectRepository.create(partnerProject);
    }

    public boolean delete(String id) {
        //TODO 删除对应图片
        return partnerProjectRepository.delete(id);
    }

    public boolean update(PartnerProject partnerProject) {
        return partnerProjectRepository.update(partnerProject);
    }


    public List<PartnerProject> findIndex() {
        return partnerProjectRepository.findIndex();
    }
}
