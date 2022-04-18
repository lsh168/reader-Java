package com.lsh.sp.service.exception;

/**
 * @Author luminous
 * @Description //业务逻辑异常
 * @Date 16:33 2022/1/12
 **/
public class BussinessException extends RuntimeException {
    private String code;
    private String msg;

    public BussinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
