package com.shedhack.filter.requestid.filter;

import com.shedhack.filter.api.constant.HttpHeaderKeysEnum;
import com.shedhack.filter.api.handler.LoggingHandler;
import com.shedhack.filter.api.model.RequestModel;
import com.shedhack.filter.api.threadlocal.RequestThreadLocalHelper;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *  This filter sets the MDC context for logging frameworks (via slf4j-api).
 *  This filter is used with {@link RequestTraceFilter}
 *  which must be called prior to this in the filter chain (that filter sets some of the
 *  properties that are required for the MDC).
 *
 *  You can modify the MDC context by extending this filter and overriding the setup method.
 *  By default the MDC context contains:
 *
 *      request-id
 *      group-id
 *      caller-id
 *
 *  The cleanup method will clear the MDC.
 *
 *  As part of the construction for LoggingRequestFilter you need to pass
 *  either a list of {@link LoggingHandler} or just one. These will be executed sequentially (for a list).
 *  These allow you to log the {@link RequestModel} to your chosen destination.
 * </pre>
 *
 * @author imamchishty
 */
public class LoggingRequestFilter implements Filter {

    private final List<LoggingHandler> loggingHandlers;

    public LoggingRequestFilter(LoggingHandler loggingHandler) {
        this.loggingHandlers = Arrays.asList(loggingHandler);
    }

    public LoggingRequestFilter(List<LoggingHandler> loggingHandlers) {
        this.loggingHandlers = loggingHandlers;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            // Set the MDC context
            setup(RequestThreadLocalHelper.get());

            // log
            for(LoggingHandler handler : loggingHandlers) {
                handler.log(RequestThreadLocalHelper.get());
            }

            // continue down the chain
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {

            // clear the MDC
            cleanup();
        }
    }

    public void destroy() {

    }

    /**
     * Sets the MDC with:
     *
     * request-id
     * group-id
     */
    public void setup(RequestModel model) {
        if(model != null) {
            MDC.put(HttpHeaderKeysEnum.GROUP_ID.key(), model.getGroupId());
            MDC.put(HttpHeaderKeysEnum.REQUEST_ID.key(), model.toString());
            MDC.put(HttpHeaderKeysEnum.CALLER_ID.key(), model.getCallerId());
        }
    }

    /**
     * Clears the MDC.
     */
    public void cleanup() {
        MDC.clear();
    }
}
