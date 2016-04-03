package com.shedhack.filter.requestid.helper;

import com.shedhack.filter.requestid.model.RequestModel;

/**
 * ThreadLocal wrapper for {@link com.shedhack.filter.requestid.model.RequestModel}
 *
 * @author imamchishty
 */
public class RequestHelper {

    private static final ThreadLocal<RequestModel> local = new ThreadLocal<>();

    public static void set(RequestModel model) {
        local.set(model);
    }

    public static RequestModel get() {
        return local.get();
    }

    public static void clear(){
        local.remove();
    }

}