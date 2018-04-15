package wang.crick.business.haro.core.domain;

import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;

import java.util.ArrayList;
import java.util.List;

public class PartnerProject extends BaseDomain {

    private String name;
    private String coverPhotoName;
    private String coverPhotoType;
    private List<PartnerProjectImage> partnerProjectImageList = new ArrayList<PartnerProjectImage>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPhotoName() {
        return coverPhotoName;
    }

    public void setCoverPhotoName(String coverPhotoName) {
        this.coverPhotoName = coverPhotoName;
    }

    public String getCoverPhotoType() {
        return coverPhotoType;
    }

    public void setCoverPhotoType(String coverPhotoType) {
        this.coverPhotoType = coverPhotoType;
    }

    public List<PartnerProjectImage> getPartnerProjectImageList() {
        return partnerProjectImageList;
    }

    public void setPartnerProjectImageList(List<PartnerProjectImage> partnerProjectImageList) {
        this.partnerProjectImageList = partnerProjectImageList;
    }

    //ext fileName & fileType
    public String getFileName(){
        return getCoverPhotoName()+"."+getCoverPhotoType();
    }
}
