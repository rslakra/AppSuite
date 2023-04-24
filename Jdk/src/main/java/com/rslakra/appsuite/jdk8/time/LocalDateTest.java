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
package com.rslakra.appsuite.jdk8.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * @author Rohtash Lakra
 * @date 09/20/2017 03:24:10 PM
 */
public class LocalDateTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Enter 2 Dates in (dd mm yyyy) format.");
        Scanner in = new Scanner(System.in);
        int dayAcutal = in.nextInt();
        int monthActual = in.nextInt();
        int yearActual = in.nextInt();
        int dayExpected = in.nextInt();
        int monthExpected = in.nextInt();
        int yearExpected = in.nextInt();
        in.close();

        try {
            LocalDate dateReturned = LocalDate.of(yearActual, monthActual, dayAcutal);
            LocalDate dateExpected = LocalDate.of(yearExpected, monthExpected, dayExpected);
            Period period = Period.between(dateExpected, dateReturned);
            System.out.println("period:" + period);
            System.out.println(
                    "Years:" + period.getYears() + ", Months:" + period.getMonths() + ", Days:" + period.getDays());
            int allDays = (int) ChronoUnit.DAYS.between(dateExpected, dateReturned);
            System.out.println("Days Total:" + allDays);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
