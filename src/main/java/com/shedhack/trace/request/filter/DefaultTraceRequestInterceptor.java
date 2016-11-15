package com.shedhack.trace.request.filter;

import com.google.gson.Gson;
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

    public DefaultTraceRequestInterceptor(Gson gson) {
        if(gson != null) {
            this.gson = gson;
        }
        else {
            this.gson = new Gson();
        }
    }

    private Gson gson;


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

        if(request != null && LOGGER.isInfoEnabled()) {
            LOGGER.info(gson.toJson(request));
        }
    }
}
