package com.rslakra.appsuite.identity.enums;

/**
 * @author Rohtash Lakra
 * @created 2/25/22 11:00 AM
 */
public enum AuthType {
    PASSWORD,
    TWO_FA,     /* 2FA */
    TOKEN,
    BIOMETRIC,
    CAPTCHA,
    SSO,     /* Single sign-on */
    MFA,
    ;
}
