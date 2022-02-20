/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, 
 * 	  this list of conditions and the following disclaimer.
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

import com.devamatre.core.thread.ThreadPool;
import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

public class TestThreadPool {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogManager.configure("src/log4j.xml");
		final Logger log = LogManager.getLogger(TestThreadPool.class);
		
		TestThreadPool tPoolTest = new TestThreadPool();
		
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