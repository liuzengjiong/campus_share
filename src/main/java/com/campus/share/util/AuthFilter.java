package com.campus.share.util;

import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.AppConfig;
import com.campus.share.dao.UserLoginMapper;
import com.campus.share.model.UserLogin;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 验证登录态
 */
@Component
public class AuthFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private final String[] unCheckList = new String[]{
            "/login",
            "/essay/list",
            "/image",
            "/upload"
    };

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private AppConfig appConfig;

    private boolean isNeedCheck(final String targetUrl){
        if(targetUrl == null){
            return true;
        }
        for(String unCheckUrl : this.unCheckList){
            if((targetUrl.indexOf(unCheckUrl) != -1)){
                return false;
            }
        }
        return true;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
            return;

        }
        String originAddr = request.getHeader("referer");
        String targetUrl = request.getRequestURL().toString();
        logger.info("{} >访问：{}",originAddr,targetUrl);
        if(!isNeedCheck(targetUrl)){
            this.setLoginInfo(request);
            chain.doFilter(req, res);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Auth ")) {
            Result result = new Result();
            result.setInfoByEnum(CodeEnum.UNLOGIN);
            HttpUtil.responseOutWithJson((HttpServletResponse)res,result);
            return;
        }
        String token = authHeader.substring(5);

        UserLogin userLogin = userLoginMapper.selectIfValidByToken(token);
        if(userLogin == null){
            Result result = new Result();
            result.setInfoByEnum(CodeEnum.UNLOGIN_TOKEN_INVALID);
            HttpUtil.responseOutWithJson((HttpServletResponse)res,result);
            return;
        }
        //更新失效时间
        Date invalidTime = DateUtils.addSeconds(new Date(), appConfig.getValidSeconds());
        userLogin.setTokenInvalidTime(invalidTime);
        userLoginMapper.updateTokenInvalidTime(userLogin);

        request.setAttribute("userLogin",userLogin);
        chain.doFilter(req,res);

    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}

    private void setLoginInfo(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Auth ")) {
            return;
        }
        String token = authHeader.substring(5);

        UserLogin userLogin = userLoginMapper.selectIfValidByToken(token);
        if(userLogin == null){
            return;
        }
        //更新失效时间
        Date invalidTime = DateUtils.addSeconds(new Date(), appConfig.getValidSeconds());
        userLogin.setTokenInvalidTime(invalidTime);
        userLoginMapper.updateTokenInvalidTime(userLogin);

        request.setAttribute("userLogin",userLogin);
    }
}