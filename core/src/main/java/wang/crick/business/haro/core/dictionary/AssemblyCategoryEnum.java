package wang.crick.business.haro.core.dictionary;

public enum AssemblyCategoryEnum {
    Company(1, "走进HN", "company"),
    Culture(2, "企业文化", "culture"),
    News(3, "新闻中心", "news"),
    Business(4, "业务领域", "business"),
    Partner(5, "合作伙伴", "partner"),
    Technology(6, "技术研发", "technology"),
    Talent(7, "人力资源", "talent"),
    Service(8, "服务中心", "service");

    private int key;
    private String value;
    private String url;

    private AssemblyCategoryEnum(int key, String value, String url) {
        this.key = key;
        this.value = value;
        this.url = url;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public static AssemblyCategoryEnum parse(int value) {
        AssemblyCategoryEnum result = null;
        switch (value) {
            case 1:
                result = AssemblyCategoryEnum.Company;
                break;
            case 2:
                result = AssemblyCategoryEnum.Culture;
                break;
            case 3:
                result = AssemblyCategoryEnum.News;
                break;
            case 4:
                result = AssemblyCategoryEnum.Business;
                break;
            case 5:
                result = AssemblyCategoryEnum.Partner;
                break;
            case 6:
                result = AssemblyCategoryEnum.Technology;
                break;
            case 7:
                result = AssemblyCategoryEnum.Talent;
                break;
            case 8:
                result = AssemblyCategoryEnum.Service;
                break;

        }
        return result;
    }

    public static AssemblyCategoryEnum parseByAssemblyType(int type) {
        AssemblyCategoryEnum result = null;
        switch (type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                result = AssemblyCategoryEnum.Company;
                break;
            case 6:
            case 7:
                result = AssemblyCategoryEnum.Culture;
                break;
            case 8:
            case 9:
                result = AssemblyCategoryEnum.News;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                result = AssemblyCategoryEnum.Business;
                break;
            case 14:
            case 15:
                result = AssemblyCategoryEnum.Partner;
                break;
            case 16:
            case 17:
                result = AssemblyCategoryEnum.Technology;
                break;
            case 18:
            case 19:
            case 20:
                result = AssemblyCategoryEnum.Talent;
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                result = AssemblyCategoryEnum.Service;
                break;

        }
        return result;
    }

}