package com.rslakra.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.install4j.api.launcher.StartupNotification;

/**
 * This class allows you to register a listener to receive startup events in
 * single instance mode on Microsoft Windows and file open events on Mac OS X.
 * 
 * The documentation also says:
 * 
 * For multiple files, files are surrounded by double-quotes and separated by
 * spaces.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 * @reference: http://resources.ej-technologies.com/install4j/help/api/com/
 *             install4j /api/launcher/StartupNotification.html
 */
public final class StartupNotificationListener implements StartupNotification.Listener {
	
	private static Logger log = LogManager.getLogger(StartupNotificationListener.class);
	
	/**
	 * startupPerformed
	 */
	public void startupPerformed(String parameters) {
		log.debug("parameters:" + parameters);
	}
}
