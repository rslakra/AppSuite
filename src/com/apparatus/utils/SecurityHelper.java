package com.apparatus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.X509Factory;

public class SecurityHelper {
	
	/* NEWLINE */
	public static final String NEWLINE = "\n".intern();
	
	/*
	 * Encodings (also used as extensions)
	 * The DER extension is used for binary DER encoded certificates. These
	 * files may also bear the CER or the CRT extension. Proper English usage
	 * would be “I have a DER encoded certificate”
	 */
	public static final String X509DER_EXT = ".DER".intern();
	
	/*
	 * Encodings (also used as extensions)
	 * The PEM extension is used for different types of X.509v3 files which
	 * contain ASCII (Base64) armored data prefixed with a “—– BEGIN …” line.
	 */
	public static final String X509PEM_EXT = ".PEM".intern();
	
	/*
	 * Common Extensions
	 * The CRT extension is used for certificates. The certificates may be
	 * encoded as binary DER or as ASCII PEM. The CER and CRT extensions are
	 * nearly synonymous. Most common among *nix systems
	 */
	public static final String X509CRT_EXT = ".CRT".intern();
	
	/*
	 * Common Extensions
	 * alternate form of .crt (Microsoft Convention) You can use MS to convert
	 * .crt to .cer (.both DER encoded .cer, or base64[PEM] encoded .cer) The
	 * .cer file extension is also recognized by IE as a command to run a MS
	 * cryptoAPI command (specifically rundll32.exe
	 * cryptext.dll,CryptExtOpenCER) which displays a dialogue for importing
	 * and/or viewing certificate contents.
	 */
	public static final String X509CER_EXT = ".CER".intern();
	
	/*
	 * Common Extensions
	 * The KEY extension is used both for public and private PKCS#8 keys. The
	 * keys may be encoded as binary DER or as ASCII PEM.
	 */
	public static final String X509KEY_EXT = ".KEY".intern();
	
	/* BASE64Encoder */
	public static final BASE64Encoder BASE64ENCODER = new BASE64Encoder();
	
	/* BASE64Decoder */
	public static final BASE64Decoder BASE64DECODER = new BASE64Decoder();
	
	/* DEFAULT_DATE_PATTERN */
	private static final String DEFAULT_DATE_PATTERN = "yyyyMMddHHmmss";
	
	/**
	 * Encodes the dataBytes into the base64 string.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static String encodedToBase64(byte[] dataBytes) {
		return BASE64ENCODER.encode(dataBytes);
	}
	
	/**
	 * Decodes the base64Encoded string to normal bytes.
	 * 
	 * @param base64Encoded
	 * @return
	 */
	public static byte[] decodeFromBase64(String base64Encoded) {
		byte[] decodeBytes = null;
		try {
			decodeBytes = BASE64DECODER.decodeBuffer(base64Encoded);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return decodeBytes;
	}
	
	/**
	 * Saves the specified certificate to the specified file.
	 * 
	 * @param certificate
	 * @param file
	 */
	public static void saveX509v3Certificate(Certificate certificate, File file) {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
			writer.write(X509Factory.BEGIN_CERT);
			writer.write(NEWLINE);
			// encoder.encodeBuffer(certificate.getEncoded(), out);
			// writer.write(encoder.encode(certificate.getEncoded()));
			// writer.write(DatatypeConverter.printBase64Binary(certificate.getEncoded()));
			writer.write(DatatypeConverter.printBase64Binary(certificate.getEncoded()).replaceAll("(.{64})", "$1\n"));
			writer.write(NEWLINE);
			writer.write(X509Factory.END_CERT);
			writer.flush();
			writer.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Saves the specified certificate to the specified filePath.
	 * 
	 * @param certificate
	 * @param filePath
	 */
	public static void saveX509v3Certificate(Certificate certificate, String filePath) {
		saveX509v3Certificate(certificate, new File(filePath));
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public static String generateBase64CertificateFromP12File(String filePath) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String base64Certificate = null;
		try {
			File p12File = new File(filePath);
			byte[] fileBytes = FileUtils.readFileToByteArray(p12File);
			base64Certificate = encodedToBase64(fileBytes);
			
			File certFile = new File(filePath + "_" + dateString(DEFAULT_DATE_PATTERN) + ".cert");
			FileUtils.writeStringToFile(certFile, base64Certificate, false);
			System.out.println("Certificate file generated at:" + certFile.getAbsolutePath());
		} catch(Exception ex) {
			ex.getMessage();
		}
		
		return base64Certificate;
	}
	
	public static String dateString(String dateFormatPattern) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
		String dateString = simpleDateFormat.format(date);
		System.out.println("Date formatted into " + dateFormatPattern + " format: " + dateString);
		return dateString;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		InputStream inputStream = null;
		try {
			String keyStoreFilePath = "/Applications/JBoss/JBoss-EAP-6.4/standalone/configuration/bvrohtash.keystore";
			File file = new File(keyStoreFilePath);
			inputStream = new FileInputStream(file);
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			String password = "rsing8887";
			keyStore.load(inputStream, password.toCharArray());
			
			Enumeration<?> enumeration = keyStore.aliases();
			while(enumeration.hasMoreElements()) {
				String alias = (String) enumeration.nextElement();
				System.out.println("alias name: " + alias);
				Certificate certificate = keyStore.getCertificate(alias);
				System.out.println(certificate.toString());
				Key publicKey = certificate.getPublicKey();
				System.out.println("publicKey:" + publicKey.toString());
				String certFilePath = alias + System.currentTimeMillis() + X509CRT_EXT;
				saveX509v3Certificate(certificate, certFilePath);
			}
			
			// // UPDATE- OBTAIN PRIVATE KEY
			// Key key = keyStore.getKey("keystore", password.toCharArray());
			// String encodedKey = Base64.encode(key.getEncoded());
			// System.out.println("encodedKey:" + encodedKey);
			// } catch(UnrecoverableKeyException e) {
			// e.printStackTrace();
			
			// // database key "string.ecx_apns_certificate_director"
			// final String DIRECTOR_P12_FILE =
			// "/Users/singhr/Documents/BoardVantage/Certificates/DirectorCertificates.p12";
			//
			// // database key "string.ecx_apns_certificate_nextgen"
			// final String NEXT_GEN_P12_FILE =
			// "/Users/singhr/Documents/BoardVantage/Certificates/NextGenCertificates.p12";
			//
			// Generate the Base63 encoded certificate from p12 file.
			// generateBase64CertificateFromP12File(DIRECTOR_P12_FILE);
			// System.out.println("base64Certificate:" + base64Certificate);
			
			// generateBase64CertificateFromP12File(NEXT_GEN_P12_FILE);
			
			// dateString(DEFAULT_DATE_PATTERN);
			
		} catch(CertificateException ex) {
			ex.printStackTrace();
		} catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch(KeyStoreException ex) {
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.close(inputStream);
		}
	}
	
}
