package com.campus.share.bean.vo.req;

public class UploadAvatarReq {

    private String fileStr;

    private String originName;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getFileStr() {
        return fileStr;
    }

    public void setFileStr(String fileStr) {
        this.fileStr = fileStr;
    }
}
