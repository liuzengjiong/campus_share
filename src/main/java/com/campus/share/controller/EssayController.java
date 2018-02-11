package com.campus.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.model.Essay;
import com.campus.share.service.EssayService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/essay")
public class EssayController {

    Logger logger = LoggerFactory.getLogger(EssayController.class);

    @Autowired
    private EssayService essayService;

    @Value("${spring.resources.static-locations}")
    private String statisPath;

    @RequestMapping("/list")
    public JSONObject list(HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin","*");

        String jsonStr = FileUtils.readFileToString(new File("db.json"));

        System.out.println("返回结果");

        JSONObject result = new JSONObject();
        result.put("code",0);
        result.put("body",JSONObject.parse(jsonStr));

        return result;
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result add(@RequestBody Essay essay){
        logger.info("请求添加帖子：{}" , JSONObject.toJSONString(essay));
        Result result = new Result();
        if(essayService.add(essay)){
            result.setInfoByEnum(CodeEnum.SUCCESS);
        }else{
            result.setInfoByEnum(CodeEnum.FAIL);
        }
        logger.info("返回结果：{}", JSONObject.toJSONString(result));
        return result;
    }




}
