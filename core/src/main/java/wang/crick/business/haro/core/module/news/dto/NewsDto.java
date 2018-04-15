package wang.crick.business.haro.core.module.news.dto;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;

public class NewsDto extends BasePageDto {

    private String title;
    private String author;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
