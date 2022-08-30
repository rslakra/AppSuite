package com.rslakra.core.http;

import static com.xebialabs.restito.semantics.Action.composite;
import static com.xebialabs.restito.semantics.Action.delay;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;

import com.rslakra.core.http.HttpClientBuilder;
import com.rslakra.core.http.HttpMethod;
import com.rslakra.core.http.Request;
import com.xebialabs.restito.semantics.Action;
import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Rohtash Lakra
 * @created 3/26/21 7:47 PM
 */
public abstract class AbstractHttpClientTest {

    protected StubServer server;
    protected Action timeoutAction;
    protected Action successAction;
    private String clientName;

    public AbstractHttpClientTest(final String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    @BeforeClass
    public synchronized void initServer() {
        server = new StubServer().run();
        timeoutAction = composite(
            delay(1000),
            status(HttpStatus.OK_200),
            stringContent("success")
        );
        successAction = composite(
            status(HttpStatus.OK_200),
            stringContent("success")
        );
    }

    @AfterClass
    public void stopServer() {
        server.stop();
    }

    /**
     * @param url
     * @param httpMethod
     * @return
     * @throws URISyntaxException
     */
    protected Request newRequest(final String url, final HttpMethod httpMethod) throws URISyntaxException {
        return Request.newBuilder().setUri(new URI(url)).setHttpMethod(httpMethod).build();
    }

    /**
     * @param clientName
     * @return
     */
    protected HttpClientBuilder newClientBuilder(final String clientName) {
        return new HttpClientBuilder(clientName);
    }

}
