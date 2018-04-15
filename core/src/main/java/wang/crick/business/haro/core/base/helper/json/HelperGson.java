package wang.crick.business.haro.core.base.helper.json;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Json转换工具
 * 对Gson的封装
 */
public class HelperGson {

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }
}
