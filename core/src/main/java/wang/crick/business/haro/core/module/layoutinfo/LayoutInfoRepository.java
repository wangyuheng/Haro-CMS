package wang.crick.business.haro.core.module.layoutinfo;

import wang.crick.business.haro.core.domain.LayoutInfo;

public interface LayoutInfoRepository {

    LayoutInfo findUnique();

    boolean update(LayoutInfo layoutInfo);

    boolean create(LayoutInfo layoutInfo);
}
