/**
 * DeviceHelper.js
 * 
 * @author Rohtash Singh
 */
var onlineStatus = "Online";
var clientType = "Browser";
var urlString = "";
var syncStatus = "Pending";
var defaultLocale = "en-us";

/**
 * Defines singleton class with static methods.
 * 
 * @author: Rohtash Singh Lakra
 */

var DeviceHelper = {
	// user agent
	getUserAgent : function() {
		return navigator.userAgent.toLowerCase();
	},

	isApp : function() {
		return (isDesktopClient || isSurfaceApp || isJUPEnabled);
	},

	isMobileApp : function() {
		return (isSurfaceApp || isJUPEnabled);
	},

	// desktop
	isDesktopClient : function() {
		return isDesktopClient;
	},

	isPhone : function() {
		return (DeviceHelper.getUserAgent().match(/Mobile/i) != null && !this
				.isIPad());
	},

	// ipad
	isIPad : function() {
		return (DeviceHelper.getUserAgent().match(/iPad/i) != null);
	},

	// ipod
	isIPod : function() {
		return (DeviceHelper.getUserAgent().match(/iPod/i) != null);
	},

	// iphone
	isIPhone : function() {
		return (DeviceHelper.getUserAgent().match(/iPhone/i) != null);
	},

	// standalone
	isStandAlone : function() {
		return (window.navigator.standalone != null && window.navigator.standalone === true);
	},

	// safari
	isSafari : function() {
		return (DeviceHelper.getUserAgent().match(/safari/) != null);
	},

	// ios
	isIOS : function() {
		return (DeviceHelper.isIPad() || DeviceHelper.isIPod() || MobileUtil
				.isIPhone());
	},

	// android
	isAndroid : function() {
		return (DeviceHelper.getUserAgent().match(/(android)/) != null);
	},

	// surface
	isWindows : function() {
		return (DeviceHelper.getUserAgent().match(/(windows)/i) != null
				&& MobileUtil.getUserAgent().match(/(touch)/i) != null && isSurfaceApp);
	},

	// mobile device (ipad/iphone/ipod/android etc.)
	isMobileClient : function() {
		return (DeviceHelper.isIOS() || DeviceHelper.isAndroid() || DeviceHelper
				.isSurface());
	},

	// web view
	isWebView : function() {
		return (DeviceHelper.isMobileClient() && (!DeviceHelper.isStandAlone() && !DeviceHelper
				.isSafari()));
	},

	/**
	 * Returns true if the value is either null, empty or 'undefined' otherwise
	 * false.
	 * 
	 * @param value
	 * @returns
	 */
	isNullOrEmpty : function(value) {
		return (value === undefined || value === null || value.length <= 0) ? true
				: false;
	},

	/**
	 * android Locale Language
	 */
	getAndroidLocaleLanguage : function() {
		return (DeviceHelper.isAndroid() && DeviceHelper.getUserAgent().match(
				/[a-z]{2}-[a-z]{2}/));
	},

	/**
	 * Shows a toast containing the message passed to the method in its message
	 * parameter.
	 * 
	 * @param message
	 */
	showAndroidToastMessage : function(message) {
		jsAndroidProxy.showMessage(message);
	},
	
	getOnlineStatus()
	{
		return onlineStatus;
	},
	setOnlineStatus : function(value) {
		console.log("onlineStatus:", value);
		onlineStatus = value;
	},
	
	isJupOffline : function() {
		return isJUPOffline;
	},

	setOnlineStatus : function(value) {
		console.log("Setting Jup status:", value);
		isJUPOffline = value;
	},

	isPortret : function() {
		return window.innerHeight > window.innerWidth;
	},

	getHeight : function() {
		var htt = 764;
		try {
			var tmpHtt = parseInt(window.innerHeight);
			htt = Math.round((tmpHtt * 97) / 100) + 1;
		} catch (err) {

		}
		return htt;
	},

	getWidth : function() {
		var wid = 1024;
		try {
			var tmpWtt = parseInt(window.innerWidth);
			wid = Math.round((tmpWtt * 96) / 100) + 4;
		} catch (err) {

		}
		return wid;
	},

	getLocale : function() {
		return bLocale;
	},

	setLocale : function(value) {
		bLocale = value;
	}

}

function showAlert() {
	if (DeviceHelper.isIPad()) {
		alert('iPad');
	}

	if (DeviceHelper.isIPod()) {
		alert('iPod');
	}

	if (DeviceHelper.isIPhone()) {
		alert('iPhone');
	}

	if (DeviceHelper.isStandAlone()) {
		alert('StandAlone');
	}

	if (DeviceHelper.isSafari()) {
		alert('Safari');
	}

	if (DeviceHelper.isAndroid()) {
		alert('Android');
	}

	if (DeviceHelper.isIOS()) {
		alert('iOS');
	}

	if (DeviceHelper.isWebView()) {
		alert('You are using a WebView');
	}
}
