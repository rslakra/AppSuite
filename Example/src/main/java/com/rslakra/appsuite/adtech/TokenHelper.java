package com.rslakra.appsuite.adtech;

import com.rslakra.appsuite.core.jwt.JWTUtils;
import com.rslakra.appsuite.core.jwt.JwtRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 3/31/20 4:30 PM
 */
public class TokenHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);
    private static String URL_PATH_ACCESS_TOKEN = "/identity/oauth2/access_token?realm=";
    private static int EXPIRATION_TIME_IN_MINUTES = 30;

    /**
     * @param args
     */
    public static void main(String[] args) {
        GeminiApps apps = GeminiApps.CI_PROD_GEMINI_EXTERNAL_TEST_APP;
        String base64EncodedToken = JWTUtils.encodeClientIdAndSecret(apps.getClientId(), apps.getClientSecret());
        JwtRequest jwtRequest = JwtRequest.builder()
            .clientId(apps.getClientId())
            .clientSecret(apps.getClientSecret())
            .serverBaseUrl(apps.getServerBaseUrl())
            .pathSegment(URL_PATH_ACCESS_TOKEN)
            .realm(apps.getRealm())
            .expirationTimeInMinutes(EXPIRATION_TIME_IN_MINUTES)
            .build();
        String jwtToken = JWTUtils.jwtToken(jwtRequest);
        LOGGER.info("\nFor \n\nclientId\n{} \n\nclientSecret\n{} \n\nbase64EncodedToken\n{} \n\njwtToken\n{}\n\n",
                    apps.getClientId(), apps.getClientSecret(), base64EncodedToken, jwtToken);
    }
}
