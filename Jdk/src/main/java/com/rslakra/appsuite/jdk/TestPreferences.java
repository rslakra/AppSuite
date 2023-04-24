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

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author rohtash.singh Created on Jun 10, 2005
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestPreferences {
    Preferences root = Preferences.systemRoot();

    private void check() {
        String name = "Lakra";
        System.out.println("sysRoot.name() : " + root.name());
        if (root.isUserNode()) {
            System.out.println("User Preferences Node");
        } else {
            System.out.println("System Preferences Node");
        }
        try {
            String[] children = root.childrenNames();
            for (int i = 0; i < children.length; i++) {
                System.out.println("Children[" + (i + 1) + "] : " + children[i]);
            }
            System.out.println(" ... " + root.parent());
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
        // Create New Preferences.
        // Preferences childNode = sysRoot.node("Lakra");
        // childNode.put("1601","Rohtash Singh");
    }

    public void deleteNode(String key) {
        try {
            String[] keys = root.keys();
            System.out.println("keys.length : " + keys.length);
            for (int i = 0; i < keys.length; i++) {
                System.out.println("Keys[" + (i + 1) + "] : " + keys[i]);
                // if(keys[i].equalsIgnoreCase(key)) {
                // System.out.println("Deleting " + keys[i]);
                // root.remove(keys[i]);
                // }
            }
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestPreferences p = new TestPreferences();
        p.check();
    }
}
