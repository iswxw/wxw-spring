package com.wxw.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  (2)全局级别异常处理器 实现HandlerExceptionResolver接口
 *     缺点分析：比如这种方式全局异常处理返回JSP、velocity等视图比较方便
 * @Author: wxw
 * @create: 2020-08-09-11:57
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生全局异常!");
        ModelMap mmp=new ModelMap();
        mmp.addAttribute("ex",ex.getMessage());
        response.addHeader("Content-Type","application/json;charset=UTF-8");
        try {
            System.out.println("ex = " + ex);
            new ObjectMapper().writeValue(response.getWriter(),"网络不给力，请稍后重试！");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
/**
 * 使用方式： 只需要将该Bean加入到Spring容器，可以通过Xml配置，也可以通过注解方式加入容器;
 *           方法返回值不为null才有意义，如果方法返回值为null，可能异常就没有被捕获.
 */
