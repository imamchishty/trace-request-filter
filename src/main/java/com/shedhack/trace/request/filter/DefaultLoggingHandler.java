package com.shedhack.trace.request.filter;

import com.shedhack.trace.request.api.logging.LoggingHandler;
import com.shedhack.trace.request.api.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation which uses SLF4J api to
 * setup the MDC (in the setup method), to log at INFO level (via the log method)
 * and finally to clear the MDC (in the cleanup method).
 *
 * @author imamchishty
 */
public class DefaultLoggingHandler implements LoggingHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoggingHandler.class);

    /**
     * Logs the {@link RequestModel}
     *
     */
    public void log(RequestModel model) {
        if(model != null) {
            LOGGER.info("{" +
                    "applicationId='" + model.getApplicationId() + '\'' +
                    ", requestId='" + model.getRequestId() + '\'' +
                    ", groupId='" + model.getGroupId() + '\'' +
                    ", callerId='" + model.getCallerId() + '\'' +
                    ", path='" + model.getPath() + '\'' +
                    ", sessionId='" + model.getSessionId() + '\'' +
                    ", httpMethod='" + model.getHttpMethod() + '\'' +
                    ", clientAddress='" + model.getClientAddress() + '\'' +
                    ", hostAddress='" + model.getHostAddress() + '\'' +
                    ", dateTime=" + model.getDateTime() +
                    ", headers=" + model.getHeaders() +
                    '}');
        }
    }



}
