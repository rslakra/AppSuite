/*
 * Created on Dec 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.rslakra.java.events.customizedevent;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimerComponent extends Component implements Runnable {
	private int interval;
	private TimerListener timerListner;
	private static EventQueue eventQueue;
	
	public TimerComponent(int newInterval) {
		Thread thread = new Thread(this);
		thread.start();
		eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		enableEvents(0);
	}
	
	public void addTimerListener(TimerListener newTimerListener) {
		timerListner = newTimerListener;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(interval);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			TimerEvent timerEvent = new TimerEvent(this);
			//Post timer event to event queue.
			eventQueue.postEvent(timerEvent);
		}
	}
	
	public void processEveent(AWTEvent awtEvent) {
		if(awtEvent instanceof TimerEvent) {
			if(timerListner != null) {
				timerListner.timeElapsed((TimerEvent)awtEvent);
			}
		}else {
			super.processEvent(awtEvent);
		}
	}
}
