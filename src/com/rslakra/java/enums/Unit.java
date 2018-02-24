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
package com.rslakra.java.enums;

/**
 * http://en.wikipedia.org/wiki/Bit
 * 
 * A bit is the basic unit of information in computing and digital
 * communications.[1] A bit can have only one of two values, and may therefore
 * be physically implemented with a two-state device. These values are most
 * commonly represented as either a 0or1. The term bit is a portmanteau of
 * binary digit.
 * 
 * The bit is not defined in the International System of Units (SI). However,
 * the International Electrotechnical Commission issued standard IEC 60027,
 * which specifies that the symbol for binary digit should be bit, and this
 * should be used in all multiples, such as kbit, for kilobit.[7] However, the
 * lower-case letter b is widely used as well and was recommended by the IEEE
 * 1541 Standard (2002). In contrast, the upper case letter B is the standard
 * and customary symbol for byte.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Unit {
	/* bit = a bit can have only one of two values (as either a 0 or 1). */
	BIT(1),
	/* nibble = 4 bits (a four-bit aggregation, or half an octet) */
	NIBBLE(BIT.getValue() * 4),
	/* byte = 8 bits */
	BYTE(BIT.getValue() * 8),
	/* 1 KB (KilloByte) = 1024 bytes */
	KB(1024),
	/* 1 MB (MegaByte) = 1024 * 1024 bytes */
	MB(KB.getValue() * KB.getValue()),
	/* 1 GB (GigaByte) = 1024 * 1024 * 1024 bytes */
	GB(MB.getValue() * KB.getValue()),
	/* 1 TB (TeraByte) = 1024 * 1024 * 1024 * 1024 bytes */
	TB(GB.getValue() * KB.getValue()),
	/* 1024 bytes */
	MIN_BUFFER_SIZE(KB.getValue()),
	/* 1024 * 4 bytes */
	NORMAT_BUFFER_SIZE(KB.getValue() * NIBBLE.getValue()),
	/* 1024 * * bytes */
	MAX_BUFFER_SIZE(KB.getValue() * BYTE.getValue()), ;

	/**
	 * 
	 * @param value
	 */
	private Unit(long value) {
		this.value = value;
	}

	/**
	 * Returns the value of this ENUM.
	 * 
	 * @return
	 */
	public long getValue() {
		return value;
	}

	/** value */
	private long value;
}
