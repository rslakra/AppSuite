package com.rslakra.core.http;

import com.rslakra.core.ToString;

import java.util.Arrays;

/**
 * HTTP status codes HTTP defines a bunch of meaningful status codes that can be returned from your API. These can be
 * leveraged to help the API consumers route their responses accordingly. I've curated a short list of the ones that you
 * definitely should be using:
 * <p>
 * This is a list of Hypertext Transfer Protocol (HTTP) response status codes. Status codes are issued by a server in
 * response to a client's request made to the server. The first digit of the status code specifies one of five standard
 * classes of responses. The message phrases shown are typical, but any human-readable alternative may be provided.
 * <p>
 * <p>
 * All HTTP response status codes are separated into five classes or categories. The first digit of the status code
 * defines the class of response, while the last two digits do not have any classifying or categorization role. There
 * are five classes defined by the standard:
 * <pre>
 * 1xx informational response – the request was received, continuing process
 * 2xx successful – the request was successfully received, understood, and accepted
 * 3xx redirection – further action needs to be taken in order to complete the request
 * 4xx client error – the request contains bad syntax or cannot be fulfilled
 * 5xx server error – the server failed to fulfil an apparently valid request
 * </pre>
 * <p>
 * <p>
 * https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
 *
 * @author Rohtash Lakra
 * @created 9/16/22 1:59 PM
 */
public enum StatusCode {

    /* 2xx */
    OK(200, "OK",
       "Response to a successful GET, PUT, PATCH or DELETE. Can also be used for a POST that doesn't result in a creation."),
    CREATED(201, "Created",
            "Response to a POST that results in a creation. Should be combined with a Location header pointing to the location of the new resource"),
    ACCEPTED(202, "Accepted",
             "The request has been accepted for processing, but the processing has not been completed. The request might or might not be eventually acted upon, and may be disallowed when processing occurs."),
    NO_CONTENT(204, "No Content",
               "Response to a successful request that won't be returning a body (like a DELETE request)"),
    /* 3xx */
    MOVED_PERMANENTLY(301, "Moved Permanently", "This and all future requests should be directed to the given URI."),
    MOVED_TEMPORARILY(302, "Found (\"Moved temporarily\")", "Tells the client to look at (browse to) another URL."),
    NOT_MODIFIED(304, "Not Modified", "Used when HTTP caching headers are in play"),
    /* 4xx */
    BAD_REQUEST(400, "Bad Request", "The request is malformed, such as if the body does not parse"),
    UNAUTHORIZED(401, "Unauthorized",
                 "When no or invalid authentication details are provided. Also useful to trigger an auth popup if the API is used from a browser"),
    FORBIDDEN(403, "Forbidden",
              "When authentication succeeded but authenticated user doesn't have access to the resource"),
    NOT_FOUND(404, "Not Found", "When a non-existent resource is requested"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed",
                       "When an HTTP method is being requested that isn't allowed for the authenticated user"),
    NOT_ACCEPTABLE(406, "Not Acceptable",
                   "The requested resource is capable of generating only content not acceptable according to the Accept headers sent in the request."),
    REQUEST_TIMEOUT(408, "Request Timeout", "The server timed out waiting for the request."),
    CONFLICT(409, "Conflict",
             "Indicates that the request could not be processed because of conflict in the current state of the resource, such as an edit conflict between multiple simultaneous updates."),
    RESOURCE_UNAVAILABLE(410, "Gone",
                         "Indicates that the resource at this end point is no longer available. Useful as a blanket response for old API versions"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type",
                           "If incorrect content-type was provided as part of the request"),
    VALIDATION_ERROR(422, "Unprocessable Entity", "Used for validation errors"),
    TOO_MANY_REQUESTS(429, "Too Many Requests", "When a request is rejected due to rate limiting"),
    /* 5xx */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error",
                          "A generic error message, given when an unexpected condition was encountered and no more specific message is suitable."),
    NOT_IMPLEMENTED(501, "Not Implemented",
                    "he server either does not recognize the request method, or it lacks the ability to fulfil the request. Usually this implies future availability (e.g., a new feature of a web-service API)."),
    BAD_GATEWAY(502, "Bad Gateway",
                "The server was acting as a gateway or proxy and received an invalid response from the upstream server."),
    SERVICE_UNAVAILABLE(503, "Service Unavailable",
                        "The server cannot handle the request (because it is overloaded or down for maintenance). Generally, this is a temporary state."),
    GATEWAY_TIMEOUT(504, "Gateway Timeout",
                    "The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.");

    private final int statusCode;
    private final String reasonPhrase;
    private final String description;

    /**
     * @param statusCode
     * @param reasonPhrase
     * @param description
     */
    private StatusCode(final int statusCode, final String reasonPhrase, final String description) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.description = description;
    }

    /**
     * @return
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(StatusCode.class)
            .add("statusCode", getStatusCode())
            .add("reasonPhrase", getReasonPhrase())
            .add("description", getDescription())
            .toString();
    }

    /**
     * StatusCode
     *
     * @param statusCode
     * @return
     */
    public static StatusCode toStatusCode(final int statusCode) {
        return Arrays.stream(values()).filter(tempStatusCode -> tempStatusCode.getStatusCode() == statusCode)
            .findFirst().orElse(null);
    }
}
