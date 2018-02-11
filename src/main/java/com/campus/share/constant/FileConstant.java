package com.campus.share.constant;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class FileConstant {

    @Value("${spring.http.multipart.location}")
    private String basePath;

    private final String imagePath = "image/";

    private final String dateFormat = "yyyyMMdd";

    /**
     * 根据当天日期获取相对目录 - 图片上传
     * @return
     */
    public String getRelativeUploadPath(){
        String dateStr = DateFormatUtils.format(new Date(),dateFormat);
        return imagePath + dateStr + "/";
    }

    /**
     * 获取物理存储路径
     * @return
     */
    public String getRealBasePath(){
        return basePath;
    }

    /**
     * 获取服务器地址
     * @param request
     * @return
     */
    public String getServerBasePath(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    }

}
