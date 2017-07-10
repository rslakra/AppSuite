package com.rslakra.utils;

import java.io.File;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class CertUtil {
	
	private static final String DEFAULT_DATE_PATTERN = "yyyyMMddHHmmss";
	
	// database key "string.ecx_apns_certificate_director"
	// private static final String DIRECTOR_P12_FILE =
	// "/Users/singhr/Documents/BoardVantage/Certificates/DirectorCertificates.p12";
	private static final String DEVELOPMENT_DIRECTOR_P12_FILE = "/Users/singhr/Documents/BoardVantage/Certificates/ECXDevCertificates.p12";
	
	// database key "string.ecx_apns_certificate_nextgen"
	private static final String DEVELOPMENT_NEXT_GEN_P12_FILE = "/Users/singhr/Documents/BoardVantage/Certificates/NextGenCertificates.p12";
	
	public static void main(String[] args) {
		String base64Certificate = null;
		// Generate the Base63 encoded certificate from p12 file.
		base64Certificate = generateBase64CertificateFromP12File(DEVELOPMENT_DIRECTOR_P12_FILE);
		System.out.println("base64Certificate:\n" + base64Certificate);
		//
		// base64Certificate = null;
		// base64Certificate =
		// generateBase64CertificateFromP12File(DEVELOPMENT_NEXT_GEN_P12_FILE);
		// System.out.println("base64Certificate:\n" + base64Certificate);
		
		// dateString(DEFAULT_DATE_PATTERN);
	}
	
	public static String generateBase64CertificateFromP12File(String filePath) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String base64Certificate = null;
		try {
			File p12File = new File(filePath);
			byte[] fileBytes = FileUtil.readFileBytes(p12File);
			base64Certificate = Base64.getEncoder().encodeToString(fileBytes);
			
			File certFile = new File(filePath + "_" + dateString(DEFAULT_DATE_PATTERN) + ".cert");
			FileUtil.saveFile(base64Certificate.getBytes(), certFile);
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
	
}
