/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.msf4j.util;

import org.wso2.carbon.messaging.CarbonMessage;
import org.wso2.carbon.messaging.Constants;
import org.wso2.carbon.messaging.DefaultCarbonMessage;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;

import javax.ws.rs.core.HttpHeaders;

/**
 * Utility methods related to HTTP.
 */
public class HttpUtil {

    public static final String EMPTY_BODY = "";
    public static final String CLOSE = "close";
    public static final String KEEP_ALIVE = "keep-alive";

    /**
     * Create a CarbonMessage for a specific status code.
     *
     * @param status HTTP status code
     * @return CarbonMessage representing the status
     */
    public static CarbonMessage createTextResponse(int status, String msg) {
        DefaultCarbonMessage response = new DefaultCarbonMessage();
        response.setProperty(Constants.HTTP_STATUS_CODE, status);
        response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(msg.length()));
        response.setStringMessageBody(msg);
        return response;
    }

    /**
     * Set connection header of the response object according to the
     * connection header of the request.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     */
    public static void setConnectionHeader(Request request, Response response) {
        String connection = request.getHeader(Constants.HTTP_CONNECTION);
        if (connection != null && CLOSE.equalsIgnoreCase(connection)) {
            response.setHeader(Constants.HTTP_CONNECTION, CLOSE);
        } else {
            response.setHeader(Constants.HTTP_CONNECTION, KEEP_ALIVE);
        }
    }
}
