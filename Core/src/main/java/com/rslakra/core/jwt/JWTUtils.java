package com.rslakra.core.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.rslakra.core.PayloadBuilder;
import com.rslakra.core.Utils;
import com.rslakra.core.utils.BeanUtils;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.*;

/**
 * @Author Rohtash Lakra
 * @Since 3/10/20 10:57 AM
 */
public enum JWTUtils {
    INSTANCE;

    public static final String ALGO_RSA = "RSA";
    public static final String UTF_8 = "UTF-8";
    public static final String NEW_LINE = "\n";
    public static final String PRIVATE_KEY = "_private.key";
    public static final String PUBLIC_KEY = "_public.key";
    public static final String JWKS_FILE_SUFFIX = ".well-known/jwks.json";
    public static final String REQUEST_TRACER = "Request-Tracer";
    private KeyFactory rsaKeyFactory;
    private SignedJWT signedJWT;
    private RSAKey rsaKey;
    private String keyFolderPath;
    private String service;

    private final PayloadBuilder<String, HttpsJwks> PayloadBuilder = new PayloadBuilder();

    /**
     * @return
     */
    public String getKeyFolderPath() {
        return keyFolderPath;
    }

    /**
     * @param keyFolderPath
     */
    public void setKeyFolderPath(String keyFolderPath) {
        this.keyFolderPath = keyFolderPath;
    }

    /**
     * @param line
     * @return
     */
    public boolean endsWithKey(final String line) {
        return line.endsWith(" KEY-----");
    }

    /**
     * Returns true if the line is certificates begin line otherwise false.
     *
     * @param line
     * @return
     */
    public boolean startsWithBegin(final String line) {
        return (line.startsWith("-----BEGIN ") && endsWithKey(line));
    }

    /**
     * Returns true if the line is certificates end line otherwise false.
     *
     * @param line
     * @return
     */
    public boolean startsWithEnd(final String line) {
        return (line.startsWith("-----END ") && endsWithKey(line));
    }

    /**
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return
     */
    public String getPrivateKeyFile() {
        return (getService() + PRIVATE_KEY);
    }

    /**
     * @return
     */
    public String getPublicKeyFile() {
        return (getService() + PUBLIC_KEY);
    }

