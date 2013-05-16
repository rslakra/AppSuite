/**
 * Copyright (c) 2009 Emerging Health IT. All rights reserved.
 * 
 * @author Rohtash Singh (rosingh@emerginghealthit.com)
 * @version 1.0
 */
package com.devamatre.testcases.core.quartz;

import com.devamatre.core.quartz.ThreadPool;
import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		/* logger */
		final Logger log = LogManager.getLogger(ThreadPoolTest.class);
		ThreadPoolTest tPoolTest = new ThreadPoolTest();

		ThreadPool tPool = new ThreadPool(5);
		log.debug("ThreadPool Test Started!");
		Runnable one = tPoolTest.createRunnable(log, "One", 2500);
		tPool.execute(one);

		Runnable two = tPoolTest.createRunnable(log, "Two", 1000);
		tPool.execute(two);

		Runnable three = tPoolTest.createRunnable(log, "Three", 1500);
		tPool.execute(three);

		Runnable four = tPoolTest.createRunnable(log, "Four", 3000);
		tPool.execute(four);

		Runnable five = tPoolTest.createRunnable(log, "Five", 800);
		tPool.execute(five);

		Runnable six = tPoolTest.createRunnable(log, "Six", 2000);
		tPool.execute(six);
		log.debug("ThreadPool Test Finished!");
	}

	public Runnable createRunnable(final Logger log, final String name,
			final long delay) {
		return new Runnable() {
			public void run() {
				try {
					log.debug("[" + name + "] starting up.");
					Thread.sleep(delay);
					log.debug("[" + name + "] processsing ...");
					Thread.sleep(2000);
					log.debug("[" + name + "] leaving.");
				} catch (InterruptedException ie) {
					log.error("[" + name + "] interrupted!");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			public String toString() {
				return name;
			}
		};
	}
}