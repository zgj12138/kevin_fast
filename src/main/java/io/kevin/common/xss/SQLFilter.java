package io.kevin.common.xss;

import org.apache.commons.lang.StringUtils;

/**
 * SQL过滤
 * @author ZGJ
 * @date 2017/7/1 15:17
 **/
public class SQLFilter {
    /**
     * SQL注入过滤
     * @param str
     * @return
     */
    public static String sqlInject(String str) {
        if(StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'"|;\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select",
                "delete", "update", "declare", "alert", "drop"};
        for(String keyword : keywords) {
            if(str.indexOf(keyword) != -1) {
                throw new RuntimeException("包含非法字符");
            }
        }
        return str;
    }
}
