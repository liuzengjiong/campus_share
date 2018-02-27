package com.campus.share.controller;

import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/resource-types",method = RequestMethod.GET)
    public Result resourceType(){
        Result result = new Result();

        result.setData(configService.getValuesByType(FieldConstant.SOURCE_TYPE));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

    @RequestMapping(value = "/reward-types",method = RequestMethod.GET)
    public Result rewardType(){
        Result result = new Result();

        result.setData(configService.getValuesByType(FieldConstant.REWARD_TYPE));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }
}
