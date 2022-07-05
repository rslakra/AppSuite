package com.rslakra.core.rest;

import com.rslakra.core.rest.RestClient;

import java.net.URISyntaxException;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:55 PM
 */
public class RestClientTest {

    /**
     *
     * @throws URISyntaxException
     */
    public void testRestClient() throws URISyntaxException {
        RestClient restClient = new RestClient();
        final String
            urlString =
            "https://unifiedgeo.slnstg.geotech.yahoo.com:4443/geo/v1/place/woe/2488042?optionalfields=(woe.ancestors)&locale=en-US&size=1&minconfidence=0.5";
        restClient.setUri(urlString);
//        restClient.con.iwsApiPath.replaceAll('localhost', InetAddress.localHost.hostName))
        restClient.setHttpClient(null);
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-RESTRICTED' = "RESTRICTED"
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-YBY' = "SYSTEM-EWS-FT-USER"
//        restClient.restClientIWS.defaultRequestHeaders. 'Y-YBY-ROLE' = "I"
//        restClient.restClientIWS.handler.failure = {resp, data ->
    }
}
