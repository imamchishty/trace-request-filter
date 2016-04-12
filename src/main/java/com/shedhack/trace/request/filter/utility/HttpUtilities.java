package com.shedhack.trace.request.filter.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * HTTP Utilities
 *
 * @author imamchishty
 */
public class HttpUtilities {

    private static final String JSON_START_BRACE = "{";
    private static final String JSON_END_BRACE = "}";
    private static final String JSON_COLON = ":";
    private static final String JSON_COMMA = ",";
    private static final String JSON_QUOTE = "\"";

    /**
     * Returns a JSON string from the header keys + values.
     */
    public static String headerNamesValuesAsString(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();

        StringBuffer sb = new StringBuffer(JSON_START_BRACE);

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();

            sb.append(JSON_QUOTE).append(headerName).append(JSON_QUOTE)
                .append(JSON_COLON).append(JSON_QUOTE)
                    .append(request.getHeader(headerName)).append(JSON_QUOTE);

            if(headerNames.hasMoreElements())
                sb.append(JSON_COMMA);
        }

        sb.append(JSON_END_BRACE);

        return sb.toString();
    }

}
