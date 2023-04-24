package com.rslakra.appsuite.core;

import static com.rslakra.appsuite.core.BeanUtils.assertNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra
 * @created 6/16/21 4:56 PM
 */
public class PayloadTest {

    @Test
    public void testOfPair() {
        Payload<String, String> payload = Payload
            .newBuilder()
            .ofPair("name", "rohtash");
        assertEquals("rohtash", payload.get("name"));
    }

    @Test
    public void testWithObjects() {
        Payload<String, Object> payload = Payload
            .newBuilder()
            .withMessage("Rohtash")
            .withStatus("active")
            .withDeleted(false)
            .withCause(new Throwable("Generic payload!"));
        assertNonNull(payload);
        assertEquals("Rohtash", payload.get(Payload.MESSAGE));
        assertEquals("active", payload.get(Payload.STATUS));
        assertEquals(false, payload.get(Payload.DELETED));
        assertEquals("Generic payload!", payload.get(Payload.ERROR));
    }

    @Test
    public void testWithPayload() {

        Payload<String, Object> imagePayload = Payload
            .newBuilder()
            .ofPair("name", "trulli")
            .ofPair("url", "https://www.w3schools.com/html/pic_trulli.jpg")
            .ofPair("width", 825)
            .ofPair("height", 668);

        Payload<String, Payload> payload = Payload
            .newBuilder()
            .ofPair("imagePayload", imagePayload);

        assertNonNull(payload);
        assertTrue(payload.contains("imagePayload"));
        Payload<String, Object> tempImagePayload = payload.getPayload("imagePayload");
        assertNonNull(tempImagePayload);
        assertEquals("trulli", tempImagePayload.get("name"));
        assertEquals("https://www.w3schools.com/html/pic_trulli.jpg", tempImagePayload.get("url"));
        assertEquals(825, tempImagePayload.get("width"));
        assertEquals(668, tempImagePayload.get("height"));
    }

}
