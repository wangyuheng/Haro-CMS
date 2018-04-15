package wang.crick.business.haro.core.module.assembly;

import wang.crick.business.haro.core.domain.assembly.Assembly;

import java.util.List;

public interface AssemblyRepository {
    Assembly findUniqueByType(int type);
    List<Assembly> findByCategory(int category);
    boolean update(Assembly entity);

    boolean batchInsert(List<Assembly> entityList);
}