    /**
     * Returns the <code>KeyFactory</code> for the <code>algorithm</code> algorithm.
     *
     * @param algorithm
     * @return
     */
    public KeyFactory getKeyFactory(final String algorithm) {
        if (rsaKeyFactory == null) {
            try {
                rsaKeyFactory = KeyFactory.getInstance(algorithm);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }

        return rsaKeyFactory;
    }

    /**
     * @param rawBytes
     * @return
     */
    public String toUTF8String(final byte[] rawBytes) {
        try {
            return new String(rawBytes, UTF_8);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * @param pathString
     * @return
     */
    public Path getPath(final String pathString) {
        if (BeanUtils.isNullOrEmpty(getKeyFolderPath())) {
            final String pkgString = Utils.toClassPathString(JWTUtils.class, pathString);
            return Paths.get("src/main/java", pkgString);
        } else {
            return Paths.get(getKeyFolderPath(), pathString);
        }
    }

    /**
     * Reads the <code>pathString</code> file bytes.
     *
     * @param pathString
     * @return
     * @throws IOException
     */
    public byte[] readAllBytes(final String pathString) throws IOException {
        return Files.readAllBytes(getPath(pathString));
    }

    /**
     * @param keyLines
     * @param removeKeyLines
     * @return
     */
    public String toString(final List<String> keyLines, final boolean removeKeyLines) {
        final StringBuilder sBuilder = new StringBuilder();
        if (keyLines != null) {
            keyLines.forEach(line -> {
                if (removeKeyLines) {
                    if (!(startsWithBegin(line) || startsWithEnd(line))) {
                        sBuilder.append(line);
                    }
                } else {
                    sBuilder.append(line);
                }
            });
        }

        return sBuilder.toString();
    }

    /**
     * @param pathString
     * @param removeKeyLines
     * @return
     * @throws IOException
     */
    public String readKeyContents(final String pathString, final boolean removeKeyLines) throws IOException {
        return toString(Files.readAllLines(getPath(pathString)), removeKeyLines);
    }

    /**
     * @param inputStream
     * @param addNewLines
     * @return
     * @throws IOException
     */
    public String readContents(final InputStream inputStream, final boolean addNewLines)
            throws IOException {
        final StringBuilder sBuilder = new StringBuilder();
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            while ((line = bReader.readLine()) != null) {
                if (startsWithBegin(line) || startsWithEnd(line)) {
                    continue;
                }
                sBuilder.append(line);
                if (addNewLines) {
                    sBuilder.append(NEW_LINE);
                }
            }
        }

        return sBuilder.toString();
    }

//    /**
//     * Returns the <code>PrivateKey</code> for the <code>pathString</code>.
//     *
//     * @param pathString
//     * @return
//     * @throws Exception
//     */
//    public PrivateKey loadPrivateKey(final String pathString) throws Exception {
//        PrivateKey privateKey = null;
//        final KeyFactory keyFactory = getKeyFactory(ALGO_RSA);
//        final List<String> keyLines = Files.readAllLines(getPath(pathString));
//        final String keyContents = toString(keyLines, true);
//        byte[] keyBytes = Base64.getDecoder().decode(keyContents);
//        /*
//         * PKCS#! has the 'BEGIN RSA PRIVATE KEY' header, and
//         * PKCS#8 has the 'BEGIN PRIVATE KEY' header.
//         */
//        if (keyLines.get(0).contains("BEGIN PRIVATE KEY")) {
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//            privateKey = keyFactory.generatePrivate(keySpec);
//        } else if (keyLines.get(0).contains(ALGO_RSA)) {
//            final DerInputStream derReader = new DerInputStream(keyBytes);
//            final DerValue[] seq = derReader.getSequence(0);
//            // skip version seq[0];
//            final BigInteger modulus = seq[1].getBigInteger();
//            final BigInteger publicExponent = seq[2].getBigInteger();
//            final BigInteger privateExponent = seq[3].getBigInteger();
//            final BigInteger primeP = seq[4].getBigInteger();
//            final BigInteger primeQ = seq[5].getBigInteger();
//            final BigInteger primeExponentP = seq[6].getBigInteger();
//            final BigInteger primeExponentQ = seq[7].getBigInteger();
//            final BigInteger crtCoefficient = seq[8].getBigInteger();
//            RSAPrivateCrtKeySpec
//                keySpec =
//                new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ,
//                                         primeExponentP,
//                                         primeExponentQ, crtCoefficient);
//            privateKey = keyFactory.generatePrivate(keySpec);
//        } else {
//            throw new RuntimeException("Unsupported PEM Key!");
//        }
//
//        return privateKey;
//    }

    /**
     * @return
     * @throws Exception
     */
    public PrivateKey loadPrivateKey() throws Exception {
//        return loadPrivateKey(getPrivateKeyFile());
        return null;
    }

    /**
     * Returns the <code>PublicKey</code> for the <code>pathString</code>.
     *
     * @param pathString
     * @return
     * @throws Exception
     */
    public PublicKey loadPublicKey(final String pathString) throws Exception {
        PublicKey publicKey = null;
        final KeyFactory keyFactory = getKeyFactory(ALGO_RSA);
        final List<String> keyLines = Files.readAllLines(getPath(pathString));
        final String keyContents = toString(keyLines, true);
//        keyContents = keyContents.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
//        keyContents = keyContents.replace("\\s+", "");
        byte[] keyBytes = Base64.getDecoder().decode(keyContents);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * @return
     * @throws Exception
     */
    public PublicKey loadPublicKey() throws Exception {
        return loadPublicKey(getPublicKeyFile());
    }

    /**
     * Adds/Subtracts the number of days in the given date.
     *
     * @param date
     * @param days
     */
    public Date addDays(final Date date, final int days) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * @return
     */
    public SignedJWT getSignedJWT() {
        return signedJWT;
    }

    /**
     * @return
     */
    public RSAKey getRSAKey() {
        return rsaKey;
    }

    /**
     * @param size
     * @param keyId
     * @throws JOSEException
     */
    public void setRSAKey(final int size, final String keyId) throws JOSEException {
        if (Objects.isNull(getRSAKey())) {
            rsaKey = new RSAKeyGenerator(size)
                    .keyID(keyId)
                    .generate();
        }
    }

//    /**
//     * @param priKeyBytes
//     * @param pubKeyBytes
//     * @return
//     * @throws InvalidKeySpecException
//     */
//    public KeyPair toKeyPair(final byte[] priKeyBytes, final byte[] pubKeyBytes)
//        throws InvalidKeySpecException {
//        final KeyFactory keyFactory = getKeyFactory(ALGO_RSA);
//        PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(priKeyBytes));
//        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(pubKeyBytes));
//        return new KeyPair(pubKey, priKey);
//    }

    /**
     * The rsaKey to be set.
     *
     * @throws JOSEException
     */
    public void setRSAKey() throws JOSEException {
        if (Objects.isNull(getRSAKey())) {
            try {
//                PrivateKey privateKey = loadPrivateKey(getPrivateKeyFile());
                PrivateKey privateKey = null;
                PublicKey publicKey = loadPublicKey(getPublicKeyFile());
                RSAKey.Builder rsaKeyBuilder = new RSAKey.Builder((RSAPublicKey) publicKey).privateKey(privateKey);
                rsaKey = rsaKeyBuilder.build();
            } catch (Exception ex) {
                throw new JOSEException(ex.getLocalizedMessage(), ex);
            }
        }
    }

    /**
     * @return
     */
    public RSAKey getRSAPublicKey() {
        return getRSAKey().toPublicJWK();
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
    public JWSHeader createJWSHeader(final JWSAlgorithm algorithm, final JOSEObjectType type) {
        return new JWSHeader.Builder(algorithm).type(type).build();
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
     *
     * @param keyId
     * @param audience
     * @param issuer
     * @param subject
     * @param issuedAt
     * @param expiredOn
     * @return
     */
    public JWTClaimsSet createJWTClaims(final String keyId, final String audience, final String issuer,
                                        final String subject, final Date issuedAt, final Date expiredOn) {
        // Prepare JWT with claims set
        final JWTClaimsSet.Builder payloadBuilder = new JWTClaimsSet.Builder();
        if (audience != null) {
            payloadBuilder.audience(audience);
        }
        payloadBuilder.issuer(issuer);
        payloadBuilder.subject(subject);
        if (keyId != null) {
            payloadBuilder.claim("cli", keyId);
        }
//        Date issueTime = Date.from(Instant.ofEpochSecond(issuedAt.getTime()));
//        System.out.println("issueTime:" + issueTime);
//        Date expiryTime = Date.from(Instant.ofEpochMilli(Instant.now().plusMillis(60 * 1000).toEpochMilli()));
//        System.out.println("expiryTime:" + expiryTime);
        payloadBuilder.issueTime(issuedAt);
        payloadBuilder.expirationTime(new Date(expiredOn.getTime() + 60 * 1000));
        return payloadBuilder.build();
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
     *
     * @param audience
     * @param issuer
     * @param subject
     * @return
     */
    public JWTClaimsSet createJWTClaims(final String audience, final String issuer, final String subject) {
        return createJWTClaims(null, audience, issuer, subject, new Date(), addDays(new Date(), 1));
    }


    /**
     * JWT Header
     * <pre>
     * {
     * "alg": "HS256",
     * "typ": "JWT"
     * }
     * </pre>
     * <p>
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
     *
     * @param audience
     * @param issuer
     * @param subject
     * @param clientSecret
     * @throws JOSEException
     */
    public String createJWTToken(final String audience, final String issuer, final String subject,
                                 final String clientSecret) throws JOSEException {
        final JWSHeader jwsHeader = createJWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT);
        // Prepare JWT with claims set
        final JWTClaimsSet jwtClaimsSet = createJWTClaims(audience, issuer, subject);
        // Create the signed JWT.
        final SignedJWT signedJwt = new SignedJWT(jwsHeader, jwtClaimsSet);
        signedJwt.sign(new MACSigner(clientSecret));
        // To serialize to compact form
        return signedJwt.serialize();
    }

    /**
     * JWT Header
     * <pre>
     * {
     * "alg": "HS256",
     * "typ": "JWT"
     * }
     * </pre>
     * <p>
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
     *
     * @param audience
     * @param clientId
     * @param clientSecret
     * @throws JOSEException
     */
    public String createJWTToken(final String audience, final String clientId, final String clientSecret)
            throws JOSEException {
        return createJWTToken(audience, clientId, clientId, clientSecret);
    }

    /**
     * Parse the JWT and verify with client secret.
     *
     * @param jwtToken
     * @param clientSecret
     * @throws ParseException
     */
    public SignedJWT verifyJWTToken(final String jwtToken, final String clientSecret) throws ParseException,
            JOSEException {
        // parse the JWS and
        SignedJWT signedJwt = SignedJWT.parse(jwtToken);
        // verify its RSA signature
        JWSVerifier verifier = new MACVerifier(clientSecret);
        signedJwt.verify(verifier);
        return signedJwt;
    }

    /**
     * JWT Header
     * <pre>
     * {
     * "alg": "HS256",
     * "typ": "JWT"
     * }
     * </pre>
     * <p>
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
     *
     * @param keyId
     * @param subject
     * @param issuer
     * @throws JOSEException
     */
    public void createJWTToken(final String keyId, final String audience, final String issuer, final String subject,
                               final Date issuedAt, final Date expiredOn)
            throws JOSEException {
        //RSA signatures require a public and private RSA key pair, the public key
        // must be made known to the JWS recipient in order to verify the signatures
//        setRSAKey(keySize, keyId);
        setRSAKey();

        // prepare header
        final JWSHeader jwsHeader = createJWSHeader(JWSAlgorithm.RS256, JOSEObjectType.JWT);
        // Prepare JWT with claims set
        final JWTClaimsSet
                jwtClaimsSet =
                createJWTClaims(keyId, audience, issuer, subject, issuedAt, new Date(expiredOn.getTime() + 60 * 1000));
        signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);

        // Create RSA-signer with the private key
        final JWSSigner signer = new RSASSASigner(getRSAKey());
        // Compute the RSA signature
        signedJWT.sign(signer);

        /**
         * To serialize to compact form, produces something like
         *
         * eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2LmZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3QdmaXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7-jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
         */
//        String s = signedJWT.serialize();

//        // On the consumer side, parse the JWS and verify its RSA signature
//        signedJWT = SignedJWT.parse(s);

//        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
//        assertTrue(signedJWT.verify(verifier));
//
//// Retrieve / verify the JWT claims according to the app requirements
//        assertEquals("alice", signedJWT.getJWTClaimsSet().getSubject());
//        assertEquals("https://c2id.com", signedJWT.getJWTClaimsSet().getIssuer());
//        assertTrue(new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime()));
    }

    /**
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public String serialize() {
        return signedJWT.serialize();
    }

    /**
     * On the consumer side, parse the JWS and verify its RSA signature
     *
     * @param jwtToken
     * @param rsaPublicKey
     * @return
     * @throws JOSEException
     */
    public boolean verify(final String jwtToken, final RSAKey rsaPublicKey) throws JOSEException, ParseException {
        JWSVerifier verifier = new RSASSAVerifier(rsaPublicKey);
        final SignedJWT jwtSigned = SignedJWT.parse(jwtToken);
        return jwtSigned.verify(verifier);
    }


    /**
     * Fetches the public key from the <code>urlJWKSFilePath</code>.
     * <p>
     * The time period to cache the JWKs from the endpoint, if the cache directive headers of the response are not
     * present or indicate that the content should not be cached. This is useful because the content of a JWKS endpoint
     * should be cached in the vast majority of situations and cache directive headers that indicate otherwise are
     * likely a mistake or misconfiguration.
     * <p>
     * The default value, used when this method is not called, of the default cache duration is 3600 seconds (1 hour).
     *
     * @param urlJWKSFilePath
     * @param defaultCacheDuration the length in seconds of the default cache duration
     * @return
     */
    public List<JsonWebKey> fetchJsonWebKeys(final String urlJWKSFilePath, final long defaultCacheDuration)
            throws JoseException, IOException {
        if (BeanUtils.isNullOrEmpty(urlJWKSFilePath)) {
            throw new IllegalArgumentException("Invalid JWKS file url!");
        }

        HttpsJwks jwksRequest = PayloadBuilder.get(urlJWKSFilePath);
        if (BeanUtils.isNullOrEmpty(jwksRequest)) {
            jwksRequest = new HttpsJwks(urlJWKSFilePath);
            PayloadBuilder.add(urlJWKSFilePath, jwksRequest);
            jwksRequest.setDefaultCacheDuration(defaultCacheDuration);
        }

        return jwksRequest.getJsonWebKeys();
    }

    /**
     * @param urlJWKSFilePath
     * @param defaultCacheDuration
     * @return
     */
    public Key fetchPublicKey(final String urlJWKSFilePath, final long defaultCacheDuration)
            throws JoseException, IOException {
        final List<JsonWebKey> jsonWebKeys = fetchJsonWebKeys(urlJWKSFilePath, defaultCacheDuration);
        if (BeanUtils.isNullOrEmpty(jsonWebKeys)) {
            return null;
        }

        JsonWebKey jsonWebKey = jsonWebKeys.get(0);
        return jsonWebKey.getKey();
    }

    /**
     * @param requestTracerPrefix
     * @return
     */
    public static String nextRequestTracer(final String requestTracerPrefix) {
        return String.format("%s-%d", (requestTracerPrefix == null ? "requestTracer" : requestTracerPrefix),
                System.currentTimeMillis());
    }

    /**
     * @return
     */
    public static String nextRequestTracer() {
        return nextRequestTracer("requestTracer");
    }

}
