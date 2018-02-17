/**
 * 
 */
package com.rslakra.java;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Rohtash Singh Lakra
 * @date   02/08/2018 02:00:13 PM
 */
public class TimerExample {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timer.cancel();
			}
		}, 100 * 60);
		System.out.println("Timer Started.");
	}
	
}
