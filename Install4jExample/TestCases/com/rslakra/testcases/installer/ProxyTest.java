package com.rslakra.testcases.installer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.btr.proxy.search.ProxySearch;
import com.btr.proxy.search.ProxySearch.Strategy;
import com.btr.proxy.util.PlatformUtil;
import com.btr.proxy.util.PlatformUtil.Platform;

public class ProxyTest {
	
	private static final String DEFAULT_URL = "https://unreachable/";
	
	private static void setDefaultProxySelector() {
		ProxySearch proxySearch = new ProxySearch();
		proxySearch.setPacCacheSettings(32, 1000 * 60 * 5);
		if(PlatformUtil.getCurrentPlattform() == Platform.WIN) {
			proxySearch.addStrategy(Strategy.IE);
			proxySearch.addStrategy(Strategy.FIREFOX);
			proxySearch.addStrategy(Strategy.JAVA);
		} else if(PlatformUtil.getCurrentPlattform() == Platform.LINUX) {
			proxySearch.addStrategy(Strategy.GNOME);
			proxySearch.addStrategy(Strategy.KDE);
			proxySearch.addStrategy(Strategy.FIREFOX);
		} else {
			proxySearch.addStrategy(Strategy.OS_DEFAULT);
		}
		
		ProxySelector proxySelector = proxySearch.getProxySelector();
		System.out.println("proxySelector:" + proxySelector);
		ProxySelector.setDefault(proxySelector);
	}
	
	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		setDefaultProxySelector();
		List<Proxy> proxies = null;
		try {
			ProxySelector proxySelector = ProxySelector.getDefault();
			if(proxySelector != null) {
				proxies = proxySelector.select(new URI(DEFAULT_URL));
				Proxy proxy = (proxies == null ? Proxy.NO_PROXY : proxies.get(0));
				if(proxy == null) {
					proxy = Proxy.NO_PROXY;
				}
				System.out.println("proxy:" + proxy + ", proxy.type:" + proxy.type());
				InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
				if(proxyAddress == null) {
					System.out.println("No Proxy");
				} else {
					System.out.println("proxy hostname:" + proxyAddress.getHostName() + ", port:" + proxyAddress.getPort());
				}
				
				// System.out.println();
				// for (Proxy proxy : proxies) {
				// System.out.println("proxy:" + proxy + ", proxy.type:"
				// + proxy.type());
				// InetSocketAddress proxyAddress = (InetSocketAddress)
				// proxy
				// .address();
				// if (proxyAddress == null) {
				// System.out.println("No Proxy");
				// } else {
				// System.out.println("proxy hostname:"
				// + proxyAddress.getHostName() + ", port:"
				// + proxyAddress.getPort());
				// }
				// }
			}
		} catch(URISyntaxException e) {
			System.err.println(e);
		}
	}
	
}
