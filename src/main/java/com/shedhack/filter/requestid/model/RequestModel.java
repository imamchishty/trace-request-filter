package com.shedhack.filter.requestid.model;

/**
 * <pre>
 *  Request model containing the following properties:
 *
 *  requestId, groupId, callerId, path, sessionId, httpMethod,
 *  clientAddress, hostAddress
 *
 * </pre>
 *
 * @author imamchishty
 */
public class RequestModel {


    // ----------------------------------
    // Static inner class for the builder
    // ----------------------------------

    public static class Builder {

        RequestModel model = new RequestModel();

        public Builder withRequestId(String requestId) {
            model.requestId = requestId;
            return this;
        }

        public Builder withGroupId(String groupId) {
            model.groupId = groupId;
            return this;
        }

        public Builder withCallerId(String callerId) {
            model.callerId = callerId;
            return this;
        }

        public Builder withPath(String path) {
            model.path = path;
            return this;
        }

        public Builder withSessionId(String sessionId) {
            model.sessionId = sessionId;
            return this;
        }

        public Builder withHttpMethod(String method) {
            model.httpMethod = method;
            return this;
        }

        public Builder withClientAddress(String address) {
            model.clientAddress = address;
            return this;
        }

        public Builder withHostAddress(String address) {
            model.hostAddress= address;
            return this;
        }

        public RequestModel build() {
            return model;
        }
    }

    // ----------------
    // Static method
    // ----------------

    public static Builder builder() {
        return new Builder();
    }

    // -----------------------
    // Request Model variables
    // -----------------------

    private String requestId, groupId, callerId, path, sessionId, httpMethod,
            clientAddress, hostAddress;

    public RequestModel() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestModel model = (RequestModel) o;

        if (!callerId.equals(model.callerId)) return false;
        if (!clientAddress.equals(model.clientAddress)) return false;
        if (!groupId.equals(model.groupId)) return false;
        if (!hostAddress.equals(model.hostAddress)) return false;
        if (!httpMethod.equals(model.httpMethod)) return false;
        if (!path.equals(model.path)) return false;
        if (!requestId.equals(model.requestId)) return false;
        if (!sessionId.equals(model.sessionId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = requestId.hashCode();
        result = 31 * result + groupId.hashCode();
        result = 31 * result + callerId.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + sessionId.hashCode();
        result = 31 * result + httpMethod.hashCode();
        result = 31 * result + clientAddress.hashCode();
        result = 31 * result + hostAddress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "requestId='" + requestId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", callerId='" + callerId + '\'' +
                ", path='" + path + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                '}';
    }

    public String toStringWithoutMDC() {
        return "{" +
                "path='" + path + '\'' +
                ", callerId='" + callerId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                '}';
    }
}
