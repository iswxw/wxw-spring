package com.wxw.domain;

import lombok.Data;

/**
 * @author: wxw
 * @date: 2021-03-21-11:54
 * @link:
 * @description:
 */
@Data
public class ExceptionInfo {
    private String location;
    private String exceptionMessage;
    private String exceptionType;
    private Object detailMessage;
}
