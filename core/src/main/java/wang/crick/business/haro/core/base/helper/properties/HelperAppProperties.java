package wang.crick.business.haro.core.base.helper.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取app.properties配置信息
 */
public class HelperAppProperties {
    private static Properties properties = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = HelperAppProperties.class.getClassLoader();
        }
        InputStream is = null;
        try {
            is = classLoader.getResourceAsStream("conf/app.properties");
            properties.load(is);
            is.close();
            is = null;
        } catch (Exception e) {
            try {
                properties.load(new FileInputStream(new File("src/main/resources/conf/app.properties")));
            } catch (Exception e1) {
                System.err.println("not find app.properties in src/main/resources/conf/app.properties");
            }
        }
    }

    private static Properties getProperties() {
        return properties;
    }

    public static String getString(String key) {
        return getProperties().getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    public static boolean getBoolean(String key, Boolean defaultValue) {
        String value = getProperties().getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.valueOf(value);
    }
}
