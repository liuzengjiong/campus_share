package com.campus.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.bean.vo.EssayVO;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.constant.FileConstant;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.Config;
import com.campus.share.model.Essay;
import com.campus.share.model.UserLogin;
import com.campus.share.service.ConfigService;
import com.campus.share.service.EssayService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

@RestController
@RequestMapping("/essay")
public class EssayController {

    Logger logger = LoggerFactory.getLogger(EssayController.class);

    @Autowired
    private EssayService essayService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private FileConstant fileConstant;

    @Value("${spring.resources.static-locations}")
    private String statisPath;


    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result add(@RequestBody EssayVO essay){
        logger.info("请求添加任务：{}" , JSONObject.toJSONString(essay));
        Result result = new Result();

        if(essayService.add(essay)){
            result.setInfoByEnum(CodeEnum.SUCCESS);
        }else{
            result.setInfoByEnum(CodeEnum.FAIL);
        }
        logger.info("返回结果：{}", JSONObject.toJSONString(result));
        return result;
    }


    @RequestMapping(value= "/list/{page}/{pageSize}" , method = RequestMethod.GET)
    public Result list(String keyword, String essayType, String sourceType,
                       @PathVariable Integer page, @PathVariable Integer pageSize,
                       HttpServletRequest request){
        if(page < 1){
            page = 1;
        }

        Result result = new Result();
        PageInfo<EssayVO> pageInfo = essayService.searchEssay(keyword,essayType,sourceType,page,pageSize,fileConstant.getServerBasePath(request));
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setData(pageInfo);
        return result;
    }
    @RequestMapping(value = "/{essayId}" , method = RequestMethod.GET)
    public Result getOne(@PathVariable Long essayId,
                         HttpServletRequest request){
        Result result = new Result();
        EssayVO essay = essayService.selectById(essayId,fileConstant.getServerBasePath(request));
        if(essay == null){
            throw new BusinessException(CodeEnum.FAIL_ESSAY_NOT_EXIST);
        }
        result.setData(essay);
        result.setCode(CodeEnum.SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value = "/changeStatus/{essayId}/{currStatus}" , method = RequestMethod.POST)
    public Result changeStatus(@PathVariable Long essayId,@PathVariable Integer currStatus,
                         HttpServletRequest request){
        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        Essay essay = essayService.selectSimpleInfo(essayId);
        if(essay == null){
            throw new BusinessException(CodeEnum.FAIL_ESSAY_NOT_EXIST);
        }
        logger.info(JSONObject.toJSONString(userLogin));
        logger.info(JSONObject.toJSONString(essay));
        if(userLogin == null || userLogin.getUserId() != essay.getAuthorId()){
            throw new BusinessException(CodeEnum.NO_PERMISSION);
        }
        Integer changedStatus = (currStatus+1) % 2;
        if(!essayService.changeStatus(essayId,changedStatus)){
            throw new BusinessException(CodeEnum.FAIL);
        }
        result.setData(changedStatus);
        result.setCode(CodeEnum.SUCCESS.getCode());
        return result;
    }


}
