package wang.crick.business.haro.core.domain;

import wang.crick.business.haro.core.base.mvc.entity.BaseDomain;

/**
 * 新闻
 * <p>
 * Created by CR on 2015/9/18.
 */
public class News extends BaseDomain {
    private String title;
    private String source;
    private String author;
    private String summary;
    private int type;
    private int hotFlag;
    private int topFlag;
    private int newFlag;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(int hotFlag) {
        this.hotFlag = hotFlag;
    }

    public int getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(int topFlag) {
        this.topFlag = topFlag;
    }

    public int getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(int newFlag) {
        this.newFlag = newFlag;
    }
}
