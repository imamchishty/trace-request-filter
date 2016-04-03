package com.shedhack.filter.requestid.model;

/**
 * Simple model containing basic tracing Id's.
 * @author imamchishty
 */
public class RequestModel {

    private final String requestId, groupId, callerId;

    public RequestModel(String requestId, String groupId, String callerId) {
        this.requestId = requestId;
        this.groupId = groupId;
        this.callerId = callerId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCallerId() {
        return callerId;
    }
}
