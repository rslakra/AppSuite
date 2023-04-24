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

/**
 * A class that stores parameters for serial ports.
 */
public class SerialParameters implements SMSConstants {

    private String portName;
    private int baudRate;
    private int flowControlIn;
    private int flowControlOut;
    private int databits;
    private int stopbits;
    private int parity;

    /**
     * Default constructor. Sets parameters to no port, 9600 baud, no flow
     * control, 8 data bits, 1 stop bit, no parity.
     */
    public SerialParameters() {
        this("", 9600, SERIAL_PORT_FLOWCONTROL_NONE, SERIAL_PORT_FLOWCONTROL_NONE, SERIAL_PORT_DATABITS_8,
                SERIAL_PORT_STOPBITS_1, SERIAL_PORT_PARITY_NONE);
    }

    /**
     * Parameterized constructor.
     *
     * @param portName       The name of the port.
     * @param baudRate       The baud rate.
     * @param flowControlIn  Type of flow control for receiving.
     * @param flowControlOut Type of flow control for sending.
     * @param databits       The number of data bits.
     * @param stopbits       The number of stop bits.
     * @param parity         The type of parity.
     */
    public SerialParameters(String portName, int baudRate, int flowControlIn, int flowControlOut, int databits,
                            int stopbits, int parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.flowControlIn = flowControlIn;
        this.flowControlOut = flowControlOut;
        this.databits = databits;
        this.stopbits = stopbits;
        this.parity = parity;
    }

    /**
     * Sets port name.
     *
     * @param portName New port name.
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * Gets port name.
     *
     * @return Current port name.
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Sets baud rate.
     *
     * @param baudRate New baud rate.
     */
    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    /**
     * Sets baud rate.
     *
     * @param baudRate New baud rate.
     */
    public void setBaudRate(String baudRate) {
        this.baudRate = Integer.parseInt(baudRate);
    }

    /**
     * Gets baud rate as an <code>int</code>.
     *
     * @return Current baud rate.
     */
    public int getBaudRate() {
        return baudRate;
    }

    /**
     * Gets baud rate as a <code>String</code>.
     *
     * @return Current baud rate.
     */
    public String getBaudRateString() {
        return Integer.toString(baudRate);
    }

    /**
     * Sets flow control for reading.
     *
     * @param flowControlIn New flow control for reading type.
     */
    public void setFlowControlIn(int flowControlIn) {
        this.flowControlIn = flowControlIn;
    }

    /**
     * Sets flow control for reading.
     *
     * @param flowControlIn New flow control for reading type.
     */
    public void setFlowControlIn(String flowControlIn) {
        this.flowControlIn = stringToFlow(flowControlIn);
    }

    /**
     * Gets flow control for reading as an <code>int</code>.
     *
     * @return Current flow control type.
     */
    public int getFlowControlIn() {
        return flowControlIn;
    }

    /**
     * Gets flow control for reading as a <code>String</code>.
     *
     * @return Current flow control type.
     */
    public String getFlowControlInString() {
        return flowToString(flowControlIn);
    }

    /**
     * Sets flow control for writing.
     *
     * @param flowControlIn New flow control for writing type.
     */
    public void setFlowControlOut(int flowControlOut) {
        this.flowControlOut = flowControlOut;
    }

    /**
     * Sets flow control for writing.
     *
     * @param flowControlIn New flow control for writing type.
     */
    public void setFlowControlOut(String flowControlOut) {
        this.flowControlOut = stringToFlow(flowControlOut);
    }

    /**
     * Gets flow control for writing as an <code>int</code>.
     *
     * @return Current flow control type.
     */
    public int getFlowControlOut() {
        return flowControlOut;
    }

    /**
     * Gets flow control for writing as a <code>String</code>.
     *
     * @return Current flow control type.
     */
    public String getFlowControlOutString() {
        return flowToString(flowControlOut);
    }

    /**
     * Sets data bits.
     *
     * @param databits New data bits setting.
     */
    public void setDatabits(int databits) {
        this.databits = databits;
    }

    /**
     * Sets data bits.
     *
     * @param databits New data bits setting.
     */
    public void setDatabits(String databits) {
        if (databits.equals("5")) {
            this.databits = SERIAL_PORT_DATABITS_5;
        }
        if (databits.equals("6")) {
            this.databits = SERIAL_PORT_DATABITS_6;
        }
        if (databits.equals("7")) {
            this.databits = SERIAL_PORT_DATABITS_7;
        }
        if (databits.equals("8")) {
            this.databits = SERIAL_PORT_DATABITS_8;
        }
    }

    /**
     * Gets data bits as an <code>int</code>.
     *
     * @return Current data bits setting.
     */
    public int getDatabits() {
        return databits;
    }

