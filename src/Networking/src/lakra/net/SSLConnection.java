/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SSLConnection {
	final int HTTP_PORT = 1505;
	//Port Number which the server will be listening on
	final int HTTPS_PORT = 443;
	final String SERVER_HOST = "10.0.61.151";	

	final String CLIENT_HOST = "10.0.61.151";
	
	public String getCertificate();
}
