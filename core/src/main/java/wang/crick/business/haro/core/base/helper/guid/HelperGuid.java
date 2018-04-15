package wang.crick.business.haro.core.base.helper.guid;

import java.util.UUID;

/**
 * GUID工具
 */
public class HelperGuid {

    public static final int COMMON_GUID_LENGTH = 36;

    public static String getRandomStr() {
        return UUID.randomUUID().toString();
    }

    public static boolean isUUID(String arg) {
        return null != arg && !"".equals(arg) && 36 == arg.length();
    }

    /**
     * 替换guid前面的字符串
     * ！！替换后的uuid可能会重复
     */
    public static String replaceStart(String uuid, String prefix) {
        if (isUUID(uuid)) {
            return prefix + uuid.substring(prefix.length());
        } else {
            return null;
        }
    }
}
