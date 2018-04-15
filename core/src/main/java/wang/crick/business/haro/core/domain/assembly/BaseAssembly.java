package wang.crick.business.haro.core.domain.assembly;

import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseAssembly implements Serializable {

    private String id;
    private String content;
    private int type;
    private int category;
    private String publishUser;
    private Date publishTime;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    //ext type
    public AssemblyTypeEnum getAssemblyTypeEnum(){
        return AssemblyTypeEnum.parse(getType());
    }
}
