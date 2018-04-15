package wang.crick.business.haro.core.base.helper.collection;

import java.util.Collection;

/**
 * 集合工具
 */
public class HelperCollection {

    public static <T extends Collection> boolean isBlank(T collection) {
        return null == collection || collection.size() == 0;
    }

    public static <T extends Collection> boolean isNotBlank(T collection) {
        return !isBlank(collection);
    }
}
