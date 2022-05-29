package com.rslakra.core.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Cipher;

/**
 * @Author Rohtash Lakra
 * @Since 3/10/20 10:59 AM
 */
public class JWTUtilsTest {

    private static Logger LOGGER = LoggerFactory.getLogger(JWTUtilsTest.class);

    @Test
    public void testAddDays() {
        final Date issuedAt = new Date();
        System.out.println("issuedAt: " + issuedAt);
        final Date expiredOn = JWTUtils.INSTANCE.addDays(issuedAt, 5);
        System.out.println("expiredOn: " + expiredOn);
    }


    /**
     * JWT Header
     * <pre>
     * {
     * "alg": "HS256",
     * "typ": "JWT"
     * }
     * </pre>
     */
    @Test
    public void testCreateJWSHeader() {
        JWSHeader jwsHeader = JWTUtils.INSTANCE.createJWSHeader(JWSAlgorithm.RS256, JOSEObjectType.JWT);
        Assert.assertNotNull(jwsHeader);
        Assert.assertEquals(JWSAlgorithm.RS256, jwsHeader.getAlgorithm());
        Assert.assertEquals(JOSEObjectType.JWT, jwsHeader.getType());
    }

    /**
     * JWT Claims
     * <pre>
     * {
     * "aud": "{protocol}://{b2b.host}/identity/oauth2/access_token?realm=<your-realm>",
     * "iss": "{client_id}",
     * "sub": "{client_id}",
     * "exp": “{expiry time in seconds}”,
     * "iat": “{issued time in seconds}”
     * }
     * </pre>
     */
    @Test
    public void testCreateJWTClaims() {
        String audience = "http://localhost/identity/oauth2/access_token?realm=lakra";
        String clientId = "helloClient";
        final JWTClaimsSet jwtClaims = JWTUtils.INSTANCE.createJWTClaims(audience, clientId, clientId);
        Assert.assertNotNull(jwtClaims);
        Assert.assertEquals(audience, jwtClaims.getAudience().get(0));
        Assert.assertEquals(clientId, jwtClaims.getSubject());
        Assert.assertEquals(clientId, jwtClaims.getIssuer());
    }

    @Test
    public void testCreateJWTToken() {
        String audience = "http://localhost/identity/oauth2/access_token?realm=lakra";
        String clientId = "helloClient";
        String clientSecret = UUID.randomUUID().toString();
        String jwtToken = null;
        SignedJWT signedJwt = null;
        JWTClaimsSet jwtClaims = null;
        try {
            jwtToken = JWTUtils.INSTANCE.createJWTToken(audience, clientId, clientSecret);
            System.out.println(jwtToken);
            signedJwt = JWTUtils.INSTANCE.verifyJWTToken(jwtToken, clientSecret);
            jwtClaims = signedJwt.getJWTClaimsSet();
        } catch (JOSEException | ParseException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            ex.printStackTrace();
        }

        Assert.assertNotNull(jwtToken);
        Assert.assertNotNull(signedJwt);
        Assert.assertNotNull(jwtClaims);
        Assert.assertEquals(audience, jwtClaims.getAudience().get(0));
        Assert.assertEquals(clientId, jwtClaims.getSubject());
        Assert.assertEquals(clientId, jwtClaims.getIssuer());
    }

