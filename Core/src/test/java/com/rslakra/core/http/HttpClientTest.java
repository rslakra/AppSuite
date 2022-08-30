package com.rslakra.core.http;

import com.rslakra.core.StopWatch;
import com.rslakra.core.jwt.JWTUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Rohtash Lakra
 * @created 2/28/20 10:14 AM
 */
public class HttpClientTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);
    private HttpClient httpClient;

    public HttpClientTest() {
        httpClient = HTTPUtils.INSTANCE.getHttpClient(1, 1000, 1000);
    }

    /**
     * @param url
     */
    public void testExternalRequest(final String url) {
        final StopWatch stopWatch = new StopWatch();
        HttpGet getRequest = null;
        HttpResponse httpResponse = null;
        try {
            String urlEncoded = URLEncoder.encode(url, JWTUtils.UTF_8);
            LOGGER.debug("urlEncoded:{}", urlEncoded);
            getRequest = new HttpGet(new URL(url).toURI());
            stopWatch.startTimer();
            httpResponse = httpClient.execute(getRequest);
            stopWatch.stopTimer();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            LOGGER.debug("statusCode:{}", statusCode);
            String payload = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            LOGGER.debug("payload:{}", payload);
        } catch (IOException | URISyntaxException ex) {
            stopWatch.stopTimer();
            LOGGER.error(ex.getLocalizedMessage(), ex);
        } finally {
            LOGGER.debug("stopWatch:{}" + stopWatch);
            HTTPUtils.close(getRequest, httpResponse);
        }
    }

    @Test
    public void testHttpPost() {
        HttpPost postRequest = null;
        HttpResponse httpResponse = null;
        final StopWatch stopWatch = new StopWatch();
        try {
            String urlString = "https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR";
            String urlEncoded = URLEncoder.encode(urlString, JWTUtils.UTF_8);
            LOGGER.debug("urlString:{}, urlEncoded:{}", urlString, urlEncoded);
            postRequest = new HttpPost(new URL(urlString).toURI());
            postRequest.setHeader(HTTPUtils.REQUEST_TRACER, HTTPUtils.nextRequestTracer());
            LOGGER.debug("requestTracer:{}", HTTPUtils.getRequestTracer(postRequest));
            LOGGER.debug("postRequestUri:{}", postRequest.getURI());
            LOGGER.debug("postRequestUri:{}", postRequest.getURI());
            stopWatch.startTimer();
            httpResponse = httpClient.execute(postRequest);
            stopWatch.stopTimer();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            LOGGER.debug("statusCode:{}", statusCode);
            String payload = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            LOGGER.debug("payload:{}", payload);
        } catch (IOException | URISyntaxException ex) {
            stopWatch.stopTimer();
            LOGGER.error(ex.getLocalizedMessage(), ex);
        } finally {
            LOGGER.debug("stopWatch:{}", stopWatch);
            HTTPUtils.close(postRequest, httpResponse);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpClientTest httpClientTest = new HttpClientTest();
        final String
                urlString =
                "https://unifiedgeo.slnstg.geotech.yahoo.com:4443/geo/v1/place/woe/2488042?optionalfields=(woe.ancestors)&locale=en-US&size=1&minconfidence=0.5";
        httpClientTest.testExternalRequest(urlString);
    }
}
