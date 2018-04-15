package wang.crick.business.haro.core.module.assembly.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.domain.assembly.Assembly;
import wang.crick.business.haro.core.module.assembly.AssemblyRepository;
import wang.crick.business.haro.core.module.assembly.AssemblyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssemblyServiceImpl implements AssemblyService {

    @Autowired
    private AssemblyRepository assemblyRepository;

    public Assembly findUniqueByType(int type) {
        return assemblyRepository.findUniqueByType(type);
    }

    public List<Assembly> findByCategory(int category) {
        return assemblyRepository.findByCategory(category);
    }

    public boolean publish(Assembly entity) {
        if (HelperGuid.isUUID(entity.getId())) {
            return assemblyRepository.update(entity);
        } else {
            return false;
        }
    }

    public boolean init() {
        List<Assembly> entityList = new ArrayList<Assembly>();
        Date now = new Date();
        for (AssemblyTypeEnum assemblyTypeEnum : AssemblyTypeEnum.values()) {
            Assembly entity = new Assembly();
            entity.setId(HelperGuid.getRandomStr());
            entity.setCategory(AssemblyCategoryEnum.parseByAssemblyType(assemblyTypeEnum.getKey()).getKey());
            entity.setType(assemblyTypeEnum.getKey());
            entity.setPublishTime(now);
            entityList.add(entity);
        }
        return assemblyRepository.batchInsert(entityList);
    }
}
