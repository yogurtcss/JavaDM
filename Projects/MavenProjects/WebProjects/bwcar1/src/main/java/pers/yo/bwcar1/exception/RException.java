package pers.yo.bwcar1.exception;

public class RException extends RuntimeException {
    //关于Response响应的异常

    private int code; //状态码
    private String msg; //异常信息

    public RException() {
    }

    public RException(String msg) {
        this.msg = msg;
    }

    public RException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
