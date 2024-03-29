package com.zj.common.config;

import com.alibaba.fastjson.JSONObject;
import com.zj.common.constant.StateEnum;
import com.zj.common.exception.MyAuthException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: 全局异常拦截
 * @date 2023/1/13 14:28
 */
@ControllerAdvice
@ResponseBody
public class GlobalException extends Exception {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JSONObject notNullFilter(MethodArgumentNotValidException e) {
        Result<List<String>> result = new Result<>();
        // 错误类型表单验证错误
        result.setResultEnum(StateEnum.FROM_FAIL);
        List<String> errorMessageList = new ArrayList<String>();
        result.setData(errorMessageList);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            errorMessageList.add(error.getDefaultMessage());
        }
        return result.toJSON();
    }
    @ExceptionHandler(value = MyAuthException.class)
    public JSONObject authFilter(MyAuthException e){
        e.printStackTrace();
        return new Result<String>().setResultEnum(StateEnum.LOGIN_EXPIRES).setData(e.getMessage()).toJSON();
    }

    @ExceptionHandler(value = Exception.class)
    public JSONObject normalFilter(Exception e) {
        e.printStackTrace();
        return new Result().setResultEnum(StateEnum.FAILED).toJSON();
    }

}
