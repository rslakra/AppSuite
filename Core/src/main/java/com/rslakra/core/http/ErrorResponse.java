package com.rslakra.core.http;

import java.util.List;

/**
 * <pre>
 * {
 *  "statusCode" : 422,
 *  "message" : "Validation Failed",
 *  "errors" : [
 *      {
 *          "code" : 5432,
 *          "field" : "firstName",
 *          "message" : "First name cannot have fancy characters"
 *      },
 *      {
 *          "code" : 5622,
 *          "field" : "password",
 *          "message" : "Password cannot be blank"
 *      }
 *  ]
 * }
 * </pre>
 *
 * @author Rohtash Lakra (rlakra)
 * @created 9/16/22 2:57 PM
 */
public class ErrorResponse {
    private int statusCode;
    private String message;
    private List<Error> errors;
}
