package com.campus.share.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.campus.share.model.UserLogin;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 采用MD5加密解密
 *
 */
public class MD5Util {


    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     *
     * @return 加密过后的字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:10
     */
    public static String getMD5(String... strs) {
        String str = StringUtils.join(strs);
        return getMD5(str);
    }

    /**
     * 得到MD5加密的字符串
     *
     * @param str 要进行加密的字符串
     * @return 加密过后的字符串
     */
    public static String getMD5(String str) {
        // 生成一个MD5摘要
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            // 计算MD5
            messageDigest.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("生成MD5错误", e.getMessage());
        }
        return "";
    }

    /**
     * 对user进行加密
     *
     * @param userLogin
     * @return 加密过后的字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:09
     */
    public static String getMD5(UserLogin userLogin) {
        return getMD5(userLogin.getUserNo(), userLogin.getPassword());
    }

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(3L);
        userLogin.setUserNo("111111");
        userLogin.setPassword("111111");
        System.out.println(getMD5(userLogin));
    }

}