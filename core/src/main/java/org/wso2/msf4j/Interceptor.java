/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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

package org.wso2.msf4j;

/**
 * Interface that needs to be implemented to intercept handler method calls.
 */
public interface Interceptor {

    /**
     * preCall is run before a handler method call is made. If any of the preCalls throw exception or return false then
     * no other subsequent preCalls will be called and the request processing will be terminated,
     * also no postCall interceptors will be called.
     *
     * @param request           HttpRequest being processed.
     * @param responder         HttpResponder to send response.
     * @param serviceMethodInfo Info on handler method that will be called.
     * @return true if the request processing can continue, otherwise the hook should send response and return false to
     * stop further processing.
     */
    boolean preCall(Request request, Response responder, ServiceMethodInfo serviceMethodInfo) throws Exception;

    /**
     * postCall is run after a handler method call is made. If any of the postCalls throw and exception then the
     * remaining postCalls will still be called. If the handler method was not called then postCall interceptors will
     * not be called.
     *
     * @param request           HttpRequest being processed.
     * @param status            Http status returned to the client.
     * @param serviceMethodInfo Info on handler method that was called.
     */
    void postCall(Request request, int status, ServiceMethodInfo serviceMethodInfo) throws Exception;
}
