package wang.crick.business.haro.core.module.layoutinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.domain.LayoutInfo;
import wang.crick.business.haro.core.module.layoutinfo.LayoutInfoRepository;
import wang.crick.business.haro.core.module.layoutinfo.LayoutInfoService;

import java.util.Date;

@Service
public class LayoutInfoServiceImpl implements LayoutInfoService {

    @Autowired
    private LayoutInfoRepository layoutInfoRepository;

    public LayoutInfo findUnique() {
        return layoutInfoRepository.findUnique();
    }

    public boolean publish(LayoutInfo layoutInfo) {
        if (HelperGuid.isUUID(layoutInfo.getId())) {
            return layoutInfoRepository.update(layoutInfo);
        } else {
            return false;
        }
    }

    public boolean init() {
        LayoutInfo layoutInfo = new LayoutInfo();
        layoutInfo.setId(HelperGuid.getRandomStr());
        layoutInfo.setCopyright("大连汉诺工程技术有限公司 版权所有 All rights reserved");
        layoutInfo.setHotline("123456");
        layoutInfo.setIcpNo("辽ICP备000000号");
        layoutInfo.setAddress("辽宁省大连市135号");
        layoutInfo.setZipCode("116011");
        layoutInfo.setPublishTime(new Date());
        return layoutInfoRepository.create(layoutInfo);
    }
}
