package com.campus.share.bean.vo.req;

import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;

public class RegisterReq {
    private UserLogin userLogin;

    private UserInfo userInfo;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
