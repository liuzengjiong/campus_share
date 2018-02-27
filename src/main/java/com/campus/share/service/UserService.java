package com.campus.share.service;

import com.campus.share.bean.Result;
import com.campus.share.model.UserLogin;

public interface UserService {

    Result login(UserLogin loginReq,String serverPath);
}
