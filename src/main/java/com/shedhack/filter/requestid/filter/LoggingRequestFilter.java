package com.shedhack.filter.requestid.filter;

import com.shedhack.filter.requestid.helper.RequestHelper;

import javax.servlet.*;
import java.io.IOException;

/**
 * <pre>
 *  This filter sets the MDC context for logging frameworks (via slf4j-api).
 *  This filter is used with {@link com.shedhack.filter.requestid.filter.RequestIdFilter}
 *  which must be called prior to this in the filter chain (that filter sets some of the
 *  properties that are required for the MDC).
 *
 *  You can modify the MDC context by extending this filter and overriding the setMDC method.
 *  By default the MDC context contains:
 *
 *      request-id
 *      group-id
 *
 *  It will also log at INFO level via the log method (once again you could override this).
 *  It will attempt to log the entire {@link com.shedhack.filter.requestid.model.RequestModel}.
 * </pre>
 *
 * @author imamchishty
 */
public class LoggingRequestFilter implements Filter {

    private final LoggingHelper loggingHelper;

    public LoggingRequestFilter(LoggingHelper helper) {
        this.loggingHelper = helper;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            // Set the MDC context
            loggingHelper.setMDC(RequestHelper.get());

            // log
            loggingHelper.log(RequestHelper.get());

            // continue down the chain
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {

            // clear the MDC
            loggingHelper.clearMDC();
        }
    }

    public void destroy() {

    }

}
