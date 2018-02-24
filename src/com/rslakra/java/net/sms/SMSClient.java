///******************************************************************************
// * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
// * 
// * This code is licensed to Devamatre under one or more contributor license 
// * agreements. The reproduction, transmission or use of this code, in source 
// * and binary forms, with or without modification, are permitted provided 
// * that the following conditions are met:
// * 1. Redistributions of source code must retain the above copyright
// * 	  notice, this list of conditions and the following disclaimer.
// * 2. Redistributions in binary form must reproduce the above copyright
// *    notice, this list of conditions and the following disclaimer in the
// *    documentation and/or other materials provided with the distribution.
// * 
// * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
// * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// * SUCH DAMAGE.
// *      
// * Devamatre reserves the right to modify the technical specifications and or 
// * features without any prior notice.
// *****************************************************************************/
//package com.rslakra.java.net.sms;
//
//public class SMSClient implements Runnable, SMSConstants {
//
//	private Thread myThread = null;
//
//	private int mode = -1;
//	private String recipient = null;
//	private String message = null;
//
//	private int status = -1;
//	private long messageNo = -1;
//
//	public SMSClient(int mode) {
//		this.mode = mode;
//	}
//
//	public int sendMessage(String recipient, String message) {
//		this.recipient = recipient;
//		this.message = message;
//		System.out.println("recipient: " + recipient + " message: " + message);
//		myThread = new Thread(this);
//		myThread.start();
//		// run();
//		return status;
//	}
//
//	public void run() {
//
//		Sender aSender = new Sender(recipient, message);
//		try {
//			// send message
//			aSender.send();
//
//			System.out.println("sending ... ");
//
//			// in SYNCHRONOUS mode wait for return : 0 for OK, -2 for timeout,
//			// -1 for other errors
//			if (MODE_SYNCHRONOUS == mode) {
//				while (aSender.getStatus() == -1) {
//					myThread.sleep(1000);
//				}
//			}
//			if (aSender.getStatus() == 0) {
//				messageNo = aSender.getMessageNo();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		this.status = aSender.getStatus();
//		aSender = null;
//	}
//}