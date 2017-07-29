package io.kevin.common.utils;

/**
 * Redis所以的Keys
 * @author ZGJ
 * @date 2017/7/29 14:15
 **/
public class RedisKeys {
    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }
}
