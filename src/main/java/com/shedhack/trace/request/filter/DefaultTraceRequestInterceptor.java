package com.shedhack.trace.request.filter;

import com.shedhack.trace.request.api.interceptor.TraceRequestInterceptor;
import com.shedhack.trace.request.api.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default {@link TraceRequestInterceptor} implementation.
 * Simply logs the request model (ONLY ON EXIT) to the appropriate log file.
 *
 * @author imamchishty
 */
public class DefaultTraceRequestInterceptor implements TraceRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTraceRequestInterceptor.class);

    /**
     * {@inheritDoc}
     */
    public void onEntry(RequestModel requestModel) {
        // only record on exit
    }

    /**
     * {@inheritDoc}
     */
    public void onExit(RequestModel request) {
        if(request != null) {
            LOGGER.info("{" +
                    "\"requestId\": \"" + request.getRequestId() + "\"" +
                    ", \"applicationId\": \"" + request.getApplicationId() + "\"" +
                    ", \"groupId\": \"" + request.getGroupId() + "\"" +
                    ", \"callerId\": \"" + request.getCallerId() + "\"" +
                    ", \"path\": \"" + request.getPath() + "\"" +
                    ", \"sessionId\": \"" + request.getSessionId() + "\"" +
                    ", \"httpMethod\": \"" + request.getHttpMethod() + "\"" +
                    ", \"clientAddress\": \"" + request.getClientAddress() + "\"" +
                    ", \"hostAddress\": \"" + request.getHostAddress() + "\"" +
                    ", \"headers\": \"" + request.getHeaders() + "\"" +
                    ", \"exceptionId\": \"" + request.getExceptionId() + "\"" +
                    ", \"requestDateTime\": \"" + request.getRequestDateTime() + "\"" +
                    ", \"responseDateTime\": \"" + request.getResponseDateTime() + "\"" +
                    ", \"status\": \"" + request.getStatus() + "\"" +
                    "}");
        }
    }
}
