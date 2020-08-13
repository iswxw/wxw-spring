package com.wxw.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author ：wxw.
 * @ Date ： 9:27 2020/8/13
 * @ Description：Spring 内置的过滤器 排除内部页面跳转过滤，只过滤外部请求
 * @ Version:   v_0.0.1
 */
public class SessionSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("我是Spring 内置过滤器");
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        filterChain.doFilter(request,response);
    }
}
