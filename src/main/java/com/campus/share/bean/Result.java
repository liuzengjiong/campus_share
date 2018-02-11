package com.campus.share.bean;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.constant.CodeEnum;

/**
 * 返回结果类
 */
public class Result {

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setInfoByEnum(CodeEnum codeEnum){
        this.setCode(codeEnum.getCode());
        this.setMsg(codeEnum.getMsg());
    }
}
