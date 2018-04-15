package wang.crick.business.haro.core.module.layoutinfo;

import wang.crick.business.haro.core.domain.LayoutInfo;

public interface LayoutInfoService {

    LayoutInfo findUnique();

    boolean publish(LayoutInfo layoutInfo);

    boolean init();
}
