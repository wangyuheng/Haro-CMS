package wang.crick.business.haro.core.dictionary;

/**
 * 新闻类型
 */
public enum NewsTypeEnum {
    Company(1, "公司新闻"), Industry(2, "行业动态");

    private int key;
    private String value;

    private NewsTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}