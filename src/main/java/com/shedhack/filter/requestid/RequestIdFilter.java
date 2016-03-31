package com.shedhack.filter.requestid;

import com.shedhack.thread.threadlocal.utility.ThreadLocalUtility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * <pre>
 *     This servlet filter attempts to create a 'request-id' (key can be changed via constructor) header property
 *     if this hasn't already been set. The request-id is unique and can be used
 *     for many purposes such as building metrics (user request count), auditing,
 *     logging, exception handling etc.
 *
 *     A HTTP server could already set the request-id property in the header, this
 *     might be preferable as this may enable an end-to-end view for this request.
 *
 *     The request-id http header property is the default name. You can change this
 *     via a constructor arg.
 *
 *     To enable easy access to the request Id value it gets stored on as a ThreadLocal variable.
 *     Please note that the ThreadLocal gets cleaned up.
 *
 *     In order to store as a ThreadLocal this filter uses {@link com.shedhack.thread.threadlocal.utility.ThreadLocalUtility}.
 *     Please note that this dependency is also available in Maven Central, please refer to
 *     https://github.com/imamchishty/threadlocal-string-utility for more details about its use.
 * </pre>
 *
 * @author imamchishty
 */
public class RequestIdFilter implements Filter {

    /**
     * Key for the HTTP Header property RequestId, if not provided then it defaults to 'request-id'
     */
    public RequestIdFilter(String requestIdKey) {
        this.requestIdKey = requestIdKey;
    }

    /**
     * Default constructor with the HTTP header property for requestId key set to 'request-id'.
     */
    public RequestIdFilter() {

    }

    private String requestIdKey = "request-id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestId = ((HttpServletRequest) request).getHeader(requestIdKey);
            HeaderWrapper headerWrapper = new HeaderWrapper(httpRequest);

            if (requestId == null) {
                requestId = UUID.randomUUID().toString();
                headerWrapper.addHeader(requestIdKey, requestId);
            }

            // thread local
            ThreadLocalUtility.set(requestId);

            // continue down the chain
            chain.doFilter(headerWrapper, response);
        }
        finally {

            // clean up
            ThreadLocalUtility.clear();
        }
    }

    @Override
    public void destroy() {

    }

    public String getRequestIdKey() {
        return requestIdKey;
    }


    public class HeaderWrapper extends HttpServletRequestWrapper {

        public HeaderWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<>();

        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            for (String name : headerMap.keySet()) {
                names.add(name);
            }
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = Collections.list(super.getHeaders(name));
            if (headerMap.containsKey(name)) {
                values.add(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }

    }
}
