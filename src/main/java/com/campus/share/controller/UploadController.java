package com.campus.share.controller;

import com.campus.share.bean.Result;
import com.campus.share.constant.FileConstant;
import com.campus.share.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private FileConstant fileConstant;

    //处理文件上传
    @RequestMapping(value="/uploadImage", method = RequestMethod.POST)
    public Result uploadImg(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request,
                            HttpServletResponse response    ) {
        Result result = new Result();
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String relativePath = fileConstant.getRelativeUploadPath();
        String basePath = fileConstant.getRealBasePath();
        String serverPath = fileConstant.getServerBasePath(request);
        String saveFilePath = basePath + relativePath;
        try {
            FileUtil.uploadFile(file.getBytes(), saveFilePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String displayFullPath = serverPath + relativePath + fileName;
        result.setCode("0000");
        result.setData(displayFullPath);
        //返回json
        return result;
    }

}
