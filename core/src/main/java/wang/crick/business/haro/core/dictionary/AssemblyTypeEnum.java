package wang.crick.business.haro.core.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AssemblyTypeEnum {

    //走进HN
    Company_introduction(1, "公司简介", "introduction"),
    Company_history(2, "发展历程", "history"),
    Company_organization(3, "组织机构", "organization"),
    Company_honor(4, "资质荣誉", "honor"),
    Company_video(5, "企业视频", "video"),
    //企业文化
    Culture_idea(6, "文化理念", "idea"),
    Culture_activity(7, "企业活动", "activity"),
    //新闻中心
    News_company(8, "公司新闻", "company"),
    News_industry(9, "行业动态", "industry"),
    //业务领域
    Business_gong_ye_wei_ji_jing_hua(10, "工业尾气净化", "gong_ye_wei_ji_jing_hua"),
    Business_liu_huang_hui_shou(11, "硫磺回收", "liu_huang_hui_shou"),
    Business_han_liu_han_yan_wu_shui_chu_li(12, "含硫含盐污水处理", "han_liu_han_yan_wu_shui_chu_li"),
    Business_chao_di_liu_you_pin_jing_zhi(13, "超低硫油品精制", "chao_di_liu_you_pin_jing_zhi"),
    //合作伙伴
    Partner_project(14, "示范工程", "project"),
    Partner_contact(15, "客户名录表", "contact"),
    //技术研发
    Technology_principle(16, "工艺原理", "principle"),
    Technology_facility(17, "技术和设备", "facility"),
    //人力资源
    Talent_strategy(18, "人才战略", "strategy"),
    Talent_concept(19, "人才理念", "concept"),
    Talent_recruit(20, "诚聘精英", "recruit"),
    //服务中心
    Service_aim(21, "服务宗旨", "aim"),
    Service_range(22, "服务范围", "range"),
    Service_contact(23, "联系方式", "contact"),
    Service_feedback(24, "信息反馈", "feedback"),
    Service_advice(25, "投诉建议", "advice");
    private static List<AssemblyTypeEnum> companyList = new ArrayList<AssemblyTypeEnum>();

    static {
        companyList.add(AssemblyTypeEnum.Company_introduction);
        companyList.add(AssemblyTypeEnum.Company_history);
        companyList.add(AssemblyTypeEnum.Company_organization);
        companyList.add(AssemblyTypeEnum.Company_honor);
        companyList.add(AssemblyTypeEnum.Company_video);
    }

    private static List<AssemblyTypeEnum> cultureList = new ArrayList<AssemblyTypeEnum>();

    static {
        cultureList.add(AssemblyTypeEnum.Culture_idea);
        cultureList.add(AssemblyTypeEnum.Culture_activity);
    }

    private static List<AssemblyTypeEnum> newsList = new ArrayList<AssemblyTypeEnum>();

    static {
        newsList.add(AssemblyTypeEnum.News_company);
        newsList.add(AssemblyTypeEnum.News_industry);
    }

    private static List<AssemblyTypeEnum> businessList = new ArrayList<AssemblyTypeEnum>();

    static {
        businessList.add(AssemblyTypeEnum.Business_gong_ye_wei_ji_jing_hua);
        businessList.add(AssemblyTypeEnum.Business_liu_huang_hui_shou);
        businessList.add(AssemblyTypeEnum.Business_han_liu_han_yan_wu_shui_chu_li);
        businessList.add(AssemblyTypeEnum.Business_chao_di_liu_you_pin_jing_zhi);
    }

    private static List<AssemblyTypeEnum> partnerList = new ArrayList<AssemblyTypeEnum>();

    static {
        partnerList.add(AssemblyTypeEnum.Partner_project);
        partnerList.add(AssemblyTypeEnum.Partner_contact);
    }

    private static List<AssemblyTypeEnum> technologyList = new ArrayList<AssemblyTypeEnum>();

    static {
        technologyList.add(AssemblyTypeEnum.Technology_principle);
        technologyList.add(AssemblyTypeEnum.Technology_facility);
    }

    private static List<AssemblyTypeEnum> talentList = new ArrayList<AssemblyTypeEnum>();

    static {
        talentList.add(AssemblyTypeEnum.Talent_strategy);
        talentList.add(AssemblyTypeEnum.Talent_concept);
        talentList.add(AssemblyTypeEnum.Talent_recruit);
    }

    private static List<AssemblyTypeEnum> serviceList = new ArrayList<AssemblyTypeEnum>();

    static {
        serviceList.add(AssemblyTypeEnum.Service_aim);
        serviceList.add(AssemblyTypeEnum.Service_range);
        serviceList.add(AssemblyTypeEnum.Service_contact);
        serviceList.add(AssemblyTypeEnum.Service_feedback);
        serviceList.add(AssemblyTypeEnum.Service_advice);
    }

    private static Map<AssemblyCategoryEnum, List<AssemblyTypeEnum>> categoryMap = new HashMap<AssemblyCategoryEnum, List<AssemblyTypeEnum>>();

    static {
        categoryMap.put(AssemblyCategoryEnum.Company, companyList);
        categoryMap.put(AssemblyCategoryEnum.Culture, cultureList);
        categoryMap.put(AssemblyCategoryEnum.News, newsList);
        categoryMap.put(AssemblyCategoryEnum.Business, businessList);
        categoryMap.put(AssemblyCategoryEnum.Partner, partnerList);
        categoryMap.put(AssemblyCategoryEnum.Technology, technologyList);
        categoryMap.put(AssemblyCategoryEnum.Talent, talentList);
        categoryMap.put(AssemblyCategoryEnum.Service, serviceList);
    }

    private int key;
    private String value;
    private String url;

    private AssemblyTypeEnum(int key, String value, String url) {
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

    public static List<AssemblyTypeEnum> getAssemblyList(int category) {
        return categoryMap.get(AssemblyCategoryEnum.parse(category));
    }

    public static AssemblyTypeEnum parse(int value) {
        AssemblyTypeEnum result = null;
        switch (value) {
            case 1:
                result = AssemblyTypeEnum.Company_introduction;
                break;
            case 2:
                result = AssemblyTypeEnum.Company_history;
                break;
            case 3:
                result = AssemblyTypeEnum.Company_organization;
                break;
            case 4:
                result = AssemblyTypeEnum.Company_honor;
                break;
            case 5:
                result = AssemblyTypeEnum.Company_video;
                break;
            case 6:
                result = AssemblyTypeEnum.Culture_idea;
                break;
            case 7:
                result = AssemblyTypeEnum.Culture_activity;
                break;
            case 8:
                result = AssemblyTypeEnum.News_company;
                break;
            case 9:
                result = AssemblyTypeEnum.News_industry;
                break;
            case 10:
                result = AssemblyTypeEnum.Business_gong_ye_wei_ji_jing_hua;
                break;
            case 11:
                result = AssemblyTypeEnum.Business_liu_huang_hui_shou;
                break;
            case 12:
                result = AssemblyTypeEnum.Business_han_liu_han_yan_wu_shui_chu_li;
                break;
            case 13:
                result = AssemblyTypeEnum.Business_chao_di_liu_you_pin_jing_zhi;
                break;
            case 14:
                result = AssemblyTypeEnum.Partner_project;
                break;
            case 15:
                result = AssemblyTypeEnum.Partner_contact;
                break;
            case 16:
                result = AssemblyTypeEnum.Technology_principle;
                break;
            case 17:
                result = AssemblyTypeEnum.Technology_facility;
                break;
            case 18:
                result = AssemblyTypeEnum.Talent_strategy;
                break;
            case 19:
                result = AssemblyTypeEnum.Talent_concept;
                break;
            case 20:
                result = AssemblyTypeEnum.Talent_recruit;
                break;
            case 21:
                result = AssemblyTypeEnum.Service_aim;
                break;
            case 22:
                result = AssemblyTypeEnum.Service_range;
                break;
            case 23:
                result = AssemblyTypeEnum.Service_contact;
                break;
            case 24:
                result = AssemblyTypeEnum.Service_feedback;
                break;
            case 25:
                result = AssemblyTypeEnum.Service_advice;
                break;
        }
        return result;
    }
}