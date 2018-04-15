package wang.crick.business.haro.core.module.assembly;

import wang.crick.business.haro.core.domain.assembly.Assembly;

import java.util.List;

public interface AssemblyService {

    /**
     * 通用模块内容 每个type 对应一条记录
     */
    Assembly findUniqueByType(int type);

    List<Assembly> findByCategory(int category);

    boolean publish(Assembly entity);

    boolean init();
}
