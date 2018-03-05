package com.campus.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.bean.vo.req.RegisterReq;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FileConstant;
import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;
import com.campus.share.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private FileConstant fileConstant;

    @Autowired
    private UserService userService;

    @RequestMapping("/login/{username}")
    public JSONObject hello(@PathVariable String username) {
        JSONObject object = new JSONObject();
        object.put("data", "hello:" + username);
        return object;
    }


    @RequestMapping("/login")
    public Result login(@RequestBody UserLogin loginReq,HttpServletRequest request){
        logger.info("登录请求");
        Result result = userService.login(loginReq,fileConstant.getServerBasePath(request));
        logger.info("返回结果：{}",JSONObject.toJSONString(result));
        return result;
    }

    @RequestMapping("/register")
    public Result register(@RequestBody RegisterReq req){
        logger.info("注册请求:{}",JSONObject.toJSONString(req));
        Result result = userService.register(req.getUserLogin(),req.getUserInfo());
        logger.info("返回结果：{}",JSONObject.toJSONString(result));
        return result;
    }

    @RequestMapping("/update")
    public Result updateUser(@RequestBody UserInfo userInfo,HttpServletRequest request){
        logger.info("修改信息请求");
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            Result result = new Result();
            result.setInfoByEnum(CodeEnum.UNLOGIN);
            return result;
        }
        userInfo.setUserId(userLogin.getUserId());
        return userService.update(userInfo,fileConstant.getServerBasePath(request));
    }



}
