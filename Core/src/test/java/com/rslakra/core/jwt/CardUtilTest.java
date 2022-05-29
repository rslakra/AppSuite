package com.rslakra.core.jwt;

import org.jose4j.lang.JoseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.Key;
import java.security.PublicKey;

/**
 * @author Rohtash Lakra
 * @created 4/15/20 12:40 PM
 */
public class CardUtilTest {

    @Test
    public void testCardTokenGeneration() {
        final String EPAY_PUBLIC_KEY_URL = "https://stg.token-service.payment.yahoo.com/.well-known/jwks.json";
        final String cardNumber = "4111111111111111";
        final String cvc = "123";
        final String idAddress = "10.0.0.127";
        String cardEncryptedToken = null;
        try {
            Key publicKey = JWTUtils.INSTANCE.fetchPublicKey(EPAY_PUBLIC_KEY_URL, 5 * 60);
            CardUtils.INSTANCE.setPublicKey((PublicKey) publicKey);
            cardEncryptedToken =
                CardUtils.INSTANCE.newEncryptedToken(TokenType.CREDIT_CARD, cardNumber, cvc, idAddress);
            Assert.assertNotNull(cardEncryptedToken);
            System.out.println(cardEncryptedToken);
        } catch (JoseException | IOException ex) {
            ex.printStackTrace();
            Assert.assertNull(cardEncryptedToken);
        }
    }
}
