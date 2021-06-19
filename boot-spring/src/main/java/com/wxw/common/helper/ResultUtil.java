package com.wxw.common.helper;

import com.wxw.common.response.Result;
import com.wxw.common.enums.ResultEnum;

public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     * @param object 数据文言
     */
    public static Result<Object> success(Object object){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum._SUCCESS.getEnumCode());
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }

    /**
     * 返回异常信息
     * @param exceptionEnum 异常信息
     */
    public static Result<Object> error(ResultEnum exceptionEnum){
        Result<Object> result = new Result<>();
        result.setCode(exceptionEnum.getEnumCode());
        result.setMsg(exceptionEnum.getEnumMsg());
        result.setData(null);
        return result;
    }

    /**
     * 返回自定义信息
     */
    public static Result<Object> customize(int code, String msg, Object data){
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回异常信息
     * @param exceptionMsg 异常信息
     */
    public static Result<Object> exception(String exceptionMsg){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum._SERVER_EXCEPTION.getEnumCode());
        if(exceptionMsg == null || exceptionMsg.isEmpty()){
            exceptionMsg = ResultEnum._SERVER_EXCEPTION.getEnumMsg();
        }
        result.setMsg(exceptionMsg);
        result.setData(null);
        return result;
    }

    /**
     * 返回@validated参数校验异常信息
     * @param exceptionMsg 异常信息
     */
    public static Result<Object> validatedException(String exceptionMsg){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum._PARAMETER_ERROR.getEnumCode());
        if(exceptionMsg == null || exceptionMsg.isEmpty()){
            exceptionMsg = ResultEnum._PARAMETER_ERROR.getEnumMsg();
        }
        result.setMsg(exceptionMsg);
        result.setData(null);
        return result;
    }

}