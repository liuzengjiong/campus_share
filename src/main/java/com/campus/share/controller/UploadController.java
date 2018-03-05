package com.campus.share.controller;

import com.campus.share.bean.Result;
import com.campus.share.bean.vo.req.UploadAvatarReq;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FileConstant;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.UserInfo;
import com.campus.share.model.UserLogin;
import com.campus.share.service.UserService;
import com.campus.share.util.FileUtil;
import com.campus.share.util.SimpleStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileConstant fileConstant;

    @Autowired
    private UserService userService;

    //处理文件上传
    @RequestMapping(value="/uploadImage", method = RequestMethod.POST)
    public Result uploadImg(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request,
                            HttpServletResponse response    ) {
        Result result = new Result();
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        logger.debug("fileName-->" + fileName);
        logger.debug("getContentType-->" + contentType);
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


    @RequestMapping(value = "/avatar" , method = RequestMethod.POST)
    public Result uploadAvatar(@RequestBody UploadAvatarReq reqBean,
                               HttpServletRequest request) {
        logger.info("上传头像请求");

        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            throw new BusinessException(CodeEnum.UNLOGIN);
        }
        String relativePath = fileConstant.getRelativeUploadPath();
        String basePath = fileConstant.getRealBasePath();
        String timeStr = FileUtil.getTimestr();
        String saveFilePath = basePath + relativePath;
        String fileName = timeStr + reqBean.getOriginName();

        boolean success = FileUtil.generateImage(reqBean.getFileStr(),saveFilePath,fileName);

        if(success){
            result.setInfoByEnum(CodeEnum.SUCCESS);

            UserInfo userInfo = new UserInfo();
            userInfo.setAvatarPath(relativePath + fileName);
            userInfo.setUserId(userLogin.getUserId());
            result = userService.update(userInfo,fileConstant.getServerBasePath(request));
        }else{
            result.setInfoByEnum(CodeEnum.FAIL);
        }

        return result;

    }

}
