package com.campus.share.controller;

import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.service.FlowNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow-node")
public class FlowNodeController {

    @Autowired
    private FlowNodeService flowNodeService;

    @RequestMapping(value = "/have-received/{essayId}/{userId}",method = RequestMethod.GET)
    public Result haveReceived(@PathVariable  Long essayId,
                               @PathVariable Long userId){
        Result result = new Result();

        result.setData(flowNodeService.getLastNode(essayId,userId));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

    @RequestMapping(value = "/essay/{essayId}",method = RequestMethod.GET)
    public Result flowNodes(@PathVariable  Long essayId){
        Result result = new Result();

        result.setData(flowNodeService.getDisplayFlowNodes(essayId));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

}
