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
package com.rslakra.appsuite.jdk.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author rohtash.singh
 * <p>
 *  TODO To change the template for this generated type comment go to
 *  Window - Preferences - Java - Code Style - Code Templates
 */
public class TestDate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String dateFormat = "MM/dd/yyyy HH:MM aaa";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        System.out.println("Today is " + sdf.format(new Date()));

        GregorianCalendar gc = new GregorianCalendar();
        Date d = new Date(2021 - 1900, 11, 31);
        System.out.println("Day of the week: " + d);
        gc.set(2021, 12, 31, 0, 0, 0);
        System.err.println(gc.get(Calendar.YEAR) + "/" + gc.get(Calendar.MONTH) + "/" + gc.get(Calendar.DAY_OF_MONTH));

        System.out.println("Day of the week: " + d);
        gc.set(2021, 12, 31, 0, 0, 0);
        System.err.println(gc.get(Calendar.YEAR) + "/" + gc.get(Calendar.MONTH) + "/" + gc.get(Calendar.DAY_OF_MONTH));
    }
}
