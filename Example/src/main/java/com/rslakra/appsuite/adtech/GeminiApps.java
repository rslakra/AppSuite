package com.rslakra.appsuite.adtech;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rohtash Lakra
 * @created 4/9/21 5:08 PM
 */
@Getter
@AllArgsConstructor
public enum GeminiApps {

    /* PROD_GEMINI_TEST_APP */
    PROD_GEMINI_TEST_APP("ProdGemini",
                         "5CluuP44",
                         "dj0yJmk9cG1VblNhTWpMS2gxJmQ9WVdrOU5VTnNkWFZRTkRRbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTRk",
                         "474f5943d9d448bdfa5cb001e692bd6cd9991568",
                         "https://id-uat.b2b.yahooinc.com",
                         "adtech",
                         "https://api.gemini.yahoo.com"
    ),

    /* GEMINI_TEST_APP */
    GEMINI_TEST_APP("Gemini Inc.",
                    "fRbAuS52",
                    "dj0yJmk9U3RzYk9tY0lyNFZkJmQ9WVdrOVpsSmlRWFZUTlRJbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTk4",
                    "63afcef938e0164c1781e1f13ab6c970adb6f122",
                    "https://id-uat.b2b.yahooinc.com",
                    "adtech",
                    "https://developer.yahoo.com"
    ),

    /* CI_PROD_GEMINI_EXTERNAL_TEST_APP */
    CI_PROD_GEMINI_EXTERNAL_TEST_APP("TestExternal",
                                     "Bl820T7a",
                                     "dj0yJmk9cmFKR2EyUmxhWjJ0JmQ9WVdrOVFtdzRNakJVTjJFbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTg1",
                                     "1c92e1e945d1fee62e58acefa4e23d2a21ce0788",
                                     "https://id-uat.b2b.yahooinc.com",
                                     "adtech",
                                     "https://ciprod.api.gemini.yahoo.com"
    ),


    /* CI_PROD_GEMINI_TEST_APP */
    CI_PROD_GEMINI_TEST_APP("CiProdGemini",
                            "rKsQtP4k",
                            "dj0yJmk9bGthRld5ZUVQR0lnJmQ9WVdrOWNrdHpVWFJRTkdzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTA1",
                            "42afc3a3aa9b0ec80d969f2e9be2e3aa6a97afdb",
                            "https://id-uat.b2b.yahooinc.com",
                            "adtech",
                            "https://ciprod.api.gemini.yahoo.com"
    ),

    /* DSP_HEADLESS_USER */
    DSP_HEADLESS_USER("DSP Headless User",
                      "UCAM-Gemini-Credit-Card",
                      "97994b97-d1c9-4459-957c-c17d90bdce55",
                      "IoCDA7vNHQrj+4EKMelvIK/ddZwXOE8zhXwhXuRnLbJ58KV41g",
                      "https://id-uat.b2b.yahooinc.com",
                      "dsp",
                      "https://ciprod.api.gemini.yahoo.com"
    ),
    ;

    private final String appName;
    private final String appId;
    // Consumer Key
    private final String clientId;
    private final String clientSecret;
    private final String serverBaseUrl;
    private final String realm;
    private final String redirectUrl;

}
