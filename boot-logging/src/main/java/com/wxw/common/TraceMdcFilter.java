package com.wxw.common;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ：wxw.
 * @date ： 14:49 2021/3/23
 * @description：
 * @link:
 * @version: v_0.0.1
 */
@Configuration
public class TraceMdcFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            // MDC slf4j = logback | log4j
            // ThreadContext  log4j2
            MDC.put(TRACE_ID,traceId);

            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
           if (MDC.get(TRACE_ID) != null){
               MDC.remove(TRACE_ID);
           }
        }
    }
}
