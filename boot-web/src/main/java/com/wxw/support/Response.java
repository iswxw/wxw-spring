package com.wxw.support;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.text.MessageFormat;

@Data
public class Response {
  // 用于默认返回成功时不要去NEW
  public final static Response SUCCESS = new Response(Result.SUCCESS);
  public final static Response FAILURE = new Response(Result.FAILURE);
  protected int code;
  protected String message;

  public Response() {

  }

  /**
   *
   * @param result 错误结果
   */
  public Response(Result result) {
    this.code = result.getCode();
    this.message = result.getMessage();
  }

  /**
   *
   * @param result 错误结果
   * @param message 错误信息
   */
  public Response(Result result, String message) {
    this.code = result.getCode();
    if (message == null)
      this.message = result.getMessage();
    else
      this.message = message;

  }

  public Response(Result result, String pattern, Object... arguments){
    this.code = result.getCode();
    String message = MessageFormat.format(pattern, arguments);
    if (message == null)
      this.message = result.getMessage();
    else
      this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String toJSONString() {
    return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
  }

  @Override
  public String toString() {
    return toJSONString();
  }
}
