package com.shedhack.filter.requestid.filter;

import com.shedhack.filter.requestid.constant.HttpHeaderKeysEnum;
import com.shedhack.filter.requestid.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Default implementation.
 *
 * @author imamchishty
 */
public class DefaultLoggingHelper implements LoggingHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoggingHelper.class);

    /**
     * Sets the MDC with:
     *
     * request-id
     * group-id
     */
    public void setMDC(RequestModel model) {
        if(model != null) {
            MDC.put(HttpHeaderKeysEnum.GROUP_ID.key(), model.getGroupId());
            MDC.put(HttpHeaderKeysEnum.REQUEST_ID.key(), model.toString());
        }
    }

    /**
     * Logs the {@link com.shedhack.filter.requestid.model.RequestModel}
     *
     * The MDC will already contain the request-id, group-id, so it isn't worth
     * logging those (you'd get them twice in the log entry).
     */
    public void log(RequestModel model) {
        if(model != null) {
            LOGGER.info(model.toStringWithoutMDC());
        }
    }

    public void clearMDC() {
        MDC.clear();
    }
}
