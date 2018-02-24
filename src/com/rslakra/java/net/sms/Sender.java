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
//import java.util.Date;
//
//public class Sender implements Runnable, SMSConstants {
//
//	private SerialConnection serialConn = null;
//
//	String in, out;
//	private Thread aThread = null;
//	private long delay = STANDARD;
//	String recipient = null;
//	String message = null;
//
//	private String csca = "+916596845999"; // the message center
//	private final SerialParameters DEFAULT_PARAMETERS = new SerialParameters(PORT_COM2, 9600, 0, 0, 8, 1, 0);
//	private int step;
//	private int status = -1;
//	private long messageNo = -1;
//
//	public Sender(String recipient, String message) {
//		this.recipient = recipient;
//		this.message = message;
//	}
//
//	public int getStep() {
//		return step;
//	}
//
//	public void setStep(int step) {
//		this.step = step;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public long getMessageNo() {
//		return messageNo;
//	}
//
//	public void setMessageNo(long messageNo) {
//		this.messageNo = messageNo;
//	}
//
//	/**
//	 * connect to the port and start the dialogue thread
//	 */
//	public int send() throws Exception {
//		// SerialParameters params = defaultParameters;
//		serialConn = new SerialConnection(DEFAULT_PARAMETERS);
//		serialConn.openConnection();
//		aThread = new Thread(this);
//		aThread.start();
//		// log("start");
//		return 0;
//	}
//
//	/**
//	 * implement the dialogue thread, message / response via steps, handle time
//	 * out
//	 */
//
//	public void run() {
//
//		boolean timeOut = false;
//		long startTime = (new Date()).getTime();
//
//		while ((step < 7) && (!timeOut)) {
//			// log(""+((new Date()).getTime() - startTime);
//			// check where we are in specified delay
//			timeOut = ((new Date()).getTime() - startTime) > delay;
//
//			// if atz does not work, type to send cntrlZ and retry, in case a
//			// message was stuck
//			if (timeOut && (step == 1)) {
//				step = -1;
//				serialConn.send("" + CTRL_Z);
//			}
//
//			// read incoming string
//			String result = serialConn.getIncommingString();
//
//			log("<- " + result + "\n--------");
//			int expectedResult = -1;
//
//			try {
//				log("Step:" + step);
//
//				switch (step) {
//				case 0:
//
//					serialConn.send(MSG_TYPE_ATZ);
//					delay = LONG;
//					startTime = (new Date()).getTime();
//					break;
//
//				case 1:
//					delay = STANDARD;
//					serialConn.send(MSG_TYPE_ATH0);
//					startTime = (new Date()).getTime();
//					break;
//				case 2:
//					expectedResult = result.indexOf(STATUS_OK);
//					log("received ok =" + expectedResult);
//					if (expectedResult > -1) {
//						serialConn.send(MSG_TYPE_AT_CMGF);
//						startTime = (new Date()).getTime();
//					} else {
//						step = step - 1;
//					}
//					break;
//				case 3:
//					expectedResult = result.indexOf(STATUS_OK);
//					log("received ok =" + expectedResult);
//					if (expectedResult > -1) {
//						serialConn.send(MSG_TYPE_AT_CSCA + csca + "\"");
//						startTime = (new Date()).getTime();
//					} else {
//						step = step - 1;
//					}
//
//					break;
//				case 4:
//					expectedResult = result.indexOf(STATUS_OK);
//					log("received ok =" + expectedResult);
//					if (expectedResult > -1) {
//						serialConn.send(MSG_TYPE_AT_CMGS + recipient + "\"");
//						startTime = (new Date()).getTime();
//					} else {
//						step = step - 1;
//					}
//
//					break;
//				case 5:
//					expectedResult = result.indexOf(">");
//
//					log("received ok =" + expectedResult);
//					if (expectedResult > -1) {
//						serialConn.send(message + CTRL_Z);
//						startTime = (new Date()).getTime();
//					} else {
//						step = step - 1;
//					}
//					delay = VERYLONG;// waitning for message ack
//
//					break;
//
//				case 6:
//					expectedResult = result.indexOf(STATUS_OK);
//					// read message number
//					if (expectedResult > -1) {
//						int n = result.indexOf(MSG_TYPE_CMGS);
//						result = result.substring(n + 5);
//						n = result.indexOf("\n");
//						status = 0;
//						messageNo = Long.parseLong(result.substring(0, n).trim());
//						log("sent message no:" + messageNo);
//
//					} else {
//						step = step - 1;
//					}
//
//					break;
//				}
//				step = step + 1;
//				aThread.sleep(100);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		serialConn.closeConnection();
//		// if timed out set status
//		if (timeOut) {
//			status = -2;
//			log("*** time out at step " + step + "***");
//		}
//	}
//
//	/**
//	 * logging function, includes date and class name
//	 */
//	private void log(String s) {
//		System.out.println(new java.util.Date() + ":" + this.getClass().getName() + ":" + s);
//	}
//}