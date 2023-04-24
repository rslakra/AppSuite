package com.rslakra.appsuite.core.http.rest;

import java.net.URISyntaxException;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:55 PM
 */
public class RestClientTest {

    /**
     * @throws URISyntaxException
     */
    public void testRestClient() throws URISyntaxException {
        RestClient restClient = new RestClient();
        String urlString = "https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR";
        restClient.setUri(urlString);
//        restClient.con.iwsApiPath.replaceAll('localhost', InetAddress.localHost.hostName))
        restClient.setHttpClient(null);
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-RESTRICTED' = "RESTRICTED"
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-YBY' = "SYSTEM-EWS-FT-USER"
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-YBY-ROLE' = "I"
//        restClient.restClientIWS.handler.failure = {resp, data ->
    }
}
