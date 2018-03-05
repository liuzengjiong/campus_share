package com.campus.share.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        System.out.println(filePath);
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String getTimestr(){
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static boolean generateImage(String imgStr, String path, String fileName) {
        logger.info("{}:{}：{}",path,imgStr,fileName);
        if (imgStr == null){
            return false;
        }
        if(imgStr.indexOf(",") != -1){
            imgStr = imgStr.split(",")[1];
        }

        File targetFile = new File(path);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        String fullPath = path + fileName;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(fullPath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e) {
            logger.error("转化图片发生错误",e);
            return false;
        }
    }

}
