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

public class SyncTest {
    public static int ctr = 1;
    public static int newCtr = 1;

    public void exec() {
        System.out.println("+exec() in Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
        while (ctr != 0) {
            ctr++;
            if (ctr == (newCtr * 10000)) {
                newCtr++;
                System.out.println("Waiting Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException ie) {

                }
            }

            if (ctr == 50000) {
                newCtr = 1;
                ctr = 1;
                System.out.println("Loop Ending...");
                break;
            }
        }
        System.out.println("-exec() in Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
    }

    static class ThreadA extends Thread {
        SyncTest syncTest;

        public ThreadA(SyncTest syncTest) {
            super("ThreadA");
            this.syncTest = syncTest;
        }

        public void run() {
            System.out.println("Start [" + Thread.currentThread().getName() + "]");
            while (true) {
                if (ctr == 50000) {
                    newCtr = 1;
                    ctr = 1;
                    System.out.println("Ending Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
                    break;
                }
                syncTest.exec();
                System.out.println("Waiting Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
            }
            System.out.println("End [" + Thread.currentThread().getName() + "]");
        }
    }

    static class ThreadB extends Thread {
        SyncTest syncTest;

        public ThreadB(SyncTest syncTest) {
            super("ThreadB");
            this.syncTest = syncTest;
        }

        public void run() {
            System.out.println("Start [" + Thread.currentThread().getName() + "]");
            while (true) {
                if (ctr == 50000) {
                    newCtr = 1;
                    ctr = 1;
                    System.out.println("Ending Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
                    break;
                }
                syncTest.exec();
                System.out.println("Waiting Thread [" + Thread.currentThread().getName() + "], ctr : " + ctr);
            }
            System.out.println("End [" + Thread.currentThread().getName() + "]");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        ThreadA threadA = new ThreadA(syncTest);
        ThreadB threadB = new ThreadB(syncTest);
        System.out.println("String Threads");
        threadA.start();
        threadB.start();
        syncTest.exec();
        System.out.println("Ending Main Threads");
    }
}
