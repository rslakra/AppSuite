package com.rslakra.apache;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Rohtash Singh Lakra
 */
public class HttpExample {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final String urlString = "https://qawest.meetx.org/";

        CloseableHttpResponse httpResponse = null;

        try {
            HttpGet httpGet = new HttpGet(urlString);
            httpResponse = httpClient.execute(httpGet);
            /**
             *
             * The underlying HTTP connection is still held by the response object to allow the response content to be streamed directly from the network socket.
             * In order to ensure correct deallocation of system resources the user MUST call CloseableHttpResponse#close() from a finally clause.
             * Please note that if response content is not fully consumed the underlying connection cannot be safely re-used and will be shut down and discarded by the connection manager.
             */

            try {
                System.out.println(httpResponse.getStatusLine());
                Header[] headers = httpGet.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName() + "=" + header.getValue());
                }

                HttpEntity httpEntity = httpResponse.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(httpEntity);
            } finally {
                if(httpResponse != null){
                    httpResponse.close();
                }
            }

            // HttpPost httpPost = new HttpPost(urlString);
            // List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // nvps.add(new BasicNameValuePair("username", "vip"));
            // nvps.add(new BasicNameValuePair("password", "secret"));
            // httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            // CloseableHttpResponse response2 = httpclient.execute(httpPost);
            //
            // try {
            // System.out.println(response2.getStatusLine());
            // HttpEntity entity2 = response2.getEntity();
            // // do something useful with the response body
            // // and ensure it is fully consumed
            // EntityUtils.consume(entity2);
            // } finally {
            // response2.close();
            // }
        } finally {
            httpClient.close();
        }
    }

}
