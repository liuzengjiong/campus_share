package com.campus.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.AppConfig;
import com.campus.share.dao.UserInfoMapper;
import com.campus.share.dao.UserLoginMapper;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;
import com.campus.share.service.UserService;
import com.campus.share.util.MD5Util;
import com.campus.share.util.SimpleStringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Result update(UserInfo userInfo,String serverPath) {
        Result result = new Result();
        if(userInfoMapper.updateInfo(userInfo) > 0){
            result.setInfoByEnum(CodeEnum.SUCCESS);
            UserInfo newInfo = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
            SimpleStringUtil.setAvatarUrl(newInfo,serverPath);
            result.setData(newInfo);

        }else{
            result.setInfoByEnum(CodeEnum.FAIL);
        }
        return result;
    }

    @Override
    public UserInfo get(Long userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result register(UserLogin userLogin, UserInfo userInfo) {
        if(StringUtils.isEmpty(userLogin.getUserNo()) || StringUtils.isEmpty(userLogin.getPassword())){
            throw new BusinessException(CodeEnum.FAIL.getCode(),"用户名和密码不能为空");
        }
        UserLogin haveUser = userLoginMapper.selectByUserNo(userLogin.getUserNo());
        if(haveUser!=null){
            throw new BusinessException(CodeEnum.FAIL.getCode(),"该帐号已被注册");
        }
        String encryptPwd = MD5Util.getMD5(userLogin);
        userLogin.setPassword(encryptPwd);

        Result result = new Result();
        int x = userLoginMapper.insert(userLogin);
        if(x == 1){
            userInfo.setUserId(userLogin.getUserId());
        }else{
            throw new BusinessException(CodeEnum.FAIL.getCode(),"抱歉，注册失败，请稍后重试");
        }
        if(userInfoMapper.insert(userInfo) != 1){
            throw new BusinessException(CodeEnum.FAIL.getCode(),"抱歉，注册失败，请稍后重试");
        }
        result.setInfoByEnum(CodeEnum.SUCCESS);
        return result;
    }


}
