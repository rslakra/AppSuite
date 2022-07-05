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

/**
 * This <class>HappyObjectTest</class> class
 *
 *
 * Put it all together We now have a custom event, a custom event listener
 * interface, an event generator, and some listeners. Let's put it all together:
 *
 * 
 * @author Rohtash Singh
 * @version Feb 4, 2006
 */

public class TestHappyObject {
	
	/**
	 * This method creates a HappyObject instance and registers the listeners
	 * Sky and Birds then pinches and hugs Happy0bject.
	 */
	public static void main(String[] args) {
		
		// happy object
		HappyObject happy = new HappyObject();
		
		// listeners
		MoodListener sky = new SkyMoodListener();
		MoodListener birds = new BirdsMoodListener();
		
		// add listeners on happy object
		happy.addMoodListener(sky);
		happy.addMoodListener(birds);
		
		System.out.println("Let's pinch HappyObject and see reaction!");
		happy.receivePinch();
		System.out.println();
		
		System.out.println("Let's hug HappyObject and see reaction!");
		happy.receiveHug();
		System.out.println();
		
		System.out.println("Let's make HappyObject ANGRY and see reaction!");
		System.out.println();
		System.out.println("One Pinch!");
		happy.receivePinch();
		System.out.println();
		
		System.out.println("Second Pinch, Look out!");
		happy.receivePinch();
	}
	
}
