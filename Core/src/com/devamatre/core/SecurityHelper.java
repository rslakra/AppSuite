/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * This class handles the security.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since Jul 17, 2015 10:31:46 AM
 */
public final class SecurityHelper {

	public static final String EMPTY_STRING = "";
	public static final String HEX_DIGIT_CHARS = "0123456789ABCDEF";
	public static final String PROVIDER_BC = "BC";
	public static final String ALGO_RSA = "RSA";
	public static final String ALGO_RSA_NONE_NOPADDING = "RSA/None/NoPadding";
	public static final String ALGO_SHA_512 = "SHA-512";
	public static final String ALGO_SHA_256 = "SHA-256";
	public static final String ALGO_SHA_1 = "SHA-1";
	public static final String ALGO_SHA1PRNG = "SHA1PRNG";
	public static final String CERT_X509 = "X.509";

	/* Encryption ALGO - AES */
	public static final String ALGO_AES = "AES";
	public static final String ALGO_AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
	public static final int IV_SIZE = 16;
	public static final boolean USE_FILE_EXTENSION_AS_IV = true;

	/* Encryption ALGO - MD5 */
	public static final String ALGO_MD5 = "MD5";

	/* INSTALLATION */
	private static final String INSTALLATION = "device.id";

	/* Note: For debugging purposes, reduce value to 100. */
	private static final Integer BV_ENCRYPTION_KEY_LENGTH = 32;

	/* pbkdf2Params */
	private static PBKDF2Params pbkdf2Params = null;

	/* pbkdf2Generator */
	private static PBKDF2Generator pbkdf2Generator = null;

	/* uniqueDeviceUUID */
	private static String uniqueDeviceUUID;

	/* _offlineEncryptionKey */
	private static String _offlineEncryptionKey;

	/* publicKey */
	private static PublicKey publicKey;

	/* messageDigest */
	private static MessageDigest sha256MessageDigest;

	/* Initialize X509TrustManager for the app's lifetime. */
	private static X509TrustManager mX509TrustManager;

