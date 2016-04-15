package com.apparatus.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apparatus.utils.FileHelper;

/**
 * The default ProxySelector behavior is cool and makes happy most of the world,
 * except when it comes to HTTP and HTTPS. It might be possible that some of
 * the networks have more than one possible PROXY for these protocols and we'd
 * like our application to try them in sequence (i.e.: if the first one doesn't
 * respond, then try the second one and so on). Even more, if one of them fails
 * too many time, we'll remove it from the list in order to optimize things a
 * bit.
 * 
 * So, all we'll have to do is check that the protocol from the URI is indeed
 * HTTP (or HTTPS), in which case we will return the list of proxies, otherwise
 * we just delegate to the default one.
 * 
 * @see http://docs.oracle.com/javase/8/docs/technotes/guides/net/proxies.html
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since May 27, 2015 12:44:12 PM
 */
public class DefaultProxySelector extends ProxySelector {
	
	/** defaultProxySelector */
	private ProxySelector defaultProxySelector;
	
	/** available proxies */
	private Map<SocketAddress, DefaultProxy> defaultProxies = new HashMap<SocketAddress, DefaultProxy>();
	
	/**
	 * 
	 * @param proxySelector
	 */
	public DefaultProxySelector(ProxySelector proxySelector) {
		defaultProxySelector = proxySelector;
		// Populate the HashMap (List of proxies)
		DefaultProxy bvProxy = new DefaultProxy(new InetSocketAddress("example.proxy.com", 8008));
		defaultProxies.put(bvProxy.getSocketAddress(), bvProxy);
	}
	
	/**
	 * The select() method is called by the protocol handlers before trying to
	 * connect to a destination. For example:
	 * <code>List<Proxy> l = ProxySelector.getDefault().select(new URI("http://java.example.org/"));</code>
	 * 
	 * @param uri
	 * @see java.net.ProxySelector#select(java.net.URI)
	 */
	@Override
	public List<Proxy> select(URI uri) {
		if(uri == null) {
			throw new IllegalArgumentException("URI can't be null.");
		}
		
		List<Proxy> proxies = new ArrayList<Proxy>();
		
		// validate that the protocol from the URI is indeed HTTP (or HTTPS),
		if("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme())) {
			// Populate the list with available proxies
			for(DefaultProxy defaultProxy : defaultProxies.values()) {
				proxies.add(defaultProxy.getProxy());
			}
		} else {
			// other than HTTP or HTTPS
			if(defaultProxySelector == null) {
				proxies.add(Proxy.NO_PROXY);
			} else {
				proxies = defaultProxySelector.select(uri);
			}
		}
		
		// avoid errors
		if(proxies == null) {
			proxies = new ArrayList<Proxy>();
		}
		
		return proxies;
	}
	
	/**
	 * The connectFailed() method is called by the protocol handler whenever it
	 * failed to connect to one of the proxies returned by the select() method.
	 * 
	 * Here we'll just do the following: - If the proxy is in our list, and it
	 * failed 3 times or more, we'll just remove it from our list, making sure
	 * it won't be used again in the future.
	 * 
	 * @param uri
	 * @param socketAddress
	 * @param exception
	 * @see java.net.ProxySelector#connectFailed(java.net.URI,
	 *      java.net.SocketAddress, java.io.IOException)
	 */
	@Override
	public void connectFailed(URI uri, SocketAddress socketAddress, IOException exception) {
		if(uri == null || socketAddress == null || exception == null) {
			throw new IllegalArgumentException("Arguments can't be null.");
		}
		
		DefaultProxy defaultProxy = defaultProxies.get(socketAddress);
		if(defaultProxy != null) {
			if(defaultProxy.getFailedCount() >= 3) {
				// failed 3 times or more, just remove it
				defaultProxies.remove(socketAddress);
			}
		} else {
			// delegate to the default
			if(defaultProxySelector != null) {
				defaultProxySelector.connectFailed(uri, socketAddress, exception);
			}
		}
	}
	
	/**
	 * Child class that represents a Proxy and other required handling.
	 * 
	 * @author Rohtash Singh (rohtash.singh@gmail.com)
	 * @version 1.0.0
	 * @since May 27, 2015 1:22:23 PM
	 */
	private class DefaultProxy {
		private Proxy proxy;
		private SocketAddress socketAddress;
		// How many times this proxy failed?
		private int failedCount = 0;
		
		/**
		 * 
		 * @param socketAddress
		 */
		public DefaultProxy(InetSocketAddress socketAddress) {
			this.socketAddress = socketAddress;
			proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
		}
		
		/**
		 * Returns the socketAddress.
		 * 
		 * @return
		 */
		public SocketAddress getSocketAddress() {
			return socketAddress;
		}
		
		/**
		 * Returns the proxy.
		 * 
		 * @return
		 */
		public Proxy getProxy() {
			return proxy;
		}
		
		/**
		 * Returns the failed count (incremented by one) to check whether to use
		 * this proxy in future or not.
		 * 
		 * @return
		 */
		public int getFailedCount() {
			return (++failedCount);
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// register your proxy like this in your application.
		DefaultProxySelector proxySelector = new DefaultProxySelector(ProxySelector.getDefault());
		ProxySelector.setDefault(proxySelector);
		FileHelper.detectProxy();
	}
}
