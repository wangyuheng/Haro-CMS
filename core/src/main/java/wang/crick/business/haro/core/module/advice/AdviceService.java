package wang.crick.business.haro.core.module.advice;


import wang.crick.business.haro.core.domain.Advice;
import wang.crick.business.haro.core.module.advice.dto.AdviceDto;

import java.util.List;

public interface AdviceService {

    Advice findUnique(String id);

    List<Advice> findPaged(AdviceDto dto);

    boolean create(Advice entity);

    boolean delete(String ids);

    boolean changeState(int state, String id);
}
