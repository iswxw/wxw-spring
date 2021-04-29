package com.wxw.common.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: wxw
 * @date: 2021-03-21-14:07
 * @link:
 * @description:
 */
@Component
@Slf4j
public class LogFilter implements Filter {
    private final static String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            // 在调用前通过 MDC加入traceId，调用完成后移除
            log.info("put requestId ({}) to logger", traceId);
            MDC.put(TRACE_ID, traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            String traceId = MDC.get(TRACE_ID);
            log.info("remove traceId ({}) from logger", traceId);
            if (traceId != null) {
                MDC.remove(TRACE_ID);
            }
        }
    }
}
