package com.campus.share.constant;

public enum CodeEnum {

    SUCCESS("0000","操作成功"),
    FAIL("1000","操作失败");

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
