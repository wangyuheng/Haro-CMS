package wang.crick.business.haro.core.domain;

import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;

public class PartnerProjectImage extends BaseDomain {

    private String partnerProjectId;
    private String photoName;
    private String photoType;

    public String getPartnerProjectId() {
        return partnerProjectId;
    }

    public void setPartnerProjectId(String partnerProjectId) {
        this.partnerProjectId = partnerProjectId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    //ext fileName & fileType
    public String getFileName(){
        return getPhotoName()+"."+getPhotoType();
    }
}
