/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
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
package com.rslakra.jdk8.lambda;

import com.devamatre.core.CoreUtility;
import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {

    private final static Logger LOGGER = LogManager.getLogger(JavaFileFilter.class);

    /**
     * @param files
     */
    public static void printFiles(File[] files) {
        LOGGER.debug("printFiles(" + files + ")");
        for (File file : files) {
            LOGGER.debug("file:" + file);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
//        String pathString = CoreUtility.pathString(JavaFileFilter.class);
        String pathString = CoreUtility.pathString(CoreUtility.getUserDir(), "java/src/main/java/com/rslakra/jdk8/lambda");
        LOGGER.debug("pathString:" + pathString);
        JavaFileFilter fileFilter = new JavaFileFilter();
        File file = new File(pathString);
        File[] files = file.listFiles(fileFilter);
        LOGGER.debug("files:" + files);
        printFiles(files);
        LOGGER.debug("");

        // with lambda expression
        File tempFile = new File(pathString);
        FileFilter lambdaFilter = (File temp) -> temp.getName().endsWith(".java");
        File[] tempFiles = tempFile.listFiles(lambdaFilter);
        LOGGER.debug("tempFiles:" + tempFiles);
        printFiles(tempFiles);
    }

    /**
     * @param file
     * @return
     */
    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(".java");
    }

}
