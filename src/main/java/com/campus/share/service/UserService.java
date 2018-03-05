package com.campus.share.service;

import com.campus.share.bean.Result;
import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;

public interface UserService {

    Result login(UserLogin loginReq,String serverPath);

    Result update(UserInfo userInfo,String serverPath);

    UserInfo get(Long userId);

    Result register(UserLogin userLogin,UserInfo userInfo);
}
