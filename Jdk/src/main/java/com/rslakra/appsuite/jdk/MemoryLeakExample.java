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
package com.rslakra.appsuite.jdk;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class MemoryLeakExample {
    static Vector myVector = new Vector();
    static HashSet pendingRequests = new HashSet();

    /**
     * numFmt
     */
    static NumberFormat numberFormat;

    static {
        numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);
    }

    public void slowlyLeakingVector(int iter, int count) {
        // System.out.println("+slowlyLeakingVector()");
        for (int i = 0; i < iter; i++) {
            for (int n = 0; n < count; n++) {
                myVector.add(Integer.toString(n + i));
            }
            for (int n = count - 1; n > 0; n--) {
                // Oops, it should be n>=0
                myVector.removeElementAt(n);
            }
        }
        // System.out.println("-slowlyLeakingVector()");
    }

    public void leakingRequestLog(int iter) {
        // System.out.println("+leakingRequestLog()");
        Random requestQueue = new Random();
        for (int i = 0; i < iter; i++) {
            int newRequest = requestQueue.nextInt();
            pendingRequests.add(new Integer(newRequest));
            // processed request, but forgot to remove it
            // from pending requests
        }
        // System.out.println("-leakingRequestLog()");
    }

    public void noLeak(int size) {
        // System.out.println("+noLeak()");
        HashSet tmpStore = new HashSet();
        for (int i = 0; i < size; ++i) {
            String leakingUnit = new String("Object: " + i);
            tmpStore.add(leakingUnit);
        }
        // Though highest memory allocation happens in this
        // function, but all these objects get garbage
        // collected at the end of this method, so no leak.
        // System.out.println("-noLeak()");
    }

    public static void logMemUsage() {
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        long availMemory = rt.freeMemory();
        long usedMemory = totalMemory - availMemory;
        StringBuffer msg = new StringBuffer("Total Memory: ");

        msg.append(numberFormat.format(totalMemory));
        msg.append(", Used Memory: ");
        msg.append(numberFormat.format(usedMemory));
        msg.append(", Avail Memory: ");
        msg.append(numberFormat.format(availMemory));
        System.out.println(msg.toString());
    }

    public static void main(String[] args) throws IOException {
        MemoryLeakExample javaLeaks = new MemoryLeakExample();
        logMemUsage();
        for (int i = 0; true; i++) {
            try { // sleep to slow down leaking process
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                /* do nothing */
            }
            System.out.println("Iteration: " + i);
            logMemUsage();
            javaLeaks.slowlyLeakingVector(1000, 10);
            // logMemUsage();
            // javaLeaks.leakingRequestLog(5000);
            // logMemUsage();
            // javaLeaks.noLeak(100000);
            logMemUsage();
        }
    }
}
