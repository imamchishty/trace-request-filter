package com.shedhack.filter.requestid.filter;

import com.shedhack.filter.requestid.model.RequestModel;

/**
 * Allows developers to decide what is set in the MDC and how to log the request model.
 * The {@link com.shedhack.filter.requestid.filter.DefaultLoggingHelper} will simply
 * log the request model at INFO level, whilst the MDC will contain the request-id and the
 * global-id.
 *
 * @author imamchishty
 */
public interface LoggingHelper {

    void setMDC(RequestModel model);

    void log(RequestModel model);

    void clearMDC();
}
