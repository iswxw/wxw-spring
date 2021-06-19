package com.wxw.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 接口调用结果封装类，包含操作结果码，操作结果文言，返回数据体
 */
@Data
public class Result<T> {

    private int code;
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
