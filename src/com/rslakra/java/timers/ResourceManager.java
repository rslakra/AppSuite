/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
package com.rslakra.java.timers;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

public final class ResourceManager {

	/* logger */
	private static Logger logger = LogManager.getLogger(ResourceManager.class);

	/* NO_OF_THREADS */
	private static final int NO_OF_THREADS = 5;

	/* singleton instance */
	private static ResourceManager instance;

	/* threadPool */
	private ThreadPoolExecutor tPoolExecutor;

	/**
	 * 
	 * @param noOfThreads
	 */
	private ResourceManager(int noOfThreads) {
		logger.debug("+ResourceManager(" + noOfThreads + ")");
		tPoolExecutor = new ThreadPoolExecutor(noOfThreads, noOfThreads * 2, 50000L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		logger.debug("-ResourceManager()");
	}

	/**
	 * Single entry point.
	 * 
	 * @return
	 */
	public synchronized static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager(NO_OF_THREADS);
		}
		return instance;
	}

	/**
	 * 
	 * @param runnable
	 */
	public void execute(Runnable runnable) {
		logger.debug("+execute(" + runnable + ")");
		tPoolExecutor.execute(runnable);
		logger.debug("-execute()");
	}

	/**
	 * 
	 */
	public void stop() {
		logger.debug("+stop()");
		tPoolExecutor.shutdown();
		logger.debug("-stop()");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);

		ResourceManager resManager = new ResourceManager(5);

		logger.debug("ThreadPool Test Started!");
		Runnable one = resManager.createRunnable(logger, "One", 2500);
		resManager.execute(one);

		Runnable two = resManager.createRunnable(logger, "Two", 1000);
		resManager.execute(two);

		Runnable three = resManager.createRunnable(logger, "Three", 1500);
		resManager.execute(three);

		Runnable four = resManager.createRunnable(logger, "Four", 3000);
		resManager.execute(four);

		Runnable five = resManager.createRunnable(logger, "Five", 800);
		resManager.execute(five);

		Runnable six = resManager.createRunnable(logger, "Six", 2000);
		resManager.execute(six);

		resManager.stop();
		logger.debug("ThreadPool Test Finished!");
	}

	/**
	 * 
	 * @param log
	 * @param name
	 * @param delay
	 * @return
	 */
	public Runnable createRunnable(final Logger log, final String name, final long delay) {
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