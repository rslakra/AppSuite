package com.rslakra.core.http;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.function.Function;

/**
 * @author Rohtash Lakra
 * @created 3/26/21 5:33 PM
 */
public class StringResponseHandler extends AbstractResponseHandler<String> {

    /**
     * @param acceptCriteria
     */
    public StringResponseHandler(final Function<StatusLine, Boolean> acceptCriteria) {
        super(acceptCriteria);
    }

    public StringResponseHandler() {
        super();
    }

    /**
     * @param httpResponse
     * @return
     * @throws IOException
     */
    @Override
    protected String handleSuccessResponse(final HttpResponse httpResponse) throws IOException {
        if (httpResponse == null || httpResponse.getEntity() == null) {
            return null;
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }
}
