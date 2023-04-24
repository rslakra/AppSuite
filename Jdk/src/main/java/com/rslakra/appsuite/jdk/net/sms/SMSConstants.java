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
package com.rslakra.appsuite.jdk.net.sms;

public interface SMSConstants {

    /**
     * Delay Details.
     */
    long STANDARD = 500;
    long LONG = 2000;
    long VERYLONG = 20000;

    /* Control + Z code. */
    char CTRL_Z = (char) 26;

    // com port2
    String PORT_COM2 = "COM2";

    /* Message Types */
    String MSG_TYPE_ATZ = "atz";
    String MSG_TYPE_ATH0 = "ath0";
    String MSG_TYPE_AT_CMGF = "at+cmgf=1";
    String MSG_TYPE_AT_CSCA = "at+csca=\"";
    String MSG_TYPE_AT_CMGS = "at+cmgs=\"";
    String MSG_TYPE_CMGS = "CMGS:";

    // STATUS
    String STATUS_OK = "OK";

    // Communication Mode
    int MODE_SYNCHRONOUS = 0;
    int MODE_ASYNCHRONOUS = 1;

    /**
     * Serial port data bits
     */
    int SERIAL_PORT_DATABITS_5 = 5;
    int SERIAL_PORT_DATABITS_6 = 6;
    int SERIAL_PORT_DATABITS_7 = 7;
    int SERIAL_PORT_DATABITS_8 = 8;

    /**
     * Serial port stop bits
     */
    int SERIAL_PORT_STOPBITS_1 = 1;
    int SERIAL_PORT_STOPBITS_1_5 = 15;
    int SERIAL_PORT_STOPBITS_2 = 2;

    /**
     * Serial port parity bits
     */
    int SERIAL_PORT_PARITY_NONE = 0;
    int SERIAL_PORT_PARITY_ODD = 1;
    int SERIAL_PORT_PARITY_EVEN = 2;

    String NONE = "None";
    String PARITY_ODD = "Odd";
    String PARITY_EVEN = "Even";

    /**
     * Serial port flow control
     */
    int SERIAL_PORT_FLOWCONTROL_NONE = 0;
    int SERIAL_PORT_FLOWCONTROL_XONXOFF_IN = 1;
    int SERIAL_PORT_FLOWCONTROL_XONXOFF_OUT = 2;
    int SERIAL_PORT_FLOWCONTROL_RTSCTS_IN = 3;
    int SERIAL_PORT_FLOWCONTROL_RTSCTS_OUT = 4;

    String XONXOFF_OUT = "Xon/Xoff Out";
    String XONXOFF_IN = "Xon/Xoff In";
    String RTSCTS_IN = "RTS/CTS In";
    String RTSCTS_OUT = "RTS/CTS Out";

}
