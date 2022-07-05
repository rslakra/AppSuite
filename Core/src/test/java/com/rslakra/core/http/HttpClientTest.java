package com.rslakra.core.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author Rohtash Lakra
 * @Since 2/28/20 10:14 AM
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
        HttpGet request = null;
        HttpResponse response = null;
        try {
            String urlEncoded = URLEncoder.encode(url, "UTF-8");
            LOGGER.debug("urlEncoded:" + urlEncoded);
            request = new HttpGet(new URL(url).toURI());
            response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            LOGGER.debug("code:" + code);
            String payload = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            LOGGER.debug("payload:" + payload);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        } finally {
            HTTPUtils.INSTANCE.close(request, response);
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
