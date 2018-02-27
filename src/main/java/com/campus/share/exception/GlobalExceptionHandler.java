package com.campus.share.exception;

import com.campus.share.bean.Result;
import com.campus.share.constant.CodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

/**
 * Created by wuwf on 17/3/31.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultHandler(HttpServletRequest request, Exception e) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e);
//        modelAndView.addObject("url", request.getRequestURL());
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Result result = new Result();
        result.setInfoByEnum(CodeEnum.FAIL);
        result.setMsg("抱歉,系统发生异常,请稍后再试");


        logger.error("Intenal错误：",ex);

        return new ResponseEntity<Object>(result, NOT_EXTENDED);

    }


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result businessExHandler(HttpServletRequest request, BusinessException e) throws Exception {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());

        this.logBusi(e, request);


        return result;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonHandler(HttpServletRequest request, Exception e) throws Exception {
        Result result = new Result();
        result.setInfoByEnum(CodeEnum.FAIL);

        this.logSystem(e, request);


        return result;
    }



    private void logSystem(Exception ex, HttpServletRequest request) {
        logger.error("************************异常开始*******************************");

        logger.error("请求地址：" + request.getRequestURL());
        logger.error("异常信息",ex);
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }
        logger.error("************************异常结束*******************************");
    }

    private void logBusi(BusinessException ex, HttpServletRequest request) {
        logger.info("请求地址：" + request.getRequestURL());
        logger.info("返回信息 : " + ex.getMsg());
    }
}