    /**
     * Gets data bits as a <code>String</code>.
     *
     * @return Current data bits setting.
     */
    public String getDatabitsString() {
        switch (databits) {
            case SERIAL_PORT_DATABITS_5:
                return "5";
            case SERIAL_PORT_DATABITS_6:
                return "6";
            case SERIAL_PORT_DATABITS_7:
                return "7";
            case SERIAL_PORT_DATABITS_8:
                return "8";
            default:
                return "8";
        }
    }

    /**
     * Sets stop bits.
     *
     * @param stopbits New stop bits setting.
     */
    public void setStopbits(int stopbits) {
        this.stopbits = stopbits;
    }

    /**
     * Sets stop bits.
     *
     * @param stopbits New stop bits setting.
     */
    public void setStopbits(String stopbits) {
        if (stopbits.equals("1")) {
            this.stopbits = SERIAL_PORT_STOPBITS_1;
        }
        if (stopbits.equals("1.5")) {
            this.stopbits = SERIAL_PORT_STOPBITS_1_5;
        }
        if (stopbits.equals("2")) {
            this.stopbits = SERIAL_PORT_STOPBITS_2;
        }
    }

    /**
     * Gets stop bits setting as an <code>int</code>.
     *
     * @return Current stop bits setting.
     */
    public float getStopbits() {
        return stopbits;
    }

    /**
     * Gets stop bits setting as a <code>String</code>.
     *
     * @return Current stop bits setting.
     */
    public String getStopbitsString() {
        switch (stopbits) {
            case SERIAL_PORT_STOPBITS_1:
                return "1";
            case SERIAL_PORT_STOPBITS_1_5:
                return "1.5";
            case SERIAL_PORT_STOPBITS_2:
                return "2";
            default:
                return "1";
        }
    }

    /**
     * Sets parity setting.
     *
     * @param parity New parity setting.
     */
    public void setParity(int parity) {
        this.parity = parity;
    }

    /**
     * Sets parity setting.
     *
     * @param parity New parity setting.
     */
    public void setParity(String parity) {
        if (NONE.equals(parity)) {
            this.parity = SERIAL_PORT_PARITY_NONE;
        } else if (PARITY_EVEN.equals(parity)) {
            this.parity = SERIAL_PORT_PARITY_EVEN;
        } else if (PARITY_ODD.equals(parity)) {
            this.parity = SERIAL_PORT_PARITY_ODD;
        }
    }

    /**
     * Gets parity setting as an <code>int</code>.
     *
     * @return Current parity setting.
     */
    public int getParity() {
        return parity;
    }

    /**
     * Gets parity setting as a <code>String</code>.
     *
     * @return Current parity setting.
     */
    public String getParityString() {
        switch (parity) {
            case SERIAL_PORT_PARITY_NONE:
                return NONE;
            case SERIAL_PORT_PARITY_EVEN:
                return PARITY_EVEN;
            case SERIAL_PORT_PARITY_ODD:
                return PARITY_ODD;
            default:
                return NONE;
        }
    }

    /**
     * Converts a <code>String</code> describing a flow control type to an
     * <code>int</code> type defined in <code>SerialPort</code>.
     *
     * @param flowControl A <code>string</code> describing a flow control type.
     * @return An <code>int</code> describing a flow control type.
     */
    private int stringToFlow(String flowControl) {
        if (NONE.equals(flowControl)) {
            return SERIAL_PORT_FLOWCONTROL_NONE;
        } else if (XONXOFF_OUT.equals(flowControl)) {
            return SERIAL_PORT_FLOWCONTROL_XONXOFF_OUT;
        } else if (XONXOFF_IN.equals(flowControl)) {
            return SERIAL_PORT_FLOWCONTROL_XONXOFF_IN;
        } else if (RTSCTS_IN.equals(flowControl)) {
            return SERIAL_PORT_FLOWCONTROL_RTSCTS_IN;
        } else if (RTSCTS_OUT.equals(flowControl)) {
            return SERIAL_PORT_FLOWCONTROL_RTSCTS_OUT;
        } else {
            return SERIAL_PORT_FLOWCONTROL_NONE;
        }
    }

    /**
     * Converts an <code>int</code> describing a flow control type to a
     * <code>String</code> describing a flow control type.
     *
     * @param flowControl An <code>int</code> describing a flow control type.
     * @return A <code>String</code> describing a flow control type.
     */
    String flowToString(int flowControl) {
        switch (flowControl) {
            case SERIAL_PORT_FLOWCONTROL_NONE:
                return NONE;
            case SERIAL_PORT_FLOWCONTROL_XONXOFF_OUT:
                return XONXOFF_OUT;
            case SERIAL_PORT_FLOWCONTROL_XONXOFF_IN:
                return XONXOFF_IN;
            case SERIAL_PORT_FLOWCONTROL_RTSCTS_IN:
                return RTSCTS_IN;
            case SERIAL_PORT_FLOWCONTROL_RTSCTS_OUT:
                return RTSCTS_OUT;
            default:
                return NONE;
        }
    }
}
