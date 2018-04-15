package wang.crick.business.haro.core.module.advice;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.Advice;

import java.util.List;
import java.util.Map;

public interface AdviceRepository {

    Advice findUnique(String id);

    List<Advice> findPaged(BasePageDto dto, Map<String, Object> params);

    boolean create(Advice entity);

    boolean delete(String id);

    boolean deleteMulti(String[] ids);

    boolean updateState(int state, String id);

}
