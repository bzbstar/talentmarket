package com.bzb.talentmarket.common;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.exception.CheckParamsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bzb
 * @Description: 统一异常处理
 * @date 2018/9/25 17:19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel handle(Exception e) {
        String message = "系统服务异常，请稍后重试";
        if (e instanceof CheckParamsException) {
            CheckParamsException checkParamsException = (CheckParamsException) e;
            message = e.getMessage();
        }
        e.printStackTrace();
        return new ResultModel(false, message);
    }
}
