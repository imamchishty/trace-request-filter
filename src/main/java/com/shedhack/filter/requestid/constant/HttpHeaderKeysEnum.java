package com.shedhack.filter.requestid.constant;

/**
 * HTTP header keys.
 *
 * @author imamchishty
 */
public enum HttpHeaderKeysEnum {

    REQUEST_ID("request-id"),
    GROUP_ID("group-id"),
    CALLER_ID("caller-id"),
    HOST("host"),
    CLIENT_ADDRESS("X-Forwarded-For");

    private final String key;

    HttpHeaderKeysEnum(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
