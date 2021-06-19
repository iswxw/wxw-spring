package com.wxw.common.enums;


public enum ResultEnum {

    /**
     * 状态信息
     */
    _SUCCESS(0, "执行成功"),
    _FAILED(1, "执行失败"),
    _UNKNOWN_SERVER_ERROR(2, "未知错误"),
    _SERVER_EXCEPTION(3, "系统异常，请稍后重试"),
    _UNENABLE_OPERATE(4, "无法操作"),
    _IO_STREAM_ERROR(5, "IO流错误"),
    _PARAMETER_ERROR(6, "请求参数错误");


    private int enumCode;

    private String enumMsg;

    ResultEnum(int code, String msg) {
        this.enumCode = code;
        this.enumMsg = msg;
    }

    public int getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(int enumCode) {
        this.enumCode = enumCode;
    }

    public String getEnumMsg() {
        return enumMsg;
    }

    public void setEnumMsg(String enumMsg) {
        this.enumMsg = enumMsg;
    }
}