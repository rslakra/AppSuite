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
package com.rslakra.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com) Created on Aug 9, 2009
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestTimer {
	private Timer timer;
	
	public TestTimer(int seconds) {
		timer = new Timer();
		timer.schedule(new UpdateTask(), seconds * 1000);
	}
	
	class UpdateTask extends TimerTask {
		public void run() {
			System.out.println("OK, It's time to do something!");
			timer.cancel(); // Terminate the thread
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Schedule something to do in 5 seconds.");
		new TestTimer(5);
		System.out.println("Waiting.");
		
		DateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
		Calendar c = Calendar.getInstance();
		// c.set(Calendar.HOUR, 24);
		c.set(Calendar.HOUR_OF_DAY, 15);
		c.set(Calendar.MINUTE, 41);
		c.set(Calendar.SECOND, 00);
		
		Date timeToRun = c.getTime();
		System.out.println("timeToRun=" + timeToRun);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println(System.currentTimeMillis());
			}
		}, 1000);
		
		c.set(Calendar.HOUR_OF_DAY, 14);
		c.set(Calendar.MINUTE, 41);
		c.set(Calendar.SECOND, 00);
		
		Date timeToRuns = c.getTime();
		System.out.println("timeToRuns=" + timeToRun);
		
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("timeToRuns");
				System.out.println(System.currentTimeMillis());
			}
		}, timeToRuns);
		
	}
}
