package com.campus.share.constant;

public enum CodeEnum {

    SUCCESS("0000","操作成功"),
    FAIL("1000","抱歉,系统异常,请稍后再试"),
    UNLOGIN("2001","未登录"),
    NO_PERMISSION("3001","抱歉，您无权进行此操作"),

    SUCCESS_LOGIN("0000","登录成功"),
    FAIL_LOGIN("1001","用户名或密码不正确"),
    FAIL_LOGIN_EMPTY("1002","请输入用户名和密码"),
    FAIL_ESSAY_NOT_EXIST("1003","任务不存在"),
    FAIL_CONFIG_NOT_MATCH("1004","参数不匹配"),

    UNLOGIN_TOKEN_INVALID("2001","登录已过期");

    private String code;
    private String msg;

    CodeEnum(String code,String msg){
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
