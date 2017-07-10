package com.rslakra;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.install4j.api.launcher.StartupNotification;
import com.rslakra.services.StartupNotificationListener;

public class TestApp {

	private static Logger log = LogManager.getLogger(TestApp.class);

	/**
	 * Starting point of an application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/* Configure log4j.xml configuration file */
			DOMConfigurator.configure("log4j.xml");
			log.debug("args:" + args);
			log.debug("Java Version:" + System.getProperty("java.version"));
			/*
			 * Register a listener to receive startup events in single instance
			 * mode on Microsoft Windows or to receive file open events on OS X.
			 * Request that are already queued may be invoked immediately on the
			 * startup listener before this call returns. Subsequent calls will
			 * be from different threads.
			 */
			StartupNotification
					.registerStartupListener(new StartupNotificationListener());

			List<String> arguments = new ArrayList<String>();
			if (args != null) {
				for (String arg : args) {
					arguments.add(arg);
				}
			}

			log.debug("arguments:" + arguments);
			MyFrame myFrame = new MyFrame(arguments);
			myFrame.setVisible(true);
		} catch (Exception ex) {
			log.error(ex);
		}
	}
}
