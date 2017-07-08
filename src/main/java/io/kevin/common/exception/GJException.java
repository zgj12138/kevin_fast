package io.kevin.common.exception;

/**
 * 自定义异常
 * @author ZGJ
 * @date 2017/7/1 15:35
 **/
public class GJException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private  String msg;
    private int code = 500;

    public GJException(String msg) {
        this.msg = msg;
    }
    public GJException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GJException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public GJException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
