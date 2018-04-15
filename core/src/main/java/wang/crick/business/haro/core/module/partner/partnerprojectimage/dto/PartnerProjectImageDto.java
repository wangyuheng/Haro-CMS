package wang.crick.business.haro.core.module.partner.partnerprojectimage.dto;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;

public class PartnerProjectImageDto extends BasePageDto {
    private String partnerProjectId;

    public String getPartnerProjectId() {
        return partnerProjectId;
    }

    public void setPartnerProjectId(String partnerProjectId) {
        this.partnerProjectId = partnerProjectId;
    }
}
