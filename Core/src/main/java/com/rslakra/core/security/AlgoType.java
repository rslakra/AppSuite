package com.rslakra.core.security;

import com.rslakra.core.BeanUtils;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 7/21/22 5:28 PM
 */
public enum AlgoType {

    MD5("MD5"),
    RSA("RSA"),
    RSA_NONE_NO_PADDING("RSA/None/NoPadding"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256"),
    SHA_512("SHA-512"),
    SHA1PRNG("SHA1PRNG"),

    /* Encryption Algo */
    AES("AES"),
    AES_CBC_PKCS5PADDING("AES/CBC/PKCS5Padding"),
    ;

    private final String encType;

    /**
     * @param encType
     */
    private AlgoType(final String encType) {
        this.encType = encType;
    }

    /**
     * @return
     */
    public String getEncType() {
        return encType;
    }

    /**
     * @param algoType
     * @return
     */
    public static AlgoType forName(final String algoType) {
        AlgoType encType = null;
        if (BeanUtils.isNotEmpty(algoType)) {
            encType =
                Arrays.stream(values()).filter(name -> name.getEncType().equalsIgnoreCase(algoType)).findAny().get();
        }

        return encType;
    }
}
