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
package com.rslakra.jdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2017-09-23 10:30:16 AM
 * @since 1.0.0
 */
public class ListExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListExample.class);

    /**
     * @param args
     */
    public static void main(String args[]) {
        List listObjects = new ArrayList();
        listObjects.add("Bernadine");
        listObjects.add("Elizabeth");
        listObjects.add("Gene");
        listObjects.add("Elizabeth");
        listObjects.add("Clara");
        LOGGER.info("listObjects:{}", listObjects);
        LOGGER.info("listObjects[0]:{}", listObjects.get(0));
        LOGGER.info("listObjects[2]:{}", listObjects.get(2));

        LinkedList linkedList = new LinkedList();
        linkedList.addFirst("Bernadine");
        linkedList.addFirst("Elizabeth");
        linkedList.addFirst("Gene");
        linkedList.addFirst("Elizabeth");
        linkedList.addFirst("Clara");
        LOGGER.info("linkedList:{}", linkedList);
        linkedList.removeLast();
        linkedList.removeLast();
        LOGGER.info("After removeLast 2 times linkedList:{}", linkedList);

        List listCount = new ArrayList();
        listCount.add("One");
        listCount.add("Two");
        listCount.add("Three");
        LOGGER.info("listCount:{}", listCount);
        ListIterator listIterator = listCount.listIterator();
        listIterator.next();
        listIterator.remove();
        LOGGER.info("After next and remove listIterator:{}", listIterator);

        ArrayList list = new ArrayList<String>();
        list.add("Java");
        LOGGER.info("list:{}", list);
    }
}
