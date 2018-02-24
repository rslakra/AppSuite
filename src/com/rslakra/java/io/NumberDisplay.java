/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
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
package com.rslakra.java.io;

/**
 * The <code>NumberDisplay</code> class represents a single, two-digit display
 * of a number. It's objects can store a number value up to a given limit that
 * is specified while creating the object of it. The values range from zero
 * (inclusive) to limit-1. It can be incremented, and when the value reaches
 * that limit, it rolls around back to zero.
 * 
 * @author Rohtash Singh
 */
public class NumberDisplay {
	private enum Meridian {
		AM, PM;
	}

	/**
	 * A number value up to a given limit.
	 */
	private int rollOverLimit;

	/**
	 * A value for this display
	 */
	private int value;

	/**
	 * Checks whether the clock supports 12-hour format or not.
	 */
	private boolean support12HourFormat;

	/* represents hours */
	private boolean representHours;

	/**
	 * Contains the current meridian value.
	 */
	private Meridian meridian = Meridian.AM;

	/**
	 * The default constructor which passes the roll over limit for this object.
	 * 
	 * @param rollOverLimit
	 */
	public NumberDisplay(int rollOverLimit) {
		this(rollOverLimit, false);
	}

	/**
	 * The default constructor which passes the roll over limit for this object.
	 * 
	 * @param rollOverLimit
	 */
	public NumberDisplay(int rollOverLimit, boolean support12HourFormat) {
		representHours = ((rollOverLimit == 24) || (support12HourFormat && (rollOverLimit == 12)));
		this.rollOverLimit = rollOverLimit;
		this.support12HourFormat = support12HourFormat;
	}

	/**
	 * Returns the rollOverLimit.
	 * 
	 * @return rollOverLimit
	 */
	public int getRollOverLimit() {
		return rollOverLimit;
	}

	/**
	 * The rollOverLimit to be set.
	 * 
	 * @param rollOverLimit
	 */
	public void setRollOverLimit(int rollOverLimit) {
		this.rollOverLimit = rollOverLimit;
	}

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public int getValue() {
		return isSupport12HourFormat() ? (value - 12) : value;
	}

	/**
	 * Sets the value to the new specified value only if the new value is
	 * greater than zero or not over the limit.
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		if ((value >= 0) && (value < getRollOverLimit())) {
			this.value = value;
		}
	}

	/**
	 * Returns true if it supports the 12-hour format otherwise false.
	 * 
	 * @return
	 */
	public boolean isSupport12HourFormat() {
		return support12HourFormat;
	}

	/**
	 * Returns the current meridian value.
	 * 
	 * @return
	 */
	public String getMeridian() {
		return (isSupport12HourFormat() && getValue() >= 12) ? Meridian.AM
				.name() : Meridian.PM.name();
	}

	/**
	 * It is same as value as a string, padded with a leading zero to ensure
	 * that the result is always a two-digit number (for example "02" instead of
	 * "2").
	 * 
	 * @return
	 */
	public String getDisplayValue() {
		String displayValue = Integer.toString(getValue());
		if (getValue() < 10) {
			displayValue = "0" + getValue();
		}
		return displayValue;
	}

	/**
	 * It increments the value by one. if the value reaches the limit, it reset
	 * the value to zero.
	 * 
	 * * Increment the display value by one, rolling over to zero if the limit
	 * is reached.
	 */
	public void increment() {
		value = (getValue() + 1) % getRollOverLimit();
		// ++value;
		// if (getValue() == getRollOverLimit()) {
		// value = 0;
		// }
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "NumberDisplay<value=" + getValue() + ">";
	}
}