	static {
		try {
			/* add provider. */
			Security.addProvider(new BouncyCastleProvider());
			// initialized message digest
			sha256MessageDigest = MessageDigest.getInstance(ALGO_SHA_256);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		try {
			TrustManagerFactory trustManagerFactory = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init((KeyStore) null);
			mX509TrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	/**
	 * Initialize the PBKDF2 generator only once.
	 */
	private static PBKDF2Generator getPBKDF2Generator() {
		if (pbkdf2Generator == null) {
			synchronized (SecurityHelper.class) {
				if (pbkdf2Generator == null) {
					/* pbkdf2Params */
					pbkdf2Params = new PBKDF2Params(PBKDF2Generator.PBKDF2_WITH_HMAC_SHA1, uniqueDeviceIdBytes("."),
							PBKDF2Generator.ITERATIONS);
					/* pbkdf2Generator */
					pbkdf2Generator = new PBKDF2Generator(pbkdf2Params);
				}
			}
		}

		return pbkdf2Generator;
	}

	/**************************************************************************
	 * Generic Utility Methods.
	 *************************************************************************/

	/**
	 * Returns the unique device ID bytes.
	 *
	 * @return
	 */
	public static byte[] uniqueDeviceIdBytes(String parentFolder) {
		byte[] deviceIDBytes = null;
		if (CoreHelper.isNullOrEmpty(uniqueDeviceUUID)) {
			synchronized (SecurityHelper.class) {
				if (CoreHelper.isNullOrEmpty(uniqueDeviceUUID)) {
					File installation = new File(parentFolder, INSTALLATION);
					try {
						if (!installation.exists()) {
							IOHelper.saveFile(IOHelper.toUTF8Bytes(UUID.randomUUID().toString()), installation);
						}
						deviceIDBytes = IOHelper.readFile(installation);
						if (!CoreHelper.isNullOrEmpty(deviceIDBytes)) {
							uniqueDeviceUUID = IOHelper.toUTF8String(deviceIDBytes);
						}
					} catch (Exception e) {
						throw new RuntimeException("Failed to read/write uniqueDeviceID to	 filesystem.", e);
					}
				}
			}
		} else {
			deviceIDBytes = IOHelper.toUTF8Bytes(uniqueDeviceUUID);
		}

		return deviceIDBytes;
	}

	/**
	 * Returns the UNIQUE device ID as string.
	 *
	 * @return
	 */
	public static String uniqueDeviceIdString() {
		return IOHelper.toUTF8String(uniqueDeviceIdBytes("."));
	}

	/**************************************************************************
	 * encoding and decoding with URLEncoder and URLDecoder
	 *************************************************************************/

	/**
	 * Encodes the <code>value</code> using the specified
	 * <code>charsetName</code>. If the <code>charsetName</code> is null or
	 * empty, the <code>Charset.defaultCharset().displayName()</code> is used.
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public static String encodeWithURLEncoder(String value, String charsetName) {
		try {
			return URLEncoder.encode(value, IOHelper.defaultCharset(charsetName));
		} catch (UnsupportedEncodingException ex) {
			System.err.println(ex);
			return value;
		}
	}

	/**
	 * Encodes the <code>value</code> using the
	 * <code>Charset.defaultCharset().displayName()</code> character set.
	 * 
	 * @param value
	 * @return
	 */
	public static String encodeWithURLEncoder(String value) {
		return encodeWithURLEncoder(value, Charset.defaultCharset().displayName());
	}

	/**
	 * Decodes the <code>value</code> using the specified
	 * <code>charsetName</code>. If the <code>charsetName</code> is null or
	 * empty, the <code>Charset.defaultCharset().displayName()</code> is used.
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public static String decodeWithURLDecoder(String value, String charsetName) {
		try {
			return URLDecoder.decode(value, IOHelper.defaultCharset(charsetName));
		} catch (UnsupportedEncodingException ex) {
			System.err.println(ex);
			return value;
		}
	}

	/**
	 * Decodes the <code>value</code> using the
	 * <code>Charset.defaultCharset().displayName()</code> character set.
	 * 
	 * @param value
	 * @return
	 */
	public static String decodeWithURLDecoder(String value) {
		return decodeWithURLDecoder(value, Charset.defaultCharset().displayName());
	}

	/**
	 * Returns the base64 encoded string.
	 * 
	 * @param decrypted
	 * @return
	 */
	public static String encodeToBase64String(byte[] decrypted) {
		return Base64.getEncoder().encodeToString(decrypted);
	}

	/**
	 * Returns the base64 encoded string.
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encodeToBase64String(String plainString) {
		return encodeToBase64String(IOHelper.toUTF8Bytes(plainString));
	}

	/**
	 * Decode the encrypted string using Base64 decoding.
	 * 
	 * @param encrypted
	 * @return
	 */
	public static byte[] decodeToBase64Bytes(String encodedString) {
		return Base64.getDecoder().decode(encodedString);
	}

	/**
	 * Returns the base64 decoded string.
	 * 
	 * @param encodedString
	 * @return
	 */
	public static String decodeToBase64String(String encodedString) {
		return IOHelper.toUTF8String(decodeToBase64Bytes(encodedString));
	}

	/**
	 * Returns the base64 encoded hash string for the given paramValue.
	 * 
	 * @param paramValue
	 * @return
	 */
	public static String paramValueAsHashString(String paramValue) {
		String valueAsHashString = null;
		try {
			byte[] sha256HashBytes = getSHA256Hash(paramValue);
			valueAsHashString = encodeToBase64String(sha256HashBytes);
			valueAsHashString = validateHashString(valueAsHashString);
			valueAsHashString = valueAsHashString.trim();
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return valueAsHashString;
	}

	/**
	 * Returns the base64 encoded hash string for the given paramValue.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static String responseAsHashString(byte[] dataBytes) {
		String valueAsHashString = null;
		try {
			byte[] sha256HashBytes = getSHA256Hash(dataBytes);
			valueAsHashString = IOHelper.toHexString(sha256HashBytes);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return valueAsHashString;
	}

	/**
	 * Returns the base64 encoded hash string for the given paramValue.
	 * 
	 * @param dataString
	 * @return
	 */
	public static String responseAsHashString(String dataString) {
		return responseAsHashString(dataString.getBytes());
	}

	/**
	 * Returns the validated hash string.
	 * 
	 * @param hashString
	 * @return
	 */
	public static String validateHashString(String hashString) {
		if (!CoreHelper.isNullOrEmpty(hashString)) {
			hashString = hashString.replace('/', '_');
			hashString = hashString.replace(' ', '_');
			hashString = hashString.replace('+', '_');
		}

		return hashString;
	}

	/**************************************************************************
	 * encryption and decryption with public key
	 *************************************************************************/

	/**
	 * Encrypt the specified string with the provided key.
	 * 
	 * @param plainString
	 * @param encryptionPublicKey
	 * @return
	 */
	private static String encryptWithPublicKey(String plainString, PublicKey encryptionPublicKey) {
		String encryptedWithKey = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGO_RSA_NONE_NOPADDING, PROVIDER_BC);
			cipher.init(Cipher.ENCRYPT_MODE, encryptionPublicKey);
			byte[] plainStringBytes = IOHelper.toUTF8Bytes(plainString);
			byte[] cipherBytes = cipher.doFinal(plainStringBytes);
			encryptedWithKey = encodeToBase64String(cipherBytes);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return encryptedWithKey;
	}

	/**
	 * Encrypts the given plainString using the public key.
	 * 
	 * @param plainString
	 * @return
	 */
	public static String encryptWithPublicKey(String plainString) {
		return encryptWithPublicKey(plainString, publicKey);
	}

	/**
	 * 
	 * @return
	 */
	public static SecureRandom newSecureRandom() {
		return new SecureRandom();
	}

	/**
	 * 
	 * @return
	 * @throws CertificateException
	 */
	public static CertificateFactory newCertificateFactory() throws CertificateException {
		return CertificateFactory.getInstance(CERT_X509);
	}

	/**
	 * 
	 * @param certificateFactory
	 * @param inputStream
	 * @param closeStream
	 * @return
	 * @throws CertificateException
	 */
	public static X509Certificate newX509Certificate(CertificateFactory certificateFactory, InputStream inputStream,
			boolean closeStream) throws CertificateException {
		X509Certificate x509Cert = null;
		try {
			x509Cert = (X509Certificate) certificateFactory.generateCertificate(inputStream);
			System.out.println("ca=" + x509Cert.getSubjectDN());
		} catch (CertificateException ex) {
			System.err.println(ex);
		} finally {
			if (closeStream) {
				IOHelper.safeClose(inputStream);
			}
		}

		return x509Cert;
	}

	/**
	 * 
	 * @param inputStream
	 * @param closeStream
	 * @return
	 * @throws CertificateException
	 */
	public static X509Certificate newX509Certificate(InputStream inputStream, boolean closeStream)
			throws CertificateException {
		return newX509Certificate(newCertificateFactory(), inputStream, closeStream);
	}

	/**
	 * Encrypts the given plainString using the pre-login public key.
	 * 
	 * @param encodedCertificate
	 * @return
	 * @throws CertificateException
	 */
	public static X509Certificate getCertificate(String encodedCertificate) throws CertificateException {
		X509Certificate certificate = null;
		try {
			byte[] certBytes = decodeToBase64Bytes(encodedCertificate);
			InputStream inputStream = new ByteArrayInputStream(certBytes);
			certificate = newX509Certificate(inputStream, false);
		} catch (Exception ex) {
			System.err.println(ex);
			throw new CertificateException(ex);
		}

		return certificate;
	}

	/**
	 * 
	 * @param encodedCertificate
	 * @return
	 */
	public static PublicKey getPublicKeyFromCertificate(String encodedCertificate) {
		PublicKey publicKey = null;
		try {
			publicKey = getCertificate(encodedCertificate).getPublicKey();
		} catch (CertificateException ex) {
			System.err.println(ex);
		}

		return publicKey;
	}

	/**
	 * Returns the public key for the given key.
	 * 
	 * @param publicKeyContents
	 * @return
	 */
	public static PublicKey getPublicKey(String publicKeyContents) {
		PublicKey publicKey = null;
		try {
			byte[] decodeKeyBytes = decodeToBase64Bytes(publicKeyContents);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGO_RSA);
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeKeyBytes);
			publicKey = keyFactory.generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		} catch (InvalidKeySpecException ex) {
			System.err.println(ex);
		}

		return publicKey;
	}

	/**
	 * Encrypts the given token using the encodedCertificate.
	 * 
	 * @param token
	 * @param encodedCertificate
	 * @return
	 * @throws SecurityException
	 */
	public static String encryptWithPublicKey(String token, String encodedCertificate) throws SecurityException {
		String encryptedToken = null;
		try {
			PublicKey publicKey = getPublicKeyFromCertificate(encodedCertificate);
			Cipher cipher = Cipher.getInstance(ALGO_RSA_NONE_NOPADDING, PROVIDER_BC);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(token.getBytes());
			encryptedToken = encodeToBase64String(encryptedBytes);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		} catch (NoSuchPaddingException ex) {
			System.err.println(ex);
		} catch (InvalidKeyException ex) {
			System.err.println(ex);
		} catch (NoSuchProviderException ex) {
			System.err.println(ex);
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex);
		} catch (BadPaddingException ex) {
			System.err.println(ex);
		}

		return encryptedToken;
	}

