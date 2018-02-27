package com.campus.share.exception;

import com.campus.share.constant.CodeEnum;

public class BusinessException extends RuntimeException{

    private String code;

    private String msg;

    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
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
