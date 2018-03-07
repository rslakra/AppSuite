/*
 * Created on Dec 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.rslakra.java.events.customizedevent;

import java.util.EventListener;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TimerListener extends EventListener {

	public void timeElapsed(TimerEvent timerEvent);
	
}
