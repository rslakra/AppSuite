package com.rslakra.core.http;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.util.function.Function;

/**
 * The default response handler that could use acceptCriteria to determine which handle function to be used.
 * <p>
 * By default, we accept the response with HTTP status code between 200~299. You could define your own acceptCriteria,
 * In addition, if you would like to handle the error, you could Overwrite the method handleErrorResponse.
 *
 * @author Rohtash Lakra
 * @created 3/26/21 4:05 PM
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    // SUCCESS_CRITERIA
    public static final Function<StatusLine, Boolean>
        SUCCESS_CRITERIA =
        statusLine -> statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() <= 299;

    // ERROR_CRITERIA
    public static final Function<StatusLine, Boolean>
        ERROR_CRITERIA =
        statusLine -> statusLine.getStatusCode() < 200 || statusLine.getStatusCode() >= 300;

    // ALL_CRITERIA
    public static final Function<StatusLine, Boolean>
        ALL_CRITERIA =
        statusLine -> statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() <= 599;

    protected final Function<StatusLine, Boolean> acceptCriteria;

    /**
     * @param acceptCriteria
     */
    public AbstractResponseHandler(final Function<StatusLine, Boolean> acceptCriteria) {
        this.acceptCriteria = acceptCriteria;
    }

    /**
     *
     */
    public AbstractResponseHandler() {
        this(SUCCESS_CRITERIA);
    }

    /**
     * @param httpResponse
     * @return
     * @throws IOException
     */
    @Override
    public T handleResponse(final HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        T payload;

        if (acceptCriteria.apply(statusLine)) {
            payload = handleSuccessResponse(httpResponse);
        } else {
            payload = handleErrorResponse(httpResponse);
        }

        return payload;
    }


    /**
     * @param httpResponse
     * @return
     * @throws IOException
     */
    abstract protected T handleSuccessResponse(final HttpResponse httpResponse) throws IOException;

    /**
     * handleErrorResponse would return null by default.
     * <p>
     * The default acceptCriteria is the statusLine between 200 and 299. You could overwrite it to throw an exception
     * and Http[Sync|Async]Client would retry your request.
     *
     * @param httpResponse
     * @return
     * @throws IOException
     */
    protected T handleErrorResponse(final HttpResponse httpResponse) throws IOException {
        return null;
    }

}
