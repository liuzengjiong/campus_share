package com.campus.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.constant.FileConstant;
import com.campus.share.model.Comment;
import com.campus.share.model.Essay;
import com.campus.share.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private FileConstant fileConstant;

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        logger.info("请求添加评论：{}" , JSONObject.toJSONString(comment));
        Result result = new Result();
        if(commentService.add(comment)){
            result.setInfoByEnum(CodeEnum.SUCCESS);
        }else{
            result.setInfoByEnum(CodeEnum.FAIL);
        }
        logger.info("返回结果：{}", JSONObject.toJSONString(result));
        return result;
    }

    @RequestMapping(value= "/list" , method = RequestMethod.GET)
    public Result list(Long essayId,
                       HttpServletRequest request){
        Result result = new Result();
        List<Comment> commentList = commentService.getCommentsByEssayId(essayId,fileConstant.getServerBasePath(request));
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setData(commentList);
        return result;
    }

}
