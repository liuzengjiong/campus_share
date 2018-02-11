package com.campus.share.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

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



}
