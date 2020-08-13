package com.wxw.common.filter;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;

/**
 * 自定义过滤器
 * @Author: wxw
 * @create: 2020-08-09-22:25
 */
//public class CustomFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        long start = System.currentTimeMillis();
//        System.out.println("start = " + start);
//        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("end = " + System.currentTimeMillis());
//        System.out.println("Execute cost="+(System.currentTimeMillis()-start));
//        System.out.println(" ========================================== ");
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
