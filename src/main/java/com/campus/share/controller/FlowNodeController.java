package com.campus.share.controller;

import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.UserLogin;
import com.campus.share.service.FlowNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(value = "/essay/count",method = RequestMethod.GET)
    public Result countEssay(HttpServletRequest request){
        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            throw new BusinessException(CodeEnum.UNLOGIN);
        }
        result.setData(flowNodeService.countEssayWithFlowNode(userLogin.getUserId()));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }


    @RequestMapping(value = "/{nodeKey}",method = RequestMethod.GET)
    public Result getNodeEssay(HttpServletRequest request,
                               @PathVariable String nodeKey){
        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            throw new BusinessException(CodeEnum.UNLOGIN);
        }
        result.setData(flowNodeService.getNodeEssay(userLogin.getUserId(), nodeKey));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

    @RequestMapping(value = "/essay/actor/count",method = RequestMethod.GET)
    public Result countActorEssay(HttpServletRequest request){
        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            throw new BusinessException(CodeEnum.UNLOGIN);
        }
        result.setData(flowNodeService.countActorEssayFlowNode(userLogin.getUserId()));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

    @RequestMapping(value = "/actor/{nodeKey}",method = RequestMethod.GET)
    public Result getActorNodeEssay(HttpServletRequest request,
                               @PathVariable String nodeKey){
        Result result = new Result();
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
        if(userLogin == null){
            throw new BusinessException(CodeEnum.UNLOGIN);
        }
        result.setData(flowNodeService.getActorNodeEssay(userLogin.getUserId(), nodeKey));
        result.setInfoByEnum(CodeEnum.SUCCESS);

        return result;
    }

}
