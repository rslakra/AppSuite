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
package com.rslakra.java.thread;

import java.util.LinkedList;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * 
 * TODO: Enter description here ...
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 * @since 1.0
 * 
 */
public class ThreadPool extends ThreadGroup {

	private final static Logger log = LogManager.getLogger(ThreadPool.class);

	private final int noOfThreads;
	private final WorkerThread[] workerThreads;
	private final LinkedList<Runnable> queue;

	public ThreadPool(int noOfThreads) {
		this("ThreadPool", noOfThreads);
	}

	public ThreadPool(String name, int noOfThreads) {
		super(name);
		log.debug("+ThreadPool(" + name + ", " + noOfThreads + ")");
		/* make sure that it�s at least one */
		this.noOfThreads = Math.max(1, noOfThreads);
		this.queue = new LinkedList<Runnable>();
		this.workerThreads = new WorkerThread[noOfThreads];

		/* start all thread. */
		for (int i = 0; i < noOfThreads; i++) {
			workerThreads[i] = new WorkerThread();
			workerThreads[i].setName(getName() + "-" + (i + 1));
			workerThreads[i].start();
		}
		log.debug("-ThreadPool()");
	}

	/*
	 * 
	 */
	public void execute(Runnable runnable) {
		log.debug("+execute(" + runnable + ")");
		synchronized (queue) {
			queue.addLast(runnable);
			/* don't need to use notifyAll here as notified at the same time. */
			queue.notify();
		}
		log.debug("-execute()");
	}

	public int getNoOfThreads() {
		return noOfThreads;
	}

	/**
	 * 
	 * TODO: Enter description here ...
	 * 
	 * @author Rohtash Singh (rohtash.singh@gmail.com)
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
							log.info("waiting ...");
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
						log.info("Running ...");
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