package com.baiyu.tmall.util;

public enum ResultEnum {

    SUCCESS("0", "成功"),
    ERROR("1", "失败"),
    EXPIRE("2", "登陆失效");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
