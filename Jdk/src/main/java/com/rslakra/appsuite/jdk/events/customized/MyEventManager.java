/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk.events.customized;

import javax.swing.event.EventListenerList;

/**
 * This <class>MyEventManager</class> class manages the event registration and notification code to a class.
 *
 * @author Rohtash Singh
 * @version Feb 3, 2006
 */
public final class MyEventManager {

    // Create the listener list
    protected EventListenerList myEventListeners = new EventListenerList();

    public MyEventManager() {
        super();
    }

    // This methods allows classes to register for MyEvents
    public void addMyListener(MyListener listener) {
        myEventListeners.add(MyListener.class, listener);
    }

    // This methods allows classes to unregister for MyEvents
    public void removeMyListener(MyListener listener) {
        myEventListeners.remove(MyListener.class, listener);
    }

    // This private class is used to fire MyEvents
    void fireMyEvent(MyEvent evt) {
        Object[] listeners = myEventListeners.getListenerList();
        System.out.println("listeners.length : " + listeners.length);

        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == MyListener.class) {
                System.out.println("Calling method of : " + listeners[i].getClass());
                ((MyListener) listeners[i + 1]).myEventOccurred(evt);
            }
        }
    }

}
