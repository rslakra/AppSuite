/*
 * Created on Dec 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.rslakra.java.events.customizedevent;

import java.awt.AWTEvent;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimerEvent extends AWTEvent {
	//Timer Event Id
	private static final int TIMER_EVENT = AWTEvent.RESERVED_ID_MAX + 1601;
	
	public TimerEvent(TimerComponent timerComponent) {
		super(timerComponent, TIMER_EVENT);
	}
}
