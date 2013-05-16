/**
 * Copyright (c) 2009 Emerging Health IT. All rights reserved.
 * 
 * @author Rohtash Singh (rosingh@emerginghealthit.com)
 * @version 1.0
 */
package com.devamatre.core.quartz;

import java.util.LinkedList;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * 
 * TODO: Enter description here ...
 * 
 * @author Rohtash Singh (rosingh@emerginghealthit.com)
 * @version 1.0
 * @since 1.0
 * 
 */
public class ThreadPool extends ThreadGroup {

	/* logger */
	private final Logger logger = LogManager.getLogger(ThreadPool.class);

	private final int noOfThreads;
	private final WorkerThread[] workerThreads;
	private final LinkedList<Runnable> queue;

	/**
	 * 
	 * @param noOfThreads
	 */
	public ThreadPool(int noOfThreads) {
		this("ThreadPool", noOfThreads);
	}

	/**
	 * 
	 * @param name
	 * @param noOfThreads
	 */
	public ThreadPool(String name, int noOfThreads) {
		super(name);
		logger.debug("+ThreadPool(" + name + ", " + noOfThreads + ")");
		/* make sure that it’s at least one */
		this.noOfThreads = Math.max(1, noOfThreads);
		this.queue = new LinkedList<Runnable>();
		this.workerThreads = new WorkerThread[noOfThreads];

		/* start all thread. */
		for (int i = 0; i < noOfThreads; i++) {
			workerThreads[i] = new WorkerThread();
			workerThreads[i].setName(getName() + "-" + (i + 1));
			logger.debug("Starting [" + workerThreads[i].getName()
					+ "] thread.");
			workerThreads[i].start();
		}
		logger.debug("-ThreadPool()");
	}

	/*
	 * 
	 */
	public void execute(Runnable runnable) {
		logger.debug("+execute(" + runnable + ")");
		synchronized (queue) {
			queue.addLast(runnable);
			/* don't need to use notifyAll here as notified at the same time. */
			queue.notify();
		}
		logger.debug("-execute()");
	}

	/**
	 * 
	 * @return
	 */
	public int getNoOfThreads() {
		return noOfThreads;
	}

	/**
	 * 
	 * TODO: Enter description here ...
	 * 
	 * @author Rohtash Singh (rosingh@emerginghealthit.com)
	 * @version 1.0
	 * @since 1.0
	 */
	private class WorkerThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			Runnable runnable = null;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							logger.info("waiting ...");
							queue.wait();
						} catch (InterruptedException ie) {
							/* ignore exception */
						}
					}
					runnable = queue.removeFirst();
				}

				/*
				 * The thread's pool could leak threads, if we don't catch
				 * exception.
				 */
				try {
					if (runnable != null) {
						logger.info("Running ...");
						runnable.run();
					}
				} catch (RuntimeException re) {
					/* You might want to log something here */
					re.printStackTrace();
				}
			}
		}
	}
}