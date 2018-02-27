package com.campus.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.AppConfig;
import com.campus.share.dao.UserInfoMapper;
import com.campus.share.dao.UserLoginMapper;
import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;
import com.campus.share.service.UserService;
import com.campus.share.util.MD5Util;
import com.campus.share.util.SimpleStringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AppConfig appConfig;


    //用户登录
    @Override
    public Result login(UserLogin loginReq,String serverPath) {
        Result result = new Result();

        if(StringUtils.isEmpty(loginReq.getUserNo()) || StringUtils.isEmpty(loginReq.getPassword())){
            result.setInfoByEnum(CodeEnum.FAIL_LOGIN_EMPTY);
            return result;
        }

        UserLogin userLogin = userLoginMapper.selectByUserNo(loginReq.getUserNo());
        if(userLogin == null){
            result.setInfoByEnum(CodeEnum.FAIL_LOGIN);
            return result;
        }
        loginReq.setUserId(userLogin.getUserId());
        String encryptPwd = MD5Util.getMD5(loginReq);
        if(userLogin.getPassword().equals(encryptPwd)){
            result.setInfoByEnum(CodeEnum.SUCCESS_LOGIN);
            String token = MD5Util.getMD5(String.valueOf(userLogin.getUserId()),userLogin.getUserNo(),String.valueOf(System.currentTimeMillis()));
            Date invalidTime = DateUtils.addSeconds(new Date(), appConfig.getValidSeconds());
            userLogin.setToken(token);
            userLogin.setTokenInvalidTime(invalidTime);
            userLogin.setLastLoginTime(new Date());
            userLoginMapper.updateByPrimaryKey(userLogin);

            userLogin.setPassword(null);

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userLogin.getUserId());
            SimpleStringUtil.setAvatarUrl(userInfo,serverPath);

            JSONObject userObject=  new JSONObject();
            userObject.put("userLogin",userLogin);
            userObject.put("userInfo",userInfo);

            result.setData(userObject);

        }else{
            result.setInfoByEnum(CodeEnum.FAIL_LOGIN);
        }

        return result;
    }
}
