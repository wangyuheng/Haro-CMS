package wang.crick.business.haro.core.module.advice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.crick.business.haro.core.dictionary.AdviceStateEnum;
import wang.crick.business.haro.core.domain.Advice;
import wang.crick.business.haro.core.module.advice.AdviceRepository;
import wang.crick.business.haro.core.module.advice.AdviceService;
import wang.crick.business.haro.core.module.advice.dto.AdviceDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    public Advice findUnique(String id) {
        return adviceRepository.findUnique(id);
    }

    public List<Advice> findPaged(AdviceDto dto) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (dto.getState() > 0) {
            params.put("state", dto.getState());
        }
        return adviceRepository.findPaged(dto, params);
    }

    public boolean create(Advice entity) {
        if (StringUtils.isEmpty(entity.getTitle()) || entity.getTitle().length() > 100) {
            return false;
        }
        if (StringUtils.isEmpty(entity.getName()) || entity.getName().length() > 20) {
            return false;
        }
        if (StringUtils.isEmpty(entity.getEmail()) || entity.getEmail().length() > 40) {
            return false;
        }
        if (StringUtils.isEmpty(entity.getContent()) || entity.getContent().length() > 2000) {
            return false;
        }
        entity.setState(AdviceStateEnum.UnDo.getKey());
        return adviceRepository.create(entity);
    }

    public boolean delete(String ids) {
        boolean flag = false;
        if (!StringUtils.isEmpty(ids)) {
            if (ids.contains( "," )) {
                String[] idArr = ids.split( "," );
                flag = adviceRepository.deleteMulti( idArr );
            } else {
                flag = adviceRepository.delete( ids );
            }
        }
        return flag;
    }

    public boolean changeState(int state, String id) {
        switch (AdviceStateEnum.parse(state)) {
            case UnDo:
            case Doing:
                state++;
                break;
        }
        return adviceRepository.updateState(state, id);
    }
}
