package io.kevin.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装结果集
 * @author ZGJ
 * @date 2017/7/1 15:55
 **/
public class Result extends HashMap<String, Object>{
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
