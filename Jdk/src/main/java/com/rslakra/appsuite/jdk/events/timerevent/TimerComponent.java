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
package com.rslakra.appsuite.jdk.events.timerevent;

import java.awt.*;

/**
 * @author rohtash.singh Created on Dec 11, 2005
 * <p>
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
        while (true) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            TimerEvent timerEvent = new TimerEvent(this);
            //Post timer event to event queue.
            eventQueue.postEvent(timerEvent);
        }
    }

    public void processEveent(AWTEvent awtEvent) {
        if (awtEvent instanceof TimerEvent) {
            if (timerListner != null) {
                timerListner.timeElapsed((TimerEvent) awtEvent);
            }
        } else {
            super.processEvent(awtEvent);
        }
    }
}
