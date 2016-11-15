package com.shedhack.trace.request.filter.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP Utilities
 *
 * @author imamchishty
 */
public class HttpUtilities {


    /**
     * Returns a JSON string from the header keys + values.
     */
    public static Map<String, String> headerNamesValuesAsString(HttpServletRequest request) {

        Map<String, String> headers = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }


        return headers;
    }

}
