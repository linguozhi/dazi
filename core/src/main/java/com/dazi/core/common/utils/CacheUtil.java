package com.dazi.core.common.utils;

/**
 * @desc: 缓存工具类
 * @author:guice_lin@126.com
 * @date: 2016/12/9
 */
public class CacheUtil {
    // 前缀
    private static final String PREFIX = "umall-";

    // 过期时间:200s
    public static final Integer EXPIRED = 300;

    public static String getKey(String... params) {
        StringBuilder key = new StringBuilder(PREFIX);
        for (String pa : params) {
            key.append(pa).append("-");
        }

        return key.length() > 0 ? key.substring(0, key.length() -1) : key.toString();
    }
}