    /**
     * JWT Token:
     * <p>
     * eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI1ZDRhMDk0NzRjM2M2ODZlNDBkZTQ3YTgiLCJjbGkiOiJnZW1pbmkiLCJpc3MiOiJmb3JkZXZ0ZXN0IiwiZXhwIjoxNTkxOTA1NzE1LCJpYXQiOjE1ODQxMjk2NTV9.dF1K6mSe8FE9JNwbZQI6knxoyOJEWEc39D1WAlMsw3o3PKKEjTg-qtN7udrLNpiOQLYF_OLNVcThsfR3axJ5sC-5_HRJqxruQmVxNHFlky1HKIbnL8QPsKDkIg5Q0iFZrYP76zAEegZG9n42I2ikV_x0pflQyiAC1F0f_bM1DNCoFQ-Vtln4cg4wQ09P7U1wLPx_-sqau2krX9eL0CvHzztyfCKbwM_d_OqEtOw4zbYgX4dD2uE0mvYlBDwc08R0cEWT_kOrYbFAqRpN2VhnzIFH9bm7rnAev1DmHDCLw1MzrPTfnR_TFvS8qmxO0Aja57t7UjF4xhD9MGek2OeY0g
     * </p>
     * <p>
     * Encoded with ePay Private Key [epay.developer.fordevtest.jwt.pvtkey] using https://jwt.io/.
     * <p>
     * eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI1ZDRhMDk0NzRjM2M2ODZlNDBkZTQ3YTgiLCJjbGkiOiJnZW1pbmkiLCJpc3MiOiJmb3JkZXZ0ZXN0IiwiZXhwIjoxNTkxOTA1NzE1LCJpYXQiOjE1ODQxMjk2NTV9.iSxQFvb8CQRet6hG0SCEAYcjqfoBEfcXdk3FOkJZVu-h_O2tohbex44ga7MM0_faABqquinBudEZBuAXx4HOcjZer3VqAyHL3ZH_czmROlnmsj72rolzO5lsmUvT6Y4ggEu5kw_iiYE3p4K1eGP6VI2ATrSqHCbLv92Tj4WDL7jeupZAN0V3Ooi67u2YaO5OmInTkNd5QNUAsvgSfi9nfVSC5YCVY6oluR1gBh6o7Q4moKRc8m_clRfioZVse4TKXMOOCv7imUytZd-G5DvliYbstY-CHXYINSnu1IL4nYmayHBvNvqymWovGZ4e97WdM8BFUfUZACfex18puoxLOg
     * </p>
     */
    @Test
    public void testJWTKeyGeneration() {
        final String subject = "5d4a09474c3c686e40de47a8";
        final String audience = "audience";
        final String keyId = "gemini";
        final String issuer = "fordevtest";
        final Date issuedAt = new Date();
        final String keyFolderPath = "/Users/rlakra/Documents/VerizonMedia/ePay";
        final String serviceName = "wallet-epay-service";
//        final String serviceName = "ePayService";

        JWTUtils.INSTANCE.setKeyFolderPath(keyFolderPath);
        JWTUtils.INSTANCE.setService(serviceName);
        final Date expiredOn = JWTUtils.INSTANCE.addDays(issuedAt, 10);
        try {
            JWTUtils.INSTANCE.createJWTToken(keyId, audience, issuer, subject, issuedAt, expiredOn);
            final String jwtToken = JWTUtils.INSTANCE.serialize();
            LOGGER.debug("jwtToken:{}", jwtToken);
            final RSAKey publicKey = JWTUtils.INSTANCE.getRSAPublicKey();
            final boolean isVerified = JWTUtils.INSTANCE.verify(jwtToken, publicKey);
            Assert.assertTrue(isVerified);

            // Retrieve / verify the JWT claims according to the app requirements
            final JWTClaimsSet jwtClaims = JWTUtils.INSTANCE.getSignedJWT().getJWTClaimsSet();
            Assert.assertEquals(subject, jwtClaims.getSubject());
            Assert.assertEquals(issuer, jwtClaims.getIssuer());
            Assert.assertTrue(new Date().before(jwtClaims.getExpirationTime()));
        } catch (ParseException | JOSEException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testEncryptAndDecrypt() {
        try {
            String text = "Rohtash Singh Lakra";
            LOGGER.debug("text:{}", text);
            LOGGER.debug("text length:{}", text.getBytes(StandardCharsets.UTF_8).length);
            //encrypt the text.
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            PublicKey publicKey = JWTUtils.INSTANCE.loadPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            LOGGER.debug("Encrypted length:{}", encrypted.length);
            LOGGER.debug("Encrypted:{}", Base64.getEncoder().encodeToString(encrypted));
            //decrypt the encrypted text.
            PrivateKey privateKey = JWTUtils.INSTANCE.loadPrivateKey();
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            LOGGER.debug("Decrypted:{}", JWTUtils.INSTANCE.toUTF8String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateJWTTokenWithClientIdAndClientSecret() {
        String audience = "https://id-uat.b2b.oath.com/identity/oauth2/access_token?realm=aolcorporate/aolexternals";
        String clientId = "fd1184da-63a7-4622-b5c9-4cb3d6eb9a4f";
        String clientSecret = "eAJhm2JrmavRKKfuk5oB2gslT8lDukGWfgdZ6W2u7jGc2sZoPA";
        String jwtToken = null;
        SignedJWT signedJwt = null;
        JWTClaimsSet jwtClaims = null;
        try {
            jwtToken = JWTUtils.INSTANCE.createJWTToken(audience, clientId, clientSecret);
            System.out.println(jwtToken);
            signedJwt = JWTUtils.INSTANCE.verifyJWTToken(jwtToken, clientSecret);
            jwtClaims = signedJwt.getJWTClaimsSet();
        } catch (JOSEException | ParseException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            ex.printStackTrace();
        }

        Assert.assertNotNull(jwtToken);
        Assert.assertNotNull(signedJwt);
        Assert.assertNotNull(jwtClaims);
        Assert.assertEquals(audience, jwtClaims.getAudience().get(0));
        Assert.assertEquals(clientId, jwtClaims.getSubject());
        Assert.assertEquals(clientId, jwtClaims.getIssuer());
    }

}