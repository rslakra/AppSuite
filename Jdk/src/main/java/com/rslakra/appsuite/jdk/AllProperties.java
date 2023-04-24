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

import java.util.Enumeration;
import java.util.Properties;

public class AllProperties {

    private Properties allProperties;

    public AllProperties(Properties prop) {
        this.allProperties = prop;
        allProperties.put("AllProperties", "AllProperties");
    }

    public void addValue(String key, String value) {
        allProperties.put(key, value);
    }

    public void printValues() {
        Enumeration keys = allProperties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) allProperties.get(key);
            System.out.println(key + ": " + value);
        }
    }

    private Properties mProperties;

    public AllProperties() {
        mProperties = new Properties();
        mProperties.put("Properties", "MyProperties");
    }

    public String getValue(String key) {
        return (String) mProperties.get(key);
    }

    public void updateProperties() {
        AllProperties flatProperties = new AllProperties(mProperties);
        flatProperties.addValue("FirstName", "Rohtash");
        flatProperties.addValue("Properties", "Testing -5 Error");
        System.out.println("========== All Properties ===========");
        flatProperties.printValues();
        System.out.println("======================================");
    }

    public void printMyProperties() {
        Enumeration keys = mProperties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) mProperties.get(key);
            System.out.println(key + ": " + value);
        }
    }

    public static void main(String[] args) {
        AllProperties mProperties = new AllProperties();
        System.out.println("========== Before Change, My Properties ===========");
        mProperties.printMyProperties();
        System.out.println("======================================");
        mProperties.updateProperties();
        System.out.println("========== After Change, My Properties ===========");
        mProperties.printMyProperties();
        System.out.println("======================================");
    }
}
