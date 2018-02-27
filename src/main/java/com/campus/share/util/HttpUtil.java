package com.campus.share.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JSONObject.writeJSONString(out,responseObject);
            logger.info("返回结果：{}" , JSONObject.toJSONString(responseObject));
        } catch (IOException e) {
            logger.error("返回结果异常",e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
