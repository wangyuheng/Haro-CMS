package wang.crick.business.haro.core.dictionary;

/**
 * 反馈处理状态
 */
public enum AdviceStateEnum {

    UnDo(1, "未处理"), Doing(2, "处理中"), Done(3, "已完成");

    private int key;
    private String value;

    private AdviceStateEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static AdviceStateEnum parse(int value) {
        AdviceStateEnum result = null;
        switch (value) {
            case 1:
                result = AdviceStateEnum.UnDo;
                break;
            case 2:
                result = AdviceStateEnum.Doing;
                break;
            case 3:
                result = AdviceStateEnum.Done;
                break;
        }
        return result;
    }
}