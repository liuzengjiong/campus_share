package com.campus.share.util;

import com.campus.share.model.UserInfo;
import com.github.pagehelper.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleStringUtil {

    public static String removeHtmlTag(final String htmlStr){
        if(htmlStr == null){
            return null;
        }
        String result = htmlStr;
        String regEx_html="<[^>]+>";
        Pattern p_html=Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(result);
        result=m_html.replaceAll(""); //过滤html标签

        return result.trim();
    }

    public static void setAvatarUrl(UserInfo userInfo, String serverPath){
        if(userInfo == null){
            return;
        }
        String avatarPath = userInfo.getAvatarPath();
        if(StringUtil.isEmpty(serverPath) || StringUtil.isEmpty(avatarPath) || avatarPath.startsWith(serverPath)){
            return;
        }
        String avatarUrl = serverPath + avatarPath;
        userInfo.setAvatarPath(avatarUrl);
    }

}
