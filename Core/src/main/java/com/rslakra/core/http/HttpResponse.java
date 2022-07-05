/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code, in source and
 * binary forms, with or without modification, are permitted provided that the
 * following conditions are met: 1. Redistributions of source code must retain
 * the above copyright notice, this list of conditions and the following
 * disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;
import com.rslakra.core.IOUtils;
import com.rslakra.core.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2017-09-28 12:06:28 PM
 * @since 1.0.0
 */
public class HttpResponse implements Cloneable {

    /**
     * The response headers
     */
    private Map<String, List<String>> requestHeaders;

    /* responseCode */
    private int responseCode;

    /**
     * responseHeaders
     */
    private Map<String, List<String>> responseHeaders;
    private String jsonResponseHeaders;

    /* dataBytes */
    private byte[] dataBytes;

    /* error */
    private Throwable error;

    /**
     *
     */
    public HttpResponse() {
        reset();
    }

    /**
     * Returns the requestHeaders.
     *
     * @return
     */
    public Map<String, List<String>> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * The requestHeaders to be set.
     *
     * @param requestHeaders
     */
    public void setRequestHeaders(Map<String, List<String>> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * Returns the responseCode.
     *
     * @return
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * The responseCode to be set.
     *
     * @param responseCode
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Returns the responseHeaders.
     *
     * @return the responseHeaders
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * The responseHeaders to be set.
     *
     * @param responseHeaders the responseHeaders to set
     */
    public void setResponseHeaders(final Map<String, List<String>> responseHeaders) {
        this.responseHeaders = responseHeaders;
        if (!BeanUtils.isEmpty(responseHeaders)) {
            jsonResponseHeaders = JSONUtils.toJSONString(responseHeaders);
        }
    }

    /**
     * @return
     */
    public String getJsonResponseHeaders() {
        return jsonResponseHeaders;
    }

    /**
     * The responseHeaders to be set.
     *
     * @param jsonResponseHeaders the responseHeaders to set
     */
    public void setJsonResponseHeaders(byte[] jsonResponseHeaders) {
        if (!BeanUtils.isEmpty(jsonResponseHeaders)) {
            String jsonResponseHeader = IOUtils.toUTF8String(jsonResponseHeaders);
            this.responseHeaders = JSONUtils.jsonHeadersAsMap(jsonResponseHeader);
        }
    }

    /**
     * Returns the dataBytes.
     *
     * @return
     */
    public byte[] getDataBytes() {
        return dataBytes;
    }

    /**
     * The dataBytes to be set.
     *
     * @param dataBytes
     */
    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    /**
     * @return the error
     */
    public Throwable getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(Throwable error) {
        this.error = error;
    }

    /**
     * Returns success if my {@link #responseCode} is any of my SUCCESS states.
     */
    public boolean isSuccess() {
        return (responseCode == 200);
    }

    /**
     * Returns true if {@link #error} is not null otherwise false.
     */
    public boolean isError() {
        return (this.error != null);
    }

    /**
     * Returns the response type.
     *
     * @return
     */
    public String getMimeType() {
        String mimeType = null;
        if (BeanUtils.isNotNull(responseHeaders)) {
            mimeType = responseHeaders.get(HTTPUtils.CONTENT_TYPE).get(0);
            if (mimeType.indexOf(";") != -1) {
                mimeType = mimeType.substring(0, mimeType.indexOf(";")).trim();
            }
        }

        return mimeType;
    }

    /**
     * Resets this object.
     */
    public void reset() {
        requestHeaders = null;
        responseCode = 0;
        responseHeaders = null;
        dataBytes = null;
        error = null;
    }

    /**
     * Creates exact copy of an object.
     *
     * @see java.lang.Object#clone()
     */
    public HttpResponse clone() {
        HttpResponse cloneResponse = null;
        try {
            cloneResponse = (HttpResponse) super.clone();
            cloneResponse.requestHeaders = this.requestHeaders;
            cloneResponse.responseCode = this.responseCode;
            cloneResponse.responseHeaders = this.responseHeaders;
            cloneResponse.jsonResponseHeaders = this.jsonResponseHeaders;
            cloneResponse.dataBytes = this.dataBytes;
            cloneResponse.error = this.error;
        } catch (CloneNotSupportedException ex) {
            // This should never happen
            throw new InternalError(ex.toString());
        }

        return cloneResponse;
    }

    /**
     * Returns the string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("================ HTTP Response (Start) ================\n");
        sBuilder.append("requestHeaders:").append(getRequestHeaders()).append("\n");
        sBuilder.append("ResponseCode:").append(getResponseCode()).append("\n");
        sBuilder.append("ResponseHeaders:").append(getResponseHeaders()).append("\n");
        sBuilder.append("dataBytes:").append(IOUtils.toUTF8String(getDataBytes())).append("\n");

        if (this.error != null) {
            sBuilder.append("\n\n").append(HTTPUtils.toString(getError())).append("\n\n");
        }

        sBuilder.append("================ HTTP Response (End) ================\n");

        return sBuilder.toString();
    }


//    /**
//     * Contains all possible results from a network operation.
//     *
//     * @author Rohtash Singh Lakra
//     * @date 05/26/2017 03:22:28 PM
//     */
//    public static final class HttpResponse implements Cloneable {
//
//        /**
//         * The response headers
//         */
//        private Map<String, List<String>> requestHeaders;
//
//        /* responseCode */
//        private int responseCode;
//
//        /**
//         * responseHeaders
//         */
//        private Map<String, List<String>> responseHeaders;
//        private String jsonResponseHeaders;
//
//        /* dataBytes */
//        private byte[] dataBytes;
//
//        /* error */
//        private Throwable error;
//
//        /**
//         *
//         */
//        public HttpResponse() {
//            reset();
//        }
//
//        /**
//         * Returns the requestHeaders.
//         *
//         * @return
//         */
//        public Map<String, List<String>> getRequestHeaders() {
//            return requestHeaders;
//        }
//
//        /**
//         * The requestHeaders to be set.
//         *
//         * @param requestHeaders
//         */
//        public void setRequestHeaders(Map<String, List<String>> requestHeaders) {
//            this.requestHeaders = requestHeaders;
//        }
//
//        /**
//         * Returns the responseCode.
//         *
//         * @return
//         */
//        public int getResponseCode() {
//            return responseCode;
//        }
//
//        /**
//         * The responseCode to be set.
//         *
//         * @param responseCode
//         */
//        public void setResponseCode(int responseCode) {
//            System.out.println("setResponseCode(" + responseCode + ")");
//            this.responseCode = responseCode;
//        }
//
//        /**
//         * Returns the responseHeaders.
//         *
//         * @return the responseHeaders
//         */
//        public Map<String, List<String>> getResponseHeaders() {
//            return responseHeaders;
//        }
//
//        /**
//         * The responseHeaders to be set.
//         *
//         * @param responseHeaders the responseHeaders to set
//         */
//        public void setResponseHeaders(final Map<String, List<String>> responseHeaders) {
//            System.out.println("responseHeaders:" + responseHeaders);
//            this.responseHeaders = responseHeaders;
//            if (!BeanUtils.isNullOrEmpty(responseHeaders)) {
//                jsonResponseHeaders = JSONUtils.toJSONString(responseHeaders);
//            }
//        }
//
//        /**
//         * @return
//         */
//        public String getJsonResponseHeaders() {
//            return jsonResponseHeaders;
//        }
//
//        /**
//         * The responseHeaders to be set.
//         *
//         * @param jsonResponseHeaders the responseHeaders to set
//         */
//        public void setJsonResponseHeaders(byte[] jsonResponseHeaders) {
//            if (!BeanUtils.isNullOrEmpty(jsonResponseHeaders)) {
//                String jsonResponseHeader = IOUtils.toUTF8String(jsonResponseHeaders);
//                this.responseHeaders = JSONUtils.jsonHeadersAsMap(jsonResponseHeader);
//            }
//        }
//
//        /**
//         * Returns the dataBytes.
//         *
//         * @return
//         */
//        public byte[] getDataBytes() {
//            return dataBytes;
//        }
//
//        /**
//         * The dataBytes to be set.
//         *
//         * @param dataBytes
//         */
//        public void setDataBytes(byte[] dataBytes) {
//            System.out.println("setDataBytes(" + dataBytes + ")");
//            this.dataBytes = dataBytes;
//            System.out.println("dataBytes:" + IOUtils.toUTF8String(dataBytes));
//        }
//
//        /**
//         * @return the error
//         */
//        public Throwable getError() {
//            return error;
//        }
//
//        /**
//         * @param error the error to set
//         */
//        public void setError(Throwable error) {
//            System.out.println("setError(" + error + ")");
//            this.error = error;
//        }
//
//        /**
//         * Returns success if my {@link #status} is any of my SUCCESS states.
//         */
//        public boolean isSuccess() {
//            return (responseCode == 200);
//        }
//
//        /**
//         * Returns true if my {@link #status} is any of my ERROR states, or if
//         * my {@link #error} or {@link #failureBO} fields have anything in them.
//         */
//        public boolean isError() {
//            return (this.error != null);
//        }
//
//        /**
//         * Returns the response type.
//         *
//         * @return
//         */
//        public String getMimeType() {
//            String mimeType = null;
//
//            if (responseHeaders != null) {
//                mimeType = responseHeaders.get("Content-Type").get(0);
//                if (mimeType.indexOf(";") != -1) {
//                    mimeType = mimeType.substring(0, mimeType.indexOf(";")).trim();
//                }
//            }
//
//            return mimeType;
//        }
//
//        /**
//         * Resets this object.
//         */
//        public void reset() {
//            System.out.println("reset()");
//            requestHeaders = null;
//            responseCode = 0;
//            responseHeaders = null;
//            dataBytes = null;
//            error = null;
//        }
//
//        /**
//         * Creates exact copy of an object.
//         *
//         * @see java.lang.Object#clone()
//         */
//        public HttpResponse clone() {
//            HttpResponse cloneObject = null;
//            try {
//                cloneObject = (HttpResponse) super.clone();
//                cloneObject.requestHeaders = this.requestHeaders;
//                cloneObject.responseCode = this.responseCode;
//                cloneObject.responseHeaders = this.responseHeaders;
//                cloneObject.jsonResponseHeaders = this.jsonResponseHeaders;
//                cloneObject.dataBytes = this.dataBytes;
//                cloneObject.error = this.error;
//            } catch (CloneNotSupportedException ex) {
//                System.err.println(ex);
//                // This should never happen
//                throw new InternalError(ex.toString());
//            }
//
//            return cloneObject;
//        }
//
//        /**
//         * Returns the string representation of this object.
//         *
//         * @see java.lang.Object#toString()
//         */
//        @Override
//        public String toString() {
//            StringBuilder sBuilder = new StringBuilder();
//            sBuilder.append("===================== Operation Response (Start) =====================\n");
//            sBuilder.append("requestHeaders:").append(getRequestHeaders()).append("\n");
//            sBuilder.append("ResponseCode:").append(getResponseCode()).append("\n");
//            sBuilder.append("ResponseHeaders:").append(getResponseHeaders()).append("\n");
//            sBuilder.append("dataBytes:").append(IOUtils.toUTF8String(getDataBytes())).append("\n");
//
//            if (this.error != null) {
//                sBuilder.append("\n\n").append(HTTPUtils.toString(getError())).append("\n\n");
//            }
//
//            sBuilder.append("===================== Operation Response (End) =====================\n");
//
//            return sBuilder.toString();
//        }
//    }


}
