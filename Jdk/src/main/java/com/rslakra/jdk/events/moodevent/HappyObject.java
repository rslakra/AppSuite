/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
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
package com.rslakra.jdk.events.moodevent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This <class>HappyObject</class> class, the Object became either
 * Happy, Annnoyed, or Angry based on receiving hugs or pinches.
 * The Object will change how we learn about MrHappyObject's mood.
 *
 * Instead of calling a method, we'll register listeners with
 * HappyObject. Whenever he changes his mood, HappyObject will send
 * an event to his listeners.
 *
 *
 * @author Rohtash Singh
 * @version Feb 4, 2006
 */
public class HappyObject {
	
	// mood
	private Mood mood = Mood.HAPPY;
	
	// moodListeners
	List<MoodListener> moodListeners = new ArrayList<MoodListener>();
	
	// Constructor
	public HappyObject() {
		
	}
	
	/**
	 * This method can register listener which is interested in events.
	 *
	 * @param listener
	 *            interested in events.
	 */
	public synchronized void addMoodListener(MoodListener listener) {
		moodListeners.add(listener);
	}
	
	/**
	 * This method can un-register listener which is not interested in events.
	 *
	 * @param listener
	 *            not interested in events.
	 */
	public synchronized void removeMoodListener(MoodListener listener) {
		moodListeners.remove(listener);
	}
	
	/**
	 * This method simply creates an event indicating the mood, then
	 * loops through each listener calling moodChanged(). HappyObject
	 * calls fireMoodEvent() whenever his mood changes in the
	 * receivePinch() and receiveHug() methods.
	 */
	private synchronized void fireMoodEvent() {
		MoodEvent moodEvent = new MoodEvent(this, mood);
		
		// fire mood change event on all listeners
		for (Iterator<MoodListener> iter = moodListeners.iterator(); iter.hasNext();) {
			MoodListener listener = iter.next();
			listener.moodChanged(moodEvent);
		}
	}
	
	/**
	 * This method pinches the HappyObject and changes its mood.
	 */
	public synchronized void receivePinch() {
		
		if (mood == Mood.HAPPY) {
			mood = Mood.ANNOYED;
		} else {
			mood = Mood.ANGRY;
		}
		
		// fire mood change event
		fireMoodEvent();
	}
	
	/**
	 * This method hugs the HappyObject and changes its mood.
	 */
	public synchronized void receiveHug() {
		
		if (mood == Mood.ANNOYED) {
			mood = Mood.ANGRY;
		} else {
			mood = Mood.HAPPY;
		}
		
		// fire mood change event
		fireMoodEvent();
		
	}
	
}