	/**
	 * Decrypts the specified string with the provided key.
	 * 
	 * @param encrptedString
	 * @param decryptPublicKey
	 * @return
	 */
	private static String decryptWithPublicKey(String encrptedString, PublicKey decryptPublicKey) {
		String decryptedWithKey = null;
		try {
			byte[] decodedBytes = decodeToBase64Bytes(encrptedString);
			Cipher cipher = Cipher.getInstance(ALGO_RSA_NONE_NOPADDING, PROVIDER_BC);
			cipher.init(Cipher.DECRYPT_MODE, decryptPublicKey);
			byte[] plainStringBytes = cipher.doFinal(decodedBytes);
			decryptedWithKey = IOHelper.toUTF8String(plainStringBytes);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		} catch (NoSuchProviderException ex) {
			System.err.println(ex);
		} catch (NoSuchPaddingException ex) {
			System.err.println(ex);
		} catch (InvalidKeyException ex) {
			System.err.println(ex);
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex);
		} catch (BadPaddingException ex) {
			System.err.println(ex);
		}

		return decryptedWithKey;
	}

	/**
	 * Decrypts the given token using the encodedCertificate.
	 * 
	 * @param token
	 * @param encodedCertificate
	 * @return
	 * @throws SecurityException
	 */
	public static String decryptWithPublicKey(String token, String encodedCertificate) throws SecurityException {
		return decryptWithPublicKey(token, getPublicKeyFromCertificate(encodedCertificate));
	}

	/**
	 * Decrypts the given encrptedText using the pre-login public key.
	 * 
	 * @param base64Key
	 */
	private static PublicKey createPublicKeyFromBase64Key(String base64Key) {
		PublicKey publicKeyFromBase64Key = null;
		try {
			byte[] decodeBytes = decodeToBase64Bytes(base64Key);
			InputStream inputStream = new ByteArrayInputStream(decodeBytes);
			CertificateFactory x509Certificate = CertificateFactory.getInstance("X.509");
			X509Certificate clientCertificate = (X509Certificate) x509Certificate.generateCertificate(inputStream);
			publicKeyFromBase64Key = clientCertificate.getPublicKey();
		} catch (CertificateException ex) {
			System.err.println(ex);
		}

		return publicKeyFromBase64Key;
	}

	/**
	 * 
	 * @param base64Key
	 */
	public static void createPublicKeyFromString(String base64Key) {
		publicKey = createPublicKeyFromBase64Key(base64Key);
	}

	/**************************************************************************
	 * encryption and decryption with public key
	 *************************************************************************/

	/**
	 * Returns the salted password.
	 * 
	 * @param _password
	 * @return
	 */
	public static String generateSaltedPassword(String _password) {
		return generateSaltedPassword(Math.random() % 5000 + "###" + System.currentTimeMillis(), _password);
	}

	/**
	 * Returns the salted password.
	 * 
	 * @param _salt
	 * @param _password
	 * @return
	 */
	public static String generateSaltedPassword(String _salt, String _password) {
		return (CoreHelper.isNullOrEmpty(_salt) || CoreHelper.isNullOrEmpty(_password) ? null
				: (_salt + "###" + _password));
	}

	/**
	 * Returns the generated derived key bytes of the given entropyString.
	 * 
	 * @param entropyString
	 * @return
	 */
	public static byte[] getPBKDF2KeyBytes(String entropyString) {
		byte[] derivedKeyBytes = null;
		if (CoreHelper.isNotNullOrEmpty(entropyString)) {
			try {
				derivedKeyBytes = getPBKDF2Generator().deriveKey(entropyString, BV_ENCRYPTION_KEY_LENGTH);
			} catch (NoSuchAlgorithmException ex) {
				System.out.println(ex);
			}
		}

		return derivedKeyBytes;
	}

	/**
	 * Returns the PBKDF2 key as string for the given entropyString.
	 * 
	 * @param entropyString
	 * @return
	 */
	public static String getPBKDF2KeyAsString(String entropyString) {
		return IOHelper.toUTF8String(getPBKDF2KeyBytes(entropyString));
	}

	/**
	 * Returns the PBKDF2 key as string for the given salt and the given
	 * plainPassword.
	 * 
	 * @param salt
	 * @param plainPassword
	 * @return
	 */
	public static String getPBKDF2KeyAsString(String salt, String plainPassword) {
		return getPBKDF2KeyAsString(generateSaltedPassword(salt, plainPassword));
	}

	/**
	 * Converts the given dataBytes into the HEXA-String.
	 * 
	 * @param buffer
	 * @return
	 */
	public static String toHexString(byte[] dataBytes) {
		String hexString = EMPTY_STRING;
		if (!CoreHelper.isNullOrEmpty(dataBytes)) {
			StringBuilder hexBuilder = new StringBuilder(2 * dataBytes.length);
			for (int i = 0; i < dataBytes.length; i++) {
				hexBuilder.append(HEX_DIGIT_CHARS.charAt((dataBytes[i] >> 4) & 0x0f));
				hexBuilder.append(HEX_DIGIT_CHARS.charAt(dataBytes[i] & 0x0f));
			}

			hexString = hexBuilder.toString();
			/* make available for GC. */
			hexBuilder = null;
		}

		return hexString;
	}

	/**
	 * Returns the HEXA-String for the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static String toHexString(String plainString) {
		return toHexString(IOHelper.toUTF8Bytes(plainString));
	}

	/**
	 * Returns the plain bytes from the given HEXA-String.
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringAsBytes(String hexaString) {
		byte[] hexaBytes = null;
		if (!CoreHelper.isNullOrEmpty(hexaString)) {
			int length = hexaString.length() / 2;
			hexaBytes = new byte[length];
			for (int i = 0; i < length; i++) {
				hexaBytes[i] = Integer.valueOf(hexaString.substring(2 * i, 2 * i + 2), 16).byteValue();
			}
		}

		return hexaBytes;
	}

	/**
	 * Checks padding. If the content length is less then 16 and
	 * <code>addSpaces</code> is set to be true, then add spaces at the end
	 * (before encryption), otherwise remove extra spaces (after decryption
	 * happens).
	 * 
	 * @param input
	 * @return
	 */
	public static byte[] checkPadding(final byte[] input, final boolean addSpaces) {
		if (addSpaces) {
			if (input != null && input.length >= 16) {
				return input;
			}

			byte[] _input = new byte[16];
			int i = 0;
			for (; i < input.length; i++) {
				_input[i] = input[i];
			}
			for (; i < _input.length; i++) {
				_input[i] = 20;
			}

			return _input;
		} else {
			if (input != null && input.length >= 16) {
				int i = input.length - 1;
				for (; i >= 0; i--) {
					if (input[i] == 20) {
						continue;
					} else {
						break;
					}
				}

				byte[] _input = new byte[i + 1];
				for (; i >= 0; i--) {
					_input[i] = input[i];
				}

				return _input;
			}

			return input;
		}
	}

	/**
	 * Encrypts the given dataBytes using the given keyBytes and ivBytes.
	 * 
	 * @param dataBytes
	 * @param keyBytes
	 * @param ivBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptWithSymmetricKey(final byte[] dataBytes, final byte[] keyBytes, final byte[] ivBytes)
			throws Exception {
		byte[] encryptedBytes = null;
		long startTime = System.currentTimeMillis();
		if (!CoreHelper.isNullOrEmpty(dataBytes) && !CoreHelper.isNullOrEmpty(keyBytes)) {
			Cipher cipher = null;
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGO_AES);
			if (CoreHelper.isNullOrEmpty(ivBytes)) {
				cipher = Cipher.getInstance(ALGO_AES);
				cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			} else {
				cipher = Cipher.getInstance(ALGO_AES_CBC_PKCS5PADDING);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
				cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
				// rawBytes = checkPadding(dataBytes);
			}

			encryptedBytes = cipher.doFinal(dataBytes);
		}

		if (encryptedBytes != null) {
			System.out.println("===========> encryptWithSymmetricKey took:" + (System.currentTimeMillis() - startTime)
					+ " millis to encrypt:" + encryptedBytes.length + " bytes.");
		}

		return encryptedBytes;
	}

	/**
	 * Encrypts the dataBytes with the keyBytes.
	 * 
	 * @param dataBytes
	 * @param keyBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptWithSymmetricKey(byte[] dataBytes, byte[] keyBytes) throws Exception {
		return encryptWithSymmetricKey(dataBytes, keyBytes, null);
	}

	/**
	 * Encrypts the given dataBytes using the given key and IV.
	 * 
	 * @param dataBytes
	 * @param key
	 * @param ivBytes
	 * @return
	 */
	public static byte[] encryptWithSymmetricKey(byte[] dataBytes, String key, byte[] ivBytes) {
		byte[] encryptedBytes = null;
		try {
			encryptedBytes = encryptWithSymmetricKey(dataBytes, getSHA256Hash(key), ivBytes);
		} catch (SecurityException ex) {
			System.err.println(ex);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return encryptedBytes;
	}

	/**
	 * Encrypts the specified <code>stringToEncrypt</code> using the specified
	 * <code>key</code>.
	 * 
	 * @param dataBytes
	 * @param key
	 * @return
	 */
	public static byte[] encryptWithSymmetricKey(byte[] dataBytes, String key) {
		return encryptWithSymmetricKey(dataBytes, key, null);
	}

	/**
	 * Encrypts the specified <code>stringToEncrypt</code> using the specified
	 * <code>key</code>.
	 * 
	 * @param plainString
	 *            The string you wish to encrypt. Assume that it is in UTF-8
	 *            format.
	 * @param key
	 *            The string representation of the key to encrypt with. This
	 *            should be of sufficient entropy.
	 * @return An encrypted String in UTF-8 format.
	 * 
	 * @param stringToEncrypt
	 * @param key
	 * @return
	 */
	public static String encryptWithSymmetricKey(String plainString, String key) {
		String encryptedString = null;
		if (!CoreHelper.isNullOrEmpty(plainString) && !CoreHelper.isNullOrEmpty(key)) {
			try {
				byte[] encryptedBytes = encryptWithSymmetricKey(IOHelper.toUTF8Bytes(plainString), key);
				encryptedString = toHexString(encryptedBytes);
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}

		return encryptedString;
	}

	/**
	 * Decrypts the given dataBytes using the given keyBytes and ivBytes.
	 * 
	 * @param dataBytes
	 * @param keyBytes
	 * @param ivBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptWithSymmetricKey(final byte[] dataBytes, final byte[] keyBytes, final byte[] ivBytes)
			throws Exception {
		byte[] rawBytes = null;
		long startTime = System.currentTimeMillis();
		if (!CoreHelper.isNullOrEmpty(dataBytes) && !CoreHelper.isNullOrEmpty(keyBytes)) {
			Cipher cipher = null;
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGO_AES);
			if (CoreHelper.isNullOrEmpty(ivBytes)) {
				cipher = Cipher.getInstance(ALGO_AES);
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			} else {
				cipher = Cipher.getInstance(ALGO_AES_CBC_PKCS5PADDING);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			}

			rawBytes = cipher.doFinal(dataBytes);
		}

		if (rawBytes != null) {
			System.out.println("===========> decryptWithSymmetricKey took:" + (System.currentTimeMillis() - startTime)
					+ " millis to decrypt:" + rawBytes.length + " bytes.");
		}

		return rawBytes;
	}

	/**
	 * Decrypts the dataBytes with the given keyBytes.
	 * 
	 * @param dataBytes
	 * @param keyBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptWithSymmetricKey(byte[] dataBytes, byte[] keyBytes) throws Exception {
		return decryptWithSymmetricKey(dataBytes, keyBytes, null);
	}

	/**
	 * Decrypts the given <code>dataBytes</code> using the given
	 * <code>key</code> and <code>ivBytes</code>.
	 * 
	 * @param dataBytes
	 * @param key
	 * @param ivBytes
	 * @return
	 */
	public static byte[] decryptWithSymmetricKey(byte[] dataBytes, String key, byte[] ivBytes) {
		byte[] rawBytes = null;
		try {
			rawBytes = decryptWithSymmetricKey(dataBytes, getSHA256Hash(key), ivBytes);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return rawBytes;
	}

	/**
	 * Decrypts the given <code>dataBytes</code> using the given
	 * <code>key</code>.
	 * 
	 * @param dataBytes
	 * @param key
	 * @return
	 */
	public static byte[] decryptWithSymmetricKey(byte[] dataBytes, String key) {
		return decryptWithSymmetricKey(dataBytes, key, null);
	}

	/**
	 * Decrypts the specified <code>encryptedString</code> using the specified
	 * <code>key</code>.
	 * 
	 * @param encryptedTextString
	 * @param key
	 * @return
	 */
	public static String decryptWithSymmetricKey(String encryptedString, String key) {
		String decryptedString = null;
		if (!CoreHelper.isNullOrEmpty(encryptedString) && !CoreHelper.isNullOrEmpty(key)) {
			try {
				byte[] encryptedBytes = hexStringAsBytes(encryptedString);
				byte[] decryptedBytes = decryptWithSymmetricKey(encryptedBytes, key);
				decryptedString = IOHelper.toUTF8String(decryptedBytes);
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}

		return decryptedString;
	}

	/**
	 * Returns the default token.
	 * 
	 * @return
	 */
	public static String baseTokenAsString() {
		// TODO: UNCOMMENT IT AFTER FINAL TESTING.
		// return decodeToBase64String(BASE_TOKEN);
		return null;
	}

	/**
	 * Returns the IV for the given ivParent.
	 * 
	 * @param ivParent
	 * @return
	 * @throws SecurityException
	 */
	public static String ivForResource(String ivParent) throws SecurityException {
		if (CoreHelper.isNotNullOrEmpty(ivParent)) {
			return (ivParent.length() > IV_SIZE ? ivParent.substring(0, IV_SIZE) : ivParent);
		}

		throw new SecurityException("Unable to generte IV for: " + ivParent);
	}

	/**
	 * Returns the IV bytes for the given resource.
	 * 
	 * @param filePath
	 * @return
	 */
	public static byte[] getIVBytes(String filePath) {
		byte[] ivBytes = null;
		try {
			String ivParent = IOHelper.getFileName(filePath, true);
			if (USE_FILE_EXTENSION_AS_IV) {
				String extension = IOHelper.getExtension(ivParent);
				if (!CoreHelper.isNullOrEmpty(extension)) {
					ivParent = extension;
				}
			}
			ivBytes = ivForResource(ivParent).getBytes();
		} catch (SecurityException ex) {
			System.err.println(ex);
		}

		return ivBytes;
	}

	/**
	 * Decrypting the server URLs.
	 * 
	 * (Placeholders, for now, but I'm actually using them when the user chooses
	 * a new server.)
	 * 
	 * @param encryptedUrl
	 * @return
	 */
	public static String decryptServerUrl(String encryptedUrl) {
		return encryptedUrl;
	}

	/**
	 * 
	 * @return
	 */
	public static String offlineEncryptionKey() {
		if (_offlineEncryptionKey == null) {
			_offlineEncryptionKey = getPBKDF2KeyAsString(uniqueDeviceIdString());
		}
		return _offlineEncryptionKey;
	}

	/**
	 * Generate a Base64 encoded "salt".
	 * 
	 * @param size
	 * @return
	 */
	public static String generateSalt(int size) {
		String saltString = null;
		try {
			final SecureRandom randomGenerator = SecureRandom.getInstance(ALGO_SHA1PRNG);
			byte[] salt = new byte[size];
			randomGenerator.nextBytes(salt);
			saltString = IOHelper.toUTF8String(salt);
		} catch (Exception ex) {
			throw new Error(ex);
		}

		return saltString;
	}

	/**
	 * Generates the IV.
	 * 
	 * @return
	 */
	public static String generateIV() {
		return generateSalt(IV_SIZE);
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] generateRandomAESKey(int bytes) {
		byte[] ret = null;

		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(bytes);
			SecretKey secretKey = keyGen.generateKey();
			ret = secretKey.getEncoded();
		} catch (Exception e) {
			ret = null;
		}

		return ret;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		return generateKeyPair(ALGO_RSA, null);
	}

	/**
	 * 
	 * @param keyPairAlgorithm
	 * @param secureRandomAlgorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair generateKeyPair(String keyPairAlgorithm, String secureRandomAlgorithm)
			throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyPairAlgorithm);
		SecureRandom secureRandom = null;
		if (CoreHelper.isNullOrEmpty(secureRandomAlgorithm)) {
			secureRandom = new SecureRandom();
		} else {
			secureRandom = SecureRandom.getInstance(secureRandomAlgorithm);
		}

		// seed the keygen
		keyPairGenerator.initialize(2048, secureRandom);
		return keyPairGenerator.generateKeyPair();
	}

	/**
	 * decodes an encoded x509 certificate
	 * 
	 * @param bytes
	 * @return
	 * @throws CertificateException
	 */
	public static X509Certificate extractPublicCertificate(byte[] bytes) throws CertificateException {
		ByteArrayInputStream asnStream = new ByteArrayInputStream(bytes);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) cf.generateCertificate(asnStream);
		try {
			asnStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cert;
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String generateNewKeyWithLength(Integer length) {
		if (length <= 0) {
			return null;
		}

		String pad = "poiuytrewqasdfgbvcxzhjklmnb,.;1234567890!^&-=_+><ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String mutableKey = "";
		Random rnd = new Random();
		do {
			int randNum = rnd.nextInt(pad.length());
			mutableKey += Character.toString(pad.charAt(randNum));
		} while (mutableKey.length() < length);
		return mutableKey;
	}

	/**
	 * 
	 * @return
	 */
	public static String generateRandomSeed() {
		return generateNewKeyWithLength(BV_ENCRYPTION_KEY_LENGTH);
	}

	/**
	 * Returns the SHA256 hash for the given bytes.
	 * 
	 * @param bytes
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getSHA256Hash(byte[] bytes) throws SecurityException {
		if (CoreHelper.isNotNull(bytes)) {
			try {
				return sha256MessageDigest.digest(bytes);
			} catch (Exception ex) {
				throw new SecurityException(ex);
			}
		}

		return null;
	}

	/**
	 * Returns the SHA256 hash for the given string.
	 * 
	 * @param string
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getSHA256Hash(String string) throws SecurityException {
		return getSHA256Hash(CoreHelper.isNull(string) ? null : string.getBytes());
	}

	/**
	 * Returns the SHA256 hash for the given bytes.
	 * 
	 * @param bytes
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getSHA512Hash(byte[] bytes) throws SecurityException {
		if (CoreHelper.isNotNull(bytes)) {
			try {
				return MessageDigest.getInstance(ALGO_SHA_512).digest(bytes);
			} catch (Exception ex) {
				throw new SecurityException(ex);
			}
		}

		return null;
	}

	/**
	 * Returns the SHA256 hash for the given string.
	 * 
	 * @param string
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getSHA512Hash(String string) throws SecurityException {
		return getSHA512Hash(CoreHelper.isNull(string) ? null : string.getBytes());
	}

	/**
	 * Returns the SHA-1 hash for the specified input.
	 * 
	 * @param input
	 * @return
	 */
	public static String getSHA1Hash(String input) {
		String hashedString = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGO_SHA_1);
			messageDigest.update(IOHelper.toUTF8Bytes(input), 0, input.length());
			byte[] sha1hash = messageDigest.digest();
			hashedString = toHexString(sha1hash);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		} catch (Exception ex) {
			System.err.println(ex);
		}

		return hashedString;
	}

	/**
	 * 
	 * @param src
	 * @param iterations
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getSHA256HashForNIterations(byte[] src, int iterations) throws SecurityException {
		if (iterations < 0) {
			throw new SecurityException("Can not hash a negative number of iterations.");
		}

		try {
			final MessageDigest messageDigest = MessageDigest.getInstance(ALGO_SHA_256);
			for (int i = 0; i < iterations; i++) {
				src = messageDigest.digest(src);
			}

			return src;
		} catch (Exception ex) {
			throw new SecurityException(ex);
		}
	}

	/**
	 * produce salted SHA256 hash
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSaltedSHA256Hash(String src, String salt) throws SecurityException {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance(ALGO_SHA_256);
			messageDigest.reset();
			messageDigest.update(salt.getBytes());
			return messageDigest.digest(src.getBytes());
		} catch (Exception e) {
			throw new SecurityException(e);
		}
	}

	/**
	 * produce md4 hash
	 * 
	 * @param src
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] getMD5Hash(String src) throws SecurityException {
		try {
			return MessageDigest.getInstance(ALGO_MD5).digest(src.getBytes());
		} catch (Exception e) {
			throw new SecurityException(e);
		}
	}

	/**
	 * create random ket based in algorithim
	 * 
	 * @param algo
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static Key createKey(String algo, int length) throws Exception {
		byte[] salt = new byte[8];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);
		keyGenerator.init(length, random);
		Key key = keyGenerator.generateKey();
		return key;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Key createKeyTransferKey() throws Exception {
		byte[] salt = new byte[8];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO_AES);
		keyGenerator.init(128, random);
		Key key = keyGenerator.generateKey();
		return key;
	}

	/**
	 * Returns the check sum string for the given bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getChecksum(byte[] bytes) {
		String checkSumString = "0";
		if (!CoreHelper.isNullOrEmpty(bytes)) {
			long startTime = System.currentTimeMillis();
			try {
				MessageDigest md5 = MessageDigest.getInstance(ALGO_MD5);
				md5.update(bytes, 0, bytes.length);
				checkSumString = new BigInteger(1, md5.digest()).toString(16);
				System.out.println("Time to generate hash:" + (System.currentTimeMillis() - startTime));
			} catch (NoSuchAlgorithmException e) {
				System.err.println(e);
			}
		}

		return checkSumString;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// StringBuffer s = new StringBuffer(5000);
		// s.append("0COMP:IFSYHB-IFSYHBgetPortalsBaseRemoteService0USER:IFSYHB-7ACB8ED5EA6244CABF4A307D9268AB930PRTL:IFSYHB-703D94EDC8A74870ABD3B35983273530");
		// System.out.println("VAL:" + paramValueAsHashString(s.toString()));
		String value = "1";
		byte[] valueBytes = value.getBytes();
		System.out.println("valueBytes:" + Arrays.toString(valueBytes));
		byte[] paddedBytes = checkPadding(valueBytes, true);
		System.out.println("paddedBytes:" + Arrays.toString(paddedBytes));
		valueBytes = checkPadding(paddedBytes, false);
		System.out.println("valueBytes:" + Arrays.toString(valueBytes));

		String deviceId = "10297B1F-3D93-4EE6-B609-E43898C2C54C";
		byte[] deviceIdBytes = getSHA512Hash(deviceId);
		System.out.println(deviceIdBytes.length);
		byte[] slice = Arrays.copyOfRange(deviceIdBytes, 0, 32);
		System.out.println(slice.length);
		System.out.println("slice:" + Arrays.toString(slice));
		System.out.println("slice:" + encodeToBase64String(slice));
	}

	/**************************************************************************
	 * Unused Methods
	 *************************************************************************/

	/**
	 * 
	 * @param seed
	 * @returnf
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static byte[] getRawKey(byte[] seed) throws Exception {
		byte[] rawKey = null;
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO_AES);
		SecureRandom secureRandom = SecureRandom.getInstance(ALGO_SHA1PRNG);
		secureRandom.setSeed(seed);
		/* May have to go back to 128 if not all devices are working right. */
		keyGenerator.init(256, secureRandom);
		SecretKey skey = keyGenerator.generateKey();
		rawKey = skey.getEncoded();
		return rawKey;
	}

	/**
	 * Returns the random generated ID.
	 * 
	 * @return
	 */
	public static int getRandomId() {
		int idRandom = -1;
		try {
			idRandom = SecureRandom.getInstance(ALGO_SHA1PRNG).nextInt();
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}

		return idRandom;
	}

	/**
	 * 
	 * @param connectionHttps
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean verifySSLCertificate(HttpsURLConnection connectionHttps) {
		boolean bRet = false;
		try {
			X509Certificate[] x509Certs = (X509Certificate[]) connectionHttps.getServerCertificates();
			// TODO: Test with an invalid certificate and see if it fails.
			mX509TrustManager.checkServerTrusted(x509Certs, "SHA1withRSA");
			bRet = true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return bRet;
	}

	/**
	 * The parameters for the PBKDF2 generation.
	 * 
	 * @author Rohtash Singh Lakra
	 * @date 11/21/2016 04:04:53 PM
	 */
	public static final class PBKDF2Params {

		private String algorithm;
		private byte[] salt;
		private int iterations;

		/**
		 * 
		 * @param algorithm
		 * @param salt
		 * @param iterations
		 */
		public PBKDF2Params(final String algorithm, final byte[] salt, final int iterations) {
			this.algorithm = algorithm;
			this.salt = salt;
			this.iterations = iterations;
		}

		/**
		 * Returns the algorithm.
		 * 
		 * @return
		 */
		public String getAlgorithm() {
			return algorithm;
		}

		/**
		 * Returns the salt.
		 * 
		 * @return
		 */
		public byte[] getSalt() {
			return salt;
		}

		/**
		 * Returns the iterations.
		 * 
		 * @return
		 */
		public int getIterations() {
			return iterations;
		}
	}

	/**
	 * This class implements the PBKDF2 in pure java.
	 * 
	 * https://www.ietf.org/rfc/rfc2898.txt
	 * 
	 * @author Rohtash Singh Lakra
	 * @date 11/21/2016 04:04:10 PM
	 */
	public static final class PBKDF2Generator {
		/* The secret keys algorithm */
		public static final String PBKDF2_WITH_HMAC_SHA512 = "PBKDF2WithHmacSHA512";
		public static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

		/* Secure Random Algorithm */
		public static final String SHA1PRNG = "SHA1PRNG";

		/* Iterations - Strong */
		public static final int ITERATIONS = 10000;

		/* Key Length */
		public static final int KEY_LENGTH = 16;

		/* PBKDF2 parameters */
		private PBKDF2Params parameters;

		/**
		 * 
		 * @param parameters
		 */
		public PBKDF2Generator(PBKDF2Params parameters) {
			this.parameters = parameters;
		}

		/**
		 * 
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		public static byte[] getSalt(String algorithm) throws NoSuchAlgorithmException {
			SecureRandom secureRandom = SecureRandom.getInstance(algorithm);
			byte[] salt = new byte[16];
			secureRandom.nextBytes(salt);
			return salt;
		}

		/**
		 * Generates the PBKDF2 secret key.
		 * 
		 * @param password
		 * @param salt
		 * @param iterations
		 * @param keyLength
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength)
				throws NoSuchAlgorithmException {
			try {
				PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterations, keyLength * 8);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(parameters.getAlgorithm());
				return keyFactory.generateSecret(keySpec).getEncoded();
			} catch (InvalidKeySpecException ex) {
				throw new IllegalStateException("Invalid SecretKeyFactory", ex);
			}
		}

		/**
		 * Returns the PBKDF2 bytes of the given password.
		 * 
		 * @param password
		 * @param keyLength
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		public byte[] deriveKey(String password, int keyLength) throws NoSuchAlgorithmException {
			return pbkdf2(password.toCharArray(), parameters.getSalt(), parameters.getIterations(), keyLength);
		}

		/**
		 * Returns the PBKDF2 hex string of the given password.
		 * 
		 * @param password
		 * @param keyLength
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		public String keyAsHexString(String password, int keyLength) throws NoSuchAlgorithmException {
			return IOHelper.toHexString(deriveKey(password, keyLength));
		}

		/**
		 * Returns the PBKDF2 bytes of the given password.
		 * 
		 * @param password
		 * @param keyLength
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		public String hashPassword(String password, int keyLength) throws NoSuchAlgorithmException {
			String pbkdf2String = null;
			if (!CoreHelper.isNullOrEmpty(password)) {
				String saltHexString = IOHelper.toHexString(parameters.getSalt());
				String keyAsHexString = keyAsHexString(password, keyLength);
				pbkdf2String = (parameters.getIterations() + ":" + saltHexString + ":" + keyAsHexString);
				pbkdf2String = encodeToBase64String(pbkdf2String);
			}

			return pbkdf2String;
		}

		/**
		 * Validates the password with the hashed password.
		 * 
		 * @param originalPassword
		 * @param hashedPassword
		 * @return
		 * @throws NoSuchAlgorithmException
		 * @throws InvalidKeySpecException
		 */
		public boolean validatePassword(String password, String hashedPassword)
				throws NoSuchAlgorithmException, InvalidKeySpecException {
			boolean validPassword = false;
			if (!CoreHelper.isNullOrEmpty(password)) {
				String[] parts = hashedPassword.split(":");
				int iterations = Integer.parseInt(parts[0]);
				byte[] salt = IOHelper.toHexBytes(parts[1]);
				byte[] hash = IOHelper.toHexBytes(parts[2]);
				byte[] pbkdf2Hash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);

				int difference = hash.length ^ pbkdf2Hash.length;
				for (int i = 0; i < hash.length && i < pbkdf2Hash.length; i++) {
					difference |= hash[i] ^ pbkdf2Hash[i];
				}

				validPassword = (difference == 0);
			}

			return validPassword;
		}

		/**
		 * authenticate
		 * 
		 * @param args
		 * @throws NoSuchAlgorithmException
		 * @throws InvalidKeySpecException
		 */

		/*
		 * public static void main(String[] args) throws
		 * NoSuchAlgorithmException, InvalidKeySpecException { String password =
		 * ""; byte[] salt = PBKDF2Generator.getSalt(PBKDF2Generator.SHA1PRNG);
		 * PBKDF2Params pbkdf2Params = new PBKDF2Params(PBKDF2_WITH_HMAC_SHA1,
		 * salt, ITERATIONS); PBKDF2Generator pbkdf2Generator = new
		 * PBKDF2Generator(pbkdf2Params); String hexPassword =
		 * pbkdf2Generator.keyAsHexString(password, KEY_LENGTH);
		 * System.out.println(hexPassword); String newHexPassword =
		 * pbkdf2Generator.keyAsHexString(password, KEY_LENGTH);
		 * System.out.println(newHexPassword); String hashedPassword =
		 * pbkdf2Generator.hashPassword(password, KEY_LENGTH);
		 * System.out.println(hashedPassword); boolean matched =
		 * pbkdf2Generator.validatePassword(password, hashedPassword);
		 * System.out.println(matched); matched =
		 * pbkdf2Generator.validatePassword("", hashedPassword);
		 * System.out.println(matched); }
		 */
	}

}
