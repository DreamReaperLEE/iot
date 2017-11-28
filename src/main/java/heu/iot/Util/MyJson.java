package heu.iot.Util;

import com.google.gson.Gson;

/**
 * @Author: Sumail-Lee
 * @Date: 10:01 2017/11/27
 */
public class MyJson {

    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
