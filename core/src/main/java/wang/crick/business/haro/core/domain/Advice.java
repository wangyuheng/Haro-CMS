package wang.crick.business.haro.core.domain;

import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;
import wang.crick.business.haro.core.dictionary.AdviceStateEnum;

/**
 * 投诉建议
 */
public class Advice extends BaseDomain {

    private String title;
    private String name;
    private String email;
    private String content;
    private int state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateValue() {
        return null == AdviceStateEnum.parse(getState()) ? "" : AdviceStateEnum.parse(getState()).getValue();
    }
}
