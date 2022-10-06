package com.rslakra.core.http;

/**
 * <pre>
 * {
 *  "code" : 5432,
 *  "field" : "firstName",
 *  "message" : "First name cannot have fancy characters",
 *  "description" : "More details about the error here"
 * }
 * </pre>
 *
 * @author Rohtash Lakra (rlakra)
 * @created 9/16/22 2:57 PM
 */
public class Error {
    private int code;
    private String field;
    private String message;
    private String description;
}
