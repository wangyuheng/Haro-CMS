package wang.crick.business.haro.core.module.advice.dto;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;

public class AdviceDto extends BasePageDto {

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